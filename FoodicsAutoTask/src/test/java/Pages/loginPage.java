package Pages;
import java.time.Duration;
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

public class loginPage {
	WebDriver driver = null;
	
	By signInButton = By.xpath("(//a[@data-nav-role = 'signin'])[1]");
	By emailField = By.id("ap_email");
	By continueButton = By.xpath("//span[@id = 'continue']");
	By passwordField = By.id("ap_password");
	By credenSignInButton = By.xpath("(//span[@id = 'auth-signin-button'])[1]");


	public loginPage(WebDriver driver) {
	this.driver = driver;
	
	}
	
	public void clickonSignInPage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
		driver.findElement(signInButton).click();
	}
	
	public void signIntoAmazon(String Mail, String Passowrd) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
		
		driver.findElement(emailField).sendKeys(Mail);
		driver.findElement(continueButton).click();
		driver.findElement(passwordField).sendKeys(Passowrd);
		driver.findElement(credenSignInButton).click();
	}

}

