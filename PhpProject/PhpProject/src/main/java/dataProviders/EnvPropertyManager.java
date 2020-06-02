package dataProviders;

import java.io.IOException;
import java.util.Properties;

import utilities.DriverType;

public class EnvPropertyManager {
	
	private static Properties envProperties = new Properties();
	private static final String PROPERTIES_FILE = "config.properties";

	static {
		try {
			envProperties.load(EnvPropertyManager.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Property file is not found" + e);
		}
	}

	public static DriverType getBrowser() {
		String browser = envProperties.getProperty("browser");
		DriverType driver;
		switch (browser.toLowerCase()) {
		case "firefox":
			driver = DriverType.FIREFOX;
			break;
		case "chrome":
			driver = DriverType.CHROME;
			break;
		case "ie":
			driver = DriverType.IE;
			break;
		case "edge":
			driver = DriverType.EDGE;
			break;
		default:
			driver = DriverType.CHROME;
			break;
		}
		return driver;
	}

	public static String getUrl() {
		return envProperties.getProperty("phpTravels.url");
	}

	public static String getUsername() {
		return envProperties.getProperty("phpTravels.username");
	}

	public static String getPassword() {
		return envProperties.getProperty("phpTravels.password");
	}

	public static long getImplicitWaitInMilliSeconds() {
		try {
			return Long.parseLong(envProperties.getProperty("wait_milliseconds"));
		} catch (NumberFormatException e) {
			throw new RuntimeException(
					"Not able to parse value : " + envProperties.getProperty("wait_milliseconds") + " in to Long");
		}
	}

	public static long getPollingIntervalInMilliSeconds() {
		try {
			return Long.parseLong(envProperties.getProperty("pollinginterval_milliseconds"));
		} catch (NumberFormatException e) {
			throw new RuntimeException("Not able to parse value : "
					+ envProperties.getProperty("pollinginterval_milliseconds") + " in to Long");
		}
	}
	
	public static String getFirefoxDriver() {
		return envProperties.getProperty("driver.firefox");
	}

	public static String getChromeDriver() {
		return envProperties.getProperty("driver.chrome");
	}

	public static String getFirefoxDriverPath() {
		return envProperties.getProperty("driverpath.firefox");
	}

	public static String getChromeDriverPath() {
		return envProperties.getProperty("driverpath.chrome");
	}

	public static String getExtentReportDir() {
		return envProperties.getProperty("extentreport.dir");
	}

	public static String getFailedScreenshotDir() {
		return envProperties.getProperty("failedscreenshot.dir");
	}
	
}
