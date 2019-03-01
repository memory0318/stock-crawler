package tw.com.stock_crawler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.stock_crawler.db.entity.StockCompanyEntity;
import tw.com.stock_crawler.db.entity.StockDailyTradingEntity;
import tw.com.stock_crawler.db.repository.StockDailyTradingRepository;
import tw.com.stock_crawler.dto.DailyStockTradingDTO;
import tw.com.stock_crawler.dto.history.TseHistoryDailyStockTradingDTO;
import tw.com.stock_crawler.service.db.BaseCrudService;
import tw.com.stock_crawler.service.db.StockCompanyService;
import tw.com.stock_crawler.stock.data.MarketType;
import tw.com.stock_crawler.util.DTOUtils;
import tw.com.stock_crawler.util.DateUtils;
import tw.com.stock_crawler.util.Utils;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TWSE網址範例: http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=20180709&stockNo=2426&_=1531149260833
 */
@Service
public class StockDayHistoryService extends BaseCrudService<StockDailyTradingEntity, Long, StockDailyTradingRepository> {

	// ----- ----- Public constants
	public static final String DEFAULT_TWSE_URI_SCHEME = "http";
	public static final String DEFAULT_TPEX_URI_SCHEME = "http";
	public static final String DEFAULT_TWSE_HOST = "www.twse.com.tw/exchangeReport/STOCK_DAY";
	public static final String DEFAULT_TPEX_HOST =
			"www.tpex.org.tw/web/stock/aftertrading/daily_trading_info/st43_result.php?l=zh-tw";
	public static final String DEFAULT_TWSE_RESPONSE_TYPE = "json";
	public static final String DEFAULT_TPEX_RESPONSE_TYPE = "json";

	public static final String TWSE_QUERY_PARAM_RESPONSE = "response";
	public static final String TWSE_QUERY_PARAM_DATE = "date";
	public static final String TPEX_QUERY_PARAM_DATE = "d";
	public static final String TWSE_QUERY_PARAM_STOCK_NO = "stockNo";
	public static final String TPEX_QUERY_PARAM_STOCK_NO = "stockno";
	public static final String TWSE_QUERY_PARAM_TIMESTAMP = "_";
	public static final String TPEX_QUERY_PARAM_TIMESTAMP = "_";

	public static final BigDecimal MIN_PRICE = new BigDecimal(0.01);

	public static final DateTime FIRST_QUERY_DATETIME = DateUtils.convertMinguoDateToJodaDateTime("81/01/04");

	public static final Long MIN_FETCH_DELAY = 3000L;

	protected String twseUriScheme = DEFAULT_TWSE_URI_SCHEME;
	protected String twseHost = DEFAULT_TWSE_HOST;
	protected String twseResponseType = DEFAULT_TWSE_RESPONSE_TYPE;
	protected String tpexUriScheme = DEFAULT_TPEX_URI_SCHEME;
	protected String tpexHost = DEFAULT_TPEX_HOST;
	protected String tpexResponseType = DEFAULT_TPEX_RESPONSE_TYPE;

	private Long fetchDelay = MIN_FETCH_DELAY;
	// Repositories
	private StockDailyTradingRepository stockDailyTradingRepository;
	// Object mapper
	private ObjectMapper objectMapper = new ObjectMapper();
	// Service
	private StockCompanyService stockCompanyService;
	// DateTime formatter
	private DateTimeFormatter twseDateTimeFormatter;
	private DateTimeFormatter tpexDateTimeFormatter;
	// Lock
	private final Object lock = new Object();
	// Logger
	private final Logger logger = LoggerFactory.getLogger(StockDayHistoryService.class);

	// ***** ***** ***** ***** ***** Constructors

	public StockDayHistoryService() {
		this.twseDateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");
		this.tpexDateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");
	}

	// ***** ***** ***** ***** ***** Public methods

