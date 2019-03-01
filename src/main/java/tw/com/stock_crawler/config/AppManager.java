package tw.com.stock_crawler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import tw.com.stock_crawler.db.entity.StockCompanyEntity;
import tw.com.stock_crawler.db.repository.StockDailyTradingRepository;
import tw.com.stock_crawler.db.repository.StockMonthlyTradingRepository;
import tw.com.stock_crawler.db.repository.StockWeeklyTradingRepository;
import tw.com.stock_crawler.dto.history.HistoryDayStockTradingDTO;
import tw.com.stock_crawler.dto.history.OtcHistoryDailyStockTradingDTO;
import tw.com.stock_crawler.dto.history.TseHistoryDailyStockTradingDTO;
import tw.com.stock_crawler.service.StockDayHistoryService;
import tw.com.stock_crawler.service.db.StockCompanyService;
import tw.com.stock_crawler.stock.data.MarketType;
import tw.com.stock_crawler.util.DTOUtils;
import tw.com.stock_crawler.util.DateUtils;
import tw.com.stock_crawler.util.FileUtils;
import tw.com.stock_crawler.util.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.chrono.MinguoDate;
import java.util.Comparator;

/**
 * Created on 2018/3/15.
 */
@Component
public class AppManager {

	// ----- ----- Public constants
	public static final BigDecimal MIN_PRICE = new BigDecimal(0.00);
	public static final DateTime FIRST_WEEK = DateUtils.convertMinguoDateToJodaDateTime(
			MinguoDate.of(81, 1, 4)
	).withDayOfWeek(1);
	// Logger
	private final Logger logger = LoggerFactory.getLogger(AppManager.class);
	// Repositories
	@Autowired
	private StockDailyTradingRepository stockDailyTradingRepository;
	@Autowired
	private StockWeeklyTradingRepository stockWeeklyTradingRepository;
	@Autowired
	private StockMonthlyTradingRepository stockMonthlyTradingRepository;
	@Autowired
	private StockDayHistoryService stockDayHistoryService;
	// Services
	private StockCompanyService stockCompanyService;

	// ***** ***** ***** ***** ***** Constructors

	// ***** ***** ***** ***** ***** Getter and setter methods

	public static void main(String[] args) throws Exception {
		// log4j
		System.setProperty("catalina.home", FileUtils.createTempDir("log4j", "tmp").getPath());
		ConfigurationSource source = new ConfigurationSource(Resources.getResource("conf/log4j2.xml").openStream());
		Configurator.initialize(null, source);

		// spring
		System.setProperty("spring.profiles.active", "dev");
		ApplicationContext appContext = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/app-context-without-servlet.xml");

		//
		String webContextDir = "src/main/webapp";
		//
		AppManager appManager = appContext.getBean(AppManager.class);
		appManager.init();

		try {
			Thread.sleep(1000000000000000L);
		} finally {
			appManager.destroy();
		}
	}

	// ***** ***** ***** ***** ***** Public methods

