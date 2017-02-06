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
import wypozyczalnia.java.fx.model.Client;

public class ShowClientsController implements Initializable{

	private MainController mainController;
	
	private ObservableList<Client> clientList = FXCollections.observableArrayList();
	
	@FXML
	public TableView<Client> clientTable;
	
	@FXML
	private TableColumn<Client, String> nameColumn;
	@FXML
	private TableColumn<Client, String> surnameColumn;
	@FXML
	private TableColumn<Client, String> peselColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
		peselColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("pesel"));
		clientTable.setItems(clientList);
	}
	
	@FXML
	public void downloadData() {
		CarRental db = new CarRental();
		db.loadClientList(clientList);
		loadDatabaseData(clientList);
	}
	
	private void loadDatabaseData(ObservableList<Client> list) {
		clientTable = new TableView<Client>();
		clientTable.setItems(clientList);
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
