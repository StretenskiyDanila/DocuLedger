package org.example.businesspack.window.models.tab;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.window.models.table.DataWorkTable;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DataWorkTab extends TabManager<DataWorkDto> {
    
    public DataWorkTab(Tab tab, TableView<DataWorkDto> tableView) {
        super(tab, tableView, new DataWorkTable(tableView));
    }

    @Override
    protected void initialize() {
        tableManager.configureColumn(getColumnById("name"), "name");
        tableManager.configureColumn(getColumnById("count"), "count");
        tableManager.configureColumn(getColumnById("vat"), "vat");
        tableManager.configureColumn(getColumnById("group"), "group");
        tableManager.configureColumn(getColumnById("price"), "price");
        tableManager.configureColumn(getColumnById("summa"), "summa");
        tableManager.configureColumn(getColumnById("unitMeas"), "unitMeas");
    }

    @Override
    public void refresh() {
    }

    @SuppressWarnings("unchecked")
    private TableColumn<DataWorkDto, String> getColumnById(String columnId) {
        return tableView.getColumns().stream()
                .filter(column -> column.getId() != null && column.getId().equals(columnId))
                .map(column -> (TableColumn<DataWorkDto, String>) column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Column with ID " + columnId + " not found"));
    }

}
