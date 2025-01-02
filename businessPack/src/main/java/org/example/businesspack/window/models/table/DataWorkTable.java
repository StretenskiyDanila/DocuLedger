package org.example.businesspack.window.models.table;

import java.util.List;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.services.impl.DataWorkServiceImpl;

import javafx.scene.control.TableView;

public class DataWorkTable extends TableManager<DataWorkDto> {

    public DataWorkTable(TableView<DataWorkDto> tableAccount, String tabName) {
        super(tableAccount, new DataWorkServiceImpl(), tabName);
    }

    @Override
    protected void onMouseClicked() {
        DataWorkDto item = new DataWorkDto(tabName);
        item.setId(service.update(item));
        items.add(item);
    }

    @Override
    protected void onColumnEdit(DataWorkDto item, String property, String newValue) {
        if (item == null) {
            item = new DataWorkDto(tabName);
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

    @Override
    protected List<DataWorkDto> get() {
        return service.get(tabName);
    }

}
