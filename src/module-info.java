module Avaliacao_lp2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	exports principal;
	exports principal.controlador;
	
	opens principal.classes to javafx.base;
	opens principal.controlador to javafx.fxml;
}
