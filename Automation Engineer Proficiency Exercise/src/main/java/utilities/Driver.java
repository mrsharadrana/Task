package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	public Properties properties;
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;

	public WebDriver browserLaunch() throws Exception {

		properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//Data//data.properties");
		properties.load(fis);

		String browserName = properties.getProperty("browser");

		String browserURL = properties.getProperty("url");

		try {

			if (browserName.contains("Google Chrome")) {

				ChromeOptions options = new ChromeOptions();

				if (browserName.contains("incognito"))
					options.addArguments("--incognito");

				if (browserName.contains("headless"))
					options.addArguments("--headless");

				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

				driver = WebDriverManager.chromedriver().capabilities(options).create();
			}

			else if (browserName.contains("Mozilla Firefox")) {

				FirefoxOptions options = new FirefoxOptions();

				if (browserName.contains("incognito"))
					options.addArguments("--private");

				if (browserName.contains("headless"))
					options.addArguments("--headless");

				driver = WebDriverManager.firefoxdriver().capabilities(options).create();
			}

			else if (browserName.contains("Microsoft Edge")) {

				EdgeOptions options = new EdgeOptions();

				if (browserName.contains("incognito"))
					options.addArguments("--inprivate");

				if (browserName.contains("headless"))
					options.addArguments("--headless");

				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

				driver = WebDriverManager.edgedriver().capabilities(options).create();
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return driver;
	}

	public void sendKeys(WebElement element, String fieldName, String keysToSend) {

		try {
			element.sendKeys(keysToSend);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].value='" + keysToSend + "'", element);
		}
		test.pass("<b><i>Typed in : " + fieldName + " successfully with value : " + keysToSend+"/b></i>",
				extentScreenshot());
	}

	public void click(WebElement element, String fieldName) {

		try {
			element.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		}
		test.pass("<b><i>Clicked on : " + fieldName + " successfully</b></i>", extentScreenshot());
	}

	public void verifyText(WebElement element, String expectedResult) {

		try {
			String actualResult = element.getText();

			if (actualResult.equals(expectedResult))
				test.pass("<b><i>Text is Verified successfully Actual is : " + actualResult
						+ " and Expected is : " + expectedResult+"/b></i>", extentScreenshot());
			else
				test.fail("<b><i>Text is not Verified Actual is : " + actualResult + " and Expected is : </b></i>"
						+ expectedResult, extentScreenshot());
		} catch (Exception e) {
			test.fail("<b><i>Exception in verifying the text</b></i>", extentScreenshot());
		}
	}

	public void switchToFrame(WebElement element, String fieldName) {

		try {
			driver.switchTo().frame(element);
			test.pass("<b><i>Switched to frame : </b></i>" + fieldName, extentScreenshot());
		} catch (Exception e) {
			test.fail("<b><i>Exception in switching to frame : " + fieldName+"</b></i>", extentScreenshot());
		}
	}

	public void switchToParentFrame() {

		try {
			driver.switchTo().parentFrame();
			test.pass("<b><i>Switched to Parent Frame Successfully</b></i>", extentScreenshot());
		} catch (Exception e) {
			test.fail("<b><i>Exception in switching to parent frame</b></i>", extentScreenshot());
		}
	}

	public void scrollToElement(WebElement element, String fieldName) {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			test.pass("<b><i>Scrolled to Element : " + fieldName + " successfully</b></i>",
					extentScreenshot());
		} catch (Exception e) {
			test.fail("<b><i>Exception in scrolling to element : " + fieldName+"</b></i>", extentScreenshot());
		}
	}

	public ExtentReports extentSetup() {

		String reportPath = ".//reports//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Automatiion Reports");
		reporter.config().setDocumentTitle("Automation Document");
		reporter.config().setTheme(Theme.STANDARD);

		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Sr. Quality Analyst", "Sharad");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Operating System Version", System.getProperty("os.version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Browser", capabilities.getBrowserName());
		extent.setSystemInfo("Browser Version", capabilities.getBrowserVersion());

		return extent;

	}

	public ExtentTest extentCreateTest(String testName) {
		test = extent.createTest(testName);

		return test;

	}

	public void extentFlush() {
		extent.flush();
	}

	public static void extentReportOpen() throws IOException {
		Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "//reports//report.html").toURI());
	}

	public String takeScreenshot() {

		String source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return source;
	}

	public Media extentScreenshot() {
		Media media = MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build();
		return media;
	}

}