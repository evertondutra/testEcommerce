package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import pages.HomePage;

// essa classe ter� os comandos b�sicos de acesso.
public class BaseTests {
	
	private static WebDriver driver;
	protected HomePage homePage;
	
	@BeforeAll  // esta classe roda antes de tudo
	public static void inicializar() {
		//localia o webdriver
		System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver\\92\\chromedriver.exe");
		//estancia o driver
	driver = new ChromeDriver();
		//inclue uma espera implicita de 10 segundos
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@BeforeEach // essa classe roda antes de cada test
	public void carregarPaginaInicial() {
		// carregar a p�gina inicial
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
	
	@AfterAll // depois que terminar a execu��o de cada classe de test
	public static void finalizar() {
		driver.quit();
	}
	
}
