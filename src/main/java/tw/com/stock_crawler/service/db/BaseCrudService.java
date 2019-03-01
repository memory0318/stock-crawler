package tw.com.stock_crawler.service.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class BaseCrudService<T, ID extends Serializable, REPOSITORY extends JpaRepository<T, ID>> {

	// ----- Logger
	protected final Logger logger = LoggerFactory.getLogger(BaseCrudService.class);

	protected REPOSITORY repository;

	// ***** ***** ***** ***** ***** Getter and setter methods

	public abstract void setRepository(REPOSITORY repository);

	// ***** ***** ***** ***** ***** Public methods

	public List<T> findAll() {
		try {
			return this.repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when finding all entities.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public List<T> findAll(Sort sort) {
		try {
			return this.repository.findAll(sort);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when finding all entities with Sort object.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public Page<T> findAll(Pageable pageable) {
		try {
			return this.repository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when finding all entities with Pageable object.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public <S extends T> List save(Iterable<S> entities) {
		try {
			return this.repository.save(entities);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when saving entities.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void flush() {
		try {
			this.repository.flush();
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when flushing repository.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public <S extends T> S saveAndFlush(S entity) {
		try {
			return this.repository.saveAndFlush(entity);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when saving and flushing entity.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void deleteInBatch(Iterable<T> entities) {
		try {
			this.repository.deleteInBatch(entities);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when deleting entities in batch.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void deleteAllInBatch() {
		try {
			this.repository.deleteAllInBatch();
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when deleting all entities in batch";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public T getOne(ID id) {
		try {
			return this.repository.getOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when getting entity by ID.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public <S extends T> S save(S entity) {
		try {
			return this.repository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when saving entity";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public T findOne(ID id) {
		try {
			return this.repository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when finding entity by ID";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public boolean exists(ID id) {
		try {
			return this.repository.exists(id);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when checking whether entity projectExists or not.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public Iterable<T> findAll(Iterable<ID> ids) {
		try {
			return this.repository.findAll(ids);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when finding entities by the list of ID.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public long count() {
		try {
			return this.repository.count();
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when counting entities.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void delete(ID id) {
		try {
			this.repository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when deleting entity by ID";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void delete(T entity) {
		try {
			this.repository.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when deleting the specified entity.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void delete(Iterable<? extends T> entities) {
		try {
			this.repository.delete(entities);
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when deleting the specified entities.";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	public void deleteAll() {
		try {
			this.repository.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = "Exception occurred when deleting all entities";
			this.logger.error(errMsg, e);
			throw new RuntimeException(errMsg, e);
		}
	}

	// ***** ***** ***** ***** ***** Utility methods
}
