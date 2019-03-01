package tw.com.stock_crawler.db.entity;

import org.joda.time.DateTime;
import tw.com.stock_crawler.db.Constants;
import tw.com.stock_crawler.db.converter.JodaTimeConverter;
import tw.com.stock_crawler.stock.data.IndustrialGroup;
import tw.com.stock_crawler.stock.data.MarketType;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
@Table(
		name = StockCompanyEntity.TABLE_NAME,
		schema = Constants.SCHEMA_PUBLIC
)
public class StockCompanyEntity implements Comparable<StockCompanyEntity> {

	// ----- ----- Public constants
	// Table name
	public static final String TABLE_NAME = "stk_company";
	// Class name
	public static final String CLASS_NAME = StockCompanyEntity.class.getName();
	// Column name
	public static final String COL_SECURITY_CODE = "security_code";
	public static final String COL_SKT_NAME_CHT = "skt_cht";
	public static final String COL_SKT_NAME_ENG = "skt_eng";
	public static final String COL_ISIN_CODE = "isin";
	public static final String COL_LISTING_DATE = "listing_date";
	public static final String COL_MARKET_TYPE = "market_type";
	public static final String COL_INDUSTRIAL_GROUP = "industrial_group";
	public static final String COL_CFI_CODE = "cfi_code";
	public static final String COL_COMMENT = "comment";

	@Id
	@Column(name = COL_SECURITY_CODE)
	private String securityCode;

	@Column(name = COL_SKT_NAME_CHT)
	private String stkNameCht;

	@Column(name = COL_SKT_NAME_ENG)
	private String stkNameEng;

	@Column(name = COL_ISIN_CODE)
	private String isinCode;

	@Convert(converter = JodaTimeConverter.class)
	@Column(name = COL_LISTING_DATE)
	private DateTime listingDate;

	@Column(name = COL_MARKET_TYPE)
	private MarketType marketType;

	@Column(name = COL_INDUSTRIAL_GROUP)
	private IndustrialGroup industrialGroup;

	@Column(name = COL_CFI_CODE)
	private String cfiCode;

	@Column(name = COL_COMMENT)
	private String comment;

	// ***** ***** ***** ***** ***** Constructors

	public StockCompanyEntity() { }

	private StockCompanyEntity(Builder builder) {
		setSecurityCode(builder.securityCode);
		setStkNameCht(builder.stkNameCht);
		setStkNameEng(builder.stkNameEng);
		setIsinCode(builder.isinCode);
		setListingDate(builder.listingDate);
		setMarketType(builder.marketType);
		setIndustrialGroup(builder.industrialGroup);
		setCfiCode(builder.cfiCode);
		setComment(builder.comment);
	}

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public int compareTo(@Nonnull StockCompanyEntity o) {
		return this.securityCode.compareTo(o.securityCode);
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getStkNameCht() {
		return stkNameCht;
	}

	public void setStkNameCht(String stkNameCht) {
		this.stkNameCht = stkNameCht;
	}

	public String getStkNameEng() {
		return stkNameEng;
	}

	public void setStkNameEng(String stkNameEng) {
		this.stkNameEng = stkNameEng;
	}

	public String getIsinCode() {
		return isinCode;
	}

	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}

	public DateTime getListingDate() {
		return listingDate;
	}

	public void setListingDate(DateTime listingDate) {
		this.listingDate = listingDate;
	}

	public MarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MarketType marketType) {
		this.marketType = marketType;
	}

	public IndustrialGroup getIndustrialGroup() {
		return industrialGroup;
	}

	public void setIndustrialGroup(IndustrialGroup industrialGroup) {
		this.industrialGroup = industrialGroup;
	}

	public String getCfiCode() {
		return cfiCode;
	}

	public void setCfiCode(String cfiCode) {
		this.cfiCode = cfiCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// ***** ***** ***** ***** ***** Builder

	public static final class Builder {

		private String securityCode;
		private String stkNameCht;
		private String stkNameEng;
		private String isinCode;
		private DateTime listingDate;
		private MarketType marketType;
		private IndustrialGroup industrialGroup;
		private String cfiCode;
		private String comment;

		// ***** ***** ***** ***** ***** Constructors

		public Builder() {}

		public Builder(StockCompanyEntity copy) {
			this.securityCode = copy.getSecurityCode();
			this.stkNameCht = copy.getStkNameCht();
			this.stkNameEng = copy.getStkNameEng();
			this.isinCode = copy.getIsinCode();
			this.listingDate = copy.getListingDate();
			this.marketType = copy.getMarketType();
			this.industrialGroup = copy.getIndustrialGroup();
			this.cfiCode = copy.getCfiCode();
			this.comment = copy.getComment();
		}

		// ***** ***** ***** ***** ***** Setter methods

		public Builder setSecurityCode(String val) {
			securityCode = val;
			return this;
		}

		public Builder setStkNameCht(String val) {
			stkNameCht = val;
			return this;
		}

		public Builder setStkNameEng(String val) {
			stkNameEng = val;
			return this;
		}

		public Builder setIsinCode(String val) {
			isinCode = val;
			return this;
		}

		public Builder setListingDate(DateTime val) {
			listingDate = val;
			return this;
		}

		public Builder setMarketType(MarketType val) {
			marketType = val;
			return this;
		}

		public Builder setIndustrialGroup(IndustrialGroup val) {
			industrialGroup = val;
			return this;
		}

		public Builder setCfiCode(String val) {
			cfiCode = val;
			return this;
		}

		public Builder setComment(String val) {
			comment = val;
			return this;
		}

		// ***** ***** ***** ***** ***** Public methods

		public StockCompanyEntity build() {
			return new StockCompanyEntity(this);
		}
	}
}
