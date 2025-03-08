package org.example.businesspack.window.models.table;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.services.DataWorkService;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TableManager<T> {

    @Getter
    private final TableView<T> table;
    protected final DataWorkService service;

    protected ObservableList<T> items;
    private Optional<T> selectedDataWork;

    protected final String tabName;

    public TableManager(TableView<T> tableAccount, DataWorkService service, String tabName) {
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
                if (event.getTarget() instanceof TableColumnHeader) {
                    return;
                }

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

    public <P> void configureColumn(TableColumn<T, P> column, Function<T, ObservableValue<P>> property,
            boolean editable, StringConverter<P> converter, BiConsumer<T, P> onEditCommit) {
        column.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        if (editable) {
            column.setCellFactory(TextFieldTableCell.forTableColumn(converter));
            column.setOnEditCommit(event -> {
                T item = event.getRowValue();
                onEditCommit.accept(item, event.getNewValue());
                service.update((DataWorkDto) item);
                clearSelectedItem();
            });
        }
    }

    protected abstract List<T> get();

    protected abstract void onMouseClicked();

    private void setupKeyboardEvents() {
        table.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                selectedDataWork.ifPresentOrElse(item -> {
                    deleteSelectedRow(item);
                }, () -> {
                    log.warn("No selected row");
                });
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                clearSelectedItem();
            }
        });
    }

    private void deleteSelectedRow(T item) {
        items.remove(item);
        service.delete((DataWorkDto) item);
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