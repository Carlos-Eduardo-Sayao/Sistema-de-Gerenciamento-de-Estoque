package app;
import java.util.Scanner;

import gerenciador.CarrinhoGerenciador;
import gerenciador.EstoqueGerenciador;
import interfaces.Pagamento;
import model.Carrinho;
import model.Estoque;
import model.Produto;
import model.ProdutoCarrinho;
import pagamentos.PagamentoCartao;
import pagamentos.PagamentoPix;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Estoque <Produto> estoque = new Estoque<>();
		EstoqueGerenciador eg = new EstoqueGerenciador();
		Carrinho <ProdutoCarrinho> carrinho = new Carrinho<>();
		CarrinhoGerenciador cg = new CarrinhoGerenciador();
		Pagamento cartao = new PagamentoCartao();
		Pagamento pix = new PagamentoPix();
		
		
		System.out.println("<==Sistema de Loja==>");
		while(true) {
			System.out.println("<=Menu de opções=>");
			System.out.println("1-Aba de compras");
			System.out.println("2-Aba de estoque");
			System.out.println("3-Encerrar");
			System.out.print(">");
			int opcao = sc.nextInt();
			if(opcao == 1) {
				while(true) {
					System.out.println();
					System.out.println("<=Menu de Compras=>");
					System.out.println("1-Adicionar produto ao carrinho");
					System.out.println("2-Remover Produto do carrinho");
					System.out.println("3-Ver carrinho");
					System.out.println("4-Pagar");
					System.out.println("5-Voltar");
					System.out.print(">");
					int opcao2 = sc.nextInt();
					sc.nextLine();
					if(opcao2 == 1) {
						try {
							System.out.println();
							System.out.println("<=Estoque=>");
							eg.listar(estoque);
						}catch(Exception e){
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.print("Digite o id do produto:");
						int id = sc.nextInt();
						Produto produto =null;
						try {
							produto = estoque.getProduto(id);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.print("Digite a quantidade:");
						int quantidade = 0;
						quantidade = sc.nextInt();
						try {
							cg.verificaQuantidade(produto,quantidade);
						}
						catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho(produto.getId(),produto.getNome(),produto.getValor(),quantidade);
						cg.adicionar(carrinho, produtoCarrinho,quantidade);
						eg.atualizarQuantidade(estoque, quantidade, id);
						System.out.println("Produto Adicionado ao carrinho");
					}
					else if(opcao2 == 2) {
						try {
							System.out.println();
							System.out.println("<=Carrinho=>");
							cg.listarCarrinho(carrinho);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.print("Digite o id do produto:");
						int id = sc.nextInt();
						System.out.print("Digite a quantidade:");
						int quantidade = sc.nextInt();
						try {
							cg.remover(carrinho,estoque,id,quantidade);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.println("Produto removido do carrinho");
					}
					else if(opcao2 == 3) {
						System.out.println();
						System.out.println("<=Carrinho=>");
						try {
							cg.listarCarrinho(carrinho);
						}catch(Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
						double valorTotal = cg.calcularTotal(carrinho);
						System.out.println("Valor Total -------- R$"+valorTotal);
					}
					
					else if(opcao2 == 4) {
						System.out.println();
						System.out.println("Selecione a opção de pagamento:");
						System.out.println("1-Cartão");
						System.out.println("2-Pix");
						System.out.print(">");
						int opcao4 = sc.nextInt();
						if(opcao4 == 1) {
							System.out.println();
							double valorTotal = cg.calcularTotal(carrinho);
							System.out.println("Valor Total -------- R$"+valorTotal);
							cartao.pagamento(valorTotal);
							System.out.println("Pagamento Concluído");
							cg.esvaziarCarrinho(carrinho);
						}
						if(opcao4 == 2) {
							System.out.println();
							double valorTotal = cg.calcularTotal(carrinho);
							System.out.println("Valor Total -------- R$"+valorTotal);
							pix.pagamento(valorTotal);
							System.out.println("Pagamento Concluído");
							cg.esvaziarCarrinho(carrinho);
						}
					}
					
					else if(opcao2 == 5) {
						System.out.println();
						System.out.println("<< Voltando...");
						System.out.println();
						break;
					}
				}
			}
			
			else if(opcao == 2) {
				while(true) {
					System.out.println("");
					System.out.println("<=Menu de Estoque=>");
					System.out.println("1-Adicionar produto ao estoque");
					System.out.println("2-Remover produto do estoque");
					System.out.println("3-Buscar produto do estoque");
					System.out.println("4-Listar produtos do estoque");
					System.out.println("5-Atualizar quantidade");
					System.out.println("6-Atualizar preço");
					System.out.println("7-Voltar");
					System.out.print(">");
					int opcao3 = sc.nextInt();
					sc.nextLine();
					if(opcao3 == 1) {
						System.out.println();
						System.out.print("Digite o id do produto:");
						int id = sc.nextInt();
						sc.nextLine();
						System.out.print("Digite o nome do produto:");
						String nome = sc.nextLine();
						System.out.print("Digite a quantidade:");
						int quantidade = sc.nextInt();
						System.out.print("Digite o valor do produto:R$");
						double valor = sc.nextDouble();
						try {
							Produto produto = new Produto(id,nome,valor,quantidade);
							eg.adicionar(estoque, produto);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							System.out.println("Produto não adicionado");
							continue;
						}
						System.out.println();
						System.out.println("Produto Adicionado ao estoque");
					}
					
					else if(opcao3 == 2) {
						try {
							System.out.println();
							System.out.println("<=Estoque=>");
							eg.listar(estoque);
						}catch(Exception e){
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.print("Digite o id do produto que deseja remover:");
						int id = sc.nextInt();
						Produto produto = null;
						try {
							produto = estoque.getProduto(id);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						eg.remover(estoque, produto);
						System.out.println();
						System.out.println("Produto removido do estoque");
						System.out.println("Id(s) atualizado(s)");
						
					}
					
					else if(opcao3 == 3) {
						System.out.println();
						try {
							eg.estoqueVazio(estoque);
						}catch(Exception e){
							System.out.println(e.getMessage());
							continue;
						}
						System.out.print("Digite o id do produto que deseja buscar:");
						int id = sc.nextInt();
						Produto produto = null;
						try {
							produto = estoque.getProduto(id);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.println(produto.toString());
					}
					
					else if(opcao3 == 4) {
						System.out.println();
						System.out.println("<=Estoque=>");
						try {
							eg.listar(estoque);
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
						
					}
					
					else if(opcao3 == 5) {
						System.out.println();
						System.out.print("Digite o nome do produto:");
						String nome = sc.nextLine();
						System.out.print("Digite a nova quantidade:");
						int quantidade = sc.nextInt();
						try {
							eg.atualizarEstoque(estoque, nome, quantidade);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.println();
						System.out.println("Quantidade atualizada");
					}
					
					else if(opcao3 == 6) {
						System.out.println();
						System.out.print("Digite o nome do produto:");
						String nome = sc.nextLine();
						System.out.print("Digite o novo preço:R$");
						double valor = sc.nextInt();
						try {
							eg.atualizarPreco(estoque, nome, valor);
						}catch(Exception e) {
							System.out.println();
							System.out.println(e.getMessage());
							continue;
						}
						System.out.println();
						System.out.println("Preço atualizado");
					}
					
					else if(opcao3 == 7) {
						System.out.println();
						System.out.println("<< Voltando...");
						System.out.println();
						break;
					}
				}
			}
			
			else if(opcao == 3) {
				System.out.println("Encerrando...");
				sc.close();
				break;
			}
			
			
		}

	}

}
