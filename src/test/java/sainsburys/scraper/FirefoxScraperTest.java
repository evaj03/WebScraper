package sainsburys.scraper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebElement;
import sainsburys.exceptions.FirefoxDriverException;
import sainsburys.extractor.RipeFruitExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FirefoxScraperTest {
	private Scraper webPageScraper = null;
	
		
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	
	@Before
	public void startUp() {
		webPageScraper = new FirefoxScraperImpl();
	}


	@Test
	public void testConfigurationForEmptyUrlThrowsException() {
		thrown.expect(FirefoxDriverException.class);

		webPageScraper.configureDriverForUrl("");

		RipeFruitExtractor ripeFruitExtractor = new RipeFruitExtractor(webPageScraper);

    	ripeFruitExtractor.extract();
	}


	@Test
    public void testConfigurationForMalformedUrlThrowsException() {
    	thrown.expect(FirefoxDriverException.class);

    	webPageScraper.configureDriverForUrl("httttp://www.sainsburys.co.uk");
        RipeFruitExtractor ripeFruitExtractor = new RipeFruitExtractor(webPageScraper);

        ripeFruitExtractor.extract();
    }


    @Test
    public void testGetElementsByClassName() {
		final int MAX_ELEMENTS = 6;

		// Create stub for returned list of WebElements.
		List<WebElement> elements = new ArrayList<WebElement>();

		List spyList = spy(elements);

		stub(spyList.size()).toReturn(MAX_ELEMENTS);

		// Create mock
		Scraper mockScraper = mock(FirefoxScraperImpl.class);

		// Define return value for method extract()
		when(mockScraper.getElementsByClassName("container")).thenReturn(spyList);


		mockScraper.configureDriverForUrl("http://www.sainsburys.co.uk");

		List<WebElement> result = mockScraper.getElementsByClassName("container");

		assertEquals(result.size(), MAX_ELEMENTS);

		mockScraper.close();
    }


	@Test
	public void testGetElementByClassName() {
		final String ELEMENT_TEXT = "Sainsbury";

		// Create mock for returned WebElement.
		WebElement mockElement = mock(WebElement.class);
		when(mockElement.getText()).thenReturn(ELEMENT_TEXT);

		// Create mock Scraper
		Scraper mockScraper = mock(FirefoxScraperImpl.class);
		when(mockScraper.getElementByClassName("title")).thenReturn(mockElement);


		mockScraper.configureDriverForUrl("http://www.sainsburys.co.uk");

		WebElement result = mockScraper.getElementByClassName("title");

		assertEquals(result.getText(), ELEMENT_TEXT);

		mockScraper.close();
	}


	@Test
	public void getElementByCssSelector() {
		final String ELEMENT_TEXT = "Sainsbury";

		// Create mock for returned WebElement.
		WebElement mockElement = mock(WebElement.class);
		when(mockElement.getText()).thenReturn(ELEMENT_TEXT);

		// Create mock Scraper
		Scraper mockScraper = mock(FirefoxScraperImpl.class);
		when(mockScraper.getElementByCssSelector("title")).thenReturn(mockElement);


		mockScraper.configureDriverForUrl("http://www.sainsburys.co.uk");

		WebElement result = mockScraper.getElementByCssSelector("title");

		assertEquals(result.getText(), ELEMENT_TEXT);

		mockScraper.close();
	}


	@Test
	public void getPageSourceForLink() {
		final String ELEMENT_TEXT = "Sainsbury";
		final String HYPERLINK    = "hyperlink";

		// Create mock Scraper
		Scraper mockScraper = mock(FirefoxScraperImpl.class);
		when(mockScraper.getPageSourceForLink(HYPERLINK)).thenReturn(ELEMENT_TEXT);


		mockScraper.configureDriverForUrl("http://www.sainsburys.co.uk");

		String result = mockScraper.getPageSourceForLink(HYPERLINK);

		assertEquals(result, ELEMENT_TEXT);

		mockScraper.close();
	}
}
