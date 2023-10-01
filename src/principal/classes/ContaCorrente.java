package principal.classes;


public class ContaCorrente {
	private String numeroConta;
	private String Agencia;
	private String DataAbertura;
	private float Saldo;

	
	public ContaCorrente() {
		this.numeroConta = "Nao informado";
		this.Agencia = "Nao informado";
		this.DataAbertura = "Nao informado";
		this.Saldo = 0;
	}
	
	public ContaCorrente(String numeroConta, String Agencia, 
						 String DataAbertura, float Saldo) {
		this.numeroConta = numeroConta;
		this.Agencia = Agencia;
		this.DataAbertura = DataAbertura;
		this.Saldo = Saldo;
	}
	
	
	public String getNumeroConta() {
		return this.numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public String getAgencia() {
		return this.Agencia;
	}
	
	public void setAgencia(String agencia) {
		this.Agencia = agencia;
	}
	
	public String getDataAbertura() {
		return DataAbertura;
	}
	
	public float getSaldo() {
		return Saldo;
	}
	

	public void setDataAbertura(String dataAbertura) {
		this.DataAbertura = dataAbertura;
	}
	
	
	public void Sacar(float valor) {
			Saldo -= valor;	
	}
	
	public void depositar(float valor) {
		Saldo += valor;
	}
	
	@Deprecated public double calcularendimento(double taxa) {
		double rend = Saldo + (Saldo * (taxa/100));
		System.out.println("O rendimento de "+ taxa +"% em cima do saldo de "+ Saldo +" reais pertencente a conta "+ numeroConta +" e de "+ rend +" reais");
		return rend;
	}
	


	@Override
	public String toString() {
		return "Numero da Conta = " + numeroConta + "\nAgencia = " + Agencia 
				+ "\nDataAbertura = " + DataAbertura
				+ "\nSaldo = " + Saldo;
	}

	


}
