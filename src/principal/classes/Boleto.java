package principal.classes;

import java.util.Map;

public class Boleto extends Formadepagamento {

	private final float PRECO_EMISSAO_BOLETO = 3;
	
	public Boleto() {
		this.form = Forma.BOLETO;
	}
	
	@Override
	public boolean transacao(Comprador comp,Vendedor vend) {
		float valoresapagar = 0;
		for (Map.Entry<Produto, Integer> entry : comp.CarrinhodeCompras.entrySet()) {
			Produto key = entry.getKey();
			Integer value = entry.getValue();
			valoresapagar += (key.getPreco() * value);
		}
		float valoresapagarcomtaxa = valoresapagar + PRECO_EMISSAO_BOLETO;
		
		if (valoresapagarcomtaxa > comp.conta.getSaldo()) {
			return false;
		}else {
				comp.conta.Sacar(valoresapagarcomtaxa);
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
		return "Boleto";
	}

}
