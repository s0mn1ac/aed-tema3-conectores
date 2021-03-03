package es.aed.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import es.aed.dtos.HistoryDto;
import es.aed.models.HistoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class HistoryController implements Initializable {
	
	private HistoryModel historyModel = new HistoryModel();
	
	private HistoryDto selectedHistory;
	
	@FXML
	private VBox view;
	
	@FXML
	private TextField idTextField;
	
	@FXML
	private ComboBox<Integer> idCoinComboBox;
	
	@FXML
	private DatePicker dateDatePicker;
	
	@FXML
	private TextField euroValueTextField;
	
	@FXML
	private Button resetButton;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private TableView<HistoryDto> tableView;
	
	@FXML
	private TableColumn<HistoryDto, Integer> idHistory;
	
	@FXML
	private TableColumn<HistoryDto, Integer> idCoin;
	
	@FXML
	private TableColumn<HistoryDto, Date> date;
	
	@FXML
	private TableColumn<HistoryDto, Float> euroValue;
	
	@FXML
	private void onClickResetButton(ActionEvent e) {
		this.clearTextFields();
		this.getAllHistory();
		this.selectedHistory = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	@FXML
	private void onClickAddButton(ActionEvent e) {
		this.historyModel.addHistory(new HistoryDto(this.idCoinComboBox.getValue(), Date.valueOf(this.dateDatePicker.getValue()), Float.parseFloat(this.euroValueTextField.getText())));
		this.clearTextFields();
		this.getAllHistory();
		this.selectedHistory = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	@FXML
	private void onClickUpdateButton(ActionEvent e) {
		this.historyModel.updateHistory(new HistoryDto(this.selectedHistory.getIdHistory(), this.idCoinComboBox.getValue(),  Date.valueOf(this.dateDatePicker.getValue()), Float.parseFloat(this.euroValueTextField.getText())));
		this.clearTextFields();
		this.getAllHistory();
		this.selectedHistory = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	@FXML
	private void onClickDeleteButton(ActionEvent e) {
		this.historyModel.deleteHistory(this.selectedHistory);
		this.clearTextFields();
		this.getAllHistory();
		this.selectedHistory = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
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
		this.initTableColumns();
		this.initClickEvent();
		this.initButtons();
		this.getAllHistory();
	}
	
	private void initTableColumns() {
		
		this.idHistory.setCellValueFactory(new PropertyValueFactory<HistoryDto, Integer>("idHistory"));
		this.idCoin.setCellValueFactory(new PropertyValueFactory<HistoryDto, Integer>("idCoin"));
		this.date.setCellValueFactory(new PropertyValueFactory<HistoryDto, Date>("date"));
		this.euroValue.setCellValueFactory(new PropertyValueFactory<HistoryDto, Float>("euroValue"));

		this.idHistory.setStyle( "-fx-alignment: CENTER;");
		this.idCoin.setStyle( "-fx-alignment: CENTER;");
		this.date.setStyle( "-fx-alignment: CENTER;");
		this.euroValue.setStyle( "-fx-alignment: CENTER;");

	}
	
	private void initClickEvent() {
		
		this.tableView.setRowFactory(tableView -> {
			TableRow<HistoryDto> tableRow = new TableRow<HistoryDto>();
			tableRow.setOnMouseClicked(event -> {
				if (!tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
					HistoryDto history = tableRow.getItem();
					this.selectedHistory = history;
					this.loadRowInfo();
				}
			});
			return tableRow;
		});
		
	}
	
	private void initButtons() {
		this.addButton.disableProperty().bind(
				this.idCoinComboBox.valueProperty().isNull()
			.or(this.dateDatePicker.valueProperty().isNull())
			.or(this.euroValueTextField.textProperty().isEmpty())
			.or(this.updateButton.disabledProperty().not())
		);
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	private void getAllHistory() {
		List<HistoryDto> historyArray = this.historyModel.getAllHistory();
		List<Integer> idCoins = this.historyModel.getAllIdCoins();
		this.tableView.getItems().clear();
		this.tableView.getItems().addAll(historyArray);
		this.idCoinComboBox.getItems().clear();
		this.idCoinComboBox.getItems().addAll(idCoins);
		Integer nextId = historyArray.get(historyArray.size() - 1).getIdHistory() + 1;
		this.idTextField.setText(nextId.toString());;
		this.idTextField.setDisable(true);
	}
	
	private void loadRowInfo() {
		this.idTextField.setText(this.selectedHistory.getIdHistory().toString());
		this.idCoinComboBox.setValue(this.selectedHistory.getIdCoin());
		this.dateDatePicker.setValue(this.selectedHistory.getDate().toLocalDate());
		this.euroValueTextField.setText(this.selectedHistory.getEuroValue().toString());
		this.updateButton.setDisable(false);
		this.deleteButton.setDisable(false);
	}
	
	private void clearTextFields() {
		this.idCoinComboBox.setValue(null);
		this.dateDatePicker.setValue(null);
		this.euroValueTextField.clear();
	}

}
