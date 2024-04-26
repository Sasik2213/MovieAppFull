package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PopularPage;
import pages.SearchPage;

public class Index {

	public void checkHeadingText(WebElement element, String texString) {
		Assert.assertEquals(element.getText().toString(), texString, "Please Verify Once Properly!!");
	}

	public void checkUserNameLabel(WebElement element, String teString) {
		Assert.assertEquals(element.getText().toString(), teString, "Please Verify Once Properly!!");
	}

	public void checkPasswordLabel(WebElement element, String teString) {
		Assert.assertEquals(element.getText().toString(), teString, "Please Verify Once Properly!!");
	}

	public void checkErrorMessageOccured(WebElement element) {
		System.out.println(element.getText().toString());
	}

	public WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public PopularPage popularPage;
	public SearchPage searchPage;
	public AccountPage accountPage;

	@BeforeTest
	public void start() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://qamoviesapp.ccbp.tech");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

	}

	@AfterTest
	public void end() {
		driver.close();
	}

	@Test
	public void loginPageUI() throws Exception {

		loginPage = new LoginPage(driver);
		loginPage.checkLogo(loginPage.siteLogo);
		checkHeadingText(loginPage.loginText, "Login");
		checkUserNameLabel(loginPage.userNameText, "USERNAME");
		checkPasswordLabel(loginPage.passwordText, "PASSWORD");
		loginPage.checkLoginBtn(loginPage.loginText);
	}

	@Test
	public void loginPageFunctions() throws Exception {
		loginPage = new LoginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn)).click();
		checkErrorMessageOccured(driver.findElement(By.xpath("//p[@class='error-message']")));
		loginPage.loginEntries("Hello", "Rahul@2021");
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn)).click();
		checkErrorMessageOccured(driver.findElement(By.xpath("//p[@class='error-message']")));
		loginPage.loginEntries("rahul", "rahul@2021");
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn)).click();
	}

	@Test
	public void homePageTest() throws Exception {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		loginPage.loginEntries("rahul", "rahul@2021");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn)).click();
		homePage.checkElementHead(homePage.homeBtn);
		homePage.checkAnyElementFindOrNot(driver.findElement(By.xpath("//h1[normalize-space()='Trending Now']")));
		List<WebElement> details = driver.findElements(By.xpath("//div[@class='home-movie-details-container']"));
		for (WebElement webElement : details) {
			System.out.println(webElement.getText());
		}
		homePage.checkAnyElementFindOrNot(driver.findElement(By.xpath("//div[@class='home-movie-details-container']")));
		homePage.isElementDisplayed(homePage.playBtn);
		homePage.isElementDisplayed(homePage.contactUs);
	}

	@Test
	public void HeaderSectionUI() throws Exception {

		loginPage = new LoginPage(driver);
		loginPage.loginEntries("rahul", "rahul@2021");
		driver.findElement(By.xpath("//nav[@class='nav-header ']")).isDisplayed();
		driver.findElement(By.xpath("//img[@alt='website logo']")).isDisplayed();
		List<WebElement> detailsView = driver.findElements(By.xpath("//nav[@class='nav-header ']"));
		for (WebElement webElement : detailsView) {
			System.out.println(webElement.getText());
		}
	}

	@Test
	public void HeaderSectionFunctionalities() throws Exception {

		loginPage = new LoginPage(driver);
		loginPage.loginEntries("rahul", "rahul@2021");
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.xpath("//a[normalize-space()='Popular']"))).build().perform();
		driver.navigate().back();
		actions.click(driver.findElement(By.xpath("//img[@alt='profile']"))).build().perform();
		driver.navigate().back();
	}

	@Test
	public void PopularPageUI() throws Exception {

		loginPage = new LoginPage(driver);
		loginPage.loginEntries("rahul", "rahul@2021");
		popularPage = new PopularPage(driver);
		popularPage.clickPopularLink();
		driver.findElement(By.xpath("//img[@alt='Venom']")).isDisplayed();

	}

	@Test
	public void searchPageUI() throws Exception {

		loginPage = new LoginPage(driver);
		loginPage.loginEntries("rahul", "rahul@2021");
		searchPage = new SearchPage(driver);
		searchPage.enterTextInSearchBar("Venom");
		List<WebElement> testings = driver.findElements(By.xpath("//div[@class='home-search-container']"));
		System.out.println(testings.size());

	}

	@Test
	public void AccountPageUI() throws Exception {

		loginPage = new LoginPage(driver);
		accountPage = new AccountPage(driver);
		loginPage.loginEntries("rahul", "rahul@2021");
		accountPage.clickProfile();
		accountPage.clickLogOut();
	}
}
