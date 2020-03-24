package br.com.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.utils.Utils;

public class LoginPage {
	
	/**
	 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
	 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
	 * @throws InterruptedException 
	 * @version 1.0.0
     * @since Release 1.0
	 */	

	/**
	 * Instância privada do WebDriver que chama a suite de teste.
	 */
	private static final WebDriver driver;
	private static final WebDriverWait wait;

	/**
	 * Construtor que adiciona a instância do WebDriver para utilização dos métodos.
	 * 
	 */

	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 40);
		driver.navigate().to(Property.SITE_ADDRESS);
	}

	// Login no sistema;
	static By preencherCampoEmail = By.id("login_email");
	static By preencherCampopassword = By.id("login_password");
	static By clicarButtonLogin = By.cssSelector("button[id*=btnLogin]");
	static By clicarLogout = By.linkText("eu@papito.io");
	static By clicarButtonSair = By.linkText("Sair");
	
	//Login Falha E-mail
	static By verificarMensagemEmail = By.xpath("//div[text()='Email is required']");
	
	//Login Falha Password
	static By verificaMensagemPassword = By.xpath("//div[text()='Password is required']");
	

	public LoginPage() {

	}
		
	//Método que realiza a autenticação na tela Login.
	public static void validacaoLogin (String email, String pwd) {
		driver.findElement(preencherCampoEmail).clear();
		driver.findElement(preencherCampoEmail).sendKeys(email);
		driver.findElement(preencherCampopassword).clear();
		driver.findElement(preencherCampopassword).sendKeys(pwd);
		driver.findElement(clicarButtonLogin).click();

	}

    //Método que valida o sucesso da autenticação do usuário no sistema.
	@Test
	public static void textPresentHome(String mensagem) {
	List<WebElement> eleCheck = Utils.findElementsByXpath("/html[1]/body[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/small[1]");
	if (eleCheck != null && eleCheck.size() > 0) {eleCheck.get(0).getAttribute("Hello, Papito");
	driver.findElement(clicarLogout).click();
	driver.findElement(clicarButtonSair).click();

		}
	}
	
	// Método que realiza a autenticação com "Falha" na tela de Login.
	public static void validacaoLoginFalhaEmail (String pwd, String falhaemail) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(preencherCampopassword).clear();
		driver.findElement(preencherCampopassword).sendKeys(pwd);
		driver.findElement(clicarButtonLogin).click();
		Thread.sleep(3000);
		driver.findElement(verificarMensagemEmail).getAttribute(falhaemail);
		Thread.sleep(2000);
		driver.findElement(preencherCampoEmail).clear();
		driver.findElement(preencherCampopassword).clear();
		
	}
	
	// Método que realiza a autenticação com "Falha" na tela de Login.
	public static void validacaoLoginFalhaPassword (String email, String falhapassword) throws InterruptedException {
		driver.findElement(preencherCampopassword).clear();
		Thread.sleep(2000);
		driver.findElement(preencherCampoEmail).sendKeys(email);
		driver.findElement(clicarButtonLogin).click();
		Thread.sleep(3000);
		driver.findElement(verificaMensagemPassword).getAttribute(falhapassword);
		Thread.sleep(2000);
		driver.findElement(preencherCampoEmail).clear();
		driver.findElement(preencherCampopassword).clear();
	
	}
}
