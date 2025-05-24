package org.example.businesspack.window;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.businesspack.BusinessPackApplication;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.UserDataDto;
import org.example.businesspack.exports.PDFGenerator;
import org.example.businesspack.request.NotificationDto;
import org.example.businesspack.request.enums.ChannelNotification;
import org.example.businesspack.request.enums.TabState;
import org.example.businesspack.services.NotificationClient;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.UserDataService;
import org.example.businesspack.services.impl.PersonServiceImpl;
import org.example.businesspack.window.callback.DataSubmissionCallback;
import org.example.businesspack.window.models.tab.AccountTab;
import org.example.businesspack.window.models.tab.ActWorksTab;
import org.example.businesspack.window.models.tab.SelectedTabException;
import org.example.businesspack.window.models.tab.TabManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@Slf4j
public class MainWindow {

    private final PersonService servicePerson = new PersonServiceImpl();
    private final NotificationClient notificationClient = new NotificationClient();
    private final UserDataService userDataService = new UserDataService();

    private TabManager<DataWorkDto> dataWorkManager;
    private TabManager<DataWorkDto> actWorksManager;
    private final PDFGenerator pdfGenerator = new PDFGenerator();

    @FXML
    private Tab account, actWorks, orderOutfit;

    @FXML
    private TableView<DataWorkDto> tableAcc, tableAct;

//    @FXML
//    private TableColumn<DataWorkDto, String> group, name, unitMeas;
//
//    @FXML
//    private TableColumn<DataWorkDto, Integer> count;
//
//    @FXML
//    private TableColumn<DataWorkDto, BigDecimal> price, summa, vat;

    @FXML
    private DatePicker dateAcc, dateAct;

    @FXML
    private Button export, print, send, buttonIncrementAcc, buttonIncrementAct, completed;

    @FXML
    private CheckBox checkNotification;

    @FXML
    private TextField labelNumberAccount;

    @FXML
    private ComboBox<PersonDto> producerAcc, consumerAcc, producerAct, consumerAct, passedAct, acceptedAct;

    @FXML
    void onExport(ActionEvent event) throws Exception {
        String fileName = labelNumberAccount.getText();
//        if (account.isSelected()) {
//            pdfGenerator.generatePdfItext(dataWorkManager, fileName);
//        }
//        else if (actWorks.isSelected()) {
//            pdfGenerator.generatePdfItext(actWorksManager, fileName);
//        }
        Path path = Path.of(fileName);
        if (Files.exists(path)) {
            notificationClient.uploadFile("bot", "stretenskiy_danila", path);
        }
    }

    @FXML
    void onIncrementNumber(ActionEvent event) {

    }

    @FXML
    @SneakyThrows
    void onPrint(ActionEvent event) {
        log.info("Запрос на обновление данных");
        TabManager<?> selectedTab = getCurrentManager();
        selectedTab.updateSelectedItem();

        log.info("Успешное обновление данных");
    }

    @FXML
    void onSend(ActionEvent event) throws Exception {
        notificationClient.deleteNotification("bot", "stretenskiy_danila");
    }

    @FXML
    @SneakyThrows
    void onComplete(ActionEvent event) {
        UserDataDto userData = userDataService.getById(1);
        List<ChannelNotification> channels = Stream.of(
                        userData.getTelegramName() != null ? ChannelNotification.TELEGRAM : null,
                        userData.getEmail() != null ? ChannelNotification.MAIL : null)
                .filter(Objects::nonNull)
                .toList();

        TabManager<?> selectedTab = getCurrentManager();

        channels.forEach(channel -> notificationClient.changeNotification(NotificationDto.builder()
                        .userName(userData.getTelegramName())
                        .userMail(userData.getEmail())
                        .state(TabState.FINISHED)
                        .tabName(selectedTab.getTab().getText())
                        .channel(channel)
                        .build()));
        selectedTab.clearWorkspace();
    }

    @FXML
    void initialize() {
        servicePerson.delete();
        checkNotification.setOnAction(getActionCheckNotification(checkNotification));

        // Инициализация таба Счета
        dataWorkManager = new AccountTab(account, tableAcc, List.of(producerAcc, consumerAcc), dateAcc);
        // Инициализация таба Акты
        actWorksManager = new ActWorksTab(actWorks, tableAct, List.of(producerAct, consumerAct, passedAct, acceptedAct), dateAct);

        dataWorkManager.setPropertyTab(getPropertyTabListener(dataWorkManager));
        actWorksManager.setPropertyTab(getPropertyTabListener(actWorksManager));
    }

    private EventHandler<ActionEvent> getActionCheckNotification(CheckBox checkNotification) {
        return event1 -> {
            if (checkNotification.isSelected()) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(BusinessPackApplication.class.getResource("notification-settings.fxml"));
                    AnchorPane page = loader.load();

                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Настройки уведомлений");
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(checkNotification.getScene().getWindow());
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    NotificationSettings controller = loader.getController();
                    controller.setDialogStage(dialogStage);
                    boolean oldEnable = checkNotification.isSelected();
                    controller.setCallback(new DataSubmissionCallback() {
                        @Override
                        public void onDataSubmitted() {
                            checkNotification.setSelected(true);
                        }

                        @Override
                        public void onCancelled() {
                            checkNotification.setSelected(false);
                        }
                    });
                    boolean newEnable = checkNotification.isSelected();
                    if (oldEnable != newEnable) {
                        UserDataDto dto = controller.getDto();
                        if (dto.getTelegramName() != null)
                            notificationClient.changeNotificationEnable(
                                    "bot", dto.getTelegramName(), newEnable);
                        else if (dto.getEmail() != null)
                            notificationClient.changeNotificationEnable(
                                    "mail", dto.getEmail(), newEnable);
                    }

                    dialogStage.showAndWait();
                    //dialogStage.hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private ChangeListener<Boolean> getPropertyTabListener(TabManager<?> tabManager) {
        return (obs, oldVal, newVal) -> {
            if (checkNotification.isSelected() && tabManager.isStatusIdle() &&
                    newVal && tabManager.isWorkingTab()) {
                tabManager.setStatus(TabState.RUNNING);

                UserDataDto userData = userDataService.getById(1);
                List<ChannelNotification> channels = Stream.of(
                                userData.getTelegramName() != null ? ChannelNotification.TELEGRAM : null,
                                userData.getEmail() != null ? ChannelNotification.MAIL : null)
                        .filter(Objects::nonNull)
                        .toList();

                channels.forEach(channel -> notificationClient.changeNotification(
                                NotificationDto.builder()
                                        .tabName(tabManager.getTab().getId())
                                        .state(TabState.RUNNING)
                                        .userName(userData.getTelegramName())
                                        .userMail(userData.getEmail())
                                        .channel(channel)
                                        .build()));
            }

        };
    }

    private TabManager<?> getCurrentManager() throws SelectedTabException {
        if (account.isSelected())
            return dataWorkManager;
        else if (actWorks.isSelected())
            return actWorksManager;
        else
            throw new SelectedTabException("Не выбрана ни одна вкладка");
    }

}