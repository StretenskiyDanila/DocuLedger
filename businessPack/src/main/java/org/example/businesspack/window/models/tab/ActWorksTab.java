package org.example.businesspack.window.models.tab;

import java.util.List;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.window.models.table.DataWorkTable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ActWorksTab extends TabManager<DataWorkDto> {

   public ActWorksTab(Tab tab, TableView<DataWorkDto> tableView, List<ComboBox<PersonDto>> comboBoxs) {
        super(tab, tableView, new DataWorkTable(tableView, tab.getId()), comboBoxs);
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
