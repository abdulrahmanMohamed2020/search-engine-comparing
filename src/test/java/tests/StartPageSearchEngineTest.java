package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.DuckGoSearchPage;
import pages.StartPageSearchEnginePage;
import utils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class StartPageSearchEngineTest extends BaseTest{
    // Object Declaration
    private StartPageSearchEnginePage startPageSearchEnginePage;
    private DuckGoSearchPage duckGoSearchPage;
    private Map<String,String> duckGoSearchResultsMap = new HashMap<>();
    private Map<String,String> startPageSearchResultsMap = new HashMap<>();
    private static final String START_PAGE_URL = "https://www.startpage.com/";
    private static final String DUCK_GO_URL = "https://duckduckgo.com/";
    private static final String SEARCH_KEYWORD = "Facebook";

    @Test(description = "Verify The Title Contains The Keyword On Start Page Search Engine")
    public void verifyTheTitleContainsTheKeyword() {
        getDriver().manage().deleteAllCookies();

        // Navigate to the search page
        System.out.println("Navigate to this URL: "+START_PAGE_URL);
        getDriver().get((START_PAGE_URL));

        // Initiate the search page object
        startPageSearchEnginePage =  new StartPageSearchEnginePage(getDriver());

        // Type a specific keyword in the search input field
        System.out.println("Type this Keyword ("+SEARCH_KEYWORD+") in the search input filed");
        startPageSearchEnginePage.searchByKeyword(SEARCH_KEYWORD);

        // Pressing Enter Key after typing the search keyword
        System.out.println("Pressing Enter Key");
        startPageSearchEnginePage.clickEnterKey();

        // Getting the search results elements
        startPageSearchResultsMap = getSearchResultsMap(startPageSearchEnginePage.getSearchResults());

        // Printing the First 10 search elements (Title and URLs)
        printTheSearchResults(startPageSearchResultsMap);

        // Checking if each element title contains the search keyword
        startPageSearchResultsMap.values().forEach( e -> assertTrue(e.contains("facebook"),
                "There is one Title doesn't contain the search keyword"));
    }

    @Test(description = "Compare The Two Search Results")
    public void compareTheTwoSearchResults() {
        getDriver().manage().deleteAllCookies();

        // Helper method to do the whole actions needed on Start Page Search Engine
        prepareSearchResultsOnStartPage();
        System.out.println(Constant.SEPARATOR);

        // Helper method to do the whole actions needed on Duck Duck Go Search Engine
        prepareSearchResultsOnDuckGo();
        System.out.println(Constant.SEPARATOR);

        // Listing the Websites which found in both search engines
        printTheMostPopularItems();
    }

    private void prepareSearchResultsOnStartPage() {
        // Navigate to the search page
        System.out.println("Navigate to this URL: "+START_PAGE_URL);
        getDriver().get((START_PAGE_URL));

        // Initiate the search page object
        startPageSearchEnginePage =  new StartPageSearchEnginePage(getDriver());

        // Type a specific keyword in the search input field
        System.out.println("Type this Keyword ("+SEARCH_KEYWORD+") in the search input filed");
        startPageSearchEnginePage.searchByKeyword(SEARCH_KEYWORD);

        // Pressing Enter Key after typing the search keyword
        System.out.println("Pressing Enter Key");
        startPageSearchEnginePage.clickEnterKey();

        // Getting the search results elements
        startPageSearchResultsMap = getSearchResultsMap(startPageSearchEnginePage.getSearchResults());

        // Printing the First 10 search elements (Title and URLs)
        printTheSearchResults(startPageSearchResultsMap);
    }

    private void prepareSearchResultsOnDuckGo() {
        // Navigate to the search page
        System.out.println("Navigate to the second URL: "+DUCK_GO_URL);
        getDriver().get((DUCK_GO_URL));

        // Initiate the search page object
        duckGoSearchPage =  new DuckGoSearchPage(getDriver());

        // Type a specific keyword in the search input field
        System.out.println("Type this Keyword ("+SEARCH_KEYWORD+") in the search input filed");
        duckGoSearchPage.searchByKeyword(SEARCH_KEYWORD);

        // Pressing Enter Key after typing the search keyword
        System.out.println("Pressing Enter Key");
        duckGoSearchPage.clickEnterKey();

        // Getting the search results elements
        duckGoSearchResultsMap = getSearchResultsMap(duckGoSearchPage.getSearchResults());
        // Printing the First 10 search elements (Title and URLs)
        printTheSearchResults(duckGoSearchResultsMap);
    }

    private Map<String,String> getSearchResultsMap(List<WebElement> searchResultsElements) {
        // Saving the search results (URL and Title) in Map
        Map<String,String> searchResultsMap = new HashMap<>();

        // Filling this Map by the first 10 results elements
        for(int counter=0 ; counter<10 ;counter++){
            searchResultsMap.put(searchResultsElements.get(counter).getAttribute("href"),
                    searchResultsElements.get(counter).getText().toLowerCase());
        }
        return searchResultsMap;
    }

    private void printTheSearchResults(Map<String,String> searchResults) {
        // Wrapping on each element to print it
        searchResults.entrySet().forEach(attr->
        {
            System.out.println("Link : "+attr.getKey());
            System.out.println("Its Title : "+attr.getValue());

            // Checking if it contains the search keyword or not
            if(attr.getValue().contains("facebook")) {
                System.out.println(Constant.CONTAINS);
            } else {
                System.out.println(Constant.DOES_NOT_CONTAINS);
            }
        });
    }

    private void printTheMostPopularItems() {
        // Saving the website URL and Title in Map
        Map<String,String> mostPopular = new HashMap<>();

        // Wrapping on each URL and Title from first search engine and see if it's found in the second one and save it as popular
        startPageSearchResultsMap.entrySet().forEach(key ->
        {
            duckGoSearchResultsMap.keySet().forEach(key2 ->
            {
                if(key2.contains(key.getKey())) {
                    mostPopular.put(key.getKey(), key.getValue());
                }
            });
        });

        System.out.println("The Most Popular Items are:");
        mostPopular.entrySet().forEach(e->
        {
            System.out.println("The Link is : "+e.getKey());
            System.out.println("The Title is : "+e.getValue()+"\n\t\t#####################");
        });
    }

}
