package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopularPage {
	
	public WebDriver driver;
	
	public PopularPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Popular']")
	private WebElement popularLink;
	public void clickPopularLink() {
		popularLink.click();
	}

}
