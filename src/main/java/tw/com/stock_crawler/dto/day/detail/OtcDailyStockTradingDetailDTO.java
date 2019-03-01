package tw.com.stock_crawler.dto.day.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import tw.com.stock_crawler.util.json.deserializer.BigDecimalDeserializer;
import tw.com.stock_crawler.util.json.deserializer.LongCommaRemovalDeserializer;
import tw.com.stock_crawler.util.json.deserializer.WhiteSpaceRemovalDeserializer;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class OtcDailyStockTradingDetailDTO implements DailyStockTradingDetailDTO {

	@JsonProperty
	@JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
	private String securityCode;

	@JsonProperty
	@JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
	private String securityName;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal closingPrice;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal change;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal openingPrice;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal highestPrice;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal lowestPrice;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long tradeVolume;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long tradeValue;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long transaction;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal lastBestBidPrice;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal lastBestAskPrice;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long issuedShares;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal nextDayUpLimitPrice;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal nextDayDownLimitPrice;

	// ***** ***** ***** ***** ***** Constructors

	public OtcDailyStockTradingDetailDTO() { }

	private OtcDailyStockTradingDetailDTO(Builder builder) {
		setSecurityCode(builder.securityCode);
		setSecurityName(builder.securityName);
		setClosingPrice(builder.closingPrice);
		setChange(builder.change);
		setOpeningPrice(builder.openingPrice);
		setHighestPrice(builder.highestPrice);
		setLowestPrice(builder.lowestPrice);
		setTradeVolume(builder.tradeVolume);
		setTradeValue(builder.tradeValue);
		setTransaction(builder.transaction);
		setLastBestBidPrice(builder.lastBestBidPrice);
		setLastBestAskPrice(builder.lastBestAskPrice);
		setIssuedShares(builder.issuedShares);
		setNextDayUpLimitPrice(builder.nextDayUpLimitPrice);
		setNextDayDownLimitPrice(builder.nextDayDownLimitPrice);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Override
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	@Override
	public BigDecimal getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(BigDecimal closingPrice) {
		this.closingPrice = closingPrice;
	}

	@Override
	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	@Override
	public BigDecimal getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(BigDecimal openingPrice) {
		this.openingPrice = openingPrice;
	}

	@Override
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	@Override
	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	@Override
	public long getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(long tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	@Override
	public long getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(long tradeValue) {
		this.tradeValue = tradeValue;
	}

	@Override
	public long getTransaction() {
		return transaction;
	}

	public void setTransaction(long transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getLastBestBidPrice() {
		return lastBestBidPrice;
	}

	public void setLastBestBidPrice(BigDecimal lastBestBidPrice) {
		this.lastBestBidPrice = lastBestBidPrice;
	}

	public BigDecimal getLastBestAskPrice() {
		return lastBestAskPrice;
	}

	public void setLastBestAskPrice(BigDecimal lastBestAskPrice) {
		this.lastBestAskPrice = lastBestAskPrice;
	}

	public long getIssuedShares() {
		return issuedShares;
	}

	public void setIssuedShares(long issuedShares) {
		this.issuedShares = issuedShares;
	}

	public BigDecimal getNextDayUpLimitPrice() {
		return nextDayUpLimitPrice;
	}

	public void setNextDayUpLimitPrice(BigDecimal nextDayUpLimitPrice) {
		this.nextDayUpLimitPrice = nextDayUpLimitPrice;
	}

	public BigDecimal getNextDayDownLimitPrice() {
		return nextDayDownLimitPrice;
	}

	public void setNextDayDownLimitPrice(BigDecimal nextDayDownLimitPrice) {
		this.nextDayDownLimitPrice = nextDayDownLimitPrice;
	}

	// ***** ***** ***** ***** ***** Builder

	public static final class Builder {

		private String securityCode;
		private String securityName;
		private BigDecimal closingPrice;
		private BigDecimal change;
		private BigDecimal openingPrice;
		private BigDecimal highestPrice;
		private BigDecimal lowestPrice;
		private long tradeVolume;
		private long tradeValue;
		private long transaction;
		private BigDecimal lastBestBidPrice;
		private BigDecimal lastBestAskPrice;
		private long issuedShares;
		private BigDecimal nextDayUpLimitPrice;
		private BigDecimal nextDayDownLimitPrice;

		// ***** ***** ***** ***** ***** Constructors

		private Builder() {}

		// ***** ***** ***** ***** ***** Setter methods

		@Nonnull
		public Builder setSecurityCode(@Nonnull String securityCode) {
			this.securityCode = securityCode;
			return this;
		}

		@Nonnull
		public Builder setSecurityName(@Nonnull String securityName) {
			this.securityName = securityName;
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
		public Builder setTransaction(long transaction) {
			this.transaction = transaction;
			return this;
		}

		@Nonnull
		public Builder setLastBestBidPrice(@Nonnull BigDecimal lastBestBidPrice) {
			this.lastBestBidPrice = lastBestBidPrice;
			return this;
		}

		@Nonnull
		public Builder setLastBestAskPrice(@Nonnull BigDecimal lastBestAskPrice) {
			this.lastBestAskPrice = lastBestAskPrice;
			return this;
		}

		@Nonnull
		public Builder setIssuedShares(long issuedShares) {
			this.issuedShares = issuedShares;
			return this;
		}

		@Nonnull
		public Builder setNextDayUpLimitPrice(@Nonnull BigDecimal nextDayUpLimitPrice) {
			this.nextDayUpLimitPrice = nextDayUpLimitPrice;
			return this;
		}

		@Nonnull
		public Builder setNextDayDownLimitPrice(@Nonnull BigDecimal nextDayDownLimitPrice) {
			this.nextDayDownLimitPrice = nextDayDownLimitPrice;
			return this;
		}

		@Nonnull
		public OtcDailyStockTradingDetailDTO build() {
			return new OtcDailyStockTradingDetailDTO(this);
		}
	}
}
