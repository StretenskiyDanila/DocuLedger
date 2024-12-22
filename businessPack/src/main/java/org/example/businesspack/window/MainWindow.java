package org.example.businesspack.window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.PersonRole;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.ComboBoxManager;
import org.example.businesspack.window.models.TableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow {

    private final PersonService servicePerson = new PersonServiceImpl();

    private TableManager tableManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab account;

    @FXML
    private Tab actWorks;

    @FXML
    private Tab orderOutfit;

    @FXML
    private TableView<DataWorkDto> tableAccount;

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
        servicePerson.delete();

        tableManager = new TableManager(tableAccount);
        initializeTable();

        ComboBoxManager.configureComboBox(producer, PersonRole.PRODUCER, new PersonServiceImpl());
        ComboBoxManager.configureComboBox(consumer, PersonRole.CONSUMER, new PersonServiceImpl());
    }

    private void initializeTable() {
        tableManager.configureColumn(count, "count");
        tableManager.configureColumn(vat, "vat");
        tableManager.configureColumn(group, "group");
        tableManager.configureColumn(price, "price");
        tableManager.configureColumn(summa, "summa");
        tableManager.configureColumn(unitMeas, "unitMeas");
        tableManager.configureColumn(name, "name");

    }

}