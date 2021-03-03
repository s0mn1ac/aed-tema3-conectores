package es.aed.dtos;

public class CoinDto {
	
	private Integer idCoin;
	private String name;
	private String country;
	
	public CoinDto() { }
	
	public CoinDto(Integer idCoin, String name, String country) {
		this.idCoin = idCoin;
		this.name = name;
		this.country = country;
	}
	
	public CoinDto(String name, String country) {
		this.name = name;
		this.country = country;
	}

	public Integer getIdCoin() {
		return idCoin;
	}

	public void setIdCoin(Integer idCoin) {
		this.idCoin = idCoin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
