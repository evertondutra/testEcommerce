package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.Funcoes;

public class ConfirmedPage {
	private WebDriver driver;
	
	
	
	// texto de confirmação
	private By textoConfirmacao = By.cssSelector("#content-hook_order_confirmation h3");
	
	//total de produtos
	private By precoTotalProdutos = By.cssSelector("div.order-confirmation-table div.order-line div.row div.bold");
	
	//email para o usuário correto
	private By confirmaEmail = By.cssSelector("#content-hook_order_confirmation p");
	
	//total geral com taxa incluida
	private By totalTaxaIncluido = By.cssSelector("div.order-confirmation-table table tr:nth-child(4) td:nth-child(2)");
	
	//Confirmar o método de pagamento
	private By confirmaMetodoPagamento = By.cssSelector("#order-details ul li:nth-child(2) ");
	
	

	public ConfirmedPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obter_textoConfirmacao() {
		return driver.findElement(textoConfirmacao).getText().toUpperCase();
	}
	
	public String obter_confirmaEmail() {
		//evertonteste@teste.com
		String texto = driver.findElement(confirmaEmail).getText();
		texto = Funcoes.removeTexto(texto, "An email has been sent to the ");
		texto = Funcoes.removeTexto(texto, " address.");
		return  texto;
	}
	
	public String obter_precoTotalProdutos() {
		return driver.findElement(precoTotalProdutos).getText();
	}
	
	public String obter_totalTaxaIncluido() {
		String obter_totalTaxaIncluido = driver.findElement(totalTaxaIncluido).getText();
		obter_totalTaxaIncluido = Funcoes.removeTexto(obter_totalTaxaIncluido, "$");
		return obter_totalTaxaIncluido;
	}
	
	public String obter_confirmaMetodoPagamento() {
		return driver.findElement(confirmaMetodoPagamento).getText();
	}
	
	
	

}
