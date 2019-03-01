package tw.com.stock_crawler.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.stock_crawler.db.entity.StockCompanyEntity;

import java.util.List;

public interface StockCompanyRepository extends JpaRepository<StockCompanyEntity, String> {

	StockCompanyEntity findBySecurityCode(String securityCode);

	List<StockCompanyEntity> findAllByOrderBySecurityCode();

	List<StockCompanyEntity> findByStkNameChtLike(String stockName);
}
