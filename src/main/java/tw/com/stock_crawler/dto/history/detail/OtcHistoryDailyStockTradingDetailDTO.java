package tw.com.stock_crawler.dto.history.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.DateTime;
import tw.com.stock_crawler.util.json.deserializer.BigDecimalDeserializer;
import tw.com.stock_crawler.util.json.deserializer.LongCommaRemovalDeserializer;
import tw.com.stock_crawler.util.json.deserializer.MinguoDateTimeDeserializer;
import tw.com.stock_crawler.util.json.deserializer.OtcJodaTimeDeserializer;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class OtcHistoryDailyStockTradingDetailDTO {

	@JsonProperty
	@JsonDeserialize(using = OtcJodaTimeDeserializer.class)
	private DateTime date;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long tradeVolume;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long tradeValue;

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
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal closingPrice;

	@JsonProperty
	@JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal change;

	@JsonProperty
	@JsonDeserialize(using = LongCommaRemovalDeserializer.class)
	private long transaction;

	// ***** ***** ***** ***** ***** Constructors

	public OtcHistoryDailyStockTradingDetailDTO() { }

	private OtcHistoryDailyStockTradingDetailDTO(Builder builder) {
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

	// ***** ***** ***** ***** ***** Public static methods

	public static Builder newBuilder() {
		return new Builder();
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

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

	// ***** ***** ***** ***** ***** Builder methods

	public static final class Builder {

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

		private Builder() {}

		// ***** ***** ***** ***** ***** Setter methods

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
		public OtcHistoryDailyStockTradingDetailDTO build() {
			return new OtcHistoryDailyStockTradingDetailDTO(this);
		}
	}
}
