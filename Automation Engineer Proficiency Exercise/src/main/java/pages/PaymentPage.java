package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

	public WebDriver driver;

	private By resumenDeLaCompra = By.className("summary-message-top");
		
	private By tarjeta = By.xpath("//p[text()='Tarjeta']");
	
	private By usarNuevaTarjeta = By.xpath("//label[@for='radio-c']");
	
	private By numeroDeTarjeta = By.id("cardnumberunique");
	
	private By mes = By.xpath("//input[@placeholder='Mes']");
	
	private By ano = By.xpath("//input[@placeholder='Año']");
	
	private By cvv = By.xpath("//input[@placeholder='CVV']");

	private By correoElectronico = By.xpath("//input[@type='email']");

	private By pagarConTarjeta = By.id("paylimit");

	private By correoElectronicoTelefonoMovil = By.id("usrname");

	private By contrasena = By.name("password");

	private By captchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");

	private By captcha = By.xpath("//div[@class='recaptcha-checkbox-border']");

	private By acceso = By.xpath("(//*[text()='Acceso'])[2]");

	public WebElement resumenDeLaCompra() {
		return (WebElement) resumenDeLaCompra;
	}

	public WebElement tarjeta() {
		return (WebElement) tarjeta;
	}

	public WebElement usarNuevaTarjeta() {
		return (WebElement) usarNuevaTarjeta;
	}

	public WebElement numeroDeTarjeta() {
		return (WebElement) numeroDeTarjeta;
	}

	public WebElement mes() {
		return (WebElement) mes;
	}

	public WebElement ano() {
		return (WebElement) ano;
	}

	public WebElement cvv() {
		return (WebElement) cvv;
	}

	public WebElement correoElectronico() {
		return (WebElement) correoElectronico;
	}

	public WebElement pagarConTarjeta() {
		return (WebElement) pagarConTarjeta;
	}

	public WebElement correoElectronicoTelefonoMovil() {
		return (WebElement) correoElectronicoTelefonoMovil;
	}

	public WebElement contrasena() {
		return (WebElement) contrasena;
	}

	public WebElement captchaFrame() {
		return (WebElement) captchaFrame;
	}

	public WebElement captcha() {
		return (WebElement) captcha;
	}

	public WebElement acceso() {
		return (WebElement) acceso;
	}

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}