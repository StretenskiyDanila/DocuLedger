package org.example.businesspack.window.models.tab;

import org.example.businesspack.window.models.table.TableManager;

import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import lombok.Getter;

public abstract class TabManager<T> {
 
    protected final Tab tab;

    @Getter
    protected final TableView<T> tableView;
    
    @Getter
    protected final TableManager<T> tableManager;

    public TabManager(Tab tab, TableView<T> tableView, TableManager<T> tableManager) {
        this.tab = tab;
        this.tableView = tableView;
        this.tableManager = tableManager;
        initialize();
    }

    protected abstract void initialize();

    public abstract void refresh();
    
}
