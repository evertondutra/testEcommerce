package steps;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.HomePage;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

public class ComprarProdutosSteps {
	
	private static WebDriver driver;
	private HomePage homePage = new HomePage(driver);
	
	@Before
	public static void inicializar() {
		//localia o webdriver
		System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver\\92\\chromedriver.exe");
		//estancia o driver
	driver = new ChromeDriver();
		//inclue uma espera implicita de 10 segundos
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Dado("que estou na pagina inicial")
	public void que_estou_na_pagina_inicial() {
	    homePage.carregarPaginaInicial();
	    assertThat(homePage.obterTituloPagina(), is("Loja de Teste"));
	}
	
	@Quando("nao estou logado")
	public void nao_estou_logado() {
		assertThat(homePage.estaLogado(), is(false));
	    
	}

	@Entao("visualizo {int} produtos disponiveis")
	public void visualizo_produtos_disponiveis(Integer int1) {
		assertThat(homePage.contarProdutos(), is(int1));
	    
	}

	@Entao("carrinho esta zerado")
	public void carrinho_esta_zerado() {
	    assertThat(homePage.obterQuantidadeProdutosNoCarrinho(), is(0));
	}
	
	// #####--Segundo Cenario--#####
	
	LoginPage loginPage;
	@Quando("estou logado")
	public void estou_logado() {
		// Clicar no bot�o Sign In na home page
		loginPage = homePage.clicarBotaoSignIn();
				
				// Preencher usu�rio e senha
		loginPage.preencherEmail("evertonteste@teste.com");
		loginPage.preencherPassword("teste");
				// Clicar no bot�o Sign In para logar
		loginPage.clicarBotaoSignIn();
				// Validar se o usu�rio esta logado de fato
		assertThat(homePage.estaLogado("Everton Teste"), is (true));
				//Voltar para a p�gina inicial
		homePage.carregarPaginaInicial();
	}

	ProdutoPage produtoPage;
	String nomeProduto_HomePage;
	String precoProduto_HomePage;
	
	String nomeProduto_ProdutoPage;
	String precoProduto_ProdutoPage;
	
	@Quando("seleciono o produto na posicao {int}")
	public void seleciono_o_produto_na_posicao(Integer indice) {
		nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		produtoPage = homePage.clicarProduto(indice);
		
		nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
		precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
		

	}

	@Quando("o nome do produto na tela principal e na tela produto eh {string}")
	public void o_nome_do_produto_na_tela_principal_eh(String nomeProduto) {
		assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto.toUpperCase()));
		assertThat(nomeProduto_ProdutoPage.toUpperCase(), is(nomeProduto.toUpperCase()));

	}

	@Quando("preco do produto na tela principal e na tela produto eh {string}")
	public void preco_do_produto_na_tela_principal_eh(String precoProduto) {
		assertThat(precoProduto_HomePage, is (precoProduto));
		assertThat(precoProduto_ProdutoPage, is (precoProduto_ProdutoPage));

	}

	ModalProdutoPage modalProdutoPage;
	@Quando("adiciono o produto no carrinho com tamanho {string} cor {string} quantidade {int}")
	public void adiciono_o_produto_no_carrinho_com_tamanho_cor_quantidade(String tamanhoProduto, String corProduto, Integer quantidadeProduto) {
		
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		System.out.println(listaOpcoes.get(0));
		System.out.println("tamanho da lista " + listaOpcoes.size());
		// * Alterar o tamanho para M
		produtoPage.selecionaOpcaoDropDown(tamanhoProduto);
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		// Selecionar Cor
		produtoPage.selecionarCorPreta();
		
		//Selecionar Quantidade
		produtoPage.alterarQuantidade(quantidadeProduto);
		
		//Adicionar no carrinho
		modalProdutoPage = produtoPage.clicarBotaoaddToCard();
		
		assertThat(modalProdutoPage.obterMensagemProdutoAdicionado()
				.endsWith("Product successfully added to your shopping cart"),is (true));
		
	}

	@Entao("o produto aparece na contirmacao com nome {string} preco {string} tamanho {string} cor {string} quantidade {int}")
	public void o_produto_aparece_na_contirmacao_com_nome_preco_tamanho_cor_quantidade(String nomeProduto, String precoProduto, String tamanhoProduto,
			String corProduto, Integer quantidadeProduto) {
		assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
		
		Double precoProdutoDoubleEncontrado = Double.parseDouble(modalProdutoPage.obterPrecoProduto().replace("$", ""));
		Double precoProdutoDoubleEsperado   = Double.parseDouble(precoProduto.replace("$", ""));

		
		assertThat(modalProdutoPage.obterTamanhoProduto(),is (tamanhoProduto));
		assertThat(modalProdutoPage.obterCorProduto(), is (corProduto));
		assertThat(modalProdutoPage.obterQuiantidadeProduto(), is (Integer.toString(quantidadeProduto)));
		
		
		String subtotalProdutoString = modalProdutoPage.obterSubtotal();
		subtotalProdutoString = subtotalProdutoString.replace("$", "");
		Double subtotalEncontrado = Double.parseDouble(subtotalProdutoString);
		
		Double subtotalCalculadoEsperado = quantidadeProduto * precoProdutoDoubleEsperado;
		
		assertThat(subtotalEncontrado,is (subtotalCalculadoEsperado));

	}
	
	@After // depois que terminar a execu��o de cada classe de test
	public static void finalizar() {
		driver.quit();
	}
	
	

}
