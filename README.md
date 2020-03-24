# SeleniumAutomationWithPageObjects
Projeto com o framework Selenium utilizando "PageObjects" na sua estrutura. O projeto foi criado para ajudar os iniciantes em automação, também servirá como meu portfólio pessoal.

# Site de demonstração para praticar o WebDriver:

- [HeroKuapp](http://the-internet.herokuapp.com/)
- [jqueryui](https://jqueryui.com/demos/)
- [PHPTravels](https://phptravels.com/demo/)
- [Newtours](http://newtours.demoaut.com/)
- [Way2Automation](http://www.way2automation.com/demo.html)
- [AutomationPractice](http://automationpractice.com/index.php)
- [Demoqa](https://demoqa.com/)

# Índice

- Pré-requisito
- Configuração
- Instalação e Execução do Projeto
- Page Objects
- Suporte
- License

# Pré Requisitos

- JDK install 8
- Eclipse IDE install
- Maven install
- JUnit install
- Selenium plugin install

# Configuração do Ambiente

## Variáveis de Ambiente

### Java

- Windows: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html

### Maven

- Windows: https://www.mkyong.com/maven/how-to-install-maven-in-windows/


# Instalação e Execução do Projeto

1. Faça o download do zip ou clone do repositório Git
2. Descompacte o arquivo zip (se você tiver baixado um)
3. Abra o Eclipse
4. File ->> Import ->> Maven ->> Existing Maven Projects >> Navegue até a pasta em que você descompactou o zip
5. Você irá executar o teste pelo Junit
6. Junit
7. src/main/java ->> br.com.test ->> "DashboardValidation.java" ou "LoginValidation.java"
8. Clique com o botão direito do mouse no arquivo e execute como Run As "JUnit test"

# Page Objects
> Na interface do usuário do seu aplicativo da web, há áreas nas quais seus testes interagem. Um Objeto de Página simplesmente os modela como objetos no código de teste. Isso reduz a quantidade de código duplicado e significa que, se a interface do usuário for alterada, a correção precisará ser aplicada apenas em um local.

	}

	// Método que valida os "Textos" e "Títulos" na tela de Dashboard.
	public static void validacaoTextoTelaDashboard(String dashboard, String tasks, String settings, String profile,
			String password) {
		Utils.isVisible(validarTextoDashboard);
		driver.findElement(validarTextoDashboard).getAttribute(dashboard);
		driver.findElement(validarTextoTasks).getAttribute(tasks);
		driver.findElement(validarTextoSettings).getAttribute(settings);
		driver.findElement(validarTextoProfile).getAttribute(profile);
		driver.findElement(validarTextoPassword).getAttribute(password);

	  }
    
# Suporte
## E-mail: massayoshi.campos@gmail.com
## Linkedin: https://www.linkedin.com/in/massayoshi-campos/
> Se você tiver alguma dúvida sobre este repositório ou precisar de ajuda para contribuir, não hesite em entrar em contato comigo.

## Contribuições
> Se você tem algum exemplo de código que gostaria de contribuir para este repositório, sinta-se à vontade para abrir uma solicitação de recebimento.

## Comentário
> Os colaboradores deste repositório ficarão muito gratos por receber feedback! Se você deseja elogiar ou comentar algum exemplo de teste ou o repositório como um todo, faça-o pelo e-mail. Eu adoraria ouvir o que você pensa, então, reserve um momento para me informar.

# License
- Esse código é livre para ser usado dentro dos termos da licença MIT license
- Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
- Copyright 2020 ©Massayoshi Campos
