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
    protected List<DataWorkDto> get() {
        return service.get(tabName);
    }

}
