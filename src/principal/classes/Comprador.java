package principal.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Comprador extends Pessoa {
	static Scanner leitor = new Scanner(System.in);
	private String cpf;
	protected Map<String, Integer> comprasRealizadas = new HashMap<>();
	protected List<Float> valoresapagar = new ArrayList<>();
	protected Map<Produto, Integer> CarrinhodeCompras = new HashMap<>();
	
	public Comprador() {
		super();
		this.comprasRealizadas = new HashMap<>();
		this.valoresapagar = new ArrayList<>();
	}

	public Comprador(String nome, String cpf, String Conta, String Agencia, 
			  		String DataAbertura, float Saldo) {
		super(nome,Conta,Agencia,DataAbertura, Saldo);
		this.cpf = cpf;
	}
	
	public Comprador(String cpf) {
		this.cpf = cpf;
	}

	@Deprecated public static Comprador cadastroComprador() {
		
		
			System.out.println("Informe: ");
			System.out.println("Nome: ");
			String nome = leitor.nextLine();
			System.out.println("Cpf: ");
			String cpf = leitor.nextLine();
			System.out.println("Numero da conta: ");
			String conta = leitor.nextLine();
			System.out.println("Agencia: ");
			String agencia = leitor.nextLine();
			System.out.println("Data de abertura da conta (dd/mm/aaaa): ");
			String dataAbertura = leitor.nextLine();
			System.out.println("Saldo da conta: ");
			Float saldo = Float.parseFloat(leitor.nextLine());
			
			return new Comprador(nome, cpf, conta, agencia, dataAbertura, saldo);
		
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void mostrarvaloresapagar() {
		for (Float valores: valoresapagar) {
			System.out.println(valores);
		}
	}
	
	public void mostrarcomprasRealizadas() {
		System.out.println(comprasRealizadas);
	}
	
	public void AdicionaraoCarrinho(Produto prod) {
		this.CarrinhodeCompras.put(prod, this.CarrinhodeCompras.getOrDefault(prod, 0) + 1);
	}
	
	public void LimparCarrinho() {
		this.CarrinhodeCompras.clear();
	}
	
	public boolean tamanhodocarrinho() {
		if (CarrinhodeCompras.size() == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override 
	public boolean equals(Object other) {
		if (other == null || !other.getClass().equals(Comprador.class)) {
			return false;
		}
		else {
			Comprador otherComp = (Comprador)other;
			if ((otherComp.getCpf().equals(this.cpf))) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}
	
	@Override
	public String toString() {
		
		return "Nome : " + this.getNome() +
				"\nCPF : " + this.getCpf() + 
				"\nConta :\n " + conta +
				"\nValores a pagar " + valoresapagar +
				"\nCompras realizadas " + comprasRealizadas; 
	}
}
