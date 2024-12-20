package org.example.businesspack.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import lombok.experimental.UtilityClass;

import org.example.businesspack.entities.PersonRole;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.impl.PersonServiceImpl;

@UtilityClass
public class ComboBoxUtils {

    private final PersonService service = new PersonServiceImpl();

    public void setComboBoxSearchProperty(ComboBox<String> comboBox, PersonRole role) {
        comboBox.setEditable(true);
        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });
        comboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });

        ObservableList<String> options = FXCollections.observableArrayList(service.get(role).stream()
                .map(entity -> entity.getName())
                .toList());
        comboBox.setItems(options);

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            var list = options.filtered(option -> option.toLowerCase().contains(newValue.toLowerCase()));
            comboBox.setItems(list);
            comboBox.show();
        });
    }

}
