package gerenciador;

import excessoes.EstoqueVazioException;
import excessoes.PrecoInvalidoException;
import excessoes.ProdutoNaoEncontradoException;
import interfaces.GetterSetters;
import model.Estoque;

public class EstoqueGerenciador {
	public <T extends GetterSetters> void adicionar(Estoque<T> estoque,T produto) {
		if(produto.getValor() < 0 ) {
			throw new PrecoInvalidoException("Preço Inválido");
		}
		estoque.getEstoque().add(produto);
	}
	
	public <T extends GetterSetters> void remover(Estoque<T> estoque, T produto) {
		estoque.getEstoque().remove(produto);
		for(T p : estoque.getEstoque()) {
			p.setId(p.getId() - 1);
		}
	}
	
	public <T> String buscar(Estoque<T> estoque,int id) {
		return estoque.getEstoque().get(id).toString();
	}
	
	public <T> void listar(Estoque<T> estoque) {
		if(estoque.getEstoque().isEmpty()) {
			throw new EstoqueVazioException("O estoque está vázio");
		}
		for(T produto : estoque.getEstoque()) {
			System.out.println(produto.toString());
		}
	}
	
	public <T> void estoqueVazio(Estoque<T> estoque) {
		if(estoque.getEstoque().isEmpty()) {
			throw new EstoqueVazioException("O estoque está vázio");
		}
	}
	
	public <T extends GetterSetters> void atualizarQuantidade(Estoque<T> estoque , int quantidade , int id) {
		for(T p : estoque.getEstoque()) {
			if(p.getId() == id) {
				if(quantidade == p.getQuantidade()) {
					p.setQuantidade(0);
				}else {
					p.setQuantidade(p.getQuantidade()-quantidade);
				}
			}
		}
	}
	
	public <T extends GetterSetters> void atualizarEstoque(Estoque<T> estoque , String nome , int quantidade) {
		for(T p : estoque.getEstoque()) {
			if(p.getNome().equals(nome)) {
				p.setQuantidade(quantidade);
				return;
			}
		}
		throw new ProdutoNaoEncontradoException("Produto não encontrado para atualizar");
	}
	
	public <T extends GetterSetters> void atualizarPreco(Estoque<T> estoque , String nome , double valor) {
		for(T p : estoque.getEstoque()) {
			if(p.getNome().equals(nome)) {
				p.setValor(valor);
				return;
			}
		}
		throw new ProdutoNaoEncontradoException("Produto não encontrado para atualizar");
	}
	
	
	
	
	
}
