package org.example.businesspack.window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.enums.PersonRole;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.ComboBoxPersonManager;
import org.example.businesspack.window.models.tab.DataWorkTab;
import org.example.businesspack.window.models.tab.TabManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow {

    private final PersonService servicePerson = new PersonServiceImpl();

    private TabManager<DataWorkDto> dataWorkManager; 
    private TabManager<DataWorkDto> actWorksManager; 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab account, actWorks, orderOutfit;

    @FXML
    private TableView<DataWorkDto> tableAccount, tableActWorks;

    @FXML
    private TableColumn<DataWorkDto, String> count, group, name, price, summa, vat, unitMeas;

    @FXML
    private DatePicker date;

    @FXML
    private Button export, print, send, buttonIncrement;

    @FXML
    private TextField labelNumber;

    @FXML
    private ComboBox<PersonDto> producer, consumer;

    @FXML
    void onExport(ActionEvent event) {

    }

    @FXML
    void onIncrementNumber(ActionEvent event) {

    }

    @FXML
    void onPrint(ActionEvent event) {
        PersonDto selectedNameP = producer.getSelectionModel().getSelectedItem();///?????????
        servicePerson.update(selectedNameP);

        PersonDto selectedNameC = consumer.getSelectionModel().getSelectedItem();
        servicePerson.update(selectedNameC);

        System.out.println("Успешное обновление данных");
    }

    @FXML
    void onSend(ActionEvent event) {

    }

    @FXML
    void initialize() {
        servicePerson.clearMonth();

        dataWorkManager = new DataWorkTab(account, tableAccount);
        actWorksManager = new DataWorkTab(actWorks, tableActWorks);

        ComboBoxPersonManager.configureComboBox(producer, PersonRole.PRODUCER, new PersonServiceImpl());
        ComboBoxPersonManager.configureComboBox(consumer, PersonRole.CONSUMER, new PersonServiceImpl());
    }

}