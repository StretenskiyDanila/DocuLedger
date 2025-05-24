package org.example.businesspack.window.models.tab;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import lombok.Getter;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.enums.PersonRole;
import org.example.businesspack.repositories.TabStatusRepository;
import org.example.businesspack.request.enums.TabState;
import org.example.businesspack.services.TabStatusService;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.combo_box.ComboBoxManager;
import org.example.businesspack.window.models.combo_box.PersonComboBox;
import org.example.businesspack.window.models.table.TableManager;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class TabManager<T> {

    private final TabStatusService tabStatusService = new TabStatusService();

    protected final Tab tab;
    protected final List<ComboBoxManager<PersonDto>> comboBoxManagers;
    protected final TableManager<T> tableManager;
    protected final DatePicker dataPicker;

    public TabManager(Tab tab, TableManager<T> tableManager, List<ComboBox<PersonDto>> comboBoxs, DatePicker dataPicker) {
        this.tab = tab;
        this.tableManager = tableManager;
        this.dataPicker = dataPicker;
        this.comboBoxManagers = initializeComboBoxProdCons(comboBoxs);
        initialize();
    }

    public void updateSelectedItem() {
        if (tab.isSelected()) {
            comboBoxManagers.forEach(ComboBoxManager::updateSelectedItem);
        }
    }

    public abstract void refresh();

    public boolean isStatusIdle() {
        return tabStatusService.getStatus(tab.getId()).equals(TabState.FINISHED);
    }

    public void setStatus(TabState state) {
        tabStatusService.update(tab.getId(), state);
    }

    protected abstract void initialize();

    public void setPropertyTab(ChangeListener<Boolean> changeListener) {
        tab.selectedProperty().addListener(changeListener);
    }

    public boolean isWorkingTab() {
        return !(comboBoxManagers.stream().allMatch(ComboBoxManager::isEmpty) &&
                tableManager.isEmpty() &&
                dataPicker.getValue() == null);
    }

    public void clearWorkspace() {
        comboBoxManagers.forEach(ComboBoxManager::clear);
        tableManager.clear();
        dataPicker.setValue(null);
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
