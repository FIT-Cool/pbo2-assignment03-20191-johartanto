package com.John.Controller;
/**
 * author:Johnson Hartanto
 * NRP:1772017
 */
import com.John.Entity.Category;
import com.John.Entity.Item;
import com.John.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
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
    public TableView<Item> tableDepartment;

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
        Alert alert=new Alert(Alert.AlertType.ERROR);
        boolean found=false;
        for (Item i:this.getItems()){
            if (i.getName().equals(txtName.getText())){
                found=true;
            }
        }
        if (found){
            alert.setContentText("Duplicate item name");
            alert.setTitle("Error");
            alert.show();
        }
        else{
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || comboCat.getSelectionModel().getSelectedIndex()==-1 || exDate.getEditor().getText().isEmpty()){
                alert.setTitle("Error");
                alert.setContentText("Please fill name/id/category/date");
                alert.show();
            }
            else {
                Item item=new Item();
                item.setName(txtName.getText());
                item.setId(Integer.valueOf(txtId.getText()));
                item.setCategory(comboCat.getValue());
                item.setExDate(exDate.getValue());
                this.getItems().add(item);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setDisable(true);
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
            return new SimpleStringProperty(String.valueOf(i.getExDate()));
        });
    }

    public void categoryAct(ActionEvent actionEvent) {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/CategoryMan.fxml"));
            BorderPane root=loader.load();
            CategoryFormController controller=loader.getController();
            controller.setMainFormController(this);
            Stage mainStage=new Stage();
            mainStage.setTitle("Category Form");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeAct(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void resetAct(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        comboCat.getSelectionModel().select(-1);
        exDate.getEditor().clear();
    }

    public void updateAct(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.ERROR);

            if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || comboCat.getSelectionModel().getSelectedIndex()==-1 || exDate.getEditor().getText().isEmpty()){
                alert.setTitle("Error");
                alert.setContentText("Please fill name/id/category/date");
                alert.show();
            }
            else {
                int index=tableDepartment.getSelectionModel().getSelectedIndex();
                Item i=tableDepartment.getSelectionModel().getSelectedItem();
                i.setId(Integer.valueOf(txtId.getText()));
                i.setName(txtName.getText());
                i.setCategory(comboCat.getSelectionModel().getSelectedItem());
                i.setExDate(exDate.getValue());
                this.getItems().set(index,i);
            }

        txtId.clear();
        txtName.clear();
        comboCat.getSelectionModel().select(-1);
        exDate.getEditor().clear();
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    public void tableClicked(MouseEvent mouseEvent) {
        if (tableDepartment.getSelectionModel().getSelectedIndex()>-1){
            Item i=tableDepartment.getSelectionModel().getSelectedItem();
            txtName.setText(i.getName());
            txtId.setText(String.valueOf(i.getId()));
            comboCat.getSelectionModel().select(i.getCategory());
            exDate.getEditor().setText(String.valueOf(i.getExDate()));
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
        }
    }
}
