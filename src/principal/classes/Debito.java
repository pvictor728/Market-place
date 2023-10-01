package principal.classes;

import java.util.Map;

public class Debito extends Formadepagamento {
	
	private final float TAXA_PERCENTUAL_CARTAO = 2;

	public Debito() {
		this.form = Forma.DEBITO;
	}
	
	@Override
	public boolean transacao(Comprador comp,Vendedor vend) {	
		float valoresapagar = 0;
		for (Map.Entry<Produto, Integer> entry : comp.CarrinhodeCompras.entrySet()) {
			Produto key = entry.getKey();
			Integer value = entry.getValue();
			valoresapagar += (key.getPreco() * value);
		}
		
		float valor = valoresapagar + (valoresapagar * (TAXA_PERCENTUAL_CARTAO/100));
		if (valor > comp.conta.getSaldo()) {
			return false;
		}else {
			comp.conta.Sacar(valor);
			vend.conta.depositar(valoresapagar);
			for (Map.Entry<Produto, Integer> entry : comp.CarrinhodeCompras.entrySet()) {
				Produto key = entry.getKey();
				Integer value = entry.getValue();
				comp.comprasRealizadas.put(key.getNome(), comp.comprasRealizadas.getOrDefault(key.getNome(), 0) + value);
				vend.vendasRealizadas.put(key.getNome(), vend.vendasRealizadas.getOrDefault(key.getNome(), 0) + value);
			}
			return true;
		}
	}
	
	@Override
	public String toString() {
		return "Debito";
	}

}
