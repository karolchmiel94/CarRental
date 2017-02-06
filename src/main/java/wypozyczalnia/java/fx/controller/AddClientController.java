package wypozyczalnia.java.fx.controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import wypozyczalnia.java.fx.database.CarRental;
import wypozyczalnia.java.fx.model.Client;
import javafx.scene.control.Alert.AlertType;

public class AddClientController {

	private MainController mainController;
	
	@FXML
	private TextField clientName, clientSurname, clientId;
	
	@FXML
	public void backButton() {
		goToMenu();
	}

	@FXML
	public void addClient() {
		String name = clientName.getText();
		String surname = clientSurname.getText();
		String id = clientId.getText();
		
		if(checkingText(name, "imie") && checkingText(surname, "nazwisko") && checkingId(id)) {
			confirmationDialog(name, surname, id);
		}
	}

	//Confirming data and sending it to database
	private void confirmationDialog(String name, String surname, String clientId) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie");
		alert.setContentText("Czy te dane s¹ prawid³owe?\nImiê: " + name + "\nNazwisko: " + surname +
								"\nPesel: " + clientId);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.out.println("Adding client");
			CarRental db = new CarRental();
			Client client = new Client(name, surname, clientId);
			db.insertClient(client);
			goToMenu();
		}
	}
	
	//Checking whether name is typed right
	public boolean checkingText(String text, String label) {
		if ((text.equals("")) || (!isText(text))) {
			String alert = "Pole " + label + " nie mo¿e byæ puste i musi sk³adaæ siê jedynie z liter!";
			warningAlert(alert);
			return false;
		} else { return true; }
	}
	
	//Checking whether id is typed right
	public boolean checkingId(String id) {
		if ((!isNumber(id)) || (id.length() != 11)) {
			String alert = "Podany z³y numer PESEL!";
			warningAlert(alert);
			return false;
		} else { 
		return true;
		}
	}
	
	//Is string a text
	public boolean isText(String text) {
		if (!text.chars().allMatch(Character::isLetter)) {
	            return false;
	        }
	    return true;
	}
	
	//Is string a number
	public boolean isNumber(String name) {
		char[] chars = name.toCharArray();
		for (char c : chars) {
		    if(!Character.isDigit(c)) {
		        return false;
		}} return true;
	}

	public void warningAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public void goToMenu() {
		mainController.loadMenuScreen();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
