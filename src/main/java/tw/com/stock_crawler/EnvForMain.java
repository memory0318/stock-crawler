package tw.com.stock_crawler;


import com.google.common.io.Resources;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tw.com.gips.utils.FileUtil;

import java.io.File;

/**
 * NOTE: 若使用 IntelliJ 執行測試，請記得將 Working directory 設為 $MODULE_DIR$ (與 maven/gradle 執行時採用的路徑相同)
 */
public final class EnvForMain {
	// TODO: 預設應該照 web.xml 裡的設定來決定比較好
	public final static String ACTIVE_PROFILE = "dev"; // 空字串表示沒有特定 profile
	public final static String WWB_CONTEXT_DIR = "src/main/webapp";
	public final static String DEFAULT_APP_CONTEXT_FILE = "file:src/main/webapp/WEB-INF/spring/app-context-without-servlet.xml"; // 只是預設
	//
	private static File _tmpDirForLog4j = null;
	private static boolean log4jInitialized = false;
	private static ApplicationContext _context = null;

	public static void initAll() {
		initAll(DEFAULT_APP_CONTEXT_FILE, ACTIVE_PROFILE);
	}

	public static void initAll(String appContextFile, String activeProfile) {
		initBasic();
		initSpringAppContext(appContextFile, activeProfile);
	}

	public static void initBasic() {
		initLog4j();
	}

	public static void initLog4j() {
		if (!log4jInitialized) {
			// 初始化 log4j
			// TODO: 目前如果 TestCase 使用 @RunWith(SpringJUnit4ClassRunner.class) 的話，log4j 將會提早初始化 (因裡面有 private static final Log logger = LogFactory.getLog(...) )，這邊的初始化就會無效
			if (true) {
				if (_tmpDirForLog4j == null) {
					_tmpDirForLog4j = FileUtil.createTempDir(null, "log4j", "tmp");
					_tmpDirForLog4j.deleteOnExit(); // 程式離開後自動刪除, 沒作用!?
				}
				System.setProperty("catalina.home", _tmpDirForLog4j.getPath()); // for log4j
				//System.setProperty("log4j.configurationFile", "conf/1og4j2.xml"); // not ok now

				try {
					//String logConfigFile = "src/main/webapp/WEB-INF/log4j2.xml";
					//ConfigurationSource source = new ConfigurationSource(new FileInputStream(logConfigFile));
					ConfigurationSource source = new ConfigurationSource(Resources.getResource("conf/log4j2.xml").openStream());
					Configurator.initialize(null, source);
					log4jInitialized = true;

//					Logger logger = LoggerFactory.getLogger(EnvForMain.class);
//					logger.trace("log4j test. level: trace");
//					logger.debug("log4j test. level: debug");
//					logger.info("log4j test. level: info");
//					logger.warn("log4j test. level: warn");
//					logger.error("log4j test. level: error");

				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	public static ApplicationContext initSpringAppContext(String appContextFile, String activeProfile) {
		if (activeProfile != null && activeProfile.length() > 0) {
			System.setProperty("spring.profiles.active", ACTIVE_PROFILE); // specify active spring profile // TODO: 使用 ConfigurableEnvironment#setActiveProfiles(String...) ?
			//System.setProperty("spring.profiles.default", ACTIVE_PROFILE); // specify default spring profile // TODO: 使用 ConfigurableEnvironment#setActiveProfiles(String...) ?
		}
		ApplicationContext appContext = new FileSystemXmlApplicationContext(appContextFile);

		_context = appContext;
		return appContext;
	}

	public static ApplicationContext getSpringAppContext() {
		return _context;
	}

	public static void setSpringAppContext(ApplicationContext appContext) {
		_context = appContext;
	}

	public static void destroy() {
		if (_tmpDirForLog4j != null)
			FileUtil.delete(_tmpDirForLog4j);
	}

}
