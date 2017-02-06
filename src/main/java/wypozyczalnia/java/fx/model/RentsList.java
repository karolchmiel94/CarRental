package wypozyczalnia.java.fx.model;

import javafx.beans.property.SimpleStringProperty;

public class RentsList {

	private SimpleStringProperty name;
	private SimpleStringProperty surname;
	private SimpleStringProperty brand;
	private SimpleStringProperty model;
	private SimpleStringProperty regNumber;
	
	public String getName() {
		return name.get();
	}
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	public String getSurname() {
		return surname.get();
	}
	public void setSurname(SimpleStringProperty surname) {
		this.surname = surname;
	}
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
	public String getRegNumber() {
		return regNumber.get();
	}
	public void setRegNumber(SimpleStringProperty regNumber) {
		this.regNumber = regNumber;
	}

	public RentsList(String name, String surname, String brand, String model, String regNumber) {
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.brand = new SimpleStringProperty(brand);
		this.model = new SimpleStringProperty(model);
		this.regNumber = new SimpleStringProperty(regNumber);		
	}
}
