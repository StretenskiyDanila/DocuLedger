package org.example.businesspack.window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> box;

    @FXML
    private TextField textArea;

    @FXML
    void onBox(ActionEvent event) {
        String g = box.getSelectionModel().getSelectedItem();
        textArea.setText(g);
    }

    @FXML
    void onText(ActionEvent event) {
        String text = textArea.getText();
        System.out.println(text);
    }

    @FXML
    void initialize() {
        box.getItems().clear();

        ObservableList<String> str = FXCollections.observableList(
                Stream.of("Str", "dsd", "hello").map(x -> x.length() + ", " + x).toList());


        box.setItems(str);
    }
}