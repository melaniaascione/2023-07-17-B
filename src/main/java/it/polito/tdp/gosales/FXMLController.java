package it.polito.tdp.gosales;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.Model;
import it.polito.tdp.gosales.model.Products;
import it.polito.tdp.gosales.model.Retailers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {

    private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbBrand;

    @FXML
    private ComboBox<String> cmbProdotto;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextArea txtResGrafo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaPercorso(ActionEvent event) {

    }
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	int anno = this.cmbAnno.getValue();
    	String brand = this.cmbBrand.getValue();
    	
    	if(brand!="" && anno!=0) {
    		this.model.creaGrafo(brand, anno);
    	}else if(brand=="") {
    		this.txtResult.setText("inserire un brand");
    	}else if(anno == 0) {
    		this.txtResult.setText("selezionare un anno");
    	}
    	
    	List<Products> vertici = this.model.getVertici(brand);
    	
    	this.txtResGrafo.setText("Num Nodi: " + vertici.size() + "\nNum Archi: " + this.model.nArchi());
    	
    	this.txtArchi.setText("Top 3 archi: \n");
        List<Arco> topArchi = this.model.getTopArchi();
        for (Arco a : topArchi)
            txtArchi.appendText(a+"\n");
    	
    }

    @FXML
    void initialize() {
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBrand != null : "fx:id=\"cmbColore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProdotto != null : "fx:id=\"cmbProdotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArchi != null : "fx:id=\"txtArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResGrafo != null : "fx:id=\"txtResGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	this.cmbAnno.getItems().add(2015);
    	this.cmbAnno.getItems().add(2016);
    	this.cmbAnno.getItems().add(2017);  
    	this.cmbAnno.getItems().add(2018);
    	this.cmbBrand.getItems().addAll(this.model.getBrand());
    }

}
