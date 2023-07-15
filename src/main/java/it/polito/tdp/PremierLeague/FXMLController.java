/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.ConnMax;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnConnessioneMassima"
    private Button btnConnessioneMassima; // Value injected by FXMLLoader

    @FXML // fx:id="btnCollegamento"
    private Button btnCollegamento; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinuti"
    private TextField txtMinuti; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMese"
    private ComboBox<String> cmbMese; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM1"
    private ComboBox<Match> cmbM1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM2"
    private ComboBox<Match> cmbM2; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doConnessioneMassima(ActionEvent event) {
    	
    	
    	this.txtResult.clear();
    	
    	if(this.model.getGrafo() == null) {
    		this.txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	
    	List<ConnMax> result = this.model.getConnessioneMassima();
    	
    	if(result.size()>0) {
    		this.txtResult.appendText("Coppie con connessione massima: \n");
    		for(ConnMax c : result) {
    			
    			this.txtResult.appendText("\n"+c);
    		}
    	}
    	
    	
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	Integer min = 0;
    	try {
    	    min = Integer.parseInt(this.txtMinuti.getText().strip());
    	} catch (NumberFormatException e) {
    	    txtResult.setText("Inserisci un valore numerico per i minuti.");
    	    return;
    	}
    	if (min <= 0) { 
    	    txtResult.setText("Inserisci un numero positivo.");
    	    return;
    	}
    	
    	String mese = this.cmbMese.getValue();
    	
    	if(mese == null) {
    		this.txtResult.appendText("Inserisci un mese per continuare.");
    		return;
    	}
    	
    	this.model.creaGrafo(mese, min);
    	
    	this.txtResult.appendText(this.model.infoGrafo());
    	
    	this.btnCollegamento.setDisable(false);
    	this.btnConnessioneMassima.setDisable(false);
    }

    @FXML
    void doCollegamento(ActionEvent event) {
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConnessioneMassima != null : "fx:id=\"btnConnessioneMassima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCollegamento != null : "fx:id=\"btnCollegamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMinuti != null : "fx:id=\"txtMinuti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMese != null : "fx:id=\"cmbMese\" was not injected: check your FXML file 'Scene.fxml'.";        assert cmbM1 != null : "fx:id=\"cmbM1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbM2 != null : "fx:id=\"cmbM2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.btnCollegamento.setDisable(true);
    	this.btnConnessioneMassima.setDisable(true);
    	this.cmbMese.getItems().addAll("January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    }
    
    
}
