package Pages;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class addToCartPage {
	WebDriver driver = null;
	
	By allButton = By.xpath("//a[@id = 'nav-hamburger-menu']");
	By hamburgerMenu = By.id("hmenu-content");
	By seeAllButton = By.xpath("//a[@class = 'hmenu-item hmenu-compressed-btn']");
	By filteringMenu = By.id("s-refinements");
	By freeShippingCheckBox = By.xpath("//input[@aria-labelledby = 'Free Shipping']/parent::label");
	By searchResultsDiv = By.xpath("//div[contains(@class, 's-main-slot s-result-list')]");
	By conditionTypeNew = By.xpath("//ul[contains(@aria-labelledby, 'condition-type')]//span[text() = 'New']");
	By sortByDropdown = By.id("s-result-sort-select");
	By addToCartButton = By.id("add-to-cart-button");
	By ignoreAdvertisment = By.id("attachSiNoCoverage");
	By goToBasket = By.xpath("(//a[contains(text(),'Go to basket')])[2]");
	By productTitle = By.id("productTitle");
	By productTitleinBasekt = By.className("a-truncate-cut");
	By basketActiveItems = By.xpath("//div[@data-name = 'Active Items']");
	By proceedCheckoutButton = By.xpath("//input[@data-feature-id = 'proceed-to-checkout-action']");
	By emptyCartMsg = By.xpath("//h1[contains(text(), 'Your Amazon Cart is empty')]");
	By CODRadioButton = By.xpath("//input[contains(@value, 'paymentMethod=COD')]");
	By paymentMethodButton = By.xpath("//span[contains(@class, 'compact-continue-action')]");
	By ignoreOfferButton = By.xpath("//button[contains(text(), 'Not at the moment')]");
	
	
	public addToCartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openAllCategories() {
		driver.findElement(allButton).click();
	}
	
	public void seeAllCategories() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(hamburgerMenu));
		driver.findElement(seeAllButton).click();
	}
	
	public void selectCategory(String categoryName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = '" + categoryName + "']")));
		driver.findElement(By.xpath("//div[text() = '" + categoryName + "']")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	public void selectSubCategory(String subCategoryName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = '" + subCategoryName + "']")));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		WebElement element = driver.findElement(By.xpath("//a[text() = '" + subCategoryName + "']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		
	}
	
	public void chooseFreeShipingFilter() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(filteringMenu));
		driver.findElement(freeShippingCheckBox).click();
	}
	
	public void chooseConditionType(String targetCondition) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsDiv));
		driver.findElement(By.xpath("//ul[contains(@aria-labelledby, 'condition-type')]//span[text() = '" + targetCondition + "']")).click();
	}
	
	public void selectSortOption(String optionText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));
        WebElement dropdownElement = driver.findElement(sortByDropdown);
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText(optionText);
        driver.navigate().refresh();
    }
	
	
	public void processProducts(int targetPrice) {
	    boolean itemsAddedOnCurrentPage;

	    do {
	        itemsAddedOnCurrentPage = false;

	        List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class = 'a-price-whole']"));

	        for (WebElement priceElement : priceElements) {
	            int price = Integer.parseInt(priceElement.getText().replace(",", ""));

	            if (price < targetPrice) {
	                try {
	                    WebElement titleElement = priceElement.findElement(By.xpath("/ancestor::div[@class = 'a-section a-spacing-small a-spacing-top-small']"));

	                    openInNewTab(titleElement);
	                    itemsAddedOnCurrentPage = true;
	                } catch (NoSuchElementException e) {
	                    System.out.println("Product title element not found for price: " + price);
	                }
	            }
	        }

	        if (itemsAddedOnCurrentPage) {
	            switchBetweenTabsAndAddToCart();
	        }

	        try {
	            WebElement nextPageButton = driver.findElement(By.className("s-pagination-next"));
	            if (!nextPageButton.getAttribute("class").contains("s-pagination-disabled")) {
	                nextPageButton.click();
	                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	            } else {
	                break;
	            }
	        } catch (NoSuchElementException e) {
	            break;
	        }
	    } while (true);
	}

	private void openInNewTab(WebElement element) {
	    Actions actions = new Actions(driver);

	    actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();

	    Set<String> handles = driver.getWindowHandles();
	    handles.remove(driver.getWindowHandle());
	    driver.switchTo().window(handles.iterator().next());
	}

	private void switchBetweenTabsAndAddToCart() {
	    Set<String> handles = driver.getWindowHandles();

	    for (String handle : handles) {
	        driver.switchTo().window(handle);
	    
	        ClickAddItemToCart();
	    }
	}
	
	public void ClickAddItemToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
		driver.findElement(addToCartButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ignoreAdvertisment));
		driver.findElement(ignoreAdvertisment).click();
	}
	
	public void goToBasket() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(goToBasket));
		driver.findElement(goToBasket).click();
	}
	
	public String retriveItemText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
		String itemName = driver.findElement(productTitle).getText();
		return itemName;
	}
	
	
	public void ItemsDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(proceedCheckoutButton));
		driver.findElement(basketActiveItems).isDisplayed();
	}
	
	public void ProceedCheckOut() {
		driver.findElement(proceedCheckoutButton).click();
	}
	
	public void CODPaymentMethod() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CODRadioButton));
		
		driver.findElement(CODRadioButton).click();
		driver.findElement(paymentMethodButton).click();
	}
	
	public void ignoreOffer() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ignoreOfferButton));
		WebElement element = driver.findElement(ignoreOfferButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
	
	
	private List<WebElement> getPriceElements() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[@class = 'a-text-right aok-nowrap a-nowrap'])[not(position() = 4)]")));
	    return driver.findElements(By.xpath("(//td[@class = 'a-text-right aok-nowrap a-nowrap'])[not(position() = 4)]"));
	}

	private WebElement getTotalPriceElement() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.a-color-price.a-size-medium.a-text-right.grand-total-price.aok-nowrap.a-text-bold.a-nowrap")));
	    return driver.findElement(By.cssSelector("td.a-color-price.a-size-medium.a-text-right.grand-total-price.aok-nowrap.a-text-bold.a-nowrap"));
	}

	public void verifyTotalPrice() {
	    WebElement totalPriceElement = getTotalPriceElement();
	    String totalText = totalPriceElement.getText().replaceAll("[^0-9-]", ""); 
	    int total = Integer.parseInt(totalText);

	    List<WebElement> priceElements = getPriceElements();

	    int sum = 0;

	    for (WebElement priceElement : priceElements) {
	        String priceText = priceElement.getText().replaceAll("[^0-9-]", "");
	        int price = Integer.parseInt(priceText);
	        sum += price;
	    }

	    Assert.assertTrue(sum == total);
	}
	
}
