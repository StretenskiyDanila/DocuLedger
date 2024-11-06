package org.example.businesspack.window;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.example.businesspack.window.model.AccountModel;

public class Account {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonIncrement;

    @FXML
    private ComboBox<String> customer;

    @FXML
    private ComboBox<String> producer;

    @FXML
    private DatePicker date;

    @FXML
    private Button export;

    @FXML
    private TableView<AccountModel> tableAccount;

    @FXML
    private TableColumn<AccountModel, String> group;

    @FXML
    private TableColumn<AccountModel, String> count;

    @FXML
    private TableColumn<AccountModel, String> name;

    @FXML
    private TableColumn<AccountModel, String> price;

    @FXML
    private TableColumn<AccountModel, String> summa;

    @FXML
    private TableColumn<AccountModel, String> unitMeas;

    @FXML
    private TableColumn<AccountModel, String> vat;

    @FXML
    private TextField labelNumber;

    @FXML
    private Button print;

    @FXML
    private Button send;

    @FXML
    void choiceDate(ActionEvent event) {

    }

    @FXML
    void onChangeNumber(ActionEvent event) {

    }

    @FXML
    void onChoiceCustomer(ActionEvent event) {

    }

    @FXML
    void onChoiceProducer(ActionEvent event) {

    }

    @FXML
    void onExport(ActionEvent event) {

    }

    @FXML
    void onIncrementNumber(ActionEvent event) {

    }

    @FXML
    void onPrint(ActionEvent event) {

    }

    @FXML
    void onSend(ActionEvent event) {

    }

    @FXML
    void onAdd(MouseEvent event) {
        event.
        if (event.getClickCount() == 1 && tableAccount.getSelectionModel().isEmpty()) {
            ObservableList<AccountModel> accountModels = tableAccount.getItems();
            // Создание новой записи
            AccountModel newPerson = new AccountModel(); // Измените начальные значения по своему усмотрению
            accountModels.add(newPerson);

            // Прокрутка к новой строке и выделение её
            tableAccount.scrollTo(newPerson);
            tableAccount.getSelectionModel().select(newPerson);
        }
    }

    @FXML
    void onUpdateTable(ActionEvent event) {

        ObservableList<AccountModel> items = tableAccount.getItems();
        items.forEach(System.out::println);
    }

    @FXML
    void initialize() {
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        vat.setCellValueFactory(new PropertyValueFactory<>("vat"));
        group.setCellValueFactory(new PropertyValueFactory<>("group"));
        unitMeas.setCellValueFactory(new PropertyValueFactory<>("unitMeas"));
        summa.setCellValueFactory(new PropertyValueFactory<>("summa"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        count.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn());

    }

}

