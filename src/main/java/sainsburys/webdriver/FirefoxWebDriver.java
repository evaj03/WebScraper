package sainsburys.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import sainsburys.exceptions.FirefoxDriverException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class FirefoxWebDriver implements Driver {
	private static final Logger LOGGER                           = Logger.getLogger(FirefoxWebDriver.class.getName());
	private static final int    MAX_FIND_ELEMENT_TIMEOUT_SECONDS = 5;
	
	
	public WebDriver configureWebDriver(final String url) throws FirefoxDriverException {
		WebDriver driver = null;
		
		try {
			driver = new FirefoxDriver();
			configure(driver, url);
		} catch (FirefoxDriverException e) {
			if (driver != null) {
				driver.close();
				driver.quit();
			}
			throw e;
		}
		
		return driver;
	}
	
	
	// NON-API
    private void configure(final WebDriver driver, final String url) {
    	try {
    		validateUrl(url);
    		
    		// Set timeout when trying to find elements on page.
    		driver.manage().timeouts().implicitlyWait(MAX_FIND_ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    	
    		driver.get(url);
    	} catch (WebDriverException e) {
    		String msg = "Error whilst configuring Driver: " + e.toString();
			logAndThrowException(msg);
    	}
    }
    
    
    /**
     * Check if given URL is not malformed.
     */
    private void validateUrl(final String url) {
   		try {
			new URL(url);
		} catch (MalformedURLException e) {
			String msg = "Error whilst validating supplied URL: '" + url + "' :: " + e.toString();
			logAndThrowException(msg);
		}
    }


    /**
	 * Ensure exception message is written to log file prior to throwing exception.
	 * @param msg
	 */
    private void logAndThrowException(final String msg) {
		LOGGER.severe(msg);
		throw new FirefoxDriverException(msg);
    }
}
