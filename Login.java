import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://www.rahulshettyacademy.com/loginpagePractise/");
		String userName = getUserName(driver);
		String password = getPassword(driver);
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector(".customradio:nth-child(2)")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));

		driver.findElement(By.id("okayBtn")).click();
		WebElement options = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select dropdown = new Select(options);
		dropdown.selectByIndex(0);
		//System.out.println(dropdown.getFirstSelectedOption().getText());
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("terms")));

		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Checkout")));

		List<WebElement> products = driver.findElements(By.cssSelector(".card-footer .btn-info"));

		for (int i = 0; i < products.size(); i++)

		{

			products.get(i).click();

		}

		driver.findElement(By.partialLinkText("Checkout")).click();

		driver.findElement(By.cssSelector("button.btn.btn-success")).click();

		driver.findElement(By.id("country")).sendKeys("india");

		driver.findElement(By.xpath("//a[contains(text(),'India')]")).click();
		
		driver.findElement(By.cssSelector("div.checkbox.checkbox-primary label")).click();
		
		driver.findElement(By.xpath("//input[@value='Purchase']")).click();
		
		String verifyText = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible")).getText();
		System.out.println("String....."+verifyText);

		Assert.assertEquals(verifyText ,  "×\r\nSuccess! Thank you! Your order will be delivered in next few weeks :-).");
	}

	public static String getUserName(WebDriver driver) {
		driver.get("https://www.rahulshettyacademy.com/loginpagePractise/");
		String userNameText = driver.findElement(By.xpath("//form[@id='login-form']/div[7]/p")).getText();

		// (username is rahulshettyacademy and Password is learning)
		String[] userNameArray = userNameText.split("[ ()]+");
		String userName = userNameText.split("[ ()]+")[3];
		System.out.println("Number of Words:  " + userNameArray.length);
		for (int i = 0; i < userNameArray.length; i++)
			System.out.println("userNameArray[" + i + "] = " + userNameArray[i]);

		return userName;
	}

	public static String getPassword(WebDriver driver) {
		driver.get("https://www.rahulshettyacademy.com/loginpagePractise/");
		String passwordText = driver.findElement(By.xpath("//form[@id='login-form']/div[7]/p")).getText();

		// (username is rahulshettyacademy and Password is learning)
		String[] passwordArray = passwordText.split("[ ()]+");
		String password = passwordText.split("[ ()]+")[7];
		System.out.println("Number of Words:  " + passwordArray.length);
		for (int i = 0; i < passwordArray.length; i++)
			System.out.println("passwordArray[" + i + "] = " + passwordArray[i]);

		return password;
	}

}
