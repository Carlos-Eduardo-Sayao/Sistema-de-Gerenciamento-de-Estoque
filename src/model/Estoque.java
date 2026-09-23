package model;
import java.util.ArrayList;
import java.util.List;

import excessoes.ProdutoNaoEncontradoException;

public class Estoque <T>{
	private List <T> estoque;
	
	public Estoque() {
		estoque = new ArrayList<>();
	}
	
	public List<T> getEstoque(){
		return this.estoque;
	}
	
	public T getProduto(int id) {
		if(id < 0 || id >= estoque.size()) {
			throw new ProdutoNaoEncontradoException("Produto não encontrado no estoque");
		}
	    return estoque.get(id);
	}
	
	
}
