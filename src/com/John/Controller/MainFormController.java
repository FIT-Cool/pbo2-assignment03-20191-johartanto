package com.John.Controller;

import com.John.Entity.Category;
import com.John.Entity.Item;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.time.LocalDate;
public class MainFormController implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public ComboBox<Category> comboCat;
    public DatePicker exDate;
    public Button btnSave;
    public Button btnUpdate;
    public TableColumn<Item,String> col01;
    public TableColumn<Item,String> col02;
    public TableColumn<Item,String> col03;
    public TableColumn<Item,String> col04;
    public TableView tableDepartment;

    private ObservableList<Item> items;
    private ObservableList<Category> categories;
    public ObservableList<Item> getItems() {
        if (items==null){
            items=FXCollections.observableArrayList();
        }
        return items;
    }

    public ObservableList<Category> getCategories() {
        if (categories==null){
            categories=FXCollections.observableArrayList();
        }
        return categories;
    }
    public void saveTable(ActionEvent actionEvent) {
        Item item=new Item();
        item.setName(txtName.getText());
        item.setId(Integer.valueOf(txtId.getText()));
        item.setCategory(comboCat.getValue());
        item.setExDate(String.valueOf(exDate.getValue()));
        this.getItems().add(item);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableDepartment.setItems(this.getItems());
        comboCat.setItems(this.getCategories());
        col01.setCellValueFactory(data->{
            Item i=data.getValue();
            return new SimpleStringProperty(String.valueOf(i.getId()));
        });
        col02.setCellValueFactory(data->{
            Item i=data.getValue();
            return new SimpleStringProperty(i.getName());
        });
        col03.setCellValueFactory(data->{
            Item i=data.getValue();
            return new SimpleStringProperty(i.getCategory().getName());
        });
        col04.setCellValueFactory(data->{
            Item i=data.getValue();
            return new SimpleStringProperty(i.getExDate());
        });
    }
}
