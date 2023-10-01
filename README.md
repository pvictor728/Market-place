# Market-place
Projeto da disciplina de Linguagem de programação II

# Escopo
Implementação de um sistema de cadastro de compradores e vendedores onde é possível registrar um catálogo de produtos bem como compras realizadas. As compras podem ser realizadas por diferentes formas de pagamento.

# Conceitos do sistema

Forma de Pagamento

. PIX Instantaneamente debita o valor da conta do comprador e deposita na conta do vendedor.
. Boleto Verifica se a data de vencimento do boleto não foi excedida. Instantaneamente debita o valor da conta do comprador e adiciona nos valores a receber do vendedor, descontado do valor para emissão do boleto.
. Débito Instantaneamente debita o valor da conta do comprador e deposita na conta do vendedor, descontado da taxa percentual de cobrança da operadora do cartão.
. Credito Coloca o valor na lista de valores a pagar do comprador e adiciona nos valores a receber do vendedor, descontado da taxa percentual de cobrança da operadora do cartão.
. Compra/Venda

. CPF Comprador
. CNPJ do Vendedor
. Forma de Pagamento
. Itens
. Comprador

. nome
. cpf
. saldo da conta
. valores a pagar
. compras realizadas
. Vendedor

. nome
. cnpj
. saldo da conta
. valores a receber
. vendas realizadas
. catalogo de produtos
. Produto

. codigo
. nome
. precoUnitario

# Requisitos:
As funcionalidades de cadastro de vendedores, compradores e produtos foram realizadas através da criação das instâncias através de construtores e respectiva inclusão nas coleções.

O sistema possui uma interface gráfica para cadastrar compras
A interface gráfica implementa uma estrutura de carrinho de compras que vai sendo preenchido à medida que o usuário inclui os itens
A interface gráfica permite imprimir os dados de um comprador com base no seu CPF
A interface gráfica permite imprimir os dados de um vendedor com base no seu CNPJ
Se a data de validade do boleto já tiver expirado, o sistema deve lançar uma exceção
As exceções devem ser tratadas com mensagens amigáveis aos usuários
