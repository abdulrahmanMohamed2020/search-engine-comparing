package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DuckGoSearchPage extends BasePage{

    private static final By searchText = By.name("q");
    private static final By searchResults = By.cssSelector("[data-testid='result-title-a']");

    public DuckGoSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyword(String keyword){
        findElement(searchText).clear();
        findElement(searchText).sendKeys(keyword);
    }

    public void clickEnterKey(){
        actionClickByKeys(searchText);
    }
    public List<WebElement> getSearchResults(){
        return findElements(searchResults);
    }
}
