package org.example.businesspack.window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.businesspack.dto.AccountDto;
import org.example.businesspack.repositories.AccountRepository;
import org.example.businesspack.repositories.MasterRepository;
import org.example.businesspack.services.AccountService;
import org.example.businesspack.services.impl.AccountServiceImpl;
import org.example.businesspack.utils.ComboBoxUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow {

    private final AccountService accountService = new AccountServiceImpl();
    private ObservableList<AccountDto> items;
    private AccountDto selectedAccount;

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
    private Button buttonIncrement;

    @FXML
    private TableColumn<AccountDto, String> count;

    @FXML
    private ComboBox<String> consumer;

    @FXML
    private DatePicker date;

    @FXML
    private Button export;

    @FXML
    private Button addTable;

    @FXML
    private Button updateTable;

    @FXML
    private Button deleteRows;

    @FXML
    private TableColumn<AccountDto, String> group;

    @FXML
    private TextField labelNumber;

    @FXML
    private TableColumn<AccountDto, String> name;

    @FXML
    private TableColumn<AccountDto, String> price;

    @FXML
    private Button print;

    @FXML
    private ComboBox<String> producer;

    @FXML
    private Button send;

    @FXML
    private TableColumn<AccountDto, String> summa;

    @FXML
    private TableView<AccountDto> tableAccount;

    @FXML
    private TableColumn<AccountDto, String> unitMeas;

    @FXML
    private TableColumn<AccountDto, String> vat;

    @FXML
    private TextField summaText;

    @FXML
    private TextField unitMeasText;

    @FXML
    private TextField countText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField vatText;

    @FXML
    private TextField groupText;

    @FXML
    private TextField priceText;

    @FXML
    void onAddTable(ActionEvent event) {
        AccountDto accountDto = buildFromField();
        items.add(accountDto);
        accountService.saveAccount(accountDto);
        tableAccount.setItems(items);
    }

    @FXML
    void onDeleteRows(ActionEvent event) {
        if (selectedAccount != null) {
            accountService.deleteAccount(selectedAccount);
            items = FXCollections.observableList(accountService.getAccounts());
            tableAccount.setItems(items);
        }
    }

    @FXML
    void onUpdateTable(ActionEvent event) {
        if (selectedAccount != null) {
            AccountDto accountUpdate = buildFromField();
            accountService.updateAccount(selectedAccount, accountUpdate);
            items = FXCollections.observableList(accountService.getAccounts());
            tableAccount.setItems(items);
        }
    }

    @FXML
    void onSelectedItem(MouseEvent event) {
        selectedAccount = tableAccount.getSelectionModel().getSelectedItem();
        setField(selectedAccount);
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
    void initialize() {
        items = FXCollections.observableList(accountService.getAccounts());

        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        vat.setCellValueFactory(new PropertyValueFactory<>("vat"));
        group.setCellValueFactory(new PropertyValueFactory<>("group"));
        unitMeas.setCellValueFactory(new PropertyValueFactory<>("unitMeas"));
        summa.setCellValueFactory(new PropertyValueFactory<>("summa"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableAccount.setItems(items);
        tableAccount.setEditable(true);

        ComboBoxUtils.setComboBoxSearchProperty(producer, new AccountRepository());
        ComboBoxUtils.setComboBoxSearchProperty(consumer, new MasterRepository());
    }

    private AccountDto buildFromField() {
        String name = nameText.getText();
        String count = countText.getText();
        String vat = vatText.getText();
        String group = groupText.getText();
        String price = priceText.getText();
        String summa = summaText.getText();
        String unitMeas = unitMeasText.getText();

        return AccountDto.builder()
                .count(count)
                .group(group)
                .summa(summa)
                .vat(vat)
                .price(price)
                .unitMeas(unitMeas)
                .name(name)
                .build();
    }

    private void setField(AccountDto account) {
        nameText.setText(account.getName());
        countText.setText(account.getCount());
        vatText.setText(account.getVat());
        groupText.setText(account.getGroup());
        priceText.setText(account.getPrice());
        summaText.setText(account.getSumma());
        unitMeasText.setText(account.getUnitMeas());
    }

}