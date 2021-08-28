# language: pt
Funcionalidade: Comprar produtos
  Como um usuario logado
  Eu quero escolher o produto
  e visualizar esse produto no carrinho
  Para concluir o pedido

  @validacaoinicial
  Cenario: Deve mostrar a lista de oito produtos na pagina inicial
    Dado que estou na pagina inicial
    Quando nao estou logado
    Entao visualizo 8 produtos disponiveis
    E carrinho esta zerado

  @fluxopadrao
  Esquema do Cenario: Deve mostrar o produto escolhido confirmado
    Dado que estou na pagina inicial
    Quando estou logado
    E seleciono o produto na posicao <posicao>
    E o nome do produto na tela principal e na tela produto eh <nomeProduto>
    E preco do produto na tela principal e na tela produto eh <precoProduto>
    E adiciono o produto no carrinho com tamanho <tamanhoProduto> cor <corProduto> quantidade <quantidadeProduto>
    Entao o produto aparece na contirmacao com nome <nomeProduto> preco <precoProduto> tamanho <tamanhoProduto> cor <corProduto> quantidade <quantidadeProduto>

    Exemplos: 
      | posicao | nomeProduto                   | precoProduto | tamanhoProduto | corProduto | quantidadeProduto |
      |       0 | "Hummingbird Printed T-Shirt" | "$19.12"     | "M"            | "Black"    |                 2 |
      |       1 | "Hummingbird Printed Sweater" | "$28.72"     | "XL"           | "N/A"      |                 3 |
