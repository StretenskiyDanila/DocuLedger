package org.example.businesspack.window.models.tab;

import java.util.List;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.window.models.table.DataWorkTable;
import org.example.businesspack.window.models.table.converter.BigDecimalConverter;
import org.example.businesspack.window.models.table.converter.IntegerConverter;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.util.converter.DefaultStringConverter;

public class ActWorksTab extends TabManager<DataWorkDto> {

    public ActWorksTab(Tab tab, TableView<DataWorkDto> tableView, List<ComboBox<PersonDto>> comboBoxs, DatePicker datePicker) {
        super(tab, new DataWorkTable(tableView, tab.getId()), comboBoxs, datePicker);
    }

    @Override
    protected void initialize() {
        tableManager.configureColumn(getColumnById("name"), DataWorkDto::getName, true,
                new DefaultStringConverter(), (item, newValue) -> item.getName().set(newValue));
        tableManager.configureColumn(getColumnById("count"),
                entity -> entity.getCount().asObject(), true, new IntegerConverter(),
                (item, newValue) -> item.getCount().set(newValue));
        tableManager.configureColumn(getColumnById("vat"), DataWorkDto::getVat, true, new BigDecimalConverter(),
                (item, newValue) -> item.getVat().set(newValue));
        tableManager.configureColumn(getColumnById("group"), DataWorkDto::getGroup, true,
                new DefaultStringConverter(), (item, newValue) -> item.getGroup().set(newValue));
        tableManager.configureColumn(getColumnById("price"), DataWorkDto::getPrice, true,
                new BigDecimalConverter(), (item, newValue) -> item.getPrice().set(newValue));
        tableManager.configureColumn(getColumnById("summa"), DataWorkDto::getSumma, false,
                new BigDecimalConverter(), (item, newValue) -> item.getSumma().set(newValue));
        tableManager.configureColumn(getColumnById("unitMeas"), DataWorkDto::getUnitMeas, true,
                new DefaultStringConverter(), (item, newValue) -> item.getUnitMeas().set(newValue));
    }

    @Override
    public void refresh() {
    }

}
