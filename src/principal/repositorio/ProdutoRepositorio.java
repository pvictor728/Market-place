package principal.repositorio;

import java.util.Map;
import java.util.HashMap;
import principal.classes.Vendedor;

import principal.classes.Produto;

public class ProdutoRepositorio {
	
	//Padrão Singleton
	public static final ProdutoRepositorio INSTANCE;
	
	private Map<String,Produto> listaProdutos;
	
	
	static {
		INSTANCE = new ProdutoRepositorio();
	}

	public static ProdutoRepositorio getInstance() {
		return INSTANCE;
	}
	
	public boolean existeProduto(String codigo) {
		if (this.listaProdutos.containsKey(codigo)) {
			return true;
		}
		
		return false;
	}
	
	public void consultarProdutoCodigo(String codigo) {
		
		if (this.listaProdutos.containsKey(codigo)) {
			System.out.println("Dados do produto: \n" + this.listaProdutos.get(codigo));
			
		}else {
			System.out.println("Nao foi encontrado nenhum produto com esse codigo.");
		}
	}
	
	public void adicionarProduto(Vendedor vend, Produto produto) {
		if (this.listaProdutos.containsKey(produto.getCodigo())) {
			System.out.println("Produto já listado");
		}else {
			this.listaProdutos.putIfAbsent(produto.getCodigo(), produto);
			vend.addProdutoLista(produto);
		}
			
	}


	private ProdutoRepositorio() {
		this.listaProdutos = new HashMap<>();
	}

}
