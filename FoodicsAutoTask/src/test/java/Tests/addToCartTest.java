package Tests;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import Pages.addToCartPage;
import Pages.loginPage;

public class addToCartTest {
	
private static WebDriver driver;
	

	@BeforeTest	
		public void setUp() throws Exception {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.get("https://www.amazon.eg/-/en/");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			loginPage login_Page = new loginPage(driver);
			
			login_Page.clickonSignInPage();
			login_Page.signIntoAmazon("Add your Amazon mail Here", "Add Amazon Password Here");
			
			}
	
	@Test
	public static void AddItemsToCart() throws InterruptedException {
		addToCartPage Add_Cart = new addToCartPage(driver);
		
		String Cateogry = "Video Games";
		String subCategory = "All Video Games";
		String Condition = "New";
		String desiredSorting = "Price: High to Low";
		int targetItemPrice = 14999;
	
		Add_Cart.openAllCategories();
		Add_Cart.seeAllCategories();
		
		Add_Cart.selectCategory(Cateogry);
		Add_Cart.selectSubCategory(subCategory);
		
		Add_Cart.chooseFreeShipingFilter();
		Add_Cart.chooseConditionType(Condition);
		
		Add_Cart.selectSortOption(desiredSorting);
	
		Add_Cart.processProducts(targetItemPrice);
		Add_Cart.ClickAddItemToCart();
		Add_Cart.goToBasket();
		
		Add_Cart.ItemsDisplayed();
		Add_Cart.ProceedCheckOut();
		
		Add_Cart.CODPaymentMethod();
		Add_Cart.ignoreOffer();
		
		Add_Cart.verifyTotalPrice();
	}
	
	@AfterTest
    public void tearDown()
    {
       driver.quit();
  
    }
}
