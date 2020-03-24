package br.com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.common.LoginPage;

public class LoginValidation extends BaseTestCase{
	
	/**
	 * @author Massayoshi Campos - E-mail: massayoshi.campos@gmail.com 
	 * Criado em Março/2020 - Periodo de Quarentena Coronavírus (COVID-19)
	 * @throws Exception 
	 * @version 1.0.0
     * @since Release 1.0
	 */	
	
	@Test
	public void LoginValidationTest() throws Exception{
		
		
		//Autenticação na tela de Login.
		LoginPage.validacaoLogin("eu@papito.io", "pwd123");
		
		//Validar autenticação do usuário na tela de Login.
		LoginPage.textPresentHome("Hello, Papito");
			
		//Autenticação com Falha no E-mail.
		LoginPage.validacaoLoginFalhaEmail ("pwd123", "Email is required");
				
		//Autenticação com Falha no password.
		LoginPage.validacaoLoginFalhaPassword ("eu@papito.io", "Password is required");
		
	}
	
}
