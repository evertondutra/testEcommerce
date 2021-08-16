package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {
	// o que vou fazer para contar os produtos
	
	@Test  // validar a quantidade de produtos na página
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		// no objeto homePage vou acessar o metodo contar produtos
		//fazendo um assert para validação
		assertThat(homePage.contarProdutos(), is (8));
	}
	
	
	@Test  // validar se o carrinho esta vazio
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		assertThat(produtosNoCarrinho, is(0));
	}
	
	@Test  // escolher o item e o preço e valida-los na página do item
	public void testValidarDetalhesDoProduto_DesrcricaoEValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		System.out.println(nomeProduto_HomePage);
		System.out.println(precoProduto_HomePage);
		
		ProdutoPage produtoPage = homePage.clicarProduto(indice);
		
		String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
		
		System.out.println(nomeProduto_ProdutoPage);
		System.out.println(precoProduto_ProdutoPage);
		
		assertThat(nomeProduto_HomePage.toUpperCase(), is (nomeProduto_ProdutoPage.toUpperCase()));
		assertThat(precoProduto_HomePage, is (precoProduto_ProdutoPage));
	}
	
	
	@Test
	public void testLoginComSucesso_UsuarioLogado( ) {
		// Clicar no botão Sign In na home page
		LoginPage loginPage = homePage.clicarBotaoSignIn();
		
		// Preencher usuário e senha
		loginPage.preencherEmail("evertonteste@teste.com");
		loginPage.preencherPassword("teste");
		// Clicar no botão Sign In para logar
		loginPage.clicarBotaoSignIn();
		// Validar se o usuário esta logado de fato
		assertThat(homePage.estaLogado("Everton Teste"), is (true));
		
	}

}
