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
import wypozyczalnia.java.fx.model.RentsList;

public class ShowRentsController implements Initializable{

	private MainController mainController;
	
	private ObservableList<RentsList> rentsList = FXCollections.observableArrayList();
	
	@FXML
	public TableView<RentsList> rentsTable;
	
	@FXML
	private TableColumn<RentsList, String> nameColumn;
	@FXML
	private TableColumn<RentsList, String> surnameColumn;
	@FXML
	private TableColumn<RentsList, String> brandColumn;
	@FXML
	private TableColumn<RentsList, String> modelColumn;
	@FXML
	private TableColumn<RentsList, String> regNumberColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<RentsList, String>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<RentsList, String>("surname"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<RentsList, String>("brand"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<RentsList, String>("model"));
		regNumberColumn.setCellValueFactory(new PropertyValueFactory<RentsList, String>("regNumber"));
		rentsTable.setItems(rentsList);
	}
	
	private void loadDatabaseData(ObservableList<RentsList> list) {
		rentsTable = new TableView<RentsList>();
		rentsTable.setItems(rentsList);
	}
	
	@FXML
	public void downloadData() {
		CarRental db = new CarRental();
		db.loadRentsList(rentsList);
		loadDatabaseData(rentsList);
	}
	
	@FXML
	public void backToMenu() {
		goToMenu();
	}
	
	public void goToMenu() {
		mainController.loadMenuScreen();
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
