package model;
import java.util.List;

import excessoes.ProdutoNaoEncontradoException;

import java.util.ArrayList;
public class Carrinho<T>{
	private List<T> carrinho;
	
	public Carrinho() {
		carrinho = new ArrayList<>();
	}
	
	public List<T> getCarrinho(){
		return this.carrinho;
	}
	
	public T getProduto(int id) {
		T p = carrinho.get(id);
		if(carrinho.contains(p) == false) {
			throw new ProdutoNaoEncontradoException("Produto não encontrado no carrinho");
		}
		return carrinho.get(id);
	}
}
