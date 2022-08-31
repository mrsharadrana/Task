package testCase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

import pages.HomePage;
import pages.PaymentPage;
import utilities.Driver;

public class RechargeTest extends Driver {

	ExtentTest test;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		browserLaunch();
		extentSetup();
	}

	@Test(priority = 0)
	public void homePage() throws Exception {

		test = extentCreateTest("Home Page");

		HomePage homePage = new HomePage(driver);

		driver.get(properties.getProperty("url"));

		click(homePage.operador(), "Operador");
		click(homePage.operadorTelcel(), "Telcel");
		click(homePage.montoDeRecarga10Dollar(), "$10");
		sendKeys(homePage.numeroDeCelular(), "Numero de celular", "8465433546");
		click(homePage.siguiente(), "Siguiente");
	}

	@Test(priority = 1)
	public void paymentPage() throws Exception {

		test = extentCreateTest("Payment Page");

		PaymentPage p = new PaymentPage(driver);

		verifyText(p.resumenDeLaCompra(), "Resumen de la compra");
		click(p.tarjeta(), "Tarjeta");
		click(p.usarNuevaTarjeta(), "Usar nueva tarjeta");
		sendKeys(p.numeroDeTarjeta(), "Numero de tarjeta", "4111111111111111");
		sendKeys(p.mes(), "Mes", "11");
		sendKeys(p.ano(), "Ano", "25");
		sendKeys(p.cvv(), "CVV", "111");
		sendKeys(p.correoElectronico(), "Correo electronico", "test@test.com");
		click(p.pagarConTarjeta(), "Pagar con Tarjeta");
		sendKeys(p.correoElectronicoTelefonoMovil(), " Correo Electrónico / Teléfono móvil",
				"automationexcersise@test.com");
		sendKeys(p.contrasena(), "Contrasena", "123456");
		scrollToElement(p.acceso(), "Accesso");
		switchToFrame(p.captchaFrame(), "Captcha");
		click(p.captcha(), "Checkbox");
		switchToParentFrame();
		click(p.acceso(), "Acceso");

		if (p.acceso().isDisplayed())
			test.fail("<b><i>Payment Confirmation Screen is not Appearing due to captcha</b></i>",extentScreenshot());
		else
			test.pass("<b><i>RechargeTest Successful</b></i>",extentScreenshot());
	}

	@AfterSuite
	public void tearDown() throws Exception {
		extentFlush();
		extentReportOpen();
	}
}