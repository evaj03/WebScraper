package sainsburys.webdriver;


import org.openqa.selenium.WebDriver;

import sainsburys.exceptions.FirefoxDriverException;


public interface Driver {
	WebDriver configureWebDriver(final String url) throws FirefoxDriverException;
}
