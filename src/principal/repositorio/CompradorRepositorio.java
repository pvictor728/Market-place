package principal.repositorio;

import principal.classes.Comprador;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



public class CompradorRepositorio {
	
	//Padrão Singleton
	public static final CompradorRepositorio INSTANCE;
	
	private Map<String,Comprador> listaCompradores;
	
	static {
		INSTANCE = new CompradorRepositorio();
	}
	
	public static CompradorRepositorio getInstance() {
		return INSTANCE;
	}
	
	public Comprador pegarComprador(String cpf) {
		return this.listaCompradores.get(cpf);
	}
	
	public boolean consultarCompradorCpf(String cpf) {
		if (this.listaCompradores.containsKey(cpf)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Optional<Comprador> obterComprador(String cpf) {
		return Optional.ofNullable(listaCompradores.get(cpf));
	}
	
	public void adicionarComprador(Comprador comprador) {
		this.listaCompradores.putIfAbsent(comprador.getCpf(), comprador);
	}

	private CompradorRepositorio() {
		this.listaCompradores = new HashMap<>();
	}

}
