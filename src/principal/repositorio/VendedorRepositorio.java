package principal.repositorio;

import principal.classes.Vendedor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class VendedorRepositorio {
	
	//Padrão Singleton
	public static final VendedorRepositorio INSTANCE;
	
	protected Map<String,Vendedor> listaVendedores = new HashMap<String,Vendedor>();
	
	static {
		INSTANCE = new VendedorRepositorio();
	}
	
	public static VendedorRepositorio getInstance() {
		return INSTANCE;
	}
	
	public boolean existeVendedor(String cnpj) {
		if (this.listaVendedores.containsKey(cnpj)) {
				return true;
		}
		return false;
	}
	
	public boolean consultarVendedorCnpj(String otherCnpj) {
		if (this.listaVendedores.containsKey(otherCnpj)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void adicionarVendedor(Vendedor vendedor) {
		this.listaVendedores.putIfAbsent(vendedor.getCnpj(), vendedor);
	}
	
	public Vendedor retornarUMvendedor(String cnpj) {
		return this.listaVendedores.get(cnpj);
	}
	
	public Optional<Vendedor> obterVendedor(String cnpj) {
		return Optional.ofNullable(listaVendedores.get(cnpj));
	}

	private VendedorRepositorio() {
		this.listaVendedores = new HashMap<>();
	}

}
