package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CarrinhoPage {
	private WebDriver driver;
	
	private By nomeProduto = By.cssSelector("div.product-line-info a");
	private By precoProduto = By.cssSelector("span.price");
	private By tamanhoProduto = By.xpath("//div[contains(@class,'product-line-grid')]//div[3]/span[contains(@class,'value')]");
	private By corProduto = By.xpath("//div[contains(@class,'product-line-grid')]//div[4]/span[contains(@class,'value')]");
	private By quantidadeProduto = By.cssSelector("input.js-cart-line-product-quantity");
	private By subtotalProduto = By.cssSelector("span.product-price strong");
	private By numeroItensTotal = By.className("js-subtotal");
	private By subTotal = By.cssSelector("#cart-subtotal-products span.value");
	private By shipping = By.cssSelector("#cart-subtotal-shipping span.value");
	private By totalTaxExclTotal = By.cssSelector("div.cart-summary-totals :nth-child(1) span.value");
	private By totalTaxIncTotal = By.cssSelector("div.cart-summary-totals :nth-child(2) span.value");
	private By taxasTotal = By.cssSelector("div.cart-summary-totals :nth-child(3) span.value");
	
	public CarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obter_nomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}
	
	public String obter_precoProduto() {
		return driver.findElement(precoProduto).getText();
	}

	public String obter_tamanhoProduto() {
		return driver.findElement(tamanhoProduto).getText();
	}
	
	public String obter_corProduto() {
		return driver.findElement(corProduto).getText();
	}
	public String obter_quantidadeProduto() {
		return driver.findElement(quantidadeProduto).getAttribute("value");
	}
	public String obter_subtotalProduto() {
		return driver.findElement(subtotalProduto).getText();
	}
	public String obter_numeroItensTotal() {
		return driver.findElement(numeroItensTotal).getText();
	}
	public String obter_subTotal() {
		return driver.findElement(subTotal).getText();
	}
	public String obter_shipping() {
		return driver.findElement(shipping).getText();
	}
	public String obter_totalTaxExclTotal() {
		return driver.findElement(totalTaxExclTotal).getText();
	}
	public String obter_totalTaxIncTotal() {
		return driver.findElement(totalTaxIncTotal).getText();
	}
	public String obter_taxasTotal() {
		return driver.findElement(taxasTotal).getText();
	} 
			                 
}

