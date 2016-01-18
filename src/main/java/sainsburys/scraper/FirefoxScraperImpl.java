package sainsburys.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sainsburys.exceptions.FirefoxDriverException;
import sainsburys.webdriver.Driver;
import sainsburys.webdriver.FirefoxWebDriver;

import java.util.List;
import java.util.logging.Logger;

public class FirefoxScraperImpl implements Scraper{
	private static final Logger LOGGER           = Logger.getLogger(FirefoxScraperImpl.class.getName());
	private static final int    MAX_WAIT_SECONDS = 10;

	private Driver    firefox;
	private WebDriver driver;
	
	{
		firefox = new FirefoxWebDriver();
	}
	
	
	/**
	 * Sets and configures driver to use URL if valid.
	 * Throws FirefoxScraperException if URL is malformed or Firefox is not installed.
	 * 
	 * @param	url	URL string to web page.
	 * @throws	FirefoxDriverException
	 */
	@Override
	public void configureDriverForUrl(final String url) throws FirefoxDriverException {
		driver = firefox.configureWebDriver(url);
	}
	
	
	
	/**
	 * Closes driver connection and cleans up any attached browsers.
	 */
	@Override
	public void close() {
		quitWebDriver();
	}

	
	@Override
	public List<WebElement> getElementsByClassName(final String className) {
		//return (new WebDriverWait(driver, MAX_WAIT_SECONDS)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
		return driver.findElements(By.className(className));
	}


	@Override
	public WebElement getElementByClassName(final String className) {
		//return (new WebDriverWait(driver, MAX_WAIT_SECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
		return driver.findElement(By.className(className));
	}


	@Override
	public WebElement getElementByCssSelector(final String cssSelector) {
		//return (new WebDriverWait(driver, MAX_WAIT_SECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
		return driver.findElement(By.cssSelector(cssSelector));
	}
	
	
	/**
	 * Navigates to hyperlink and extracts page source.
	 * 
	 * @param	hyperlink	URL to navigate to
	 * @return	String		page source of URL
	 * @throws	FirefoxDriverException
	 */
	@Override
	public String getPageSourceForLink(final String hyperlink) throws FirefoxDriverException{
		WebDriver linkDriver = firefox.configureWebDriver(hyperlink);
		
		String results = linkDriver.getPageSource();
		
		linkDriver.close();
		
		return results;
	}
	
	
	// NON-API
	private void quitWebDriver() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
