package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import base.BaseTests;

public class HomePageTests extends BaseTests {
	// o que vou fazer para contar os produtos
	@Test
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		// no objeto homePage vou acessar o metodo contar produtos
		//fazendo um assert para validação
		assertThat(homePage.contarProdutos(), is (8));
	}

}
