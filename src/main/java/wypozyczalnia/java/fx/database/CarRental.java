package wypozyczalnia.java.fx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import wypozyczalnia.java.fx.model.Car;
import wypozyczalnia.java.fx.model.Client;
import wypozyczalnia.java.fx.model.Rent;
import wypozyczalnia.java.fx.model.RentsList;
import javafx.collections.ObservableList;

public class CarRental {

	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:carRental.sb";
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement prepStmt;
	private ResultSet rs;
	
	public CarRental() {
		try {
			Class.forName(CarRental.DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No JDBC driver");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Trouble with connection opening");
			e.printStackTrace();
		}
		createTables();
	}
	
	//Creating tables
	public void createTables() {
		String createClients = "CREATE TABLE IF NOT EXISTS clients (id_client INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name varchar(255), surname varchar(255), pesel string)";
		String createCars = "CREATE TABLE IF NOT EXISTS cars (id_car INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "brand varchar(255), model varchar(255), prodYear int, regNumber string)";
		String createRents = "CREATE TABLE IF NOT EXISTS rents (id_rent INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "id_client varchar(255), id_car varchar(255))";
		try {
			stat.execute(createClients);
			stat.execute(createCars);
			stat.execute(createRents);
		} catch (SQLException e) {
			System.out.println("Problem occured while building tabels");
			e.printStackTrace();
			}		
	}
	
	//Adding client to table
	public void insertClient(Client client) {
		try {
			prepStmt = conn.prepareStatement("insert into clients values (NULL, ?, ?, ?);");
			prepStmt.setString(1, client.getName());
			prepStmt.setString(2, client.getSurname());
			prepStmt.setString(3, client.getPesel());
			prepStmt.execute();
		} catch (SQLException e) {
			System.out.println("Problem with adding client to db");
			e.printStackTrace();
		}
	}
	
	//Adding car to table
	public void insertCar(Car car) {
		try {
			prepStmt = conn.prepareStatement("insert into cars values (NULL, ?, ?, ?, ?);");
			prepStmt.setString(1, car.getBrand());
			prepStmt.setString(2, car.getModel());
			prepStmt.setInt(3, car.getProductionYear());
			prepStmt.setString(4, car.getRegistrationNumber());
			prepStmt.execute();
		} catch (SQLException e) {
			System.out.println("Problem with adding car to db");
			e.printStackTrace();
		}
	}
	
	//Adding rent to table
	public void insertRent(Rent rent) {
		try {
			prepStmt = conn.prepareStatement("insert into rents values (NULL, ?, ?);");
			prepStmt.setString(1, rent.getIdClient());
			prepStmt.setString(2, rent.getIdCar());
			prepStmt.execute();
		} catch (SQLException e) {
			System.out.println("Problem with adding rent to db");
			e.printStackTrace();
		}
	}
	
	//Loading cars' data to observable list
	public void loadCarList(ObservableList<Car> list) {
		String query = "SELECT * FROM cars";
		try {
			prepStmt = conn.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				list.add(new Car(
						rs.getString("model"),
						rs.getString("brand"),
						rs.getInt("prodYear"),
						rs.getString("regNumber")
						));
				}
			prepStmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Problem during getting data from database");
			e.printStackTrace();
		}
	}
	
	//Loading clients' data to observable list
	public void loadClientList(ObservableList<Client> list) {
		String query = "SELECT * FROM clients";
		try {
			prepStmt = conn.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				list.add(new Client(
						rs.getString("name"),
						rs.getString("surname"),
						rs.getString("pesel")
						));
				}
			prepStmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Problem during getting data from database");
			e.printStackTrace();
		}
	}
	
	//Checking if client exist in db
	public boolean checkingClient(String name, String surname, String pesel) {
		try {
			prepStmt = conn.prepareStatement("SELECT pesel FROM clients WHERE "
					+ "(name='" + name + "' AND surname='" + surname + "' AND  pesel='" + pesel + "' )");
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				prepStmt.close();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Problem while searching for data in db!");
			e.printStackTrace();
		}
		return false;
	}
	
	//Checking if cars exist in db
	public boolean checkingRegNumber(String number) {
		try {
			prepStmt = conn.prepareStatement("SELECT * FROM cars WHERE (regNumber='" + number + "')");
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				rs.close();
				prepStmt.close();
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Problem while searching for data in db!");
			e.printStackTrace();
		}
		return false;
	}
	
	//Getting car data
	public String carInfo(String number) {
		String brand, model;
		try {
			prepStmt = conn.prepareStatement("SELECT brand, model FROM cars WHERE regNumber='" + number + "'");
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				System.out.println("Element found in database!");
				brand = rs.getString("brand");
				model = rs.getString("model");
				prepStmt.close();
				return brand + " " + model;
			}
		} catch (SQLException e) {
			System.out.println("Problem while searching for data in db!");
			e.printStackTrace();
		}
		return "";
	}

	//Loading data about rents and putting it to observable list
	public void loadRentsList(ObservableList<RentsList> list) {
		String query = "select "
				+ "cl.name, cl.surname, ca.brand, ca.model, ca.regNumber "
				+ "from "
				+ "clients cl, cars ca "
				+ "join rents re "
				+ "on cl.pesel=re.id_client "
				+ "and ca.regNumber=re.id_car";
		try {
			prepStmt = conn.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while (rs.next()) {
				list.add(new RentsList(
						rs.getString("name"),
						rs.getString("surname"),
						rs.getString("brand"),
						rs.getString("model"),
						rs.getString("regNumber")
						));
				}
			prepStmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Problem during getting data from database");
			e.printStackTrace();
		}
	}
}
