package es.aed.models;

import java.util.List;

import es.aed.connection.DatabaseConnection;
import es.aed.dtos.CoinDto;

public class CoinModel {
	
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	
	public CoinModel() { }
	
	public List<CoinDto> getAllCoins() {
		return this.databaseConnection.getAllCoins();
	}
	
	public void addCoin(CoinDto coin) {
		this.databaseConnection.addCoin(coin);
	}
	
	public void updateCoin(CoinDto coin) {
		this.databaseConnection.updateCoin(coin);
	}
	
	public void deleteCoin(CoinDto coin) {
		this.databaseConnection.deleteCoin(coin);
	}

}
