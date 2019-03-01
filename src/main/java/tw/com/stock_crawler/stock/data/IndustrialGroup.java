package tw.com.stock_crawler.stock.data;

import java.util.Map;
import java.util.TreeMap;

public enum IndustrialGroup {

	Cement("01"),
	Food("02"),
	Others("03"),
	Plastic("04"),
	Chemical_Industry("05"),
	Automobile("06"),
	Textile("07"),
	Trading_And_Consumers_Goods("08"),
	Building_Material_And_Construction("09"),
	Electronic_Parts_And_Components("10"),
	Electric_Machinery("11"),
	Biotechnology_And_Medical_Care("12"),
	Electrical_And_Cable("13"),
	Glass_And_Ceramic("14"),
	Paper_And_Pulp("15"),
	Iron_And_Steel("16"),
	Rubber("17"),
	Shipping_And_Transportation("18"),
	Computer_And_Peripheral_Equipment("19"),
	Semiconductor_Industry("20"),
	Other_Electronic_Industry("21"),
	Communications_And_Internet("22"),
	Optoelectronic_Industry("23"),
	Electronic_Products_Distribution("24"),
	Information_Service_Industry("25"),
	Oil_Gas_And_Electricity_Industry("26"),
	Tourism("27"),
	Financial_And_Insurance("28"),
	Unknown("00");

	private static final Map<String, IndustrialGroup> GROUP_MAP = new TreeMap<>();

	static {
		GROUP_MAP.put("水泥工業", Cement);
		GROUP_MAP.put("Cement", Cement);
		GROUP_MAP.put("食品工業", Food);
		GROUP_MAP.put("Food", Food);
		GROUP_MAP.put("其他業", Others);
		GROUP_MAP.put("Others", Others);
		GROUP_MAP.put("塑膠工業", Plastic);
		GROUP_MAP.put("Plastic", Plastic);
		GROUP_MAP.put("化學工業", Chemical_Industry);
		GROUP_MAP.put("Chemical Industry", Chemical_Industry);
		GROUP_MAP.put("汽車工業", Automobile);
		GROUP_MAP.put("Automobile", Automobile);
		GROUP_MAP.put("紡織纖維", Textile);
		GROUP_MAP.put("Textile", Textile);
		GROUP_MAP.put("貿易百貨業", Trading_And_Consumers_Goods);
		GROUP_MAP.put("Trading & Consumers Goods", Trading_And_Consumers_Goods);
		GROUP_MAP.put("建材營造業", Building_Material_And_Construction);
		GROUP_MAP.put("Building Material&Construction", Building_Material_And_Construction);
		GROUP_MAP.put("電子零組件業", Electronic_Parts_And_Components);
		GROUP_MAP.put("Electronic Parts/Components", Electronic_Parts_And_Components);
		GROUP_MAP.put("電機機械", Electric_Machinery);
		GROUP_MAP.put("Electric Machinery", Electric_Machinery);
		GROUP_MAP.put("生技醫療業", Biotechnology_And_Medical_Care);
		GROUP_MAP.put("Biotechnology and Medical Care", Biotechnology_And_Medical_Care);
		GROUP_MAP.put("電器電纜", Electrical_And_Cable);
		GROUP_MAP.put("Electrical & Cable", Electrical_And_Cable);
		GROUP_MAP.put("玻璃陶瓷", Glass_And_Ceramic);
		GROUP_MAP.put("Glass & Ceramic", Glass_And_Ceramic);
		GROUP_MAP.put("造紙工業", Paper_And_Pulp);
		GROUP_MAP.put("Paper & Pulp", Paper_And_Pulp);
		GROUP_MAP.put("鋼鐵工業", Iron_And_Steel);
		GROUP_MAP.put("Iron & Steel", Iron_And_Steel);
		GROUP_MAP.put("橡膠工業", Rubber);
		GROUP_MAP.put("Rubber", Rubber);
		GROUP_MAP.put("航運業", Shipping_And_Transportation);
		GROUP_MAP.put("Shipping & Transportation", Shipping_And_Transportation);
		GROUP_MAP.put("電腦及週邊設備業", Computer_And_Peripheral_Equipment);
		GROUP_MAP.put("Computer and Peripheral Equipm", Computer_And_Peripheral_Equipment);
		GROUP_MAP.put("半導體業", Semiconductor_Industry);
		GROUP_MAP.put("Semiconductor Industry", Semiconductor_Industry);
		GROUP_MAP.put("其他電子業", Other_Electronic_Industry);
		GROUP_MAP.put("Other Electronic Industry", Other_Electronic_Industry);
		GROUP_MAP.put("通信網路業", Communications_And_Internet);
		GROUP_MAP.put("Communications and Internet", Communications_And_Internet);
		GROUP_MAP.put("光電業", Optoelectronic_Industry);
		GROUP_MAP.put("Optoelectronic Industry", Optoelectronic_Industry);
		GROUP_MAP.put("電子通路業", Electronic_Products_Distribution);
		GROUP_MAP.put("Electronic Products Distributi", Electronic_Products_Distribution);
		GROUP_MAP.put("資訊服務業", Information_Service_Industry);
		GROUP_MAP.put("Information Service Industry", Information_Service_Industry);
		GROUP_MAP.put("油電燃氣業", Oil_Gas_And_Electricity_Industry);
		GROUP_MAP.put("Oil, Gas and Electricity Indu", Oil_Gas_And_Electricity_Industry);
		GROUP_MAP.put("觀光事業", Tourism);
		GROUP_MAP.put("Tourism", Tourism);
		GROUP_MAP.put("金融保險業", Financial_And_Insurance);
		GROUP_MAP.put("Financial & Insurance", Financial_And_Insurance);
	}

	String code;

	IndustrialGroup(String code) {
		this.code = code;
	}

	public static IndustrialGroup getIndustrialGroup(String groupName) {
		IndustrialGroup industrialGroup = GROUP_MAP.get(groupName);
		return industrialGroup == null ? Unknown : industrialGroup;
	}

	public String getCode() {
		return this.code;
	}
}
