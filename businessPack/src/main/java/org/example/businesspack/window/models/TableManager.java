package org.example.businesspack.window.models;

import java.util.Optional;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.services.DataWorkService;
import org.example.businesspack.services.impl.DataWorkServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

public class TableManager {

    private final TableView<DataWorkDto> table;
    private final DataWorkService service = new DataWorkServiceImpl();

    private ObservableList<DataWorkDto> items;
    private Optional<DataWorkDto> selectedDataWork;

    public TableManager(TableView<DataWorkDto> tableAccount) {
        this.table = tableAccount;
        this.selectedDataWork = Optional.empty();
        initializeTable();
    }

    private void initializeTable() {
        items = FXCollections.observableList(service.get());

        table.setItems(items);
        table.setEditable(true);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                if (table.getSelectionModel() == null || table.getSelectionModel().getSelectedItem() == null) {
                    DataWorkDto item = new DataWorkDto();
                    item.setId(service.update(item));
                    items.add(item);
                    table.setItems(items);
                    clearSelectedItem();
                }
            }
        });

        selectedItemProperty();
        setupKeyboardEvents();
    }

    public void configureColumn(TableColumn<DataWorkDto, String> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            DataWorkDto item = event.getRowValue();
            if (item == null)
                item = new DataWorkDto();
            switch (property) {
                case "name" -> item.setName(event.getNewValue());
                case "count" -> item.setCount(event.getNewValue());
                case "vat" -> item.setVat(event.getNewValue());
                case "group" -> item.setGroup(event.getNewValue());
                case "price" -> item.setPrice(event.getNewValue());
                case "summa" -> item.setSumma(event.getNewValue());
                case "unitMeas" -> item.setUnitMeas(event.getNewValue());
            }
            service.update(item);
            clearSelectedItem();
        });
    }

    private void setupKeyboardEvents() {
        table.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                selectedDataWork.ifPresentOrElse(item -> {
                    deleteSelectedRow(item);
                }, () -> {
                    System.out.println("No selected row"); //TODO: заменить на обработку
                });
            }
            if (event.getCode() == KeyCode.ESCAPE) { //TODO: возможно убрать его
                clearSelectedItem();
            }
        });
    }

    private void deleteSelectedRow(DataWorkDto item) {
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
