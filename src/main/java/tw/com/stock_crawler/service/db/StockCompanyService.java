package tw.com.stock_crawler.service.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.stock_crawler.db.entity.StockCompanyEntity;
import tw.com.stock_crawler.db.repository.StockCompanyRepository;

@Service
public class StockCompanyService extends BaseCrudService<StockCompanyEntity, String, StockCompanyRepository> {

	// ----- ----- Logger
	private final Logger logger = LoggerFactory.getLogger(StockCompanyService.class);

	// ***** ***** ***** ***** ***** Public methods

	public StockCompanyEntity findBySecurityCode(String securityCode) {
		try {
			return this.repository.findBySecurityCode(securityCode);
		} catch (Exception e) {
			String errMsg = "Exception occurred when finding stock company entity.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public boolean existsStockCompany(String securityCode) {
		return this.repository.findBySecurityCode(securityCode) != null;
	}

	// ***** ***** ***** ***** ***** Utility methods

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Autowired
	@Override
	public void setRepository(StockCompanyRepository repository) {
		this.repository = repository;
	}
}
