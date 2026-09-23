package pagamentos;

import interfaces.Pagamento;

public class PagamentoPix implements Pagamento{
	@Override
	public void pagamento(double valor) {
		System.out.println("Pix:-R$"+valor);
	}
}
