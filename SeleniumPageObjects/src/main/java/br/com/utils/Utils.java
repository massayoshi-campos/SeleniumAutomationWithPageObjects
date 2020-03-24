package br.com.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.api.robot.Key;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.common.base.Function;
import com.paulhammant.ngwebdriver.NgWebDriver;

import br.com.common.Selenium;

public class Utils {
	
	/**
	 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
	 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
	 * @throws InterruptedException, FindFailed
	 * @version 1.0.0
     * @since Release 1.0
	 */	
	
	private static final WebDriver driver;
	private static final WebDriverWait wait;
	private static NgWebDriver ngWebDriver = null;

	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 40);
	}

	/**
	 * Aguardando 200 segundos para que um elemento esteja presente na página,
	 * verificando por sua presença uma vez a cada 5 segundos.
	 */

	static Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS)
			.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

	static Wait<WebDriver> fluentWaitScreean = new FluentWait<WebDriver>(driver).withTimeout(40, TimeUnit.SECONDS)
			.pollingEvery(10, TimeUnit.SECONDS).ignoring(ScriptTimeoutException.class)
			.ignoring(NoSuchElementException.class).ignoring(InvalidElementStateException.class)
			.ignoring(Exception.class);

	public static List<WebElement> findElementsByXpath(final String xpath) {
		List<WebElement> element = fluentWaitScreean.until(new Function<WebDriver, List<WebElement>>() {
			By foundBy = By.xpath(xpath);

			public List<WebElement> apply(WebDriver driver) {
				fluentWait.until(ExpectedConditions.visibilityOfElementLocated(foundBy));
				return driver.findElements(foundBy);
			}
		});
		return element;
	}

	public static String getValueByXpath(WebElement webElement, final String xpath) {
		String value = null;
		if (webElement != null && xpath != null) {
			List<WebElement> listfoundElemet = webElement.findElements(By.xpath(xpath));
			for (WebElement currentElement : listfoundElemet) {
				value = currentElement.getText();
				break;
			}
		}
		return value;
	}

	public static String getValueByXpath(final String xpath) {
		By foundBy = By.xpath(xpath);
		isVisible(foundBy);
		WebElement campo = findElement(foundBy);
		if (campo != null) {
			return campo.getText();
		}
		return null;
	}

	public static WebElement findElement(final By foundBy) {
		WebElement element = fluentWaitScreean.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				fluentWait.until(ExpectedConditions.visibilityOfElementLocated(foundBy));
				return driver.findElement(foundBy);
			}
		});
		return element;
	}

	public static WebElement findElementDisable(final By foundBy) {
		WebElement element = fluentWaitScreean.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				fluentWait.until(ExpectedConditions.presenceOfElementLocated(foundBy));
				return driver.findElement(foundBy);
			}
		});
		return element;
	}

	public static WebElement findElement(final WebElement elementToFind, final By foundBy) {
		WebElement element = fluentWaitScreean.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return elementToFind.findElement(foundBy);
			}
		});
		return element;
	}

	public String getElementXPath(WebDriver driver, WebElement element) {
		String javaScript = "function getElementXPath(elt){" + "var path = \"\";"
				+ "for (; elt && elt.nodeType == 1; elt = elt.parentNode){" + "idx = getElementIdx(elt);"
				+ "xname = elt.tagName;" + "if (idx > 1){" + "xname += \"[\" + idx + \"]\";" + "}"
				+ "path = \"/\" + xname + path;" + "}" + "return path;" + "}" + "function getElementIdx(elt){"
				+ "var count = 1;" + "for (var sib = elt.previousSibling; sib ; sib = sib.previousSibling){"
				+ "if(sib.nodeType == 1 && sib.tagName == elt.tagName){" + "count++;" + "}" + "}" + "return count;"
				+ "}" + "return getElementXPath(arguments[0]).toLowerCase();";

		return (String) ((JavascriptExecutor) driver).executeScript(javaScript, element);
	}

	/**
	 * Método para verificar a visibilidade de um elemento utilizando o locator
	 * 
	 * @param locator
	 */
	public static void isVisible(By locator) {
		fluentWaitScreean.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Método para verificar a visibilidade de um elemento utilizando o ID
	 * 
	 * @param id
	 */
	public static void isVisible(String id) {
		isVisible(By.id(id));
	}

	/**
	 * Método para verificar a presença de um elemento utilizando o locator
	 * 
	 * @param locator
	 */
	public static void isLocated(By locator) {
		fluentWaitScreean.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * Método que valida a url de uma nova janela/popup aberta e alterna entre as 2
	 * janelas
	 * 
	 * @param url
	 * @param timeWait
	 * @throws InterruptedException
	 */
	public static void testWindowURL(String url, int timeWait) throws InterruptedException {
		String windowHandleJanelaInicial = driver.getWindowHandle();
		Thread.sleep(timeWait);
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			// Se o windowHandle NÃO é igual ao windowHandle da janela inicial, eu sei que é
			// o da nova janela, então mando o WebDriver mudar para ela
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		Assert.assertEquals(url, driver.getCurrentUrl());
		driver.close();
		driver.switchTo().window(windowHandleJanelaInicial);

	}

	/**
	 * Método duplo clique no campo e informa novo valor.
	 * 
	 * @param locator
	 * @param valor
	 */
	public static void duploClick(By locator, String valor) {
		driver.findElement(locator).click();
		WebElement campo = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.doubleClick(campo).perform();
		campo.sendKeys(valor);

	}

	/**
	 * Método para verificar a presença de um elemento utilizando o ID
	 * 
	 * @param id
	 */
	public static void isLocated(String id) {
		isLocated(By.id(id));
	}

	/**
	 * Método para verificar se um elemento é clicável utilizando o locator
	 * 
	 * @param locator
	 */
	public static void isClickable(By locator) {
		fluentWaitScreean.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Método para verificar se um elemento é clicável utilizando o ID
	 * 
	 * @param id
	 */
	public static void isClickable(String id) {
		isClickable(By.id(id));
	}

	/*
	 * 
	 * Método para verificar o texto presente em um campo input
	 * 
	 * @param locator
	 */

	public static void textPresent(By locator, String text) {
		fluentWaitScreean.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
	}

	/**
	 * Método para capturar screenshot da tela
	 * 
	 * @param fileName
	 *            - Nome do arquivo
	 */
	public static void takeScreenshot(String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date data = new Date();
		try {
			FileUtils.copyFile(scrFile, new File("C:\\FotosWebDriver\\" + fileName + data.getTime() + ".jpeg"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pause for X milliseconds.
	 * 
	 * @param iTimeInMillis
	 */
	@Deprecated
	public static void wait(final int iTimeInMillis) {
		try {
			Thread.sleep(iTimeInMillis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Preenche os campos na tela onde a chave do HashMap e no do campo.
	 * 
	 * @param mapContent
	 */
	public static void fillScreeanById(Map<String, String> mapContent) {

		// waitForPageLoaded(driver);

		for (Entry<String, String> fieldEntry : mapContent.entrySet()) {
			WebElement campo = findElement(By.id(fieldEntry.getKey()));
			campo.clear();
			campo.sendKeys(fieldEntry.getValue());
		}

	}

	/**
	 * Preenche a tela apartir de mapa de xpath
	 * 
	 * @param mapContent
	 */
	public static void fillScreeanByXpath(Map<String, String> mapContent) {
		for (Entry<String, String> fieldEntry : mapContent.entrySet()) {
			WebElement campo = findElement(By.xpath(fieldEntry.getKey()));
			campo.clear();
			campo.sendKeys(fieldEntry.getValue());
		}
	}

	/**
	 * Clica no botão pelo id
	 * 
	 * @param idButtom
	 */
	public static void buttomByIdClick(String idButtom) {
		findElement(By.id(idButtom)).click();

	}

	public static void buttomByIdClick(By clickLocator) {
		findElement(clickLocator).click();
	}

	/**
	 * Clica no botão pelo xpath
	 * 
	 * @param xpathButtom
	 */
	public static void buttomByXpathClick(String xpathButtom) {
		findElement(By.xpath(xpathButtom)).click();
	}

	/**
	 * Pega valor de um campo
	 * 
	 * @param idField
	 * @return
	 */
	public static String getValueById(String idField) {
		WebElement campo = findElement(By.id(idField));
		return campo.getText();
	}

	/**
	 * O o valor do campo desable e verifica a informação.
	 * 
	 * @param idField
	 * @return
	 */
	public static String getValueDisabledById(String idField) {
		WebElement campo = findElementDisable(By.id(idField));
		return campo.getAttribute("value");
	}

	/**
	 * Preenche conteudo no combo
	 * 
	 * @param idField
	 * @param value
	 */
	public static void fillSelect(String idField, String value) {
		WebElement campo = findElement(By.id(idField));
		new Select(campo).selectByVisibleText(value);
	}

	/*
	 * @param listSearch
	 * 
	 * Retorna hashMap com chave e valores apartir de uma lista de campos Exemplo de
	 * uso List<String> listSearch = new ArrayList<String>();
	 * listSearch.add("applicationCode_0");
	 * listSearch.add("systemConfigurationName_0");
	 * 
	 * Retorno {{"applicationCode_0", "DOC-AR"}, {"systemConfigurationName_0",
	 * "CONFIGURATION-AR"}}
	 * 
	 */
	public static Map<String, String> getValueById(List<String> listSearch) {
		Map<String, String> mapReturn = new HashMap<String, String>();

		for (String fieldId : listSearch) {
			WebElement campo = findElement(By.id(fieldId));
			mapReturn.put(fieldId, campo.getText());
		}

		return mapReturn;
	}

	/*
	 * @param listSearch Retorna hashMap com chave e valores apartir de uma lista de
	 * campos Exemplo de uso List<String> listSearch = new ArrayList<String>();
	 * listSearch.add("applicationCode_0");
	 * listSearch.add("systemConfigurationName_0");
	 * 
	 * Retorno {{"applicationCode_0", "DOC-AR"}, {"systemConfigurationName_0",
	 * "CONFIGURATION-AR"}}
	 * 
	 */
	public static Map<String, String> getValueByXpath(List<String> listSearch) {
		Map<String, String> mapReturn = new HashMap<String, String>();

		for (String fieldXpath : listSearch) {
			WebElement campo = findElement(By.id(fieldXpath));
			mapReturn.put(fieldXpath, campo.getText());
		}

		return mapReturn;
	}

	/**
	 * Método para o robô realizar o upload de arquivos dentro da página.
	 * 
	 * @param string
	 */
	public static void setClipboardArquivo(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	}
	
	/**
	 * Método da ferramenta sikuli que clica no botão inserindo no pathButtom o
	 * caminho da imagem.
	 * 
	 * @throws Exception
	 */
	public static void buttomByPathClick(String pathButtom) throws FindFailed {
		Screen screen = new Screen();
		Pattern btnbuttomClick = new Pattern(pathButtom);
		screen.wait(btnbuttomClick, 10);
		screen.click(btnbuttomClick);

	}

	/**
	 * Método da ferramenta sikuli que preenche o campo texto usando a imagem.
	 * 
	 * @param texto
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public static void preencheCampo(String texto) throws FindFailed, InterruptedException {
		Screen screen = new Screen();
		Pattern preencheCampo = new Pattern(texto);
		screen.wait(preencheCampo, 10);
		screen.type(preencheCampo, texto);
		screen.type(Key.ENTER);

	}

	public static void waitForPageToBeReady(final String checkElementId) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 400; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Nothing
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Nothing
				}
				try {
					if (findElementsByXpath(checkElementId).size() > 0) {
						return true;
					}

				} catch (Exception e) {
					System.out.println("exception ignorada: " + e.getMessage());
				}
				return false;
			}
		};

		fluentWait.until(expectation);
	}

	/**
	 * Método para deletar os certificados do browser
	 * 
	 * @param deleteCertificateFromMy
	 * 
	 */

	public static void deleteCertificateFromMy(String... aliasesToPreserve) {
		try {
			KeyStore myKs = KeyStore.getInstance("Windows-My", "SunMSCAPI");
			myKs.load(null, null);

			ArrayList<String> aliasesToRemove = new ArrayList<String>();

			Enumeration<String> aliases = myKs.aliases();
			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				boolean found = false;
				for (String aliasToPreserve : aliasesToPreserve) {
					if (alias.contains(aliasToPreserve)) {
						found = true;
						break;
					}
				}
				if (!found) {
					aliasesToRemove.add(alias);
				}
			}

			for (String aliasToRemove : aliasesToRemove) {
				System.out.println("Certificado " + aliasToRemove + " será removido");
				myKs.deleteEntry(aliasToRemove);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
