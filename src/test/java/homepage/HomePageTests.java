package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.CheckoutPage;
import pages.ConfirmedPage;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;
import util.Funcoes;

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
	
	ProdutoPage produtoPage;
	String nomeProduto_ProdutoPage;
	@Test  // escolher o item e o preço e valida-los na página do item
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
	@Test  // Preenchendo o login e validando o usuário logado
	public void testLoginComSucesso_UsuarioLogado( ) {
		// Clicar no botão Sign In na home page
		loginPage = homePage.clicarBotaoSignIn();
		
		// Preencher usuário e senha
		loginPage.preencherEmail("evertonteste@teste.com");
		loginPage.preencherPassword("teste");
		// Clicar no botão Sign In para logar
		loginPage.clicarBotaoSignIn();
		// Validar se o usuário esta logado de fato
		assertThat(homePage.estaLogado("Everton Teste"), is (true));
		//Voltar para a página inicial
		carregarPaginaInicial();
		
		
	}

	ModalProdutoPage modalProdutoPage;
	@Test
	public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
		String tamanhoProduto = "M";
		String corProduto = "Black";
		Integer quantidadeProduto = 2;
		
		//--Pré condição
		//verificar se esta logado, se não estiver irá logar
		if(!homePage.estaLogado("Everton Teste")) {
			testLoginComSucesso_UsuarioLogado();
		}
		// -- Teste
		// escolher o item e o preço e valida-los na página do item
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
		modalProdutoPage = produtoPage.clicarBotaoaddToCard();
		
		//Validações
		assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));
		
		assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(),is (nomeProduto_ProdutoPage.toUpperCase()));
		
		//Remoção do sinal de sifrão
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
	
	
	// Valores Esperados
	String esperado_nomeProduto = "Hummingbird printed t-shirt";
	Double esperado_precoProduto = 19.12;
	String esperado_tamanhoProduto = "M";
	String esperado_corProduto = "Black";
	int esperado_quantidadeProduto = 2;
	Double esperado_subtotalProduto = esperado_precoProduto * esperado_quantidadeProduto;
	
	int obter_numeroItensTotal = esperado_quantidadeProduto;
	Double esperado_subTotal = esperado_subtotalProduto;
	Double esperado_shipping = 7.00;
	Double esperado_totalTaxExclTotal = esperado_subTotal + esperado_shipping;
	Double esperado_totalTaxIncTotal = esperado_totalTaxExclTotal;
	Double esperado_taxasTotal = 0.00;
	
	String esperado_nomeCliente = "Everton Teste"; 
	
	CarrinhoPage carrinhoPage;
	@Test
	public void IrParaCarrinho_InformacoesPersistidas() {
		//--Pré condiçoes
		// Produto incluido na tela ModalProdutoPage
		incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso();
		carrinhoPage = modalProdutoPage.clicaBtnProceedCheckout();
		
		//Teste
		
		// Validar todos os elementos da tela
		System.out.println("***TELA DO CARRINHO***");
		System.out.println(carrinhoPage.obter_nomeProduto());
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()));
		System.out.println(carrinhoPage.obter_tamanhoProduto());
		System.out.println(carrinhoPage.obter_corProduto());
		System.out.println(carrinhoPage.obter_quantidadeProduto());
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalProduto()));
		
		System.out.println("****PAINEIS DE TOTAIS**");
		System.out.println(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItensTotal()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotal()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shipping()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxExclTotal()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxIncTotal()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_taxasTotal()));
		
		//Asserções Hamcrest
		
		assertThat(carrinhoPage.obter_nomeProduto() , is(esperado_nomeProduto));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()), is(esperado_precoProduto));
		assertThat(carrinhoPage.obter_tamanhoProduto(), is(esperado_tamanhoProduto));
		assertThat(carrinhoPage.obter_corProduto(), is(esperado_corProduto));
		assertThat(Integer.parseInt(carrinhoPage.obter_quantidadeProduto()), is(esperado_quantidadeProduto));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalProduto()), is(esperado_subtotalProduto));
		
		assertThat(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItensTotal()), is(obter_numeroItensTotal));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotal()), is(esperado_subTotal));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shipping()), is(esperado_shipping));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxExclTotal()), is(esperado_totalTaxExclTotal));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxIncTotal()), is(esperado_totalTaxIncTotal));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_taxasTotal()), is(esperado_taxasTotal));
		
		//Asserções Junit
		/*
		assertEquals(carrinhoPage.obter_nomeProduto() , esperado_nomeProduto);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()), esperado_precoProduto);
		assertEquals(carrinhoPage.obter_tamanhoProduto(), esperado_tamanhoProduto);
		assertEquals(carrinhoPage.obter_corProduto(), esperado_corProduto);
		assertEquals(Integer.parseInt(carrinhoPage.obter_quantidadeProduto()), esperado_quantidadeProduto);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalProduto()), esperado_subtotalProduto);
		
		assertEquals(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItensTotal()), obter_numeroItensTotal);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotal()), esperado_subTotal);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shipping()), esperado_shipping);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxExclTotal()), esperado_totalTaxExclTotal);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxIncTotal()), esperado_totalTaxIncTotal);
		assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_taxasTotal()), esperado_taxasTotal);
		*/
		
	}
	
	CheckoutPage checkoutPage;
	@Test
	public void IrParaCheckout_FreteMeioPagamentoEnderecoListadoOk() {
		//Pré Condições
		//Produto Disponivel no Carrinho de Cpmpras
		IrParaCarrinho_InformacoesPersistidas();
		
		//Teste
		
		//Clicarno botão
		checkoutPage = carrinhoPage.clicarBtnProceedToChekout();
		//Validar informações na tela
		// --validando o valor total
		assertThat(Funcoes.removeCifraoDevolveDouble(checkoutPage.obter_totalTaxaIncTotal()), is(esperado_totalTaxIncTotal));
		//assertThat(checkoutPage.obter_nomeCliente(), is(esperado_nomeCliente));
		
		// --validando o nome do cliente
		assertTrue(checkoutPage.obter_nomeCliente().startsWith(esperado_nomeCliente));
		
		//Preencher informações
		checkoutPage.clicar_BtnContinueAddresses();
		
		//Validando o valor shipping method
		String encontrado_shippingValor = checkoutPage.obter_shippingValor();
		encontrado_shippingValor = Funcoes.removeTexto(encontrado_shippingValor, " tax excl.");
		Double encontrado_shippingValor_Double = Funcoes.removeCifraoDevolveDouble(encontrado_shippingValor);
		
		assertThat(encontrado_shippingValor_Double, is (esperado_shipping));
		
		checkoutPage.clicar_BtnContinueShipping();
		
		//Validando Payment
		checkoutPage.clicar_btnPyByCheck();
		//--Convertendo o valor em boolean
		String encontrado_Amount = checkoutPage.obter_amountTotal();
		encontrado_Amount = Funcoes.removeTexto(encontrado_Amount, " (tax incl.)");
		
		Double encontrado_AmountDouble = Funcoes.removeCifraoDevolveDouble(encontrado_Amount);
		
		//--Validdando Amount
		assertThat(encontrado_AmountDouble, is (esperado_totalTaxIncTotal));
		
		checkoutPage.clicarTermosOfService();
		
		
		
		
	}
	
	
	@Test
	public void finalizarPedido() {
		IrParaCheckout_FreteMeioPagamentoEnderecoListadoOk();
		
		//clicar bot[ao para confirmar pedido
		ConfirmedPage confirmedPage = checkoutPage.clicar_confirmacaoDePagamento();
		
		//Validar valores da tela
		assertTrue(confirmedPage.obter_textoConfirmacao().endsWith("YOUR ORDER IS CONFIRMED"));
		
		assertThat(confirmedPage.obter_confirmaEmail(), is("evertonteste@teste.com"));
		
		assertThat(Funcoes.removeCifraoDevolveDouble(confirmedPage.obter_precoTotalProdutos()), is (esperado_subTotal));
		
		assertThat(confirmedPage.obter_confirmaMetodoPagamento(),is("Payment method: Payments by check"));
		
	}
}
