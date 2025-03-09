package org.example.businesspack.window.models.combo_box;

import java.util.List;
import java.util.Optional;

import org.example.businesspack.services.PersonService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import lombok.Getter;

public abstract class ComboBoxManager<T> {

    @Getter
    protected ComboBox<T> comboBox;
    protected PersonService service;

    protected ObservableList<T> items;
    protected Optional<T> selectedItem;

    protected String tabName;

    ComboBoxManager(ComboBox<T> comboBox, PersonService service, String tabName) {
        this.comboBox = comboBox;
        this.service = service;
        this.tabName = tabName;
    }

    public void configureComboBox() {
        items = FXCollections.observableArrayList(get());

        comboBox.setConverter(getConverter());
        comboBox.setItems(items);

        comboBox.getEditor().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DOWN) {
                if (!comboBox.isShowing()) comboBox.show();
                comboBox.getSelectionModel().selectNext();
            } else if (e.getCode() == KeyCode.UP) {
                comboBox.getSelectionModel().selectPrevious();
            } else if (e.getCode() == KeyCode.ENTER) {
                comboBox.hide();
            }
            });     
        // TODO: добавить обработку перемещения по выпавшим данным
    }

    public void updateSelectedItem() {
        selectedItem = Optional.ofNullable(comboBox.getSelectionModel().getSelectedItem());
        updateEnterItem();
    }

    protected abstract StringConverter<T> getConverter();

    protected abstract void updateEnterItem();

    protected abstract List<T> get();

    protected abstract boolean filteredItem(T item, String newValue);

}
