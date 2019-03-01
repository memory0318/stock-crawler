package tw.com.stock_crawler.dto;

import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public class DailyStockTradingDTO {

	private String securityCode;
	private DateTime date;
	private long tradeVolume;
	private long tradeValue;
	private BigDecimal openingPrice;
	private BigDecimal highestPrice;
	private BigDecimal lowestPrice;
	private BigDecimal closingPrice;
	private BigDecimal change;
	private long transaction;

	// ***** ***** ***** ***** ***** Constructors

	private DailyStockTradingDTO(Builder builder) {
		setSecurityCode(builder.securityCode);
		setDate(builder.date);
		setTradeVolume(builder.tradeVolume);
		setTradeValue(builder.tradeValue);
		setOpeningPrice(builder.openingPrice);
		setHighestPrice(builder.highestPrice);
		setLowestPrice(builder.lowestPrice);
		setClosingPrice(builder.closingPrice);
		setChange(builder.change);
		setTransaction(builder.transaction);
	}

	public static Builder newBuilder(@Nonnull DailyStockTradingDTO copy) {
		Builder builder = new Builder();
		builder.securityCode = copy.getSecurityCode();
		builder.date = copy.getDate();
		builder.tradeVolume = copy.getTradeVolume();
		builder.tradeValue = copy.getTradeValue();
		builder.openingPrice = copy.getOpeningPrice();
		builder.highestPrice = copy.getHighestPrice();
		builder.lowestPrice = copy.getLowestPrice();
		builder.closingPrice = copy.getClosingPrice();
		builder.change = copy.getChange();
		builder.transaction = copy.getTransaction();
		return builder;
	}

	// ***** ***** ***** ***** ***** Public static methods

	public static Builder newBuilder() {
		return new Builder();
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public long getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(long tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	public long getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(long tradeValue) {
		this.tradeValue = tradeValue;
	}

	public BigDecimal getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(BigDecimal openingPrice) {
		this.openingPrice = openingPrice;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(BigDecimal closingPrice) {
		this.closingPrice = closingPrice;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public long getTransaction() {
		return transaction;
	}

	public void setTransaction(long transaction) {
		this.transaction = transaction;
	}

	// ***** ***** ***** ***** ***** Builder

	public static final class Builder {

		private String securityCode;
		private DateTime date;
		private long tradeVolume;
		private long tradeValue;
		private BigDecimal openingPrice;
		private BigDecimal highestPrice;
		private BigDecimal lowestPrice;
		private BigDecimal closingPrice;
		private BigDecimal change;
		private long transaction;

		// ***** ***** ***** ***** ***** Constructors

		public Builder() {}

		// ***** ***** ***** ***** ***** Setter methods

		@Nonnull
		public Builder setSecurityCode(@Nonnull String securityCode) {
			this.securityCode = securityCode;
			return this;
		}

		@Nonnull
		public Builder setDate(@Nonnull DateTime date) {
			this.date = date;
			return this;
		}

		@Nonnull
		public Builder setTradeVolume(long tradeVolume) {
			this.tradeVolume = tradeVolume;
			return this;
		}

		@Nonnull
		public Builder setTradeValue(long tradeValue) {
			this.tradeValue = tradeValue;
			return this;
		}

		@Nonnull
		public Builder setOpeningPrice(@Nonnull BigDecimal openingPrice) {
			this.openingPrice = openingPrice;
			return this;
		}

		@Nonnull
		public Builder setHighestPrice(@Nonnull BigDecimal highestPrice) {
			this.highestPrice = highestPrice;
			return this;
		}

		@Nonnull
		public Builder setLowestPrice(@Nonnull BigDecimal lowestPrice) {
			this.lowestPrice = lowestPrice;
			return this;
		}

		@Nonnull
		public Builder setClosingPrice(@Nonnull BigDecimal closingPrice) {
			this.closingPrice = closingPrice;
			return this;
		}

		@Nonnull
		public Builder setChange(@Nonnull BigDecimal change) {
			this.change = change;
			return this;
		}

		@Nonnull
		public Builder setTransaction(long transaction) {
			this.transaction = transaction;
			return this;
		}

		@Nonnull
		public DailyStockTradingDTO build() {
			return new DailyStockTradingDTO(this);
		}
	}
}
