package br.com.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;

import br.com.common.Property;
import br.com.common.Selenium;
import br.com.test.DashboardValidation;
import br.com.test.LoginValidation;

/**
 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
 * @throws Exception 
 * @version 1.0.0
 * @since Release 1.0
 */	

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	LoginValidation.class,
	DashboardValidation.class,
	
	
})

public class AllTest {
	
	protected static WebDriver driver;

	public static Boolean isAllTestsExecution = false;

	@BeforeClass
	public static void beforeClass() throws Exception {
		isAllTestsExecution = true;
		driver = Selenium.getDriver();
		driver.navigate().to(Property.SITE_ADDRESS);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		driver.quit();
	}

}
