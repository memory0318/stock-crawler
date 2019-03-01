package tw.com.stock_crawler.dto.day.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import tw.com.stock_crawler.util.json.deserializer.BigDecimalDeserializer;
import tw.com.stock_crawler.util.json.deserializer.LongCommaRemovalDeserializer;
import tw.com.stock_crawler.util.json.deserializer.WhiteSpaceRemovalDeserializer;

import java.math.BigDecimal;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class TseDailyStockTradingDetailDTO implements DailyStockTradingDetailDTO {

	@JsonProperty
	@JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
	private String securityCode;

	@JsonProperty
	@JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
	private String securityName;

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

	// ****** ***** ***** ***** ***** Constructors

	public TseDailyStockTradingDetailDTO() { }

	// ****** ***** ***** ***** ***** Getter and setter methods

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
	public long getTransaction() {
		return transaction;
	}

	public void setTransaction(long transaction) {
		this.transaction = transaction;
	}
}
