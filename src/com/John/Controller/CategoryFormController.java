package com.John.Controller;
/**
 * author:Johnson Hartanto
 * NRP:1772017
 */
import com.John.Entity.Category;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryFormController implements Initializable {

    public TextField txtIdCat;
    public TextField txtNameCat;
    public TableView tableDepartmentCat;
    public TableColumn<Category,String> col01;
    public TableColumn<Category,String> col02;
    private MainFormController mainFormController;

    public void setMainFormController(MainFormController mainFormController) {
        this.mainFormController = mainFormController;
        tableDepartmentCat.setItems(mainFormController.getCategories());
    }

    public void saveCat(ActionEvent actionEvent) {
        boolean found=false;
        Alert alert=new Alert(Alert.AlertType.ERROR);
        for (Category i:mainFormController.getCategories()){
            if (i.getName().equals(txtNameCat.getText())){
                found=true;
            }
        }
        if (found){
            alert.setContentText("Duplicate category name");
            alert.setTitle("Error");
            alert.show();
        }
        else{
            if (txtNameCat.getText().isEmpty() || txtIdCat.getText().isEmpty()){
                alert.setTitle("Error");
                alert.setContentText("Please fill category name");
                alert.show();
            }
            else{
                Category c=new Category();
                c.setId(Integer.valueOf(txtIdCat.getText()));
                c.setName(txtNameCat.getText());
                mainFormController.getCategories().add(c);
            }
        }
        txtIdCat.clear();
        txtNameCat.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col01.setCellValueFactory(data->{
            Category c=data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getId()));
        });
        col02.setCellValueFactory(data->{
            Category c=data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getName()));
        });
    }
}
