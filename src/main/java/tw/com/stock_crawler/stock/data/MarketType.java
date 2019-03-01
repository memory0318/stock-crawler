package tw.com.stock_crawler.stock.data;

import java.util.Map;
import java.util.TreeMap;

public enum MarketType {

	TSE("上市"), OTC("上櫃"), NONE("none");

	private static final Map<String, MarketType> TYPE_MAP = new TreeMap<>();

	static {
		TYPE_MAP.put("上市", TSE);
		TYPE_MAP.put("TWSE LISTED", TSE);
		TYPE_MAP.put("上櫃", OTC);
		TYPE_MAP.put("TPEx LISTED", OTC);
	}

	private String type;

	MarketType(String type) {
		this.type = type;
	}

	public static MarketType getMarketType(String typeName) {
		return TYPE_MAP.get(typeName);
	}

	public String getType() {
		return type;
	}
}
