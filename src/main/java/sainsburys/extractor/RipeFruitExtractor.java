package sainsburys.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sainsburys.entity.RipeFruit;
import sainsburys.entity.RipeFruits;
import sainsburys.exceptions.RipeFruitExtractionException;
import sainsburys.scraper.Scraper;

import java.util.List;
import java.util.logging.Logger;

public class RipeFruitExtractor {
	private static final Logger LOGGER                       = Logger.getLogger(RipeFruitExtractor.class.getName());
	private static final String PRODUCT_HTML_CLASSNAME       = "product";
	private static final String PRODUCT_INNER_HTML_CLASSNAME = "productInner";
	private static final String ANCHOR_HTML_CSS_SELECTOR     = "h3 > a";
	private static final String ANCHOR_HTML_HREF_ATTRIBUTE   = "href";
	private static final String DESCRIPTION_HTML_CLASSNAME   = "productText";
	private static final String KILOBYTE_SUFFIX              = "kb";
	private static final String PRICE_HTML_CLASSNAME         = "pricePerUnit";
	private static final String PRICE_SUFFIX                 = "/unit";
	
	private static final int KILOBYTE = 1024;
	
	
	private final Scraper    scraper;
	private final RipeFruits ripeFruits;
	
	
	public RipeFruitExtractor(final Scraper scraper) throws RipeFruitExtractionException {
		this.scraper    = scraper;
		this.ripeFruits = new RipeFruits();
		validate();
	}
	
	
	/**
     * Returns RipeFruits entity containing product details found on page.
     *
     * @return  RipeFruits
     */
    public RipeFruits extract() {
    	extractRipeFruit(getProducts());
    	scraper.close();
    	
    	return ripeFruits;
    }


    /**
     * Total number of products extract from URL
     *
     * @return  int
     */
    public int totalProductsExtracted() {
    	return ripeFruits.getRipeFruitProducts().size();
    }
    
    
    // NON-API
    
    /**
     * Check configuration is valid.
     */
    private void validate() {
   		validateScraper();
    }
    
    
    /**
     * Check Scraper object is not null.
     */
    private void validateScraper() {
   		if (scraper == null) {
   			String msg = "Error Scraper is null.";
			
			LOGGER.severe(msg);
			throw new RipeFruitExtractionException(msg);
		}
    }
    
    
    /**
     * Get a list of products based on the class name from HTML source
     */
    private List<WebElement> getProducts() {
    	return scraper.getElementsByClassName(PRODUCT_HTML_CLASSNAME);
    }


    /**
     * For each product extract required information.
     */
    private void extractRipeFruit(final List<WebElement> products) {
    	
    	for (WebElement product : products) {
    		// Locate element matching class name (productInner) for product
    		// All further information required can be located from within this WebElement.
    		WebElement productInner = product.findElement(By.className(PRODUCT_INNER_HTML_CLASSNAME));
    	
    		// Locate anchor tag in order to extract the product and hyperlink information
    		WebElement anchor = productInner.findElement(By.cssSelector(ANCHOR_HTML_CSS_SELECTOR));

    		String pageSource = scraper.getPageSourceForLink(anchor.getAttribute(ANCHOR_HTML_HREF_ATTRIBUTE));
   		
    		ripeFruits.getRipeFruitProducts().add(
    				new RipeFruit(
    						anchor.getText(),
    						extractAndFormatHyperlinkSize(pageSource),
    						extractUnitPrice(productInner),
    						extractDescription(pageSource)));
    	}
    	
    	ripeFruits.calculateUnitPriceTotal();
    }
    
    
    private double extractUnitPrice(final WebElement productInner) {
		String productUnitPrice = productInner
				.findElement(By.className(PRICE_HTML_CLASSNAME))
				.getText();

        // Substring to remove leading £ sign and remove trailing unit descriptor
		return Double.parseDouble(productUnitPrice.substring(1, productUnitPrice.indexOf(PRICE_SUFFIX)));
	}
    
    
    private String extractAndFormatHyperlinkSize(final String pageSource) {
    	return Integer.toString(pageSource.getBytes().length / KILOBYTE) + KILOBYTE_SUFFIX;
    }
    
    
    private String extractDescription(final String pageSource) {
    	Document doc = Jsoup.parse(pageSource);
    	
    	return doc == null ? "" : doc.getElementsByClass(DESCRIPTION_HTML_CLASSNAME).first().text();
    }
}
