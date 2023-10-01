package principal;


import principal.classes.Comprador;
import principal.classes.Vendedor;
import principal.classes.Produto;
import principal.repositorio.CompradorRepositorio;
import principal.repositorio.ProdutoRepositorio;
import principal.repositorio.VendedorRepositorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class Main extends Application {

	private static Stage stage;
	private static Scene primeiratela;
	private static Scene segundatela;
	
	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		primaryStage.setTitle("Sistema de Compra e Venda");
		instanciar_dados();
		
		Parent fxmlprimeiratela = FXMLLoader.load(getClass().getResource("/principal/tela/Tela.fxml"));
		primeiratela = new Scene(fxmlprimeiratela);
		
		Parent fxmlsegundatela = FXMLLoader.load(getClass().getResource("/principal/tela/Tela2.fxml"));
		segundatela = new Scene(fxmlsegundatela);
		
		primaryStage.setScene(primeiratela);
		primaryStage.show();
	}
	
	public static void mudartela(String scr) {
		switch (scr) {
		
		case "primeira":
			stage.setScene(primeiratela);
			break;
			
		case "segunda":
			stage.setScene(segundatela);
			break;
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
	
	private void instanciar_dados() {

		Comprador Pedro = new Comprador("Pedro", "702.003.214-12", "54366-7", "1668-3", "28/05", 1000);
		Comprador Herbet = new Comprador("Herbet", "584.001.320-08", "47236-4", "2874-1", "12/01", 1000);
		Comprador Andre = new Comprador("Andre", "120.002.752-13", "82654-0", "8236-2", "15/05", 1000);
		Comprador Joao = new Comprador("Joao", "240.000.480-24", "21478-5", "3214-4", "08/09", 1000);
		Comprador Lucas = new Comprador("Lucas", "850.368.475-23", "4458-8", "6571-5", "25/12", 1000);
		
		CompradorRepositorio.getInstance().adicionarComprador(Pedro);
		CompradorRepositorio.getInstance().adicionarComprador(Herbet);
		CompradorRepositorio.getInstance().adicionarComprador(Andre);
		CompradorRepositorio.getInstance().adicionarComprador(Joao);
		CompradorRepositorio.getInstance().adicionarComprador(Lucas);
		
		Vendedor LojaAnimais = new Vendedor("Petlandia", "12345-6", "4545-8", "11/02", 2000, "25.740.128/8888-22");
		Vendedor LojaMoveis = new Vendedor("NatalMoveis", "65432-1", "8585-4", "02/10", 2000, "99.888.777/6666-55");
		Vendedor Drogaria = new Vendedor("PagMais", "54545-5", "7474-7", "05/07", 2000, "42.658.421/7785-12");
	
		VendedorRepositorio.getInstance().adicionarVendedor(LojaAnimais);
		VendedorRepositorio.getInstance().adicionarVendedor(LojaMoveis);
		VendedorRepositorio.getInstance().adicionarVendedor(Drogaria);

		Produto Gato = new Produto("Gato", "01234", 300);
		Produto Galinha = new Produto("Galinha", "23645", 30);
		Produto Cachorro = new Produto("Cachorro", "58741", 1000);
		
		ProdutoRepositorio.getInstance().adicionarProduto(LojaAnimais, Cachorro);
		ProdutoRepositorio.getInstance().adicionarProduto(LojaAnimais, Galinha);
		ProdutoRepositorio.getInstance().adicionarProduto(LojaAnimais, Gato);
		
		Produto Mesa = new Produto("Mesa", "23854", 200);
		Produto Cadeira = new Produto("Cadeira", "52369", 50);
		Produto Cama = new Produto("Cama", "44755", 300);
		
		ProdutoRepositorio.getInstance().adicionarProduto(LojaMoveis, Mesa);
		ProdutoRepositorio.getInstance().adicionarProduto(LojaMoveis, Cadeira);
		ProdutoRepositorio.getInstance().adicionarProduto(LojaMoveis, Cama);
		
		Produto Dorflex = new Produto("Dorflex", "98745", 5);
		Produto Dipirona = new Produto("Dipirona", "54786", 10);
		Produto Nimesulida = new Produto("Nimesulida", "22334", 8);
		
		ProdutoRepositorio.getInstance().adicionarProduto(Drogaria, Dorflex);
		ProdutoRepositorio.getInstance().adicionarProduto(Drogaria, Dipirona);
		ProdutoRepositorio.getInstance().adicionarProduto(Drogaria, Nimesulida);
	}
}
