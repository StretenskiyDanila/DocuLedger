package org.example.businesspack.window.models.table;

import java.util.Optional;

import org.example.businesspack.services.DataWorkService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

public abstract class TableManager<T> {

    private final TableView<T> table;
    protected final DataWorkService<T> service;

    private ObservableList<T> items;
    private Optional<T> selectedDataWork;

    public TableManager(TableView<T> tableAccount, DataWorkService<T> service) {
        this.table = tableAccount;
        this.selectedDataWork = Optional.empty();
        this.service = service;
        initializeTable();
    }

    private void initializeTable() {
        items = FXCollections.observableList(service.get()); //TODO: перенести на 2 класса и добавить в таблицу колонку accounts/actWorks 

        table.setItems(items);
        table.setEditable(true);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                if (table.getSelectionModel() == null || table.getSelectionModel().getSelectedItem() == null) {
                    onMouseClicked(items);
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

    protected abstract void onMouseClicked(ObservableList<T> items);
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
