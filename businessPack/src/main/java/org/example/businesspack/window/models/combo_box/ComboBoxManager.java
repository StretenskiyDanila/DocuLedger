package org.example.businesspack.window.models.combo_box;

import java.util.List;
import java.util.Optional;

import org.example.businesspack.services.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public abstract class ComboBoxManager<T> {

    protected ComboBox<T> comboBox;
    protected Service<T> service;

    protected ObservableList<T> items;
    protected Optional<T> selectedItem;

    protected String tabName;

    ComboBoxManager(ComboBox<T> comboBox, Service<T> service, String tabName) {
        this.comboBox = comboBox;
        this.service = service;
        this.tabName = tabName;
    }

    public void configureComboBox() {
        items = FXCollections.observableArrayList(get());

        comboBox.setConverter(getConverter());
        var itemsFiltered = new FilteredList<>(items, p -> true);
        comboBox.setItems(items);

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            itemsFiltered.setPredicate(option -> filteredItem(option, newValue));

            if (!newValue.isEmpty() && !items.isEmpty()) {
                comboBox.show();
            }
        });
        
        // TODO: добавить обработку перемещения по выпавшим данным
    }

    public void updateSelectedItem() {
        selectedItem = Optional.ofNullable(comboBox.getSelectionModel().getSelectedItem());
        updateEnterItem();
        //comboBox.setItems(items);
    }

    protected abstract StringConverter<T> getConverter();

    protected abstract void updateEnterItem();

    protected abstract List<T> get();

    protected abstract boolean filteredItem(T item, String newValue);

}
