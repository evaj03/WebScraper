# WebScraper
WebScraper is a Java Console application that scrapes specific information
from a pre-defined URL on the Sainsbury's website.

## Dependencies

### Application
* [Mozilla Firefox Browser](https://www.mozilla.org/en-GB/firefox/new/) - See Information section below.
* [selenium-java](http://www.seleniumhq.org/download/maven.jsp) - Selenium WebDriver, specifically the FirefoxDriver.
* [jsoup](http://jsoup.org/) - Used for parsing and extracting information from DOM Document.
* [gson](https://github.com/google/gson) - Used to generate JSON formatted output.

### Test
* [junit](http://junit.org) - Framework used to create and run tests.
* [mockito-all](http://www.mockito.org) - Mocking library.

## Information
When originally tackling this task my initial inclination was to use `JSoup`, but this approach does not work as `JSoup` is a HTML parser and cannot handle the `JavaScript` on the provided web URL.  I then looked at `HtmlUnit` to try and fake a browser connection, and even with the `JavaScript` option enabled I could not get a clean copy of the underlying DOM Document with all of the required data.  After googling for a solution I have ended up using `Selenium`.  At first I tried to use the `HtmlUnitDriver` in order to provide a browserless connection, but again I could not get the desired result.  Eventually I resorted to the `FirefoxDriver` which I feel this is a sub-optimal solution due to its enforced dependency on Mozilla Firefox Browser being installed.
In light of this I would appreciate any feedback as to why a browser based solution works in terms of getting the underlying source of the web URL but a browserless one does not.  I fear I am missing something obvious!

## Requirements
* [Java 7 JDK/JRE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/) - For building and unit test execution.

## Running
After downloading project, navigate to root directory of project (e.g. C:\WebScraper):
### Unit Tests
WebScraper is built using Maven, so to execute the unit tests from the command line you must have Maven installed.

```sh
mvn test
```

### Application
To run the application using the pre-built `.jar` file (which can be found under Releases on the GitHub project.):
```sh
java -jar .\web-scraper-jar-with-dependencies.jar
```
To build the application from source, see next section.

## Building
If you require a clean build of the application then the following instructions should help:
From the root of the project enter the following:
```sh
mvn clean package
```
This will remove the target directory, re-compile all code and package into two `.jar` files:
* web-scraper-jar-with-dependencies.jar
* web-scraper.jar

To run from the command line use the `with-dependencies.jar` version.