package wypozyczalnia.java.fx.model;

import javafx.beans.property.SimpleStringProperty;

public class Client {

	private SimpleStringProperty name;
	private SimpleStringProperty surname;
	private SimpleStringProperty pesel;
	
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
	public String getPesel() {
		return pesel.get();
	}
	public void setPesel(SimpleStringProperty pesel) {
		this.pesel = pesel;
	}

	public Client(String name, String surname, String pesel) {
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.pesel = new SimpleStringProperty(pesel);
	}
	
}
