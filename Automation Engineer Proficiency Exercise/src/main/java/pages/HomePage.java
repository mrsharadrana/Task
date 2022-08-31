package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage extends Driver {

	public WebDriver driver;
	
	
	private By numeroDeCelular = By.name("mobile");

	private By operador = By.name("operator");

	private By operadorTelcel = By.xpath("//b[text()='Telcel']");

	private By montoDeRecarga10Dollar = By.xpath("//*[text()='$10']");

	private By siguiente = By.xpath("(//button[@class='button buttonRecharge'])[1]");

	public WebElement numeroDeCelular() {
		return (WebElement) numeroDeCelular;
	}

	public WebElement operador() {
		return (WebElement) operador;
	}

	public WebElement operadorTelcel() {
		return (WebElement) operadorTelcel;
	}

	public WebElement montoDeRecarga10Dollar() {
		return (WebElement) montoDeRecarga10Dollar;
	}

	public WebElement siguiente() {
		return (WebElement) siguiente;
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}