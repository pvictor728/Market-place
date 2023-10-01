package principal.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import principal.Main;
import principal.repositorio.CompradorRepositorio;
import principal.repositorio.VendedorRepositorio;
import javafx.scene.control.Menu;

public class Tela2Controlador {

    @FXML
    private CheckBox CPFcheck;

    @FXML
    private CheckBox CNPJcheck;

    @FXML
    private Label mostrardados;

    @FXML
    private TextField InputAll;

    @FXML
    private Button BotaoDados;

    @FXML
    private Label labelversatil;

	@FXML Menu Transacao;

    @FXML
    void ButtonConfirm() {
    	if(this.CNPJcheck.isSelected()) {
   		
	    	if(VendedorRepositorio.getInstance().existeVendedor(this.InputAll.getText())) {
	    		this.mostrardados.setText(VendedorRepositorio.getInstance().retornarUMvendedor(this.InputAll.getText()).toString());	    	
	    		this.mostrardados.setVisible(true);
	    		this.labelversatil.setText("Vendedor listado abaixo");
	    	}else {
	    		this.labelversatil.setText("Não existe vendedor com esse CNPJ");
	    	}
	    			   	
    	}else if(this.CPFcheck.isSelected()) {
    		
    		if(CompradorRepositorio.getInstance().consultarCompradorCpf(this.InputAll.getText())) {
	    		this.mostrardados.setText(CompradorRepositorio.getInstance().pegarComprador(this.InputAll.getText()).toString());
	    		this.mostrardados.setVisible(true);
	    		this.labelversatil.setText("Comprador listado abaixo");

    		}else {
	    		this.labelversatil.setText("Não existe comprador com esse CPF");
	    	}
    	  		
    	}
    }

    @FXML
    void CNPJcheckSelected() {
    	if(this.CNPJcheck.isSelected()) {
    		
    		this.mostrardados.setText(null);
			
			if(this.CPFcheck.isSelected()) {
				this.CPFcheck.setSelected(false);
			}
			
			this.labelversatil.setText("Informe o CNPJ do vendedor a ser listado");
			this.labelversatil.setVisible(true);
			
			this.InputAll.setVisible(true);
			this.BotaoDados.setVisible(true);
			
		}else {
			this.labelversatil.setVisible(false);
			this.InputAll.setVisible(false);
			this.BotaoDados.setVisible(false);
		}
    }

    @FXML
    void CPFcheckSelected() {
		if(this.CPFcheck.isSelected()) {
			
			this.mostrardados.setText(null);
			
			if(this.CNPJcheck.isSelected()) {
				this.CNPJcheck.setSelected(false);
			}
			
			this.labelversatil.setText("Informe o CPF do comprador a ser listado");
			this.labelversatil.setVisible(true);
			
			this.InputAll.setVisible(true);
			this.BotaoDados.setVisible(true);
			
		}else {
			this.labelversatil.setVisible(false);
			this.InputAll.setVisible(false);
			this.BotaoDados.setVisible(false);
		}
    }

	@FXML public void dasegundaparaprincipal() {
		Main.mudartela("primeira");
	}

}
