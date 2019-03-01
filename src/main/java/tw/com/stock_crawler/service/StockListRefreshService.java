package tw.com.stock_crawler.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.stock_crawler.db.entity.StockCompanyEntity;
import tw.com.stock_crawler.db.repository.StockCompanyRepository;
import tw.com.stock_crawler.stock.data.IndustrialGroup;
import tw.com.stock_crawler.stock.data.MarketType;
import tw.com.stock_crawler.stock.data.StockCompany;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StockListRefreshService {

	// ----- ----- Public constants
	public static final int DEFAULT_MAX_BODY_SIZE = 10485760;
	public static final String DEFAULT_TWSE_STK_LIST_ENG_URL = "http://isin.twse.com.tw/isin/e_C_public.jsp?strMode=2";
	public static final String DEFAULT_TWSE_STK_LIST_CHT_URL = "http://isin.twse.com.tw/isin/C_public.jsp?strMode=2";
	public static final String DEFAULT_TPEX_STK_LIST_ENG_URL = "http://isin.twse.com.tw/isin/e_C_public.jsp?strMode=4";
	public static final String DEFAULT_TPEX_STK_LIST_CHT_URL = "http://isin.twse.com.tw/isin/C_public.jsp?strMode=4";

	// Logger
	private final Logger logger = LoggerFactory.getLogger(StockListRefreshService.class);
	// Lock
	private final Object lock = new Object();

	private StockCompanyRepository repository;
	private String twseStkListEngUrl;
	private String twseStkListChtUrl;
	private String tpexStkListEngUrl;
	private String tpexStkListChtUrl;
	private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy/MM/dd");

	// ***** ***** ***** ***** ***** Public methods

	@PostConstruct
	public void init() {
		this.setTwseStkListEngUrl(DEFAULT_TWSE_STK_LIST_ENG_URL);
		this.setTwseStkListChtUrl(DEFAULT_TWSE_STK_LIST_CHT_URL);
		this.setTpexStkListEngUrl(DEFAULT_TPEX_STK_LIST_ENG_URL);
		this.setTpexStkListChtUrl(DEFAULT_TPEX_STK_LIST_CHT_URL);
	}

	@PreDestroy
	public void destroy() {
		// TODO: 2018/2/28 Save stock list urls
	}

	public void refresh() {
		try {
			this.logger.info(String.format("%s - Refresh stock company information.", DateTime.now()));
			Set<StockCompany> stkCompanies = new TreeSet<>();
			stkCompanies.addAll(this.retrieveCompanyList(this.twseStkListChtUrl, this.twseStkListEngUrl));
			stkCompanies.addAll(this.retrieveCompanyList(this.tpexStkListChtUrl, this.tpexStkListEngUrl));

			this.repository.save(
					stkCompanies.stream()
							.map(stkCompany -> new StockCompanyEntity.Builder()
									.setSecurityCode(stkCompany.getSecurityCode())
									.setStkNameCht(stkCompany.getStockChtName())
									.setStkNameEng(stkCompany.getStockEngName())
									.setIsinCode(stkCompany.getIsinCode())
									.setListingDate(this.dateTimeFormatter.parseDateTime(stkCompany.getListingDate()))
									.setMarketType(stkCompany.getMarketType())
									.setIndustrialGroup(stkCompany.getIndustrialGroup())
									.setCfiCode(stkCompany.getCfiCode())
									.build())
							.collect(Collectors.toSet())
			);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when refreshing stock list.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	// ***** ***** ***** ***** ***** Utility methods

	private Set<StockCompany> retrieveCompanyList(final String stkListChtUrl, final String stkListEngUrl) {
		// Note: 僅取得上市、上櫃公司，權證等的資訊忽略
		Pattern stockNamePattern = Pattern.compile("[0-9]{4}[　]++.*");
		try {
			Document stkListDoc = Jsoup.connect(stkListChtUrl)
					.maxBodySize(DEFAULT_MAX_BODY_SIZE)
					.get();
			Elements trEls = stkListDoc.select("tbody tr");
			StockCompany.Builder stockCompanyBuilder = new StockCompany.Builder();
			Set<StockCompany> stockCompanies = new TreeSet<>();
			for (int idx = 0; idx < trEls.size(); ++idx) {
				Element trEl = trEls.get(idx);
				Elements tdEls = trEl.select("td");
				tdEls = tdEls.removeAttr("bgColor");
				if (tdEls.size() < 3) {
					continue;
				}
				String securityCodeAndName = tdEls.get(0).text().trim();
				if (!stockNamePattern.matcher(securityCodeAndName).matches()) {
					continue;
				}
				String[] arr = securityCodeAndName.split("　");
				String securityCode = arr[0].trim();
				String securityName = arr[1];
				String isinCode = tdEls.get(1).text();
				String listingDate = tdEls.get(2).text();
				String marketType = tdEls.get(3).text();
				String industrialGroup = tdEls.get(4).text();
				String cfiCode = tdEls.get(5).text();

				StockCompany stockCompany = stockCompanyBuilder.setSecurityCode(securityCode)
						.setStockChtName(securityName)
						.setIsinCode(isinCode)
						.setListingDate(listingDate)
						.setMarketType(MarketType.getMarketType(marketType))
						.setIndustrialGroup(IndustrialGroup.getIndustrialGroup(industrialGroup))
						.setCfiCode(cfiCode)
						.build();
				stockCompanies.add(stockCompany);
			}

			return stockCompanies;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Exception occurred when extracting stock list information.", e);
		}
	}

	private void checkTwseUrl(String url) {
		if (url == null) {
			throw new RuntimeException("The specified url is null.");
		}
	}

	private void checkTpexUrl(String url) {
		if (url == null) {
			throw new RuntimeException("The specified url is null.");
		}
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Autowired
	public void setRepository(StockCompanyRepository repository) {
		this.repository = repository;
	}

	public String getTwseStkListEngUrl() {
		return twseStkListEngUrl;
	}

	public void setTwseStkListEngUrl(String twseStkListEngUrl) {
		this.checkTwseUrl(twseStkListEngUrl);
		// ----- -----
		this.twseStkListEngUrl = twseStkListEngUrl;
	}

	public String getTwseStkListChtUrl() {
		return twseStkListChtUrl;
	}

	public void setTwseStkListChtUrl(String twseStkListChtUrl) {
		this.checkTwseUrl(twseStkListChtUrl);
		// ----- -----
		this.twseStkListChtUrl = twseStkListChtUrl;
	}

	public String getTpexStkListEngUrl() {
		return tpexStkListEngUrl;
	}

	public void setTpexStkListEngUrl(String tpexStkListEngUrl) {
		this.checkTpexUrl(tpexStkListEngUrl);
		// ----- -----
		this.tpexStkListEngUrl = tpexStkListEngUrl;
	}

	public String getTpexStkListChtUrl() {
		return tpexStkListChtUrl;
	}

	public void setTpexStkListChtUrl(String tpexStkListChtUrl) {
		this.checkTpexUrl(tpexStkListChtUrl);
		// ----- -----
		this.tpexStkListChtUrl = tpexStkListChtUrl;
	}

	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
