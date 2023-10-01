package principal.controlador;

import java.time.LocalDate;
import principal.classes.Produto;
import principal.classes.Vendedor;
import principal.classes.Comprador;
import principal.classes.Formadepagamento;
import principal.classes.Credito;
import principal.classes.Debito;
import principal.Main;
import principal.classes.Boleto;
import principal.classes.Pix;
import principal.repositorio.CompradorRepositorio;
import principal.repositorio.VendedorRepositorio;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class TelaControlador {
	@FXML
	private Formadepagamento bol = new Boleto();
	
	@FXML
	private Formadepagamento pix = new Pix();
	
	@FXML
	private Formadepagamento cred = new Credito();
	
	@FXML
	private Formadepagamento deb = new Debito();
	
	@FXML
	private TextField CpfInput;
	
	@FXML
	private TextField CnpjInput;
	
	@FXML
	private Label NomeComprador;
	
	@FXML
	private Label NomeVendedor;
	
	@FXML
	private ComboBox<Produto> ProdutList;
	
	@FXML
	private ComboBox<Formadepagamento> PayList;
	
	@FXML 
	private DatePicker DateInsert;
	
	@FXML
	private Button AddCart;
	
	@FXML
	private Button CheckOut;
	
	@FXML
	private TableView<Produto> TableInsert;
	
	@FXML 
	private TableColumn<Produto, String> CodigoColum;

	@FXML 
	private TableColumn<Produto, String> NomeColum;

	@FXML 
	private TableColumn<Produto, Float> PrecoColum;
	
	@FXML
	private Menu Transacao;
	
	@FXML
	private Menu Dados;
	
	@FXML
	private Menu Sair;

	@FXML 
	private Button ButtomCPF;
	
	@FXML
	private Button ButtomCNPJ;

	@FXML 
	private Label labelpolimorfico;

	@FXML 
	private Label excessaolabeldata;

	
	@FXML
	public void initialize() {
		this.CpfInput.focusedProperty().addListener(this::IdentificarComprador);
		this.CnpjInput.focusedProperty().addListener(this::IdentificarVendedor);
		this.PayList.getItems().add(bol);
		this.PayList.getItems().add(pix);
		this.PayList.getItems().add(cred);
		this.PayList.getItems().add(deb);
		
		this.CodigoColum.setCellValueFactory(new PropertyValueFactory<Produto,String>("codigo"));
		this.NomeColum.setCellValueFactory(new PropertyValueFactory<Produto,String>("nome"));		
		this.PrecoColum.setCellValueFactory(new PropertyValueFactory<Produto,Float>("preco"));

	}
	
	@FXML public void CPFclicked() {
		NomeComprador.setText(CompradorRepositorio.getInstance().obterComprador(CpfInput.getText()).map(Comprador::getNome).orElse("Comprador não identificado"));
		this.TableInsert.getItems().clear();
	}



	@FXML public void CNPJclicked() {
		NomeVendedor.setText(VendedorRepositorio.getInstance().obterVendedor(CnpjInput.getText()).map(Vendedor::getNome).orElse("Vendedor não identificado"));
		try {
			this.ProdutList.getItems().addAll(VendedorRepositorio.getInstance().retornarUMvendedor(CnpjInput.getText()).obterProdutolista());

		if (this.ProdutList.getItems().size() > 3) { //No momento em que um vendedor é trocado por outro sem chamada da excessão, a tabela de produtos é limpada
			this.ProdutList.getItems().remove(0, 3);
		}
		}catch (Exception e) {
			this.ProdutList.getItems().clear(); //Após o lançamento de uma exceção e nenhum vendedor identificado, tabela de produtos é limpa
		}
	}
	
	
	
	private void IdentificarComprador(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (!newValue) {
			if(this.labelpolimorfico.isVisible()) {
				this.labelpolimorfico.setVisible(false);
			}
		}
		
	}
	
	private void IdentificarVendedor(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (!newValue) {
			if(this.labelpolimorfico.isVisible()) {
				this.labelpolimorfico.setVisible(false);
			}
		}
	}

	@FXML public void changedpay() {
		if (this.PayList.getValue().equals(bol)) {
			this.DateInsert.setVisible(true);
		}else {
			this.DateInsert.setVisible(false);
		}
	}

	@FXML 
	public void buttomcarclicked() {
		if(CompradorRepositorio.getInstance().consultarCompradorCpf(this.CpfInput.getText())) {
			
			if(VendedorRepositorio.getInstance().consultarVendedorCnpj(this.CnpjInput.getText())) {
				
				if (this.ProdutList.getValue() != null) {
					
					TableInsert.getItems().add(this.ProdutList.getValue());
					CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()).AdicionaraoCarrinho(this.ProdutList.getValue());
					
				}else {
					this.labelpolimorfico.setText("Nenhum produto selecionado");
					this.labelpolimorfico.setVisible(true);
				}
			}		
		}
	}

	@FXML 
	public void clickedcheckout() {
		
		if(CompradorRepositorio.getInstance().consultarCompradorCpf(this.CpfInput.getText())) {
			
			if( CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()).tamanhodocarrinho() ) {
			
			
				if(this.PayList.getValue() != null) {
					boolean aux;
					
					if(this.PayList.getValue().retornarForma() == pix.retornarForma()) {
						
						aux = pix.transacao(CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()), 
										VendedorRepositorio.getInstance().retornarUMvendedor(this.CnpjInput.getText()));
						
						this.TableInsert.getItems().clear();
						CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()).LimparCarrinho();
						
						if(aux == true) {
							this.labelpolimorfico.setText("Compra Finalizada");
							this.labelpolimorfico.setVisible(true);
							
						}else {
							this.labelpolimorfico.setText("Saldo do comprador insuficiente");
							this.labelpolimorfico.setVisible(true);
						}
					}else if(this.PayList.getValue().retornarForma() == bol.retornarForma()) {
						
						if(this.DateInsert.getValue() != null) {
							LocalDate dataAtual = LocalDate.now();
							try {
								if(this.DateInsert.getValue().isAfter(dataAtual) ||this.DateInsert.getValue().isEqual(dataAtual) ) {
									
									if(this.excessaolabeldata.isVisible()) {
										this.excessaolabeldata.setVisible(false);
									}
									
									aux = bol.transacao(CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()), 
											VendedorRepositorio.getInstance().retornarUMvendedor(this.CnpjInput.getText()));
									
									this.TableInsert.getItems().clear();
									CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()).LimparCarrinho();
									
									if(aux == true) {
										this.labelpolimorfico.setText("Compra Finalizada");
										this.labelpolimorfico.setVisible(true);
										
									}else {
										this.labelpolimorfico.setText("Saldo do comprador insuficiente");
										this.labelpolimorfico.setVisible(true);
									}
									
								}else {
									throw new Exception("Erro na inserção da data do boleto"
											+ "\nInforme uma data atual/futura");
								}
							}catch(Exception e) {								
								this.excessaolabeldata.setText(e.getMessage());
								this.excessaolabeldata.setVisible(true);
								
							}						
						}else {
							this.labelpolimorfico.setText("Informe o vencimento do boleto");
							this.labelpolimorfico.setVisible(true);
						}
					}else if(this.PayList.getValue().retornarForma() == cred.retornarForma()) {
						cred.transacao(CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()), 
											VendedorRepositorio.getInstance().retornarUMvendedor(this.CnpjInput.getText()));
						
						this.TableInsert.getItems().clear();
						CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()).LimparCarrinho();
						
					
						this.labelpolimorfico.setText("Compra Finalizada");
						this.labelpolimorfico.setVisible(true);
					
					}else if(this.PayList.getValue().retornarForma() == deb.retornarForma()) {
						aux = deb.transacao(CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()), 
								VendedorRepositorio.getInstance().retornarUMvendedor(this.CnpjInput.getText()));
						
						this.TableInsert.getItems().clear();
						CompradorRepositorio.getInstance().pegarComprador(this.CpfInput.getText()).LimparCarrinho();
						
						if(aux == true) {
							this.labelpolimorfico.setText("Compra Finalizada");
							this.labelpolimorfico.setVisible(true);
							
						}else {
							this.labelpolimorfico.setText("Saldo do comprador insuficiente");
							this.labelpolimorfico.setVisible(true);
						}			
					}
				}else {
					this.labelpolimorfico.setText("Selecione o método de pagamento");
					this.labelpolimorfico.setVisible(true);
				}
			}else {
				this.labelpolimorfico.setText("Carrinho do comprador Vazio");
				this.labelpolimorfico.setVisible(true);
			}
		}else {
			this.labelpolimorfico.setText("Comprador não existe");
			this.labelpolimorfico.setVisible(true);
		}
	}

	
	@FXML public void daprincipalparasegunda() {
		Main.mudartela("segunda");
	}



	


	


 
 
 
 
}
