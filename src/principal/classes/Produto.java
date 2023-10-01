package principal.classes;

import java.util.Scanner;

public class Produto {
	static Scanner leitor = new Scanner(System.in);
	public String nome;
	public String codigo;
	public Float preco;

	public Produto() {};
	
	public Produto(String codigo) {
		this.codigo = codigo;
	};
	
	public Produto(String nome, String codigo, float preco) {
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
	}
	
	@Deprecated public static Produto cadastrarProduto() {

			
				System.out.println("Informe: ");
				System.out.println("Nome: ");
				String nome = leitor.nextLine();
				System.out.println("Codigo: ");
				String codigo = leitor.nextLine();
				System.out.println("Preco: ");
				Float preco = Float.parseFloat(leitor.nextLine());
				
				return new Produto(nome, codigo, preco);
		
		
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCodigo() {
		return this.codigo;
	}

	
	public float getPreco() {
		return this.preco;
	}
	
	
	@Override 
	public boolean equals(Object other) {
		if (other == null || !other.getClass().equals(Produto.class)) {
			return false;
		}
		else {
			Produto otherprod = (Produto)other;
			if ((otherprod.getCodigo().equals(this.codigo))) {
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		return this.codigo.hashCode();
	}
	
	@Override
	public String toString() {
		return "Nome : " + nome +
				"\nCodigo : " + codigo +
				"\nPreco do produto :" + preco;
	}

}
