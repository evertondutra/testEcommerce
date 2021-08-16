package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {

	private WebDriver driver;
	
	// criando o apontamento
	private By nomeProduto = By.className("h1");
	private By precoProduto = By.cssSelector(".current-price  span:nth-child(1)");
	private By tamanhoProduto = By.id("group_1");
	private By inputCorPreta = By.xpath("//ul[@id='group_2']//input[@value='11']");
	private By quantidadeProduto = By.id("quantity_wanted");
	private By botaoAddToCard = By.className("add-to-cart");
	
	
	// criando o construtor
	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	

	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}
	
	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}
	// selecionar uma opção do dropdown
	public void selecionaOpcaoDropDown(String opcao) {
		encontrarDropdownSize().selectByVisibleText(opcao);
	}
	
	// retornar uma lista stringa do seletor
	public List<String> obterOpcoesSelecionadas() {
		List<WebElement> elementosSelecionados =
		encontrarDropdownSize().getAllSelectedOptions();
		List<String> listaOpcoesSelecionadas = new ArrayList();
		for (WebElement elemento : elementosSelecionados) {
			listaOpcoesSelecionadas.add(elemento.getText());
		}
		return listaOpcoesSelecionadas;
	}
	//selecionar o tamanho do Produto
	public Select encontrarDropdownSize() {
		return new Select(driver.findElement(tamanhoProduto));
	}
	//selecionar a cor
	public void selecionarCorPreta() {
		driver.findElement(inputCorPreta).click();
	}
	//selecionar a quantidade
	public void alterarQuantidade(int quantidade) {
		driver.findElement(quantidadeProduto).clear();
		driver.findElement(quantidadeProduto).sendKeys(Integer.toString(quantidade));;
	}
	
	//clicar no botão add to card
	public void clicarBotaoaddToCard( ) {
		driver.findElement(botaoAddToCard).click();
	}

}
