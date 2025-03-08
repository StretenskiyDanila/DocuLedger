package org.example.businesspack.window.models.tab;

import java.util.ArrayList;
import java.util.List;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.enums.PersonRole;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.combo_box.ComboBoxManager;
import org.example.businesspack.window.models.combo_box.PersonComboBox;
import org.example.businesspack.window.models.table.TableManager;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import lombok.Getter;

public abstract class TabManager<T> {

    protected final Tab tab;

    @Getter
    protected final List<ComboBoxManager<PersonDto>> comboBoxManagers;

    @Getter
    protected final TableManager<T> tableManager;

    @Getter
    protected final DatePicker dataPicker;

    public TabManager(Tab tab, TableManager<T> tableManager, List<ComboBox<PersonDto>> comboBoxs, DatePicker dataPicker) {
        this.tab = tab;
        this.tableManager = tableManager;
        this.dataPicker = dataPicker;
        this.comboBoxManagers = initializeComboBoxProdCons(comboBoxs);
        initialize();
    }

    public abstract void refresh();

    protected abstract void initialize();

    public void updateSelectedItem() {
        if (tab.isSelected()) {
            comboBoxManagers.stream()
                    .forEach(ComboBoxManager::updateSelectedItem);
        }
    }

    protected List<ComboBoxManager<PersonDto>> initializeComboBoxProdCons(List<ComboBox<PersonDto>> comboBoxs) {
        List<ComboBoxManager<PersonDto>> managers = new ArrayList<>(comboBoxs.size());
        for (int i = 0; i < comboBoxs.size(); i++) {
            managers.add(initializeComboBox(comboBoxs.get(i), PersonRole.values()[i]));
        }

        return managers;
    }

    @SuppressWarnings("unchecked")
    protected <P> TableColumn<DataWorkDto, P> getColumnById(String columnId) {
        return tableManager.getTable().getColumns().stream()
                .filter(column -> column.getId() != null && column.getId().equals(columnId))
                .map(column -> (TableColumn<DataWorkDto, P>) column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Column with ID " + columnId + " not found"));
    }

    private ComboBoxManager<PersonDto> initializeComboBox(ComboBox<PersonDto> comboBox, PersonRole role) {
        ComboBoxManager<PersonDto> temp = new PersonComboBox(comboBox, new PersonServiceImpl(), role, tab.getId());
        temp.configureComboBox();

        return temp;
    }

}
