package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {
	// o que vou fazer para contar os produtos
	
	@Test  // validar a quantidade de produtos na p�gina
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		// no objeto homePage vou acessar o metodo contar produtos
		//fazendo um assert para valida��o
		assertThat(homePage.contarProdutos(), is (8));
	}
	
	@Test  // validar se o carrinho esta vazio
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		assertThat(produtosNoCarrinho, is(0));
	}
	
	ProdutoPage produtoPage;
	String nomeProduto_ProdutoPage;
	@Test  // escolher o item e o pre�o e valida-los na p�gina do item
	public void testValidarDetalhesDoProduto_DesrcricaoEValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		System.out.println(nomeProduto_HomePage);
		System.out.println(precoProduto_HomePage);
		
		produtoPage = homePage.clicarProduto(indice);
		
		nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
		
		assertThat(nomeProduto_HomePage.toUpperCase(), is (nomeProduto_ProdutoPage.toUpperCase()));
		assertThat(precoProduto_HomePage, is (precoProduto_ProdutoPage));
	}
	
	LoginPage loginPage;
	@Test  // Preenchendo o login e validando o usu�rio logado
	public void testLoginComSucesso_UsuarioLogado( ) {
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
		carregarPaginaInicial();
		
		
	}

	@Test
	public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
		String tamanhoProduto = "M";
		String corProduto = "Black";
		Integer quantidadeProduto = 2;
		
		//--Pr� condi��o
		//verificar se esta logado, se n�o estiver ir� logar
		if(!homePage.estaLogado("Everton Teste")) {
			testLoginComSucesso_UsuarioLogado();
		}
		// -- Teste
		// escolher o item e o pre�o e valida-los na p�gina do item
		testValidarDetalhesDoProduto_DesrcricaoEValorIguais();
		
		//Selecionar Tamanho
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		System.out.println(listaOpcoes.get(0));
		System.out.println("tamanho da lista " + listaOpcoes.size());
		// * Alterar o tamanho para M
		produtoPage.selecionaOpcaoDropDown(tamanhoProduto);
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		System.out.println(listaOpcoes.get(0));
		System.out.println("tamanho da lista " + listaOpcoes.size());
		
		// Selecionar Cor
		produtoPage.selecionarCorPreta();
		
		//Selecionar Quantidade
		produtoPage.alterarQuantidade(quantidadeProduto);
		
		//Adicionar no carrinho
		ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoaddToCard();
		
		//Valida��es
		assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));
		
		assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(),is (nomeProduto_ProdutoPage.toUpperCase()));
		
		//Remo��o do sinal de sifr�o
		String precoProdutoString = modalProdutoPage.obterPrecoProduto();
		precoProdutoString = precoProdutoString.replace("$", "");
		Double precoProduto = Double.parseDouble(precoProdutoString);
		
		assertThat(modalProdutoPage.obterTamanhoProduto(),is (tamanhoProduto));
		assertThat(modalProdutoPage.obterCorProduto(), is (corProduto));
		assertThat(modalProdutoPage.obterQuiantidadeProduto(), is (Integer.toString(quantidadeProduto)));
		
		System.out.println(modalProdutoPage.obterSubtotal());
		String subtotalProdutoString = modalProdutoPage.obterSubtotal();
		subtotalProdutoString = subtotalProdutoString.replace("$", "");
		Double subtotal = Double.parseDouble(subtotalProdutoString);
		
		Double subtotalCalculado = quantidadeProduto * precoProduto;
		
		assertThat(subtotal,is (subtotalCalculado));
		
	}
	
}
