package br.com.test;

import org.junit.Test;
import org.sikuli.script.FindFailed;

import br.com.common.DashboardPage;
import br.com.common.LoginPage;

public class DashboardValidation extends BaseTestCase{
	
	/**
	 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
	 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
	 * @throws InterruptedException  
	 * @version 1.0.0
     * @since Release 1.0
	 */	
	
	@Test
	public void DashboardValidationTest() throws InterruptedException {
		
		//Login
		LoginPage.validacaoLogin("eu@papito.io", "pwd123");
		
		//Validar Texto Dashboard
		Thread.sleep(1500);
		DashboardPage.validacaoTextoTelaDashboard("dashboard", "tasks", "settings", "profile", "password");
				
		//Adicionar uma nova Task
		Thread.sleep(1500);
		DashboardPage.adicionarNewTask("New Task", "Tarefa Teste Automação", "20/01/2020", "Modelo de Automação Teste", "Tarefa Teste Automação");
				
		//Alterar New Task
		Thread.sleep(1500);
		DashboardPage.edicaoCamposTask("Alterado com sucesso", "13/11/1986", "Modelo alterado com sucesso", "Modelo alterado com sucesso");
				
		//Excluir New Task
		DashboardPage.exclusaoTask();
		
	}
}
