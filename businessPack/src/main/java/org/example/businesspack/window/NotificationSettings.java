package org.example.businesspack.window;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.businesspack.dto.UserDataDto;
import org.example.businesspack.services.UserDataService;
import org.example.businesspack.window.callback.DataSubmissionCallback;

public class NotificationSettings {

    private final UserDataService userDataService = new UserDataService();

    private static final String EMAIL_PATTERN = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    @Getter
    private UserDataDto dto;

    @FXML
    private Button okButton, cancelButton;

    @FXML
    private TextField telegramAccText, emailText;

    @FXML
    private Text warnCorrectEmail, incorrectDataAccount;

    @Setter
    private DataSubmissionCallback callback;

    @Setter
    private Stage dialogStage;

    @FXML
    void initialize() {
        dto = userDataService.getById(1);
        telegramAccText.setText(dto.getTelegramName());
        emailText.setText(dto.getEmail());

        okButton.setOnAction(event -> {
            String telegramName = telegramAccText.getText() == null ? "" : telegramAccText.getText().trim();
            String email = emailText.getText() == null ? "" : emailText.getText().trim();
            if (validateInput(telegramName, email)) {
                if (callback != null) {
                    dto = UserDataDto.builder()
                            .telegramName(telegramName)
                            .email(email)
                            .build();
                    userDataService.update(dto);
                    callback.onDataSubmitted();
                }
                dialogStage.close();
                return;
            }
            System.err.println("not valid");
        });

        cancelButton.setOnAction(event -> {
            if (callback != null) {
                callback.onCancelled();
            }
            dialogStage.close();
        });

        emailText.textProperty().addListener((observable, oldValue, newValue) ->
            warnCorrectEmail.setVisible(!newValue.isEmpty() && !isValidEmail(newValue))
        );
    }

    private boolean validateInput(String telegramName, String email) {
        incorrectDataAccount.setVisible(telegramName.isEmpty() && email.isEmpty());

        return !incorrectDataAccount.isVisible() && !warnCorrectEmail.isVisible();
    }

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

}
