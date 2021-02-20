package sample;


import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Data.Contact;
import sample.Data.DataModel;


import java.io.IOException;
import java.util.Comparator;

import java.util.Optional;

import static javafx.scene.control.ButtonType.OK;

public class Controller {

    @FXML
    TableView<Contact> tableView;
    ObservableList<Contact> list;
    @FXML
    BorderPane main;
    @FXML
    Button button;
   @FXML
   private Button delButton;


    public void initialize(){


        SortedList<Contact> sortedList=new SortedList<>(DataModel.getInstance().getItems(), new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getfName().compareToIgnoreCase(o2.getfName());
            }
        });
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//          Contact contact=new Contact("A","B","notes");
//        Contact contact1=new Contact("A","B","notes");
//        Contact contact2=new Contact("A","B","notes");
//        list= FXCollections.observableArrayList();
//        list.add(contact);
//        list.add(contact1);
//        list.add(contact2);
//        DataModel.getInstance().setItems(list);
//        tableView.setItems(DataModel.getInstance().getItems());
        tableView.setItems(sortedList);

//        tableView.getItems().addAll(list);

    }
    public void deleteItem(Contact contact){
        DataModel.getInstance().deleteItem(contact);
    }
//    public void newWindow()  {
//     try {
//         Dialog<ButtonType> dialog=new Dialog<>();
//        dialog.initOwner(main.getScene().getWindow());
//        FXMLLoader fxmlLoader=new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("newWindow.fxml"));
//        dialog.getDialogPane().setContent(fxmlLoader.load());
//
//
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//
//
//        Optional<ButtonType> result=dialog.showAndWait();
//         if (result.isPresent()&&result.get()== OK){
//             NewWindow newWindow=fxmlLoader.getController();
//             newWindow.addContact();
//         }
//
////         NewWindow newWindow=fxmlLoader.getController();
//
//
//     }catch (Exception e){
//         System.out.println(e.getMessage());
//     }
//
//    }

    public void deleteContact(){
        Contact contact=tableView.getSelectionModel().getSelectedItem();
        if (contact!=null){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Contact: "+contact.getfName());
            alert.setContentText("Are you sure to delete this contact? ");
           Optional<ButtonType> result=alert.showAndWait();
           if (result.isPresent()&&result.get()== OK){
               DataModel.getInstance().deleteItem(contact);
           }

        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setTitle("Please select contact");
            alert.setContentText("No contact selected");
            alert.show();
        }

    }
    public void newWindow() throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newWindow.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setTitle("New Contact Window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(main.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void editContact() throws IOException{
        Contact contact=tableView.getSelectionModel().getSelectedItem();
        if (contact!=null){
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("newWindow1.fxml"));
            Parent root=fxmlLoader.load();
            Stage stage=new Stage();
            stage.setTitle("New Contact Window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
           NewWindow1 newWindow=fxmlLoader.getController();
            newWindow.editContact(contact);
            newWindow.updateContact(contact);
        }
        else {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setTitle("Please select contact");
            alert.setContentText("Select a contact to edit");
            alert.show();
        }
    }
}
