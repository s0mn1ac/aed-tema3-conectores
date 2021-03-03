package es.aed.dtos;

import java.sql.Date;

public class HistoryDto {
	
	private Integer idHistory;
	private Integer idCoin;
	private Date date;
	private Float euroValue;
	
	public HistoryDto() { }
	
	public HistoryDto(Integer idHistory, Integer idCoin, Date date, Float euroValue) {
		this.idHistory = idHistory;
		this.idCoin = idCoin;
		this.date = date;
		this.euroValue = euroValue;
	}
	
	public HistoryDto(Integer idCoin, Date date, Float euroValue) {
		this.idCoin = idCoin;
		this.date = date;
		this.euroValue = euroValue;
	}

	public Integer getIdHistory() {
		return idHistory;
	}

	public void setIdHistory(Integer idHistory) {
		this.idHistory = idHistory;
	}

	public Integer getIdCoin() {
		return idCoin;
	}

	public void setIdCoin(Integer idCoin) {
		this.idCoin = idCoin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getEuroValue() {
		return euroValue;
	}

	public void setEuroValue(Float euroValue) {
		this.euroValue = euroValue;
	}

}
