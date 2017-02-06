package wypozyczalnia.java.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wypozyczalnia.java.fx.database.CarRental;
import wypozyczalnia.java.fx.model.Car;

public class ShowCarsController implements Initializable{
	
	private MainController mainController;
	private ObservableList<Car> carList = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Car> carsTable;
	
	@FXML
	private TableColumn<Car, String> nameColumn;
	
	@FXML
	private TableColumn<Car, String> surnameColumn;
	
	@FXML
	private TableColumn<Car, Integer> prodYearColumn;
	
	@FXML
	private TableColumn<Car, String> regNumberColumn;
		 
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("brand"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
		prodYearColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("productionYear"));
		regNumberColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
		carsTable.setItems(carList);
	}
	
	private void loadDatabaseData(ObservableList<Car> list) {
		carsTable = new TableView<Car>();
		carsTable.setItems(carList);
		carsTable.setVisible(false);
		carsTable.setVisible(true);
	}
	
	@FXML
	private void downloadData() {
		CarRental db = new CarRental();
		db.loadCarList(carList);
		loadDatabaseData(carList);
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
