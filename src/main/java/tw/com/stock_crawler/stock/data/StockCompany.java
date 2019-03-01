package tw.com.stock_crawler.stock.data;

import javax.annotation.Nonnull;

public class StockCompany implements Cloneable, Comparable<StockCompany> {

	// ----- ----- Private constants
	private static final String CLASS_NAME = StockCompany.class.getName();

	private String securityCode;
	private String stockChtName;
	private String stockEngName;
	private String isinCode;
	private String listingDate;
	private MarketType marketType;
	private IndustrialGroup industrialGroup;
	private String cfiCode;

	// ***** ***** ***** ***** ***** Constructors

	public StockCompany() { }

	// ***** ***** ***** ***** ***** Getter and setter methods

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getStockChtName() {
		return stockChtName;
	}

	public void setStockChtName(String stockChtName) {
		this.stockChtName = stockChtName;
	}

	public String getStockEngName() {
		return stockEngName;
	}

	public void setStockEngName(String stockEngName) {
		this.stockEngName = stockEngName;
	}

	public String getIsinCode() {
		return isinCode;
	}

	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}

	public String getListingDate() {
		return listingDate;
	}

	public void setListingDate(String listingDate) {
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

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public StockCompany clone() {
		try {
			StockCompany instance = (StockCompany) super.clone();
			return instance;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException(String.format("Exception occurred when cloning %s.", CLASS_NAME), e);
		}
	}

	@Override
	public int compareTo(@Nonnull StockCompany stkCompany) {
		return this.securityCode.compareTo(stkCompany.securityCode);
	}

	// ***** ***** ***** ***** ***** Builder

	public static class Builder {

		private static StockCompany protoObj = new StockCompany();

		private String securityCode;
		private String stockChtName;
		private String stockEngName;
		private String isinCode;
		private String listingDate;
		private MarketType marketType;
		private IndustrialGroup industrialGroup;
		private String cfiCode;

		// ***** ***** ***** ***** ***** Constructors

		public Builder() { }

		// ***** ***** ***** ***** ***** Getter and setter methods

		public String getSecurityCode() {
			return securityCode;
		}

		public Builder setSecurityCode(String securityCode) {
			this.securityCode = securityCode;
			return this;
		}

		public String getStockChtName() {
			return stockChtName;
		}

		public Builder setStockChtName(String stockChtName) {
			this.stockChtName = stockChtName;
			return this;
		}

		public String getStockEngName() {
			return stockEngName;
		}

		public Builder setStockEngName(String stockEngName) {
			this.stockEngName = stockEngName;
			return this;
		}

		public String getIsinCode() {
			return isinCode;
		}

		public Builder setIsinCode(String isinCode) {
			this.isinCode = isinCode;
			return this;
		}

		public String getListingDate() {
			return listingDate;
		}

		public Builder setListingDate(String listingDate) {
			this.listingDate = listingDate;
			return this;
		}

		public MarketType getMarketType() {
			return marketType;
		}

		public Builder setMarketType(MarketType marketType) {
			this.marketType = marketType;
			return this;
		}

		public IndustrialGroup getIndustrialGroup() {
			return industrialGroup;
		}

		public Builder setIndustrialGroup(IndustrialGroup industrialGroup) {
			this.industrialGroup = industrialGroup;
			return this;
		}

		public String getCfiCode() {
			return cfiCode;
		}

		public Builder setCfiCode(String cfiCode) {
			this.cfiCode = cfiCode;
			return this;
		}

		// ***** ***** ***** ***** ***** Public methods

		public StockCompany build() {
			StockCompany instance = protoObj.clone();
			instance.securityCode = this.securityCode;
			instance.stockChtName = this.stockChtName;
			instance.stockEngName = this.stockEngName;
			instance.isinCode = this.isinCode;
			instance.listingDate = this.listingDate;
			instance.marketType = this.marketType;
			instance.industrialGroup = this.industrialGroup;
			instance.cfiCode = this.cfiCode;

			this.reset();

			return instance;
		}

		// ***** ***** ***** ***** ***** Utility methods

		private void reset() {
			this.securityCode = null;
			this.stockChtName = null;
			this.stockEngName = null;
			this.isinCode = null;
			this.listingDate = null;
			this.marketType = MarketType.NONE;
			this.industrialGroup = IndustrialGroup.Unknown;
			this.cfiCode = null;
		}
	}
}
