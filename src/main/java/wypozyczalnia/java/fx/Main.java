package wypozyczalnia.java.fx;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	final int WIDTH = 600;
	final int HEIGHT = 400;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
		StackPane stackPane = loader.load();
		Scene scene = new Scene(stackPane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Wypożyczalnia");
		primaryStage.show();
	}
}