package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	public WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@alt='profile']")
	private WebElement profile;
	@FindBy(xpath = "//button[normalize-space()='Logout']")
	private WebElement logOut;
	public void clickProfile() {
		profile.click();
	}
	public void clickLogOut() {
		logOut.click();
	}
}
