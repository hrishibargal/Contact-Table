package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Data.Contact;
import sample.Data.DataModel;

import java.util.regex.Pattern;

public class NewWindow {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    GridPane gridPane;
    @FXML
    Button addButton;


    public void addContact(){
       Stage stage=(Stage) gridPane.getScene().getWindow();

        String firstName=firstNameField.getText();
        String lastName=lastNameField.getText();
        String phoneNumber=phoneNumberField.getText();
        if (lastName.isEmpty()||firstName.isEmpty()||phoneNumber.isEmpty()){
            Alert error=new Alert(Alert.AlertType.ERROR);
            error.setTitle("Incomplete fields");
            error.setHeaderText("Please fill all fields");
            error.showAndWait();
        }
        else {
              if (Pattern.matches("^[789]{1}[0-9]{9}$",phoneNumber))
             {
                 if (Pattern.matches("\\D*",firstName)&&Pattern.matches("\\D*",lastName)) {

                 Contact contact = new Contact(firstName, lastName, phoneNumber);
                 DataModel.getInstance().addData(contact);
                 stage.close();
             }
                 else {
                     Alert error=new Alert(Alert.AlertType.ERROR);
                     error.setTitle("Name of contact is not valid");
                     error.setHeaderText("Please fill valid contact name");
                     error.showAndWait();

                 }
             } else{
                Alert error=new Alert(Alert.AlertType.ERROR);
                error.setTitle("Incompatible contact");
                error.setHeaderText("Please fill valid contact of 10 digit only");
                error.showAndWait();
            }
        }


    }

    public void cancelButton(){
        Stage stage=(Stage) lastNameField.getScene().getWindow();
        stage.close();
    }
}
