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
  Cenario: Deve mostrar o produto escolhido confirmado
    Dado que estou na pagina inicial
    Quando estou logado
    E seleciono o produto na posicao 0
    E o nome do produto na tela principal e na tela produto eh "Hummingbird Printed T-Shirt"
    E preco do produto na tela principal e na tela produto eh "$19.12"
    E adiciono o produto no carrinho com tamanho "M" cor "Black" quantidade 2
    Entao o produto aparece na contirmacao com nome "Hummingbird Printed T-Shirt" preco "$19.12" tamanho "M" cor "Black" quantidade 2
