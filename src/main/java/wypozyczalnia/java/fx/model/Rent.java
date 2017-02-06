package wypozyczalnia.java.fx.model;

import javafx.beans.property.SimpleStringProperty;

public class Rent {

	private SimpleStringProperty idClient;
	private SimpleStringProperty idCar;
	
	public String getIdClient() {
		return idClient.get();
	}
	public void setIdClient(SimpleStringProperty idClient) {
		this.idClient = idClient;
	}
	public String getIdCar() {
		return idCar.get();
	}
	public void setIdCar(SimpleStringProperty idCar) {
		this.idCar = idCar;
	}

	public Rent(String idClient, String idCar) {
		this.idClient = new SimpleStringProperty(idClient);
		this.idCar = new SimpleStringProperty(idCar);
	}
}