	public void fetchHistoryFromWebBySecurityId(String securityCode) {
		try {
			if (!this.stockCompanyService.existsStockCompany(securityCode)) {
				// FIXME: 2018/9/10 throw exception?
				return;
			}
			final StockCompanyEntity stkCompanyEntity = this.stockCompanyService.findBySecurityCode(securityCode);
			DateTime queryDateTime = this.getStartQueryDateTime(stkCompanyEntity);
			while (queryDateTime.isBeforeNow()) {
				synchronized (stkCompanyEntity) {
					// FIXME: 2018/9/11 先ping網頁，否則有時候會被拒絕連線
					String url = this.genHistoryUrl(securityCode, queryDateTime, stkCompanyEntity.getMarketType());
					if (url != null) {
						while (!Utils.pingHost("www.twse.com.tw", 80, 3000)) {
//							Thread.currentThread().wait(3000);
						}
//						String responseBody = this.fetcher.getResponse(url);
						String responseBody = null;
						TseHistoryDailyStockTradingDTO dto = this.objectMapper.readValue(
								responseBody, TseHistoryDailyStockTradingDTO.class
						);
						if (this.hasValidData(dto)) {
							List<StockDailyTradingEntity> entities = DTOUtils.convertHistoryDtoToEntities(securityCode, dto)
									.stream()
									.filter(entity ->
											this.isValidPrice(entity.getOpeningPrice()) &&
													this.isValidPrice(entity.getClosingPrice())
									).collect(Collectors.toList());
							this.stockDailyTradingRepository.save(entities);
						}

						stkCompanyEntity.wait(3000L);
					}
				}
				queryDateTime = queryDateTime.plusMonths(1);
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred when fetching history from web.", e);
		}
	}

	public List<DailyStockTradingDTO> getDailyStockBySecurityCode(
			String securityCode, DateTime startTime, DateTime endTime
	) {
		List<StockDailyTradingEntity> entities = this.stockDailyTradingRepository
				.findBySecurityCodeAndDateBetween(securityCode, startTime, endTime);
		return entities.stream()
				.map(DTOUtils::convertEntityToDTO)
				.collect(Collectors.toList());
	}

	public boolean recordExists(String securityCode, DateTime queryDate) {
		return this.stockDailyTradingRepository.countBySecurityCodeAndDate(securityCode, queryDate) != 0;
	}

	public int countRecordsByDateBetween(String securityCode, DateTime startQueryDate, DateTime endQuryDate) {
		return this.stockDailyTradingRepository.countBySecurityCodeAndDateBetween(securityCode, startQueryDate, endQuryDate);
	}

	// ***** ***** ***** ***** ***** Utility methods

	private boolean hasValidData(TseHistoryDailyStockTradingDTO dto) {
		return dto.getData() != null;
	}

	private String genHistoryUrl(String securityCode, DateTime queryDate, MarketType marketType) {
		String url = null;
		switch (marketType) {
			case TSE:
				url = this.genTwseHistoryUrl(queryDate, securityCode, this.twseResponseType);
				break;
			case OTC:
				url = this.genTpexHistoryUrl(queryDate, securityCode);
				break;
		}
		return url;
	}

	private String genTwseHistoryUrl(DateTime date, String securityCode, String responseType) {
		try {
			return null;
//			return new URIBuilder()
//					.setScheme(this.twseUriScheme)
//					.setHost(this.twseHost)
//					.setParameter(TWSE_QUERY_PARAM_RESPONSE, responseType)
//					.setParameter(TWSE_QUERY_PARAM_DATE, date.toString(this.twseDateTimeFormatter))
//					.setParameter(TWSE_QUERY_PARAM_STOCK_NO, securityCode)
//					.setParameter(TWSE_QUERY_PARAM_TIMESTAMP, String.valueOf(System.currentTimeMillis()))
//					.build()
//					.toString();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred when generating TWSE url.", e);
		}
	}

	private String genTpexHistoryUrl(DateTime date, String securityCode) {
		try {
			return null;
//			return new URIBuilder()
//					.setScheme(this.tpexUriScheme)
//					.setPath(this.tpexHost)
//					.setParameter(TPEX_QUERY_PARAM_DATE, date.toString(this.tpexDateTimeFormatter))
//					.setParameter(TPEX_QUERY_PARAM_STOCK_NO, securityCode)
//					.setParameter(TPEX_QUERY_PARAM_TIMESTAMP, String.valueOf(System.currentTimeMillis()))
//					.build()
//					.toString();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred when generating TWSE url.", e);
		}
	}

	private DateTime getStartQueryDateTime(StockCompanyEntity stockCompanyEntity) {
		DateTime startQueryDateTime = null;
		StockDailyTradingEntity stockDailyTradingEntity = this.stockDailyTradingRepository
				.findFirstBySecurityCodeOrderByDateDesc(stockCompanyEntity.getSecurityCode());
		if (stockDailyTradingEntity == null) {
			DateTime listingDate = stockCompanyEntity.getListingDate();
			if (this.isBeforeFirstQueryDateTime(listingDate)) {
				listingDate = FIRST_QUERY_DATETIME;
			}
			startQueryDateTime = listingDate.withDate(
					listingDate.getYear(), listingDate.getMonthOfYear(), 1
			).withZone(DateTimeZone.forID("Asia/Taipei"));
		} else {
			startQueryDateTime = stockDailyTradingEntity.getDate().plusDays(1);
		}
		return startQueryDateTime;
	}

	private boolean isBeforeFirstQueryDateTime(DateTime queryDateTime) {
		return queryDateTime.isBefore(FIRST_QUERY_DATETIME);
	}

	private boolean isValidPrice(BigDecimal price) {
		return price.compareTo(MIN_PRICE) > 0;
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Autowired
	public void setStockCompanyService(StockCompanyService stockCompanyService) {
		this.stockCompanyService = stockCompanyService;
	}

	@Autowired
	public void setStockDailyTradingRepository(StockDailyTradingRepository stockDailyTradingRepository) {
		this.stockDailyTradingRepository = stockDailyTradingRepository;
	}

	@Autowired
	@Override
	public void setRepository(StockDailyTradingRepository repository) {
		this.repository = repository;
	}
}
