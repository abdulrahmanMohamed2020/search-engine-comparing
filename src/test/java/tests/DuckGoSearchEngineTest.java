package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.DuckGoSearchPage;
import utils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class DuckGoSearchEngineTest extends BaseTest{

    // Object Declaration
    private DuckGoSearchPage duckGoSearchPage;
    private static final String DUCK_GO_URL = "https://duckduckgo.com/";
    private static final String SEARCH_KEYWORD = "Facebook";
    private Map<String,String> duckGoSearchResultsMap = new HashMap<>();

    @Test(description = "Verify The Title Contains The Keyword On Duck Duck Go Search Engine")
    public void verifyTheTitleContainsTheKeyword() {
        getDriver().manage().deleteAllCookies();

        // Navigate to the search page
        System.out.println("Navigate to this URL: "+DUCK_GO_URL);
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

        // Checking if each element title contains the search keyword
        duckGoSearchResultsMap.values().forEach( e -> assertTrue(e.contains("facebook"),
                "There is one Title doesn't contain the search keyword"));
    }

    private Map<String,String> getSearchResultsMap(List<WebElement> searchResultsElements) {
        // Saving the search results (URL and Title) in Map
        Map<String,String> searchResultsMap = new HashMap<>();

        // Filling this Map by the first 10 results elements
        for(int counter=0 ; counter<10 ;counter++){
            searchResultsMap.put(searchResultsElements.get(counter).getAttribute("href"),
                    searchResultsElements.get(counter).getText().toLowerCase());
        }
        System.out.println("The Search Results as Map Data: "+searchResultsMap);
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

}
