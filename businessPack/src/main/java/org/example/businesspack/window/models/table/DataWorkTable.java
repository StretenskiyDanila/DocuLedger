package org.example.businesspack.window.models.table;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.services.impl.DataWorkServiceImpl;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataWorkTable extends TableManager<DataWorkDto> {

    public DataWorkTable(TableView<DataWorkDto> tableAccount) {
        super(tableAccount, new DataWorkServiceImpl());
    }

    @Override
    protected void onMouseClicked(ObservableList<DataWorkDto> items) {
        DataWorkDto item = new DataWorkDto();
        item.setId(service.update(item));
        items.add(item);
    }

    @Override
    protected void onColumnEdit(DataWorkDto item, String property, String newValue) {
        if (item == null) {
            item = new DataWorkDto();
        }
        switch (property) {
            case "name" -> item.setName(newValue);
            case "count" -> item.setCount(newValue);
            case "vat" -> item.setVat(newValue);
            case "group" -> item.setGroup(newValue);
            case "price" -> item.setPrice(newValue);
            case "summa" -> item.setSumma(newValue);
            case "unitMeas" -> item.setUnitMeas(newValue);
        }
    }

}
