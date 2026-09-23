package model;

import interfaces.GetterSetters;

public class Produto implements GetterSetters{
	private int id;
	private String nome;
	private double valor;
	private int quantidade;
	
	public Produto(int id, String nome , double valor, int quantidade) {
		this.setId(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setQuantidade(quantidade);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String toString() {
		return String.format("Id:%d -- %s -- R$%.2f --%dx", getId(),getNome(),getValor(),getQuantidade());
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
