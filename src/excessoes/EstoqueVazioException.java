package excessoes;

public class EstoqueVazioException extends RuntimeException{
	public EstoqueVazioException(String  message) {
		super(message);
	}
}
