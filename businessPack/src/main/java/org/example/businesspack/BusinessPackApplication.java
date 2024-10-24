package org.example.businesspack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;

@SpringBootApplication
public class BusinessPackApplication extends Application {

	public static String[] args;

	public static void main(String[] args) {
		BusinessPackApplication.args = args;
		Application.launch(BusinessPackApplication.class, args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(BusinessPackApplication.class.getResource("main-window.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 730, 466);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();

		new Thread(() -> {
			new SpringApplicationBuilder(BusinessPackApplication.class).run(args);
		}, "Spring Thread").start();
	}

}
