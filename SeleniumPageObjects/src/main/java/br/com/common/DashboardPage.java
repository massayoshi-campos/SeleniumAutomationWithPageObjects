package br.com.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import br.com.utils.Utils;

public class DashboardPage {
	
	/**
	 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
	 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
	 * 
	 * @version 1.0.0
     * @since Release 1.0
	 */	
	
	
	/**
	 * Instância privada do WebDriver que chama a suite de teste.
	 */
	private static final WebDriver driver;
	private static final WebDriverWait wait;

	/**
	 * Construtor que adiciona a instância do WebDriver para utilização  métodos.
	 * 
	 */

	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 40);
		driver.navigate().to(Property.SITE_ADDRESS);
		
	}
	
	//Dashboard
	static By validarTextoDashboard = By.xpath("//li[contains(text(),'Dashboard')]");
	static By validarTextoTasks = By.xpath("//a[contains(text(),'Tasks')]");
	static By validarTextoSettings = By.xpath("//li[contains(text(),'Settings')]");
	static By validarTextoProfile = By.xpath("//a[contains(text(),'Profile')]");
	static By validarTextoPassword = By.xpath("//a[contains(text(),'Password')]");
	
	//New Task
	static By adicionarNewTask = By.id("insert-button");
	static By verificarTituloPagina = By.xpath("//h3[@class='m-b-xs']");
	static By preencherCampoTitle = By.id("title");
	static By preencherCampoDate = By.id("dueDate");
	static By preencherCampoTags = By.xpath("//div[@class='bootstrap-tagsinput form-control']//input");
	static By clicarButtonSave = By.id("form-submit-button");
	static By verificarNewTask = By.id("tasks");
	
	//Alteração New Task
	static By clicarButtonEdit = By.id("edit-button");
	static By verificarTituloPaginaEdit = By.cssSelector("div.wrapper:nth-child(3) section.content div.container-fluid div.row:nth-child(1) div.col-lg-12 div.view-header:nth-child(1) div.header-title > h3.m-b-xs:nth-child(1)");
	static By preencherCampoTitleAlt = By.name("title");
	static By preencherCampoDateAlt = By.id("dueDate");
	static By preencherCampoTagsAlt = By.xpath("//div[@class='bootstrap-tagsinput form-control focus']//input");
	static By deleteTagAlt = By.xpath("//span[@class='tag label label-info']//span");
	
	//Exclusão New task
	static By clicarButtonDelete = By.id("delete-button");
	static By clicarButtonNao = By.xpath("//button[@class='btn btn-default']");
	static By clicarButtonSim = By.xpath("//button[@class='btn btn-danger']");
	

	
	public DashboardPage() {
						
	}
	
	//Método que valida os "Textos" e "Títulos" na tela de Dashboard.
	public static void validacaoTextoTelaDashboard (String dashboard, String tasks, String settings, String profile, String password) {
		Utils.isVisible(validarTextoDashboard);
		driver.findElement(validarTextoDashboard).getAttribute(dashboard);
		driver.findElement(validarTextoTasks).getAttribute(tasks);
		driver.findElement(validarTextoSettings).getAttribute(settings);
		driver.findElement(validarTextoProfile).getAttribute(profile);
		driver.findElement(validarTextoPassword).getAttribute(password);
					
	}
	
	//Método que Adicionar uma nova Task na tela.
	public static void adicionarNewTask(String titulo, String titulo1, String date, String tags, String titulo2) {
		Utils.isVisible(adicionarNewTask);
		driver.findElement(adicionarNewTask).click();
		Utils.isVisible(verificarTituloPagina);
		driver.findElement(verificarTituloPagina).getAttribute(titulo);
		driver.findElement(preencherCampoTitle).clear();
		driver.findElement(preencherCampoTitle).sendKeys(titulo1);
		driver.findElement(preencherCampoDate).clear();
		driver.findElement(preencherCampoDate).sendKeys(date);
		driver.findElement(preencherCampoTags).clear();
		driver.findElement(preencherCampoTags).sendKeys(tags);
		Utils.isVisible(clicarButtonSave);
		driver.findElement(clicarButtonSave).click();
		Utils.isVisible(verificarNewTask);
		driver.findElement(verificarNewTask).getAttribute(titulo2);
		
	}
	
		
	//Método que realiza a edição dos campos recém adicionado da nova Task na tela.
	public static void edicaoCamposTask(String titulo4, String date1, String tags1, String titulo2) throws InterruptedException {
		Utils.isVisible(clicarButtonEdit);
		driver.findElement(clicarButtonEdit).click();
		Utils.isVisible(preencherCampoTitleAlt);
		driver.findElement(preencherCampoTitleAlt).clear();
		driver.findElement(preencherCampoTitleAlt).sendKeys(titulo4);
		Utils.isVisible(preencherCampoDateAlt);
		driver.findElement(preencherCampoDateAlt).clear();
		driver.findElement(preencherCampoDateAlt).sendKeys(date1);
		driver.findElement(deleteTagAlt).click();
		Utils.isVisible(preencherCampoTagsAlt);
		driver.findElement(preencherCampoTagsAlt).clear();
		driver.findElement(preencherCampoTagsAlt).sendKeys(tags1);
		driver.findElement(clicarButtonSave).click();
		Thread.sleep(1500);
		driver.findElement(verificarNewTask).getAttribute(titulo2);
				
	}
		
	//Método que realiza a exclusão da Task na Tela.
	public static void exclusaoTask () {
		Utils.isVisible(clicarButtonDelete);
		driver.findElement(clicarButtonDelete).click();
		Utils.isVisible(clicarButtonNao);
		driver.findElement(clicarButtonNao).click();
		Utils.isVisible(clicarButtonDelete);
		driver.findElement(clicarButtonDelete).click();
		Utils.isVisible(clicarButtonSim);
		driver.findElement(clicarButtonSim).click();
		
	}

}
