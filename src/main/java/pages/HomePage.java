package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// aqui ficaram os elementos de iteração

public class HomePage {
	private WebDriver driver;
	
	List<WebElement> listaProdutos = new ArrayList();
	
// dando nome e apontando para o elemento
	private By textoProdutosNoCarrinho = By.className("cart-products-count");
	
	private By produtos = By.className("product-description");
	
	private By descricaoesDosProdutos = By.cssSelector(".product-description a");
	
	private By precoDosprodutos = By.className("price");
	
	private By botaoSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	// criando o construtor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
// ##### PARA O TEST 1 #####
	// aqui vai pegar a lista de produtos e contar
	public int contarProdutos() {
		carregarListaprodutos();
		return listaProdutos.size();
	}
	
	// aqui vai carregar a lista
	private void carregarListaprodutos() {
		listaProdutos = driver.findElements(produtos);
	}

//##### PARA O TEST 2 #####
	public int obterQuantidadeProdutosNoCarrinho() {
		String quantidadeProdutosNoCarrinho = driver.findElement(textoProdutosNoCarrinho).getText();
		// retirando os parenteses
		quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(", "");
		quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")", "");
		
		int qtdProdutosNoCarrinho = Integer.parseInt(quantidadeProdutosNoCarrinho);
		
		return qtdProdutosNoCarrinho;
	}
	
	
// ##### TESTE 3 #####
	
	public String obterNomeProduto(int indice) {
		return driver.findElements(descricaoesDosProdutos).get(indice).getText();
	}
	
	public String obterPrecoProduto(int indice) {
		return driver.findElements(precoDosprodutos).get(indice).getText();
	}
	
	public ProdutoPage clicarProduto(int indice) {
		driver.findElements(descricaoesDosProdutos).get(indice).click();
		return new ProdutoPage(driver);
	}

	
// ##### TESTE 4 ####
	public LoginPage clicarBotaoSignIn() {
		driver.findElement(botaoSignIn).click();
		return new LoginPage(driver);
	}
	
	public boolean estaLogado(String texto) {
		return texto.contentEquals(driver.findElement(usuarioLogado).getText());
	}

}
