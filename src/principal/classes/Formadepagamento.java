package principal.classes;

public abstract class Formadepagamento {
	
	protected enum Forma{
		PIX, BOLETO, DEBITO, CREDITO;
	}
	
	protected Forma form;
	
	public Forma retornarForma() {
		return this.form;
	}
	
	public abstract boolean transacao(Comprador comp,Vendedor vend);
	
}
