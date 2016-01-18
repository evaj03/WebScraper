package sainsburys.extractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sainsburys.entity.RipeFruit;
import sainsburys.entity.RipeFruits;
import sainsburys.exceptions.RipeFruitExtractionException;
import sainsburys.scraper.FirefoxScraperImpl;
import sainsburys.scraper.Scraper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RipeFruitExtractorTest {

	Scraper            firefoxScraper     = null;
	RipeFruitExtractor ripeFruitExtractor = null;
	
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	
	@Before
	public void setUp() throws Exception {
		firefoxScraper = new FirefoxScraperImpl();
	}

	@After
	public void tearDown() throws Exception {
		if (ripeFruitExtractor != null) {
			ripeFruitExtractor = null;
		}
	}

	
	@Test
	public final void testRipeFruitExtractorWithScraper() {	
		ripeFruitExtractor = new RipeFruitExtractor(firefoxScraper);
		
		assertNotNull(ripeFruitExtractor);
	}
	
	
	@Test
	public final void testRipeFruitExtractorWithNullScraper() {
		thrown.expect(RipeFruitExtractionException.class);
		
		ripeFruitExtractor = new RipeFruitExtractor(null);
	}

	
	@Test
	public final void testExtractReturningEmptyRipeFruits() {
		// Create mock
		RipeFruitExtractor mockExtractor = mock(RipeFruitExtractor.class);
		
		// Define return value for method extract()
		when(mockExtractor.extract()).thenReturn(new RipeFruits());
		
		// Expect zero products
		assertEquals(mockExtractor.extract().getRipeFruitProducts().size(), 0);
		// Expect zero total unit price
		assertEquals(mockExtractor.extract().calculateUnitPriceTotal(), 0.0, 0.0);
	}

	
	@Test
	public final void testExtractReturningTenRipeFruits() {
		final int    MAX_PRODUCTS     = 10;
		final double TOTAL_UNIT_PRICE = 73.56;

		RipeFruits      ripeFruits    = new RipeFruits();
		List<RipeFruit> ripeFruitList = new ArrayList<RipeFruit>();
		
		RipeFruits spyRipeFruits = spy(ripeFruits);
		List       spyList       = spy(ripeFruitList);
		
		stub(spyRipeFruits.getRipeFruitProducts()).toReturn(spyList);
		stub(spyRipeFruits.calculateUnitPriceTotal()).toReturn(TOTAL_UNIT_PRICE);
		stub(spyList.size()).toReturn(MAX_PRODUCTS);
		
		// Create mocks
		RipeFruitExtractor mockExtractor = mock(RipeFruitExtractor.class);
		
		// Define return values
		when(mockExtractor.extract()).thenReturn(spyRipeFruits);
		
		// Expect MAX_PRODUCTS products
		assertEquals(mockExtractor.extract().getRipeFruitProducts().size(), MAX_PRODUCTS);
		// Expect TOTAL_UNIT_PRICE total unit price
		assertEquals(mockExtractor.extract().calculateUnitPriceTotal(), TOTAL_UNIT_PRICE, 0.0);
	}
	
	
	@Test
	public final void testTotalProductsExtracted() {
		final int MAX_PRODUCTS = 23;
		
		// Create mock
		RipeFruitExtractor mockExtractor = mock(RipeFruitExtractor.class);
				
		// Define return value for method extract()
		when(mockExtractor.totalProductsExtracted()).thenReturn(MAX_PRODUCTS);
				
		// Expect MAX_PRODUCTS products
		assertEquals(mockExtractor.totalProductsExtracted(), MAX_PRODUCTS);
	}

}
