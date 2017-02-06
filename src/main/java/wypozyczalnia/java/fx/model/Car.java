package wypozyczalnia.java.fx.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Car {

	private SimpleStringProperty brand;
	private SimpleStringProperty model;
	private SimpleIntegerProperty productionYear;
	private SimpleStringProperty registrationNumber;
	
	public String getBrand() {
		return brand.get();
	}
	public void setBrand(SimpleStringProperty brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model.get();
	}
	public void setModel(SimpleStringProperty model) {
		this.model = model;
	}
	public int getProductionYear() {
		return productionYear.get();
	}
	public void setProductionYear(SimpleIntegerProperty productionYear) {
		this.productionYear = productionYear;
	}
	public String getRegistrationNumber() {
		return registrationNumber.get();
	}
	public void setRegistrationNumber(SimpleStringProperty registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public Car(String brand, String model, int prodYear, String regNumber) {
		this.brand = new SimpleStringProperty(brand);
		this.model = new SimpleStringProperty(model);
		this.productionYear = new SimpleIntegerProperty(prodYear);
		this.registrationNumber = new SimpleStringProperty(regNumber);
	}
}
