package tw.com.stock_crawler.db.entity;

import org.joda.time.DateTime;
import tw.com.stock_crawler.db.Constants;
import tw.com.stock_crawler.db.converter.JodaTimeConverter;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
		name = StockWeeklyTradingEntity.TABLE_NAME,
		schema = Constants.SCHEMA_PUBLIC
)
public class StockWeeklyTradingEntity {

	// ----- ----- Public constants
	// Table name
	public static final String TABLE_NAME = "stk_week_trading";
	// Column name
	public static final String COL_SN = "sn";
	public static final String COL_SECURITY_CODE = "security_code";
	public static final String COL_DATE = "date";
	// 成交股數
	public static final String COL_TRADE_VOLUME = "trade_volume";
	// 成交金額
	public static final String COL_TRADE_VALUE = "trade_value";
	public static final String COL_OPENING_PRICE = "opening_price";
	public static final String COL_HIGHEST_PRICE = "highest_price";
	public static final String COL_LOWEST_PRICE = "lowest_price";
	public static final String COL_CLOSING_PRICE = "closing_price";
	// 漲跌價差
	public static final String COL_CHANGE = "change";
	// 成交筆數
	public static final String COL_TRANSACTION = "transaction";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COL_SN)
	private long sn;
	@Column(name = COL_SECURITY_CODE)
	private String securityCode;
	@Convert(converter = JodaTimeConverter.class)
	@Column(name = COL_DATE)
	private DateTime date;
	@Column(name = COL_TRADE_VOLUME)
	private long tradeVolume;
	@Column(name = COL_TRADE_VALUE)
	private long tradeValue;
	@Column(name = COL_OPENING_PRICE)
	private BigDecimal openingPrice;
	@Column(name = COL_HIGHEST_PRICE)
	private BigDecimal highestPrice;
	@Column(name = COL_LOWEST_PRICE)
	private BigDecimal lowestPrice;
	@Column(name = COL_CLOSING_PRICE)
	private BigDecimal closingPrice;
	@Column(name = COL_CHANGE)
	private BigDecimal change;
	@Column(name = COL_TRANSACTION)
	private long transaction;

	// ***** ***** ***** ***** ***** Constructors

	private StockWeeklyTradingEntity(Builder builder) {
		setSn(builder.sn);
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

	// ***** ***** ***** ***** ***** Public static methods

	public static Builder newBuilder() {
		return new Builder();
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	public long getSn() {
		return sn;
	}

	public void setSn(long sn) {
		this.sn = sn;
	}

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

		private long sn;
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

		private Builder() {}

		// ***** ***** ***** ***** ***** Setter methods

		@Nonnull
		public Builder setSn(long sn) {
			this.sn = sn;
			return this;
		}

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
		public StockWeeklyTradingEntity build() {
			return new StockWeeklyTradingEntity(this);
		}
	}
}
