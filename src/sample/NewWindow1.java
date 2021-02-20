package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Data.Contact;
import java.util.regex.Pattern;

public class NewWindow1 {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    GridPane gridPane;
    @FXML
    Button updateButton;
    @FXML
    Button Cancel;

    public void editContact(Contact contact) {

        firstNameField.setText(contact.getfName());
        lastNameField.setText(contact.getlName());
        phoneNumberField.setText(contact.getMobo());
        return;


    }

    public void updateContact(Contact contact) {
        Stage stage = (Stage) gridPane.getScene().getWindow();

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (lastNameField.getText().isEmpty() || firstNameField.getText().isEmpty()) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Incomplete fields");
                    error.setHeaderText("Please fill all fields");
                    error.showAndWait();
                } else {
                    if (Pattern.matches("^[0-9]{1}[0-9]{9}$", phoneNumberField.getText())) {
                        if (Pattern.matches("\\D*", firstNameField.getText()) && Pattern.matches("\\D*", lastNameField.getText())) {
                            contact.setfName(firstNameField.getText());
                            contact.setlName(lastNameField.getText());
                            contact.setMobo(phoneNumberField.getText());
                            stage.close();
                        } else {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("Name of contact is not valid");
                            error.setHeaderText("Please fill valid contact name");
                            error.showAndWait();

                        }
                    } else {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Incompatible contact");
                        error.setHeaderText("Please fill valid contact of 10 digits only");
                        error.showAndWait();
                    }
                }
            }
        });
    }
}
