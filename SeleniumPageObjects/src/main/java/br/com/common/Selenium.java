package br.com.common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
 * 
 * @version 1.0.0
 * @since Release 1.0
 */	


public class Selenium {
	
	private static WebDriver driver = null;

	/**
	 * Verifica qual o browser escolhido no arquivo de propriedades e inicializa o driver apropriado.
	 * 
	 * @return Retorna instância do WebDriver.
	 */
	
	public static WebDriver getDriver() {
		try {
		String browser = Property.BROWSER_NAME;
		if (driver == null) {
			
			if (Browser.CHROME.equals(browser)) {
				File file = new File(Property.CHROME_DRIVE_PATH);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				ChromeOptions chromeOptions = new ChromeOptions();
					
			    chromeOptions.addExtensions(new
				File("C:\\dev\\tools\\plugins_chrome\\Chrome.crx")); DesiredCapabilities
				desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				driver = new ChromeDriver(desiredCapabilities);
				driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
	           										
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return driver;
	}

}
