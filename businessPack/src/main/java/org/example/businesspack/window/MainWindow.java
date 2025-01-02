package org.example.businesspack.window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.enums.PersonRole;
import org.example.businesspack.services.Service;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.combo_box.ComboBoxManager;
import org.example.businesspack.window.models.combo_box.PersonComboBox;
import org.example.businesspack.window.models.tab.AccountTab;
import org.example.businesspack.window.models.tab.ActWorksTab;
import org.example.businesspack.window.models.tab.TabManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow {

    private final Service<PersonDto> servicePerson = new PersonServiceImpl();

    private TabManager<DataWorkDto> dataWorkManager;
    private TabManager<DataWorkDto> actWorksManager;

    private ComboBoxManager<PersonDto> producerAccComboBox;
    private ComboBoxManager<PersonDto> consumerAccComboBox;
    private ComboBoxManager<PersonDto> producerActComboBox;
    private ComboBoxManager<PersonDto> consumerActComboBox;
    private ComboBoxManager<PersonDto> passedActComboBox;
    private ComboBoxManager<PersonDto> acceptedActComboBox;

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
    private ComboBox<PersonDto> producerAcc, consumerAcc, producerAct, consumerAct, passedAct, acceptedAcc;

    @FXML
    void onExport(ActionEvent event) {

    }

    @FXML
    void onIncrementNumber(ActionEvent event) {

    }

    @FXML
    void onPrint(ActionEvent event) {
        producerAccComboBox.updateSelectedItem();
        consumerAccComboBox.updateSelectedItem();

        System.out.println("Успешное обновление данных");
    }

    @FXML
    void onSend(ActionEvent event) {

    }

    @FXML
    void initialize() {
        servicePerson.delete();

        // Инициализация таба Счета
        dataWorkManager = new AccountTab(account, tableAcc);        
        initializeComboBoxProdCons(producerAccComboBox, consumerAccComboBox, producerAcc, consumerAcc);

        // Инициализация таба Акты
        actWorksManager = new ActWorksTab(actWorks, tableAct);
        initializeComboBoxProdCons(producerActComboBox, consumerActComboBox, producerAct, consumerAct);
        initializeComboBoxPassedAccepted(passedActComboBox, acceptedActComboBox, passedAct, acceptedAcc);
    }

    private void initializeComboBoxProdCons(ComboBoxManager<PersonDto> prodManager,
            ComboBoxManager<PersonDto> consManager,
            ComboBox<PersonDto> producer,
            ComboBox<PersonDto> consumer) {
        prodManager = new PersonComboBox(producer, new PersonServiceImpl(), PersonRole.PRODUCER);
        prodManager.configureComboBox();
        consManager = new PersonComboBox(consumer, new PersonServiceImpl(), PersonRole.CONSUMER);
        consManager.configureComboBox();
    }

    private void initializeComboBoxPassedAccepted(ComboBoxManager<PersonDto> passedManager,
            ComboBoxManager<PersonDto> acceptedManager,
            ComboBox<PersonDto> passed,
            ComboBox<PersonDto> accepted) {
        passedManager = new PersonComboBox(passed, new PersonServiceImpl(), PersonRole.PASSED);
        passedManager.configureComboBox();
        acceptedManager = new PersonComboBox(accepted, new PersonServiceImpl(), PersonRole.ACCEPTED);
        acceptedManager.configureComboBox();
    }

}