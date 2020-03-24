package br.com.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import br.com.common.Property;
import br.com.common.Selenium;
import br.com.suite.AllTest;

/**
 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
 * @throws Exception 
 * @version 1.0.0
 * @since Release 1.0
 */	

public class BaseTestCase {
	
	protected static WebDriver driver;

	@BeforeClass
	public static void beforeClass() throws Exception {
		if (!AllTest.isAllTestsExecution) {
			driver = Selenium.getDriver();
			driver.navigate().to(Property.SITE_ADDRESS);
			driver.manage().window().maximize();
			/*driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);*/
		}
	}

	@AfterClass
	public static void afterClass() throws Exception {
		if (!AllTest.isAllTestsExecution) {
			driver.quit();
		}
	}

}