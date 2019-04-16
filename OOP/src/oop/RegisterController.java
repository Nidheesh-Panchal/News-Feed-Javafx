/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Stage;

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
    private TextField register_pwd;
    @FXML
    private TextField register_username;
    @FXML
    private TextField register_repwd;
    @FXML
    private ToggleButton register_business;
    @FXML
    private ToggleButton register_entertainment;
    @FXML
    private ToggleButton register_general;
    @FXML
    private ToggleButton register_health;
    @FXML
    private ToggleButton register_science;
    @FXML
    private ToggleButton register_sports;
    @FXML
    private ToggleButton register_technology;
    
    @FXML
    private void register_clicked(ActionEvent event) throws Exception {
        String keyword="hello";
//        News news=new News();
//        news.search(keyword);
        String username=register_username.getText();
        String pass=register_pwd.getText();
        String repass=register_repwd.getText();
        String country=(String)register_country.getValue();
        
        if(username.equals(""))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter Username");
            a.show();
            return;
        }
        if(pass.equals(""))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Password field is empty");
            a.show();
            return;
        }
        if(repass.equals(""))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Retype password field is empty");
            a.show();
            return;
        }
        if(!repass.equals(pass))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Passwords do not match. Try again!");
            a.show();
            return;
        }
        if(country==null)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a country");
            a.show();
            return;
        }
        Map<String,Integer> m=new HashMap<>();
        m.put("business",0);
        m.put("entertainment",0);
        m.put("general",0);
        m.put("health",0);
        m.put("science",0);
        m.put("sports",0);
        m.put("technology",0);
        if(register_business.isSelected())
            m.put("business",1);
        if(register_entertainment.isSelected())
            m.put("entertainment",1);
        if(register_general.isSelected())
            m.put("general",1);
        if(register_health.isSelected())
            m.put("health",1);
        if(register_science.isSelected())
            m.put("science",1);
        if(register_sports.isSelected())
            m.put("sports",1);
        if(register_technology.isSelected())
            m.put("technology",1);
        
        ConnectDatabase con=new ConnectDatabase();
        con.registerDb(username, pass, country, m);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Registered Successfully!");
        a.show();
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
