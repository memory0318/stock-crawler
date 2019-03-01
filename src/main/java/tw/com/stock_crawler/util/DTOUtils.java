package tw.com.stock_crawler.util;

import tw.com.stock_crawler.db.entity.StockDailyTradingEntity;
import tw.com.stock_crawler.dto.DailyStockTradingDTO;
import tw.com.stock_crawler.dto.history.HistoryDayStockTradingDTO;
import tw.com.stock_crawler.dto.history.OtcHistoryDailyStockTradingDTO;
import tw.com.stock_crawler.dto.history.TseHistoryDailyStockTradingDTO;

import java.util.LinkedList;
import java.util.List;

public class DTOUtils {

	public static List<StockDailyTradingEntity> convertHistoryDtoToEntities(
			String securityCode, HistoryDayStockTradingDTO dto
	) {
		List<StockDailyTradingEntity> results = new LinkedList<>();
		if (dto.getData() != null) {
			if (dto instanceof TseHistoryDailyStockTradingDTO) {
				results.addAll(
						DTOUtils.convertHistoryDtoToEntities(securityCode, (TseHistoryDailyStockTradingDTO) dto)
				);
			} else if (dto instanceof OtcHistoryDailyStockTradingDTO) {
				results.addAll(
						DTOUtils.convertHistoryDtoToEntities(securityCode, (OtcHistoryDailyStockTradingDTO) dto)
				);
			}
		}
		return results;
	}

	public static List<StockDailyTradingEntity> convertHistoryDtoToEntities(
			String securityCode, TseHistoryDailyStockTradingDTO dto
	) {
		List<StockDailyTradingEntity> entities = new LinkedList<>();
		if (dto.getData() != null) {
			dto.getData().forEach(detailDTO -> entities.add(
					new StockDailyTradingEntity.Builder()
							.setSecurityCode(securityCode)
							.setDate(detailDTO.getDate())
							.setTradeVolume(detailDTO.getTradeVolume())
							.setTradeValue(detailDTO.getTradeValue())
							.setOpeningPrice(detailDTO.getOpeningPrice())
							.setHighestPrice(detailDTO.getHighestPrice())
							.setLowestPrice(detailDTO.getLowestPrice())
							.setClosingPrice(detailDTO.getClosingPrice())
							.setChange(detailDTO.getChange())
							.setTransaction(detailDTO.getTransaction())
							.build()
			));
		}
		return entities;
	}

	public static List<StockDailyTradingEntity> convertHistoryDtoToEntities(
			String securityCode, OtcHistoryDailyStockTradingDTO dto
	) {
		List<StockDailyTradingEntity> entities = new LinkedList<>();
		if (dto.getData() != null) {
			dto.getData().forEach(detailDTO -> entities.add(
					new StockDailyTradingEntity.Builder()
							.setSecurityCode(securityCode)
							.setDate(detailDTO.getDate())
							.setTradeVolume(detailDTO.getTradeVolume() * 1000)
							.setTradeValue(detailDTO.getTradeValue() * 1000)
							.setOpeningPrice(detailDTO.getOpeningPrice())
							.setHighestPrice(detailDTO.getHighestPrice())
							.setLowestPrice(detailDTO.getLowestPrice())
							.setClosingPrice(detailDTO.getClosingPrice())
							.setChange(detailDTO.getChange())
							.setTransaction(detailDTO.getTransaction())
							.build()
			));
		}
		return entities;
	}

	public static DailyStockTradingDTO convertEntityToDTO(StockDailyTradingEntity entity) {
		return new DailyStockTradingDTO.Builder()
				.setSecurityCode(entity.getSecurityCode())
				.setDate(entity.getDate())
				.setTradeVolume(entity.getTradeVolume())
				.setTradeValue(entity.getTradeValue())
				.setOpeningPrice(entity.getOpeningPrice())
				.setHighestPrice(entity.getHighestPrice())
				.setLowestPrice(entity.getLowestPrice())
				.setClosingPrice(entity.getClosingPrice())
				.setChange(entity.getChange())
				.setTransaction(entity.getTransaction())
				.build();
	}
}
