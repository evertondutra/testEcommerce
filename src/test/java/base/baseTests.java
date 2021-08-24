package base;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import pages.HomePage;

// essa classe terá os comandos básicos de acesso.
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
		// carregar a página inicial
		driver.get("https://marcelodebittencourt.com/demoprestashop/");
		// inicializar a homePage
		homePage = new HomePage(driver);
		
	}
	
	public void capturaTela(String nomeTeste, String resultado) {
		var camera = (TakesScreenshot) driver;
		File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);
		try {
			Files.move(capturaDeTela, new File("resources/screenshots/"
			+ nomeTeste + "_" +resultado + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterAll // depois que terminar a execução de cada classe de test
	public static void finalizar() {
		driver.quit();
	}
	
}
