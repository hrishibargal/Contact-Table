package sample.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataModel {
  static ObservableList<Contact> contacts;
    public static DataModel dataModel=new DataModel();

    private DataModel() {
        contacts = FXCollections.observableArrayList();
    }
    public static DataModel getInstance(){
        return dataModel;
    }
    public void setItems(ObservableList<Contact> item){
        contacts=item;
    }
    public ObservableList<Contact> getItems(){
        return contacts;
    }
    public void deleteItem(Contact contact){
        contacts.remove(contact);
    }
    public void loadItems() throws IOException {
        Path paths= Paths.get("A.txt");
        BufferedReader br= Files.newBufferedReader(paths);
        String input;
        try {


        while((input=br.readLine())!=null) {
            String[] items = input.split("\t");
            String fName = items[0];
            String lName = items[1];
            String email = items[2];
            Contact contact = new Contact(fName, lName, email);
            contacts.add(contact);
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        finally {
            if (br!=null)
                br.close();
        }
    }
    public void  storeItems() throws IOException {
        Path path = Paths.get("A.txt");
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            for (Contact contact : contacts) {
                bw.write(String.format("%s\t%s\t%s",
                        contact.getfName(),
                        contact.getlName(),
                        contact.getMobo()));
                bw.newLine();
            }
        }finally {
            if (bw!=null)
                bw.close();
        }
    }
    public void addData(Contact contact){
        contacts.add(contact);
    }

}
