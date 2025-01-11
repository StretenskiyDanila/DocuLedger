package org.example.businesspack.window.models.tab;

import java.util.List;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.window.models.table.DataWorkTable;
import org.example.businesspack.window.models.table.converter.BigDecimalConverter;
import org.example.businesspack.window.models.table.converter.IntegerConverter;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.converter.DefaultStringConverter;

public class AccountTab extends TabManager<DataWorkDto> {

    public AccountTab(Tab tab, TableView<DataWorkDto> tableView, List<ComboBox<PersonDto>> comboBoxs) {
        super(tab, tableView, new DataWorkTable(tableView, tab.getId()), comboBoxs);
    }

    @Override
    protected void initialize() {
        tableManager.configureColumn(getColumnById("name"), DataWorkDto::nameProperty, true,
                new DefaultStringConverter(), (item, newValue) -> item.setName(newValue));
        tableManager.configureColumn(getColumnById("count"),
                dto -> (ObservableValue<Integer>) dto.countProperty().asObject(), true, new IntegerConverter(),
                (item, newValue) -> item.setCount(newValue));
        tableManager.configureColumn(getColumnById("vat"), DataWorkDto::vatProperty, true, new BigDecimalConverter(),
                (item, newValue) -> item.setVat(newValue));
        tableManager.configureColumn(getColumnById("group"), DataWorkDto::groupProperty, true,
                new DefaultStringConverter(), (item, newValue) -> item.setGroup(newValue));
        tableManager.configureColumn(getColumnById("price"), DataWorkDto::priceProperty, true,
                new BigDecimalConverter(), (item, newValue) -> item.setPrice(newValue));
        tableManager.configureColumn(getColumnById("summa"), DataWorkDto::summaProperty, false,
                new BigDecimalConverter(), (item, newValue) -> item.setSumma(newValue));
        tableManager.configureColumn(getColumnById("unitMeas"), DataWorkDto::unitMeasProperty, true,
                new DefaultStringConverter(), (item, newValue) -> item.setUnitMeas(newValue));
    }

    @Override
    public void refresh() {
    }

    @SuppressWarnings("unchecked")
    private <P> TableColumn<DataWorkDto, P> getColumnById(String columnId) {
        return tableView.getColumns().stream()
                .filter(column -> column.getId() != null && column.getId().equals(columnId))
                .map(column -> (TableColumn<DataWorkDto, P>) column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Column with ID " + columnId + " not found"));
    }

}
