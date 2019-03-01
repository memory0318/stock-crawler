package tw.com.stock_crawler.config.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class WebAppContext implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	// ***** ***** ***** ***** ***** Public methods

	public String getWebAppRealPath() {
		if (this.applicationContext instanceof WebApplicationContext) {
			return ((WebApplicationContext) this.applicationContext).getServletContext().getRealPath("");
		} else {
			return null;
		}
	}
}
