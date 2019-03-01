package tw.com.stock_crawler.db.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.stock_crawler.db.entity.StockMonthlyTradingEntity;
import tw.com.stock_crawler.db.entity.StockWeeklyTradingEntity;

import java.util.List;

public interface StockWeeklyTradingRepository extends JpaRepository<StockWeeklyTradingEntity, Long> {

	List<StockWeeklyTradingEntity> findBySecurityCode(String securityCode);

	List<StockWeeklyTradingEntity> findBySecurityCodeAndDateBetween(
			String securityCode, DateTime startDateTime, DateTime endDateTime
	);

	// FIXME: 2018/9/11 需要修正
	List<StockWeeklyTradingEntity> findByDate(DateTime dateTime);

	// FIXME: 2018/9/11 需要修正
	int countBySecurityCodeAndDate(String securityCode, DateTime dateTime);

	// FIXME: 2018/9/11 需要修正
	int countBySecurityCodeAndDateBetween(String securityCode, DateTime startDateTime, DateTime endDateTime);

	StockWeeklyTradingEntity findFirstBySecurityCodeOrderByDateAsc(String securityCode);

	StockWeeklyTradingEntity findFirstBySecurityCodeOrderByDateDesc(String securityCode);
}
