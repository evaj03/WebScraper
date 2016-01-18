package sainsburys.scraper;

import java.util.List;

import org.openqa.selenium.WebElement;

import sainsburys.exceptions.FirefoxDriverException;

public interface Scraper {
	void configureDriverForUrl(final String url) throws FirefoxDriverException;
	
	void close();
	
	List<WebElement> getElementsByClassName(final String className);
	
	WebElement getElementByClassName(final String className);
	
	WebElement getElementByCssSelector(final String cssSelector);
	
	String getPageSourceForLink(final String hyperlink);
}
