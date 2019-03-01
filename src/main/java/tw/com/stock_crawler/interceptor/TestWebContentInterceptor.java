package tw.com.stock_crawler.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.util.*;

/**
 * 改善原本 WebContentInterceptor 在快取設定的判斷順序，原本的問題為若有多個 cacheMapping 符合，則"不一定"會採用哪一個設定
 * 此物件判斷的方式為，當多個 cacheMapping 符合時，則採用字串較長之 cacheMapping // TODO: 應該要採用"最接近"的 cacheMapping，但如何判斷?
 * by Matthew
 */
public class TestWebContentInterceptor extends WebContentInterceptor {
	private final Logger log = LoggerFactory.getLogger(TestWebContentInterceptor.class);
	private Map<String, Integer> cacheMappings = new LinkedHashMap<String, Integer>(); // 利用 LinkedHashMap 來調整 order
	private PathMatcher pathMatcher = new AntPathMatcher();

	@Override
	public void setPathMatcher(PathMatcher pathMatcher) {
		Assert.notNull(pathMatcher, "PathMatcher must not be null");
		this.pathMatcher = pathMatcher;
	}

	@Override
	public void setCacheMappings(Properties cacheMappings) {
		this.cacheMappings.clear();

		List<String> paths = new LinkedList<String>();
		Enumeration<?> propNames = cacheMappings.propertyNames();
		while (propNames.hasMoreElements()) {
			String path = (String) propNames.nextElement();
			paths.add(path);

		}
		Collections.sort(paths, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return -((o1 != null ? o1.length() : 0) - (o2 != null ? o2.length() : 0)); // 長度較長者往前
			}
		});
		for (String path : paths) {
			this.cacheMappings.put(path, Integer.valueOf(cacheMappings.getProperty(path)));
		}
	}

	@Override
	protected Integer lookupCacheSeconds(String urlPath) {
		// direct match?
		Integer cacheSeconds = this.cacheMappings.get(urlPath);
		if (cacheSeconds == null) {
			// pattern match?
			for (String registeredPath : this.cacheMappings.keySet()) {
				if (this.pathMatcher.match(registeredPath, urlPath)) {
					cacheSeconds = this.cacheMappings.get(registeredPath);
					break; // 因為已排序過，所以只要找到一個即可跳出迴圈
				}
			}
		}
		return cacheSeconds;
	}
}
