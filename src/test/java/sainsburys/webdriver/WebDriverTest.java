package sainsburys.webdriver;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;

import sainsburys.exceptions.FirefoxDriverException;

public class WebDriverTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public final void testConfigureFirefoxWebDriverValidUrl() {	
		final String PAGE_URL = "http://www.sainsburys.co.uk";
		
		WebDriver firefox = new FirefoxWebDriver().configureWebDriver(PAGE_URL);
		
		assertNotNull(firefox);
		
		firefox.close();
		firefox.quit();
	}

	
	@Test
	public final void testConfigureFirefoxWebDriverEmptyUrl() {
		thrown.expect(FirefoxDriverException.class);
		
		new FirefoxWebDriver().configureWebDriver("");
	}
	
	
	@Test
	public final void testConfigureFirefoxWebDriverMalformedUrl() {
		thrown.expect(FirefoxDriverException.class);
		
		new FirefoxWebDriver().configureWebDriver("http:\\www.sainsburys.co.uk");
	}
}
