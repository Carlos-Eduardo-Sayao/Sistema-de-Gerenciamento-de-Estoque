package pagamentos;

import interfaces.Pagamento;

public class PagamentoCartao implements Pagamento{
	@Override
	public void pagamento(double valor) {
		System.out.println("Cartão:-R$"+valor);
	}
}
