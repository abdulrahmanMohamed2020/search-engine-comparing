package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StartPageSearchEnginePage extends BasePage{
    private final By searchText = By.id("q");
    private final By searchResults = By.xpath("//h3//parent::a");

    public StartPageSearchEnginePage(WebDriver driver) {
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
