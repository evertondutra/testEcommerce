package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;
	
	private By email = By.name("email");
	
	private By password = By.name("password");
	
	private By botaoSignIn = By.id("submit-login");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	// metodo para preencher email
	public void preencherEmail(String texto) {
		driver.findElement(email).sendKeys(texto);
	}
	// metodo para preencher password
	public void preencherPassword(String texto) {
		driver.findElement(password).sendKeys(texto);
	}
	// metodo para clicar no botão sign in 
	public void clicarBotaoSignIn() {
		driver.findElement(botaoSignIn).click();
	}
	
	

}
