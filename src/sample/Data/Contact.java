package sample.Data;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    SimpleStringProperty fName=new SimpleStringProperty();
    SimpleStringProperty lName=new SimpleStringProperty();
    SimpleStringProperty mobo =new SimpleStringProperty();

    public Contact(String fName, String lName, String mobo) {
        this.fName.set(fName);
        this.lName.set(lName);
        this.mobo.set(mobo);
    }

    public String getfName() {
        return fName.get();
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getlName() {
        return lName.get();
    }

    public SimpleStringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public String getMobo() {
        return mobo.get();
    }

    public SimpleStringProperty moboProperty() {
        return mobo;
    }

    public void setMobo(String mobo) {
        this.mobo.set(mobo);
    }
}
