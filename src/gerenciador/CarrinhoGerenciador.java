package gerenciador;
import java.util.Iterator;

import excessoes.CarrinhoVazioException;
import excessoes.ProdutoNaoEncontradoException;
import excessoes.QuantidadeIndisponivelException;
import interfaces.GetterSetters;
import model.Carrinho;
import model.Estoque;
import model.Produto;
import model.ProdutoCarrinho;
public class CarrinhoGerenciador {
	public <T extends GetterSetters > void adicionar(Carrinho<T> carrinho , T produtoCarrinho, int quantidade) {
		for(T produto : carrinho.getCarrinho()) {
			if(produto.getId() == produtoCarrinho.getId()) {
				produto.setQuantidade(produto.getQuantidade()+quantidade);
				return;
			}
		}
		carrinho.getCarrinho().add(produtoCarrinho);
	}
	
	public void remover(Carrinho<ProdutoCarrinho> carrinho,Estoque<Produto> estoque,int id,int quantidade) {

	    Iterator<ProdutoCarrinho> it = carrinho.getCarrinho().iterator();
	    boolean encontrado = false;

	    while (it.hasNext()) {

	        ProdutoCarrinho produtoCarrinho = it.next();

	        if (produtoCarrinho.getId() == id) {
	        	encontrado = true;

	            if (quantidade > produtoCarrinho.getQuantidade()) {
	                throw new QuantidadeIndisponivelException("Quantidade maior do que a disponível no carrinho");
	            }

	            
	            for (Produto produtoEstoque : estoque.getEstoque()) {

	                if (produtoEstoque.getId() == id) {

	                    produtoEstoque.setQuantidade(
	                        produtoEstoque.getQuantidade() + quantidade
	                    );

	                    break;
	                }
	            }

	            
	            if (quantidade == produtoCarrinho.getQuantidade()) {
	                it.remove();
	            } 
	            
	            else {
	                produtoCarrinho.setQuantidade(
	                    produtoCarrinho.getQuantidade() - quantidade
	                );
	            }

	            return;
	        }
	    }
	    if (!encontrado) {
	        throw new ProdutoNaoEncontradoException("Produto não encontrado");
	    }
	    
	}
	
	public <T> void listarCarrinho(Carrinho <T> carrinho) {
		if(carrinho.getCarrinho().isEmpty()) {
			throw new CarrinhoVazioException("O carrinho está vázio");
		}
		for(T produtoCarrinho : carrinho.getCarrinho()) {
			System.out.println(produtoCarrinho.toString());
		}
	}
	
	public <T extends GetterSetters> void verificaQuantidade(T produto,int quantidade) {
		if(quantidade > produto.getQuantidade()) {
			throw new QuantidadeIndisponivelException("Quantidade maior do que a disponível no estoque");
		}
	}
	
	public <T extends GetterSetters> double calcularTotal(Carrinho <T> carrinho) {
		double valorTotal = 0;
		for(T produto : carrinho.getCarrinho()) {
			double valorTotalProduto = produto.getValor() * produto.getQuantidade();
			valorTotal += valorTotalProduto;
		}
		return valorTotal;
	}
	
	public <T> void esvaziarCarrinho(Carrinho <T> carrinho) {
		carrinho.getCarrinho().clear();
	}
}
