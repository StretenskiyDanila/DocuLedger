package org.example.businesspack.window;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.models.tab.AccountTab;
import org.example.businesspack.window.models.tab.ActWorksTab;
import org.example.businesspack.window.models.tab.TabManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<DataWorkDto> tableAcc, tableAct;

    @FXML
    private TableColumn<DataWorkDto, String> group, name, unitMeas;
    
    @FXML
    private TableColumn<DataWorkDto, Integer> count;
    
    @FXML
    private TableColumn<DataWorkDto, BigDecimal> price, summa, vat;

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