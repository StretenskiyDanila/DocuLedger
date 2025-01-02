package org.example.businesspack.window.models.table;

import java.util.List;
import java.util.Optional;

import org.example.businesspack.services.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

public abstract class TableManager<T> {

    private final TableView<T> table;
    protected final Service<T> service;

    protected ObservableList<T> items;
    private Optional<T> selectedDataWork;

    protected final String tabName;

    public TableManager(TableView<T> tableAccount, Service<T> service, String tabName) {
        this.table = tableAccount;
        this.selectedDataWork = Optional.empty();
        this.service = service;
        this.tabName = tabName;
        initializeTable();
    }

    private void initializeTable() {
        items = FXCollections.observableList(get());

        table.setItems(items);
        table.setEditable(true);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                if (table.getSelectionModel() == null || table.getSelectionModel().getSelectedItem() == null) {
                    onMouseClicked();
                    table.setItems(items);
                    clearSelectedItem();
                }
            }
        });

        selectedItemProperty();
        setupKeyboardEvents();
    }

    public void configureColumn(TableColumn<T, String> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            T item = event.getRowValue();
            onColumnEdit(item, property, event.getNewValue());
            service.update(item);
            clearSelectedItem();
        });
    }

    protected abstract List<T> get();
    protected abstract void onMouseClicked();
    protected abstract void onColumnEdit(T item, String property, String newValue);

    private void setupKeyboardEvents() {
        table.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                selectedDataWork.ifPresentOrElse(item -> {
                    deleteSelectedRow(item);
                }, () -> {
                    System.out.println("No selected row"); //TODO: заменить на обработку
                });
            }
            if (event.getCode() == KeyCode.ESCAPE) { 
                clearSelectedItem();
            }
        });
    }

    private void deleteSelectedRow(T item) {
        items.remove(item);
        service.delete(item);
        table.setItems(items);
    }

    private void selectedItemProperty() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedDataWork = Optional.ofNullable(newSelection);
        });
    }

    private void clearSelectedItem() {
        selectedDataWork = Optional.empty();
        table.getSelectionModel().select(null);
    }

}
