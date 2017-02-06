package wypozyczalnia.java.fx.controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import wypozyczalnia.java.fx.database.CarRental;
import wypozyczalnia.java.fx.model.Rent;

public class AddRentController{

	private MainController mainController;
	
	@FXML
	private TextField nameText, surnameText, idText, regNumberText;

	@FXML
	public void addRent() {
		String name = nameText.getText();
		String surname = surnameText.getText();
		String id = idText.getText();
		String regNumber = regNumberText.getText();
		
		CarRental db = new CarRental();
		
		if (db.checkingClient(name, surname, id)) {
			if (db.checkingRegNumber(regNumber)) {
				confirmationDialog(name, surname, id, regNumber);
			} else {
				warningAlert("Nie ma pojazdu o takim numerze rejestracyjnym!");
			}
		} else {
			warningAlert("Nie ma takiego klienta!");
		}
	}
	
	//Confirming data and sending it to database
	private void confirmationDialog(String name, String surname, String id, String regNumber) {
		CarRental db = new CarRental();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie");
		alert.setContentText("Czy chcesz dodaæ te wypo¿yczenie?\nWypo¿yczaj¹cy: " + name + " " + surname +
								"\nSamochód wypo¿yczany: " + db.carInfo(regNumber) + "\nNumer rejestracyjny pojazdu: " + regNumber);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Rent rent = new Rent(id, regNumber);
			db.insertRent(rent);
			goToMenu();
		}		
	}

	public void warningAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	@FXML
	public void backButton() {
		goToMenu();
	}

	public void goToMenu() {
		mainController.loadMenuScreen();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
}
