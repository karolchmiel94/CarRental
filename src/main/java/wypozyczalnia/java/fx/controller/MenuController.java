package wypozyczalnia.java.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MenuController {

	private MainController mainController;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    public void add(ActionEvent event) {
    	Button btn = (Button) event.getSource();
    	String id = btn.getId();
    	System.out.println(id);
    	FXMLLoader loader = null;
    	switch (id) {
    	case "addCar": 
    		loader = new FXMLLoader(this.getClass().getResource("/fxml/AddCar.fxml"));
    		break;
    	case "addClient":
    		loader = new FXMLLoader(this.getClass().getResource("/fxml/AddClient.fxml"));
    		break;
    	case "addRent":
    		loader = new FXMLLoader(this.getClass().getResource("/fxml/AddRent.fxml"));
    		break;
    	case "showCars":
    		loader = new FXMLLoader(this.getClass().getResource("/fxml/ShowCars.fxml"));
    		break;
    	case "showClients":
    		loader = new FXMLLoader(this.getClass().getResource("/fxml/ShowClients.fxml"));
    		break;
    	case "showRents":
    		loader = new FXMLLoader(this.getClass().getResource("/fxml/ShowRents.fxml"));
    		break;
    	default:
    		System.out.println("Nie ma takiego przycisku");
    		break;
    	}	
    	Pane pane = null;
    	try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	mainController.setScreen(pane);
    	backToMenu(id, loader);	
    }

	public void backToMenu(String id, FXMLLoader loader) {
		switch (id) {
    	case "addCar": 
    		AddCarController addCarController = loader.getController();
    		addCarController.setMainController(mainController);
    		break;
    	case "addClient":
    		AddClientController addClientController = loader.getController();
    		addClientController.setMainController(mainController);
    		break;
    	case "addRent":
    		AddRentController AddRentController = loader.getController();
    		AddRentController.setMainController(mainController);
    		break;
    	case "showCars": 
    		ShowCarsController showCarsController = loader.getController();
    		showCarsController.setMainController(mainController);
    		break;
    	case "showClients": 
    		ShowClientsController showClientsController = loader.getController();
    		showClientsController.setMainController(mainController);
    		break;
    	case "showRents": 
    		ShowRentsController showRentsController = loader.getController();
    		showRentsController.setMainController(mainController);
    		break;
    	default:
    		System.out.println("Nie ma takiego przycisku");
    		break;
    	}
	}
    
	@FXML
	public void exit() {
		Platform.exit();
	}
	
    @FXML
    public void show() {
    	System.out.println("Wyniki z bazy danych");
    }
    
    public void setMainController(MainController mainController) {
    	this.mainController = mainController;
    }
}
