package org.example.businesspack.window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.PersonRole;
import org.example.businesspack.services.DataWorkService;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.impl.DataWorkServiceImpl;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.utils.ComboBoxUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow {

    private final DataWorkService serviceDataWork = new DataWorkServiceImpl();
    private final PersonService servicePerson = new PersonServiceImpl();

    private ObservableList<DataWorkDto> items;
    private DataWorkDto selectedDataWork;

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
    private TableColumn<DataWorkDto, String> count;

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
    private TableColumn<DataWorkDto, String> group;

    @FXML
    private TextField labelNumber;

    @FXML
    private TableColumn<DataWorkDto, String> name;

    @FXML
    private TableColumn<DataWorkDto, String> price;

    @FXML
    private Button print;

    @FXML
    private ComboBox<String> producer;

    @FXML
    private Button send;

    @FXML
    private TableColumn<DataWorkDto, String> summa;

    @FXML
    private TableView<DataWorkDto> tableAccount;

    @FXML
    private TableColumn<DataWorkDto, String> unitMeas;

    @FXML
    private TableColumn<DataWorkDto, String> vat;

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
        DataWorkDto dataWorkDto = new DataWorkDto();
        buildFromField(dataWorkDto);
        items.add(dataWorkDto);
        serviceDataWork.save(dataWorkDto);
        tableAccount.setItems(items);
    }

    @FXML
    void onDeleteRows(ActionEvent event) {
        if (selectedDataWork != null) {
            serviceDataWork.delete(selectedDataWork);
            items = FXCollections.observableList(serviceDataWork.get());
            tableAccount.setItems(items);
        }
    }

    @FXML
    void onUpdateTable(ActionEvent event) {
        if (selectedDataWork != null) {
            buildFromField(selectedDataWork);
            serviceDataWork.update(selectedDataWork);
            items = FXCollections.observableList(serviceDataWork.get());
            tableAccount.setItems(items);
        }
    }

    @FXML
    void onSelectedItem(MouseEvent event) {
        selectedDataWork = tableAccount.getSelectionModel().getSelectedItem();
        setField(selectedDataWork);
    }

    @FXML
    void onExport(ActionEvent event) {

    }

    @FXML
    void onIncrementNumber(ActionEvent event) {

    }

    @FXML
    void onPrint(ActionEvent event) {
        String selectedNameP = producer.getSelectionModel().getSelectedItem();
        PersonDto personDtoP = PersonDto.builder()
                .name(selectedNameP)
                .role(PersonRole.PRODUCER)
                .build();
        servicePerson.update(personDtoP);

        String selectedNameC = consumer.getSelectionModel().getSelectedItem();
        PersonDto personDtoC = PersonDto.builder()
                .name(selectedNameC)
                .role(PersonRole.CONSUMER)
                .build();
        servicePerson.update(personDtoC);

        System.out.println("Успешное обновление данных");
    }

    @FXML
    void onSend(ActionEvent event) {

    }

    @FXML
    void initialize() {
        items = FXCollections.observableList(serviceDataWork.get());

        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        vat.setCellValueFactory(new PropertyValueFactory<>("vat"));
        group.setCellValueFactory(new PropertyValueFactory<>("group"));
        unitMeas.setCellValueFactory(new PropertyValueFactory<>("unitMeas"));
        summa.setCellValueFactory(new PropertyValueFactory<>("summa"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableAccount.setItems(items);
        tableAccount.setEditable(true);

        ComboBoxUtils.setComboBoxSearchProperty(producer, PersonRole.PRODUCER);
        ComboBoxUtils.setComboBoxSearchProperty(consumer, PersonRole.CONSUMER);

        servicePerson.delete();
    }

    private void buildFromField(DataWorkDto dataWorkDto) {
        dataWorkDto.setName(nameText.getText());
        dataWorkDto.setCount(countText.getText());
        dataWorkDto.setVat(vatText.getText());
        dataWorkDto.setGroup(groupText.getText());
        dataWorkDto.setPrice(priceText.getText());
        dataWorkDto.setSumma(summaText.getText());
        dataWorkDto.setUnitMeas(unitMeasText.getText());
    }

    private void setField(DataWorkDto account) {
        nameText.setText(account.getName());
        countText.setText(account.getCount());
        vatText.setText(account.getVat());
        groupText.setText(account.getGroup());
        priceText.setText(account.getPrice());
        summaText.setText(account.getSumma());
        unitMeasText.setText(account.getUnitMeas());
    }

}