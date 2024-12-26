package org.example.businesspack.window.models;

import java.util.Optional;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.enums.PersonRole;
import org.example.businesspack.services.PersonService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class ComboBoxPersonManager {

    public static void configureComboBox(ComboBox<PersonDto> comboBox, PersonRole role, PersonService service) {
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

            if (!list.isEmpty()) {
                comboBox.show();
            }
        });

        // Обработка нажатий клавиш для перемещения по списку
        comboBox.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DOWN) {
                comboBox.show();
                comboBox.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.UP) {
                comboBox.show();
                comboBox.getSelectionModel().selectPrevious();
                event.consume();
            } else if (event.getCode() == KeyCode.ENTER) {
                Optional<PersonDto> selectedItem = Optional.ofNullable(comboBox.getSelectionModel().getSelectedItem());
                selectedItem.ifPresent(item -> {
                    comboBox.getEditor().setText(item.getName());
                });
                comboBox.hide();
                event.consume();
            }
        });
    }

}