	public void init() {
		this.logger.info("App initializing...");

		OkHttpClient client = new OkHttpClient();

		DateTimeZone timeZone = DateTimeZone.forID("Asia/Taipei");
		while (!Utils.pingHost("www.twse.com.tw", 80, 3000)) ;
		this.stockCompanyService.findAll()
				.stream()
				.sorted(Comparator.comparing(StockCompanyEntity::getSecurityCode))
				.forEach(stkCompanyEntity -> {
					MarketType marketType = stkCompanyEntity.getMarketType();
					DateTime listingDate = stkCompanyEntity.getListingDate();
					DateTime queryTime = listingDate.withDayOfMonth(1);
					ObjectMapper objectMapper = new ObjectMapper();

					while (!queryTime.isAfterNow()) {
						int recordsInMonth = this.stockDayHistoryService.countRecordsByDateBetween(
								stkCompanyEntity.getSecurityCode(), queryTime, queryTime.plusMonths(1)
						);
						if (!this.stockDayHistoryService.recordExists(stkCompanyEntity.getSecurityCode(), queryTime) &&
								recordsInMonth == 0) {
							Request request = new Request.Builder()
									.url(this.genUrl(stkCompanyEntity.getSecurityCode(), marketType, queryTime))
									.build();
							try (Response response = client.newCall(request).execute()) {
								if (response.isSuccessful() && response.body() != null) {
									String responseStr = response.body().string();
									HistoryDayStockTradingDTO dto = this.convertStringToDTO(objectMapper, marketType, responseStr);

									DTOUtils.convertHistoryDtoToEntities(stkCompanyEntity.getSecurityCode(), dto)
											.stream()
											.filter(entity -> !this.stockDayHistoryService.recordExists(
													entity.getSecurityCode(), entity.getDate()
											))
											.forEach(entity -> this.stockDayHistoryService.save(entity));
									this.logger.debug("{}({}) - {} processed.",
											stkCompanyEntity.getSecurityCode(), stkCompanyEntity.getStkNameCht(), queryTime
									);

									Thread.sleep(2500L);
								}
							} catch (IOException | InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							this.logger.debug("{}({}) - {} existed.",
									stkCompanyEntity.getSecurityCode(), stkCompanyEntity.getStkNameCht(), queryTime
							);
						}
						queryTime = queryTime.plusMonths(1);
					}
//					if (marketType == MarketType.TSE) {
//						while (!queryTime.isAfterNow()) {
//							int recordsInMonth = this.stockDayHistoryService.countRecordsByDateBetween(
//									stkCompanyEntity.getSecurityCode(), queryTime, queryTime.plusMonths(1)
//							);
//							if (!this.stockDayHistoryService.recordExists(stkCompanyEntity.getSecurityCode(), queryTime) &&
//									recordsInMonth == 0) {
//								Request request = new Request.Builder()
//										.url(this.genUrl(stkCompanyEntity.getSecurityCode(), marketType, queryTime))
//										.build();
//								try (Response response = client.newCall(request).execute()) {
//									if (response.isSuccessful() && response.body() != null) {
//										String responseStr = response.body().string();
//										HistoryDayStockTradingDTO dto = this.convertStringToDTO(objectMapper, marketType, responseStr);
////										TseHistoryDailyStockTradingDTO dto = objectMapper.readValue(responseStr, TseHistoryDailyStockTradingDTO.class);
//
//										DTOUtils.convertHistoryDtoToEntities(stkCompanyEntity.getSecurityCode(), dto)
//												.stream()
//												.filter(entity -> !this.stockDayHistoryService.recordExists(
//														entity.getSecurityCode(), entity.getDate()
//												))
//												.forEach(entity -> this.stockDayHistoryService.save(entity));
//										this.logger.debug("{}({}) - {} processed.",
//												stkCompanyEntity.getSecurityCode(), stkCompanyEntity.getStkNameCht(), queryTime
//										);
//
//										Thread.sleep(2500L);
//									}
//								} catch (IOException | InterruptedException e) {
//									e.printStackTrace();
//								}
//							} else {
//								this.logger.debug("{}({}) - {} existed.",
//										stkCompanyEntity.getSecurityCode(), stkCompanyEntity.getStkNameCht(), queryTime
//								);
//							}
//							queryTime = queryTime.plusMonths(1);
//						}
//					} else if (marketType == MarketType.OTC) {
//
//					} else {
//						System.err.println("Something went wrong.");
//					}
				});

		this.logger.info("App initialized.");
	}

	public void destroy() {
		this.logger.info("App destroying...");

		this.logger.info("App destroyed");
	}

	// ***** ***** ***** ***** ***** Utility methods

	private HttpUrl genUrl(String securityCode, MarketType type, DateTime queryTime) {
		return type.equals(MarketType.TSE) ? this.genTseQueryUrl(securityCode, queryTime) : this.genTpexQueryUrl(securityCode, queryTime);
	}

	private HttpUrl genTpexQueryUrl(String securityCode, DateTime queryTime) {
		String queryMinguoTime = String.format("%d/%d", queryTime.getYear() - 1911, queryTime.getMonthOfYear());
		return new HttpUrl.Builder()
				.scheme("http")
				.host("www.tpex.org.tw")
				.addPathSegments("web/stock/aftertrading/daily_trading_info/st43_result.php")
				.addQueryParameter("l", "zh-tw")
				.addQueryParameter("d", queryMinguoTime)
				.addQueryParameter("stkno", securityCode)
				.build();
	}

	private HttpUrl genTseQueryUrl(String securityCode, DateTime queryTime) {
		return new HttpUrl.Builder()
				.scheme("http")
				.host("www.twse.com.tw")
				.addPathSegments("exchangeReport/STOCK_DAY")
				.addQueryParameter("response", "json")
				.addQueryParameter("date", queryTime.toString("yyyyMMdd"))
				.addQueryParameter("stockNo", securityCode)
				.build();
	}

	private HistoryDayStockTradingDTO convertStringToDTO(ObjectMapper objectMapper, MarketType type, String str) throws IOException {
		HistoryDayStockTradingDTO result = null;
		switch (type) {
			case TSE:
				result = objectMapper.readValue(str, TseHistoryDailyStockTradingDTO.class);
				break;
			case OTC:
				result = objectMapper.readValue(str, OtcHistoryDailyStockTradingDTO.class);
				break;
			default:
				throw new RuntimeException("Unrecognized type.");
		}
		return result;
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Autowired
	public void setStockCompanyService(StockCompanyService stockCompanyService) {
		this.stockCompanyService = stockCompanyService;
	}
}
