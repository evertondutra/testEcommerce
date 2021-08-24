package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	private WebDriver driver;
	
	private By totalTaxaIncTotal = By.cssSelector("div.cart-total span.value");
	private By nomeCliente = By.cssSelector("div.address");
	private By btnContinueAddresses = By.name("confirm-addresses");
	private By shippingValor = By.cssSelector("span.carrier-price");
	private By btnContinueShipping = By.name("confirmDeliveryOption");
	private By btnPyByCheck = By.id("payment-option-1");
	private By amountTotal = By.cssSelector("#payment-option-1-additional-information > section > dl > dd:nth-child(2)");
	private By termosOfService = By.className("custom-checkbox");
	private By confirmacaoDePagamento = By.id("payment-confirmation");
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obter_totalTaxaIncTotal() {
		return driver.findElement(totalTaxaIncTotal).getText();
	}
	
	public String obter_nomeCliente() {
		return driver.findElement(nomeCliente).getText();
	}
	
	public void clicar_BtnContinueAddresses() {
		driver.findElement(btnContinueAddresses).click();
	}
	
	public String obter_shippingValor() {
		return driver.findElement(shippingValor).getText();
	}
	
	public void clicar_BtnContinueShipping() {
		driver.findElement(btnContinueShipping).click();
	}
	
	public void clicar_btnPyByCheck() {
		driver.findElement(btnPyByCheck).click();
	}
	
	public String obter_amountTotal() {
		return driver.findElement(amountTotal).getText();
	}
	
	public void clicarTermosOfService() {
		driver.findElement(termosOfService).click();
	}
	
	public ConfirmedPage clicar_confirmacaoDePagamento() {
		driver.findElement(confirmacaoDePagamento).click();
		return new ConfirmedPage(driver);
	}
	
}
