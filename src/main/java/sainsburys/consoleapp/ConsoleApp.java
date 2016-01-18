package sainsburys.consoleapp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sainsburys.entity.RipeFruits;
import sainsburys.extractor.RipeFruitExtractor;
import sainsburys.scraper.FirefoxScraperImpl;
import sainsburys.scraper.Scraper;

public class ConsoleApp {
    private final static String PAGE_URL = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";

    private Scraper            scraper            = null;
    private RipeFruitExtractor ripeFruitExtractor = null;

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();

        app.configure();
        System.out.println(app.run());
    }


    private void configure() {
        scraper = new FirefoxScraperImpl();
        scraper.configureDriverForUrl(PAGE_URL);

        ripeFruitExtractor = new RipeFruitExtractor(scraper);
    }


    private String run() {
        RipeFruits result = ripeFruitExtractor.extract();

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        return gson.toJson(result);
    }
}
