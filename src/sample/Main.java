package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Data.Contact;
import sample.Data.DataModel;

import java.io.IOException;

public class Main extends Application {
// DataModel dataModel=new DataModel();
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Contact Book");
            primaryStage.setScene(new Scene(root, 800, 500));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        try {

            DataModel.getInstance().loadItems();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        try {
            DataModel.getInstance().storeItems();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
