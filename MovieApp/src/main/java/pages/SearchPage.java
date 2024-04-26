package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver driver;
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@class='search-empty-button']")
	private WebElement searchButton;
	@FindBy(xpath = "//input[@id='search']")
	private WebElement searchBar;
	@FindBy(xpath = "//button[@class='search-button']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//ul[@class='search-movies-container']")
	private WebElement movieContainer;
	
	public void clickSearchButton() {
		searchButton.click();
	}
	public void enterTextInSearchBar(String text) {
		searchButton.click();
		searchBar.sendKeys(text);
		searchBtn.click();
	}
	
}
