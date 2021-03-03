package es.aed.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import es.aed.dtos.CoinDto;
import es.aed.models.CoinModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class CoinController implements Initializable {
	
	private CoinModel coinModel = new CoinModel();
	
	private CoinDto selectedCoin;
	
	@FXML
	private VBox view;
	
	@FXML
	private TextField idCoinTextField;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField countryTextField;
	
	@FXML
	private Button resetButton;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private TableView<CoinDto> tableView;
	
	@FXML
	private TableColumn<CoinDto, Integer> idCoin;
	
	@FXML
	private TableColumn<CoinDto, String> name;
	
	@FXML
	private TableColumn<CoinDto, String> country;
	
	@FXML
	private void onClickResetButton(ActionEvent e) {
		this.clearTextFields();
		this.getAllCoins();
		this.selectedCoin = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	@FXML
	private void onClickAddButton(ActionEvent e) {
		this.coinModel.addCoin(new CoinDto(this.nameTextField.getText(), this.countryTextField.getText()));
		this.clearTextFields();
		this.getAllCoins();
		this.selectedCoin = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	@FXML
	private void onClickUpdateButton(ActionEvent e) {
		this.coinModel.updateCoin(new CoinDto(this.selectedCoin.getIdCoin(), this.nameTextField.getText(), this.countryTextField.getText()));
		this.clearTextFields();
		this.getAllCoins();
		this.selectedCoin = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	@FXML
	private void onClickDeleteButton(ActionEvent e) {
		this.coinModel.deleteCoin(this.selectedCoin);
		this.clearTextFields();
		this.getAllCoins();
		this.selectedCoin = null;
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	public CoinController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/CoinView.fxml"));
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
		this.getAllCoins();
	}
	
	private void initTableColumns() {
		
		this.idCoin.setCellValueFactory(new PropertyValueFactory<CoinDto, Integer>("idCoin"));
		this.name.setCellValueFactory(new PropertyValueFactory<CoinDto, String>("name"));
		this.country.setCellValueFactory(new PropertyValueFactory<CoinDto, String>("country"));

		this.idCoin.setStyle( "-fx-alignment: CENTER;");
		this.name.setStyle( "-fx-alignment: CENTER;");
		this.country.setStyle( "-fx-alignment: CENTER;");

	}
	
	private void initClickEvent() {
		
		this.tableView.setRowFactory(tableView -> {
			TableRow<CoinDto> tableRow = new TableRow<CoinDto>();
			tableRow.setOnMouseClicked(event -> {
				if (!tableRow.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
					CoinDto coin = tableRow.getItem();
					this.selectedCoin = coin;
					this.loadRowInfo();
				}
			});
			return tableRow;
		});
		
	}
	
	private void initButtons() {
		this.addButton.disableProperty().bind(
				this.nameTextField.textProperty().isEmpty()
			.or(this.countryTextField.textProperty().isEmpty())
			.or(this.updateButton.disabledProperty().not())
		);
		this.updateButton.setDisable(true);
		this.deleteButton.setDisable(true);
	}
	
	private void getAllCoins() {
		List<CoinDto> coins = this.coinModel.getAllCoins();
		this.tableView.getItems().clear();
		this.tableView.getItems().addAll(coins);
		Integer nextId = coins.get(coins.size() - 1).getIdCoin() + 1;
		this.idCoinTextField.setText(nextId.toString());;
		this.idCoinTextField.setDisable(true);
	}
	
	private void loadRowInfo() {
		this.idCoinTextField.setText(this.selectedCoin.getIdCoin().toString());
		this.nameTextField.setText(this.selectedCoin.getName());
		this.countryTextField.setText(this.selectedCoin.getCountry());
		this.updateButton.setDisable(false);
		this.deleteButton.setDisable(false);
	}
	
	private void clearTextFields() {
		this.nameTextField.clear();
		this.countryTextField.clear();
	}

}
