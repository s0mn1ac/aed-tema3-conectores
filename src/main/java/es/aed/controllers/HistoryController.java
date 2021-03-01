package es.aed.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HistoryController implements Initializable {
	
	@FXML
	private VBox view;
	
	@FXML
	private TextField idTextField;
	
	@FXML
	private TextField idCoinTextField;
	
	@FXML
	private TextField dateTextField;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private TableView<String> tableView;
	
	@FXML
	private TableColumn<String, String> idhistoricocambioeuro;
	
	@FXML
	private TableColumn<String, String> fkidmoneda;
	
	@FXML
	private TableColumn<String, String> fecha;
	
	@FXML
	private void onClickAddButton(ActionEvent e) {
		
	}
	
	@FXML
	private void onClickUpdateButton(ActionEvent e) {
		
	}
	
	@FXML
	private void onClickDeleteButton(ActionEvent e) {
		
	}
	
	public HistoryController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/HistoryView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return view;
	}
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
