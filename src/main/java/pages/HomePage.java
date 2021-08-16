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
	private By produtos = By.className("product-description");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	// aqui vai pegar a lista de produtos e contar
	public int contarProdutos() {
		carregarListaprodutos();
		return listaProdutos.size();
	}
	
	// aqui vai carregar a lista
	private void carregarListaprodutos() {
		listaProdutos = driver.findElements(produtos);
	}

}
