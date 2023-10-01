package principal.classes;

public abstract class Pessoa {
	private String nome;
	protected ContaCorrente conta;
	
	public Pessoa() {}

	public Pessoa(String nome, String Conta, String Agencia, 
				  String DataAbertura, float Saldo) {
		this.nome = nome;
		this.conta = new ContaCorrente(Conta, Agencia, DataAbertura, Saldo);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void mostrarconta() {
		System.out.println(this.conta.toString());
	}
	
	@Override
	public String toString() {
		return "Nome : " + nome + 
				"Conta : "
				+ conta; 
	}


}
