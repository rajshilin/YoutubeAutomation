package rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class YoutubeAutomation {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.youtube.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement searchBox = driver.findElement(By.xpath("//input[@id=\"search\"]"));
		searchBox.sendKeys("goldmines");
		searchBox.submit();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ytd-video-renderer")));

		// Locate and click on the third video from the search results
		WebElement thirdVideo = driver.findElement(By.xpath("//ytd-video-renderer[3]//a[@id='thumbnail']"));
		thirdVideo.click();

		// Wait for the video to start playing
		WebElement videoPlayer = driver.findElement(By.tagName("video"));
		WebDriverWait videoWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		videoWait.until(ExpectedConditions.attributeContains(videoPlayer, "src", "blob"));

		// Pause the video after 10 seconds
		try {
			Thread.sleep(10000); // Wait for 10 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(videoPlayer).click().build().perform();

		// Resume the video
		try {
			Thread.sleep(2000); // Wait for 2 seconds before resuming
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(videoPlayer).click().build().perform();
	}

}
