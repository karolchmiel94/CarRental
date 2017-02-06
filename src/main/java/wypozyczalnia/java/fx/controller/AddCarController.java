package wypozyczalnia.java.fx.controller;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import wypozyczalnia.java.fx.database.CarRental;
import wypozyczalnia.java.fx.model.Car;

public class AddCarController {
			
	private MainController mainController;

	@FXML 
	private TextField carBrand, carModel, carProductionYear, carRegistrationNumber;
	
	
	@FXML
	public void backButton() {
		goToMenu();
	}

	public void goToMenu() {
		mainController.loadMenuScreen();
	}
	
	@FXML
	public void addCar(ActionEvent event) {
		String brand = carBrand.getText();
		String model = carModel.getText();
		String productionYear = carProductionYear.getText();
		String registrationNumber = carRegistrationNumber.getText();
		if (checkingText(brand, "marka") && checkingText(model, "model") && checkingProductionYear(productionYear) 
				&& checkingRegistrationNumber(registrationNumber)) {
			confirmationDialog(brand, model, productionYear, registrationNumber);
		};
	}

	private void confirmationDialog(String brand, String model, String year, String regNumber) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie");
		alert.setContentText("Czy te dane s¹ prawid³owe?\nMarka: " + brand + "\nModel: " + model +
								"\nRok produkcji: " + year + "\nNumer rejestracyjny: " + regNumber);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.out.println("Adding car");
			CarRental db = new CarRental();
			Car car = new Car(brand, model, Integer.parseInt(year), regNumber);
			db.insertCar(car);
			goToMenu();
		}		
	}

	//Checking whether registration number is typed right
	public boolean checkingRegistrationNumber(String registrationNumber) {
		if (registrationNumber.length() != 7) {
			String alert = "B³¹d przy wprowadzaniu numeru tablicy rejestracyjnej!";
			warningAlert(alert);
			return false;
		} else { return true; }
	}

	//Checking whether production year is typed right
	public boolean checkingProductionYear(String productionYear) {
		if (productionYear != null) {
			if (isNumber(productionYear)) {
				int year = Integer.parseInt(productionYear);
				if ((year < 1950) || (year > 2017)) {
					String alert = "B³êdnie wprowadzony rok!";
					warningAlert(alert);
					return false;
					}
			}
				else {
				String alert = "Rok musi byæ liczb¹!";
				warningAlert(alert);
				return false;
				}
		} else {
			String alert = "B³êdnie wprowadzony rok!";
			warningAlert(alert);
			return false;
			} return true;
	}

	//Checking whether model is typed right
	public boolean checkingText(String text, String label) {
		if ((text.equals("")) || (!isText(text))) {
			String alert = "Pole " + label + " nie mo¿e byæ puste i musi sk³adaæ siê jedynie z liter!";
			warningAlert(alert);
			return false;
		} else { return true; }
	}

	//Is string a text
	public boolean isText(String text) {
		if(!text.chars().allMatch(Character::isLetter)) {
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
	    }}
	    return true;
	}
	
	public void warningAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}