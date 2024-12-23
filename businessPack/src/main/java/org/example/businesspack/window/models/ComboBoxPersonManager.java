package org.example.businesspack.window.models;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.PersonRole;
import org.example.businesspack.services.PersonService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;

public class ComboBoxPersonManager {

    public static void configureComboBox(ComboBox<PersonDto> comboBox, PersonRole role, PersonService service) {
        comboBox.setEditable(true);

        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(PersonDto item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        comboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(PersonDto item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        ObservableList<PersonDto> items = FXCollections.observableArrayList(service.get(role));
        comboBox.setItems(items);

        comboBox.setConverter(new StringConverter<PersonDto>() {
            @Override
            public String toString(PersonDto person) {
                return person == null ? null : person.getName();
            }

            @Override
            public PersonDto fromString(String string) {
                return items.stream()
                .filter(person -> person.getName().equals(string))
                .findFirst()
                .orElse(null);
            }
        });

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            var list = items.filtered(option -> option.getName().toLowerCase().contains(newValue.toLowerCase()));
            comboBox.setItems(list);
            comboBox.show();
        });
    }

}
