package org.example.businesspack.window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.services.Service;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.tab.AccountTab;
import org.example.businesspack.window.models.tab.ActWorksTab;
import org.example.businesspack.window.models.tab.TabManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow {

    private final Service<PersonDto> servicePerson = new PersonServiceImpl();

    private TabManager<DataWorkDto> dataWorkManager;
    private TabManager<DataWorkDto> actWorksManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab account, actWorks, orderOutfit;

    @FXML
    private TableView<DataWorkDto> tableAcc, tableAct;

    @FXML
    private TableColumn<DataWorkDto, String> count, group, name, price, summa, vat, unitMeas;

    @FXML
    private DatePicker dateAcc, dateAct;

    @FXML
    private Button export, print, send, buttonIncrementAcc, buttonIncrementAct;

    @FXML
    private TextField labelNumber;

    @FXML
    private ComboBox<PersonDto> producerAcc, consumerAcc, producerAct, consumerAct, passedAct, acceptedAct;

    @FXML
    void onExport(ActionEvent event) {

    }

    @FXML
    void onIncrementNumber(ActionEvent event) {

    }

    @FXML
    void onPrint(ActionEvent event) {
        if (account.isSelected()) {
            dataWorkManager.updateSelectedItem();
        }
        else if (actWorks.isSelected()) {
            actWorksManager.updateSelectedItem();
        }
        else {

        }

        System.out.println("Успешное обновление данных");
    }

    @FXML
    void onSend(ActionEvent event) {

    }

    @FXML
    void initialize() {
        servicePerson.delete();

        // Инициализация таба Счета
        dataWorkManager = new AccountTab(account, tableAcc, List.of(producerAcc, consumerAcc));        

        // Инициализация таба Акты
        actWorksManager = new ActWorksTab(actWorks, tableAct, List.of(producerAct, consumerAct, passedAct, acceptedAct));
    }


}