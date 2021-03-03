package es.aed.models;

import java.util.List;

import es.aed.connection.DatabaseConnection;
import es.aed.dtos.HistoryDto;

public class HistoryModel {
	
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	
	public HistoryModel() { }
	
	public List<HistoryDto> getAllHistory() {
		return this.databaseConnection.getAllHistory();
	}
	
	public List<Integer> getAllIdCoins() {
		return this.databaseConnection.getAllIdCoins();
	}
	
	public void addHistory(HistoryDto coin) {
		this.databaseConnection.addHistory(coin);
	}
	
	public void updateHistory(HistoryDto coin) {
		this.databaseConnection.updateHistory(coin);
	}
	
	public void deleteHistory(HistoryDto coin) {
		this.databaseConnection.deleteHistory(coin);
	}

}
