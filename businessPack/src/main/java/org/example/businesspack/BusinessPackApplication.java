package org.example.businesspack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BusinessPackApplication extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(BusinessPackApplication.class.getResource("main-window.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
		stage.sizeToScene();
	}

}