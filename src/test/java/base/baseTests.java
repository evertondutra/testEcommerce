package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;

// essa classe ter� os comandos b�sicos de acesso.
public class BaseTests {
	
	private static WebDriver driver;
	protected HomePage homePage;
	
	@BeforeAll  // esta classe roda antes de tudo
	public static void inicializar() {
		System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver\\92\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@BeforeEach // essa classe roda antes de cada test
	public void carregarPaginaInicial() {
		// carregar a p�gina inicial
		driver.get("https://marcelodebittencourt.com/demoprestashop/");
		// inicializar a homePage
		homePage = new HomePage(driver);
		
	}
	
	@AfterAll // depois que terminar a execu��o de cada classe de test
	public static void finalizar() {
		driver.quit();
	}
	
}
