/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;

/**
 *
 * @author nilu1
 */
public class RegisterController implements Initializable {
    
    @FXML
    private Label label_txt;
    
    private User user;
    @FXML
    private ComboBox register_country;
    @FXML
    private void register_clicked(ActionEvent event) throws Exception {
        String keyword="hello";
        News news=new News();
        news.search(keyword);
       // SingleSelectionModel sw=new SingleSelectionModel();
       
        
    }
    
    public void init(User getuser)
    {
        user=new User();
        user=getuser;
        System.out.println(user.getUsername());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String country[] = { "in","us", "uk"}; 
       register_country.setItems(FXCollections.observableArrayList(country)); 
    }    
    
}
