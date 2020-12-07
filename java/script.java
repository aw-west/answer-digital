

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;

public class script {

	public static String login(WebDriver driver, String username, String password) {
		driver.get("http://the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Form Authentication")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.tagName("button")).click();
		return driver.findElement(By.id("flash")).getText();
	}

	public static void sleep(long time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Interrupted while Sleeping");
		}
	}

	public static void main(String[] args) {

		// Initialise the web driver
		WebDriver driver = new FirefoxDriver();
		// WebDriver driver = new ChromeDriver();


		// Test Case 1
		String correct_username = "tomsmith";
		String correct_password = "SuperSecretPassword!";
		String wrong_username = "andrewwest";
		String wrong_password = "password1234";

		// Scenario 1
		// "Try to login with correct username and wrong password and assert login validation."
		String out11 = login(driver, correct_username, wrong_password);
		// Out:  Your password is invalid! \n ×

		// Scenario 2
		// "Try to login with wrong username and correct password and assert login validation."
		String out12 = login(driver, wrong_username, correct_password);
		// Out:  Your username is invalid! \n ×

		// Scenario 3
		// "Try to login with correct username and correct password and then logout."
		String out13 = login(driver, correct_username, correct_password);
		driver.findElement(By.linkText("Logout")).click();
		// Out:  You logged into a secure area! \n ×


		// Test Case 2
		// "To perform Test case 2, please click on **Infinite scroll on Menu**
		// Test case 2: scroll to the bottom of the page twice and scroll back to the top of the page and assert "Infinite Scroll" text"
		driver.get("http://the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Infinite Scroll")).click();  sleep(1);
		driver.findElement(By.tagName("body")).sendKeys(Keys.END);  sleep(1);
		driver.findElement(By.tagName("body")).sendKeys(Keys.END);  sleep(1);
		driver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
		String out2 = driver.findElement(By.className("jscroll-inner")).getText();
		// Out:
		// \n Temmpore et nam...
		// \n Architecto sit...
		// \n Dolores delectus...
		// \n Et accusamus...


		// Test Case 3
		// "To perform Test case 3, please click on **Key presses on Menu**
		// Test case 3: Click on 4 keys and assert the text displayed after every key press"
		driver.get("http://the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Key Presses")).click();
		driver.findElement(By.tagName("body")).sendKeys("A");
		String out31 = driver.findElement(By.id("result")).getText();
		driver.findElement(By.tagName("body")).sendKeys("1");
		String out32 = driver.findElement(By.id("result")).getText();
		driver.findElement(By.tagName("body")).sendKeys(Keys.ALT);
		String out33 = driver.findElement(By.id("result")).getText();
		driver.findElement(By.tagName("body")).sendKeys(Keys.F9);
		String out34 = driver.findElement(By.id("result")).getText();
		// Out:
		// You entered: A
		// You entered: 1
		// You entered: ALT
		// You entered: F9


		// End
		driver.quit();

		// Print Outputs
		System.out.println( "\n\n~ ~ ~ Test Results ~ ~ ~\n" );
		System.out.println( "\n~ Test Case 1 ~" );
		System.out.println( out11 );
		System.out.println( out12 );
		System.out.println( out13 );
		System.out.println( "\n~ Test Case 2 ~" );
		System.out.println( out2 );
		System.out.println( "\n~ Test Case 3 ~" );
		System.out.println( out31 );
		System.out.println( out32 );
		System.out.println( out33 );
		System.out.println( out34 );

	}
}
