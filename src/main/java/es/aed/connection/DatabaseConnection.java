package es.aed.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.aed.dtos.CoinDto;
import es.aed.dtos.HistoryDto;
import es.aed.exceptions.Alerts;
import es.aed.exceptions.Messages;
import javafx.scene.control.Alert.AlertType;

public class DatabaseConnection {
	
	private Alerts alert = new Alerts();
	
	private String database;
	private String user;
	private String password;
	
	private Connection connection;
	
	public DatabaseConnection() {
		this.database = "jdbc:mysql://localhost/seguimientomonedas?serverTimezone=UTC";
		this.user = "root";
		this.password = "toor";
		this.loadDriver();
		this.loadDatabase();
	}
	
	private void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_DRIVER, AlertType.ERROR);
		}
	}
	
	private void loadDatabase() {
		try {
			this.connection = DriverManager.getConnection(this.database, this.user, this.password);
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_DATABASE, AlertType.ERROR);
		}
	}
	
	public void closeConnection() {
		
	}
	
	// COIN
	
	public List<CoinDto> getAllCoins() {
		
		List<CoinDto> coins = new ArrayList<CoinDto>();
		
		try {
			String query = "SELECT * FROM monedas;" ;
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				CoinDto coin = new CoinDto(resultSet.getInt("idmoneda"), resultSet.getString("nombre"), resultSet.getString("pais"));
				coins.add(coin);
			}
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_DATABASE, AlertType.ERROR);
		}
		
		return coins;
		
	}
	
	public List<Integer> getAllIdCoins() {
		
		List<Integer> idCoins = new ArrayList<Integer>();
		
		try {
			String query = "SELECT * FROM monedas;" ;
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				idCoins.add(resultSet.getInt("idmoneda"));
			}
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_DATABASE, AlertType.ERROR);
		}
		
		return idCoins;
		
	}
	
	public void addCoin(CoinDto coin) {
		
		try {
			String query = "INSERT INTO monedas values(null, ?, ?);";
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, coin.getName());
			preparedStatement.setString(2, coin.getCountry());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_ADD_COIN, AlertType.ERROR);
		}
		
	}
	
	public void updateCoin(CoinDto coin) {
		
		try {
			String query = "UPDATE monedas SET nombre = ?, pais = ? WHERE idmoneda = ?;";
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, coin.getName());
			preparedStatement.setString(2, coin.getCountry());
			preparedStatement.setInt(3, coin.getIdCoin());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_ADD_COIN, AlertType.ERROR);
			System.out.println(exception);
		}
		
	}
	
	public void deleteCoin(CoinDto coin) {
		
		try {
			String query = "DELETE FROM monedas WHERE idmoneda = ?;";
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, coin.getIdCoin());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_ADD_COIN, AlertType.ERROR);
		}
		
	}
	
	// HISTORY
	
	public List<HistoryDto> getAllHistory() {
		
		List<HistoryDto> historyArray = new ArrayList<HistoryDto>();
		
		try {
			String query = "SELECT * FROM historicocambioeuro;" ;
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				HistoryDto history = new HistoryDto(resultSet.getInt("idhistoricocambioeuro"), resultSet.getInt("fkidmoneda"), resultSet.getDate("fecha"), resultSet.getFloat("equivalenteeuro"));
				historyArray.add(history);
			}
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_DATABASE, AlertType.ERROR);
		}
		
		return historyArray;
		
	}
	
	public void addHistory(HistoryDto history) {
		
		try {
			String query = "INSERT INTO historicocambioeuro values(null, ?, ?, ?);";
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, history.getIdCoin());
			preparedStatement.setDate(2, history.getDate());
			preparedStatement.setFloat(3, history.getEuroValue());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_ADD_COIN, AlertType.ERROR);
		}
		
	}
	
	public void updateHistory(HistoryDto history) {
		
		try {
			String query = "UPDATE historicocambioeuro SET fkidmoneda = ?, fecha = ?, equivalenteeuro = ? WHERE idhistoricocambioeuro = ?;";
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, history.getIdCoin());
			preparedStatement.setDate(2, history.getDate());
			preparedStatement.setFloat(3, history.getEuroValue());
			preparedStatement.setInt(4, history.getIdHistory());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_ADD_COIN, AlertType.ERROR);
			System.out.println(exception);
		}
		
	}
	
	public void deleteHistory(HistoryDto history) {
		
		try {
			String query = "DELETE FROM historicocambioeuro WHERE idhistoricocambioeuro = ?;";
			PreparedStatement preparedStatement;
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, history.getIdHistory());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_ADD_COIN, AlertType.ERROR);
		}
		
	}

}
