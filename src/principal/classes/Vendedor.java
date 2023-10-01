package principal.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Vendedor extends Pessoa {
	static Scanner leitor = new Scanner(System.in);
	private String cnpj;
	protected List<Float> valoresAReceber = new ArrayList<>();
	protected Map<String, Integer> vendasRealizadas = new HashMap<>();
	protected List<Produto> listaDeProdutos = new ArrayList<>();
	
	
	
	public Vendedor() {
		super();
		this.valoresAReceber = new ArrayList<>();
		this.vendasRealizadas = new HashMap<>();
		this.listaDeProdutos = new ArrayList<>();
	}

	public Vendedor(String nome,String Conta, String Agencia, 
					String DataAbertura, float Saldo, String cnpj) {
		
		super(nome,Conta, Agencia, DataAbertura, Saldo);
		this.cnpj = cnpj;
		this.valoresAReceber = new ArrayList<>();
		this.vendasRealizadas = new HashMap<>();
		this.listaDeProdutos = new ArrayList<>();
	}
	
	public Vendedor(String cnpj) {
		super();
		this.cnpj = cnpj;
		this.valoresAReceber = new ArrayList<>();
		this.vendasRealizadas = new HashMap<>();
		this.listaDeProdutos = new ArrayList<>();
	}
	
	@Deprecated public static Vendedor cadastrarVendedor() {
			
		
			System.out.println("Informe os dados: ");
			System.out.println("Nome: ");
			String nome = leitor.nextLine();
			System.out.println("Cnpj: ");
			String cnpj = leitor.nextLine();
			System.out.println("Numero da conta: ");
			String conta = leitor.nextLine();
			System.out.println("Agencia: ");
			String agencia = leitor.nextLine();
			System.out.println("Data de abertura da conta: ");
			String dataAbertura = leitor.nextLine();
			System.out.println("Saldo da conta: ");
			Float saldo = Float.parseFloat(leitor.nextLine());
			
			
			return new Vendedor(nome, conta, agencia, dataAbertura, saldo, cnpj);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public void addValorAReceber(float valor) {
		this.valoresAReceber.add(valor);
	}
	
	public void removerValorAReceber(float valor) {
		if (this.valoresAReceber.contains(valor)) {
			this.valoresAReceber.remove(this.valoresAReceber.indexOf(valor));
		}
		else {
			System.out.println("Valor n�o cadastrado.");
		}
	}
	
	
	public void addProdutoLista(Produto produto) {
		if (this.listaDeProdutos.contains(produto)) {
			System.out.println("Produto ja esta listado.");
		}else {
			this.listaDeProdutos.add(produto);
		}
	}
	
	public Collection<Produto> obterProdutolista() {
		if(this.listaDeProdutos == null) {
			return Collections.emptyList();
		}else {
			return this.listaDeProdutos;
		}
	}
	
	
	@Deprecated public void consultarProduto(String cod) {
		Produto produto = new Produto(cod);
		if (this.listaDeProdutos.contains(produto)) {
			System.out.println(this.listaDeProdutos.get(this.listaDeProdutos.indexOf(produto)).toString());
		}else {
			System.out.println("Produto n�o encontrado.");
		}
	}
	
	public void consultarProduto(Produto produto) {
		if (this.listaDeProdutos.contains(produto)) {
			System.out.println(this.listaDeProdutos.get(this.listaDeProdutos.indexOf(produto)).toString());
		}else {
			System.out.println("Produto nao encontrado.");
		}
	}
	
	
	 public void removerProdutoLista(String cod) {
		Produto produto = new Produto(cod);
		if (this.listaDeProdutos.contains(produto)) {
			this.listaDeProdutos.remove(this.listaDeProdutos.get(this.listaDeProdutos.indexOf(produto)));
			System.out.println("Produto removido com sucesso.");
		}else {
			System.out.println("Produto n�o encontrado.");
		}
	}
	

	@Override
	public boolean equals(Object other) {
		if (other == null || !other.getClass().equals(Vendedor.class)){
			return false;
		}
		Vendedor otherVendedor = (Vendedor)other;
		if (otherVendedor.getCnpj() == this.cnpj) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.cnpj.hashCode();
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() +
				"\nCNPJ: " + this.getCnpj() +
				"\nSaldo: " + this.conta.getSaldo() +
		"\nValores a receber: " + valoresAReceber +
		"\nVendas Realizadas: " + vendasRealizadas +
		"\nLista de produtos: " + listaDeProdutos;
	}
	

}
