/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author nilu1
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label_text;
    @FXML
    private TextField login_username;
    @FXML
    private PasswordField login_pwd;
    private User user;
    
    @FXML
    private void register_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void login_clicked(ActionEvent event) throws IOException, Exception {
//////       String username=login_username.getText();
//////       String pwd=login_pwd.getText();
//////        login_username.setText("");
//////        login_pwd.setText("");
//////        ConnectDatabase connect=new ConnectDatabase();
//////        boolean res=connect.loginDb(username, pwd);
//////        if (res)
//////        {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            user=new User();
            user.setUsername("n");
            MainController controller=loader.getController();
            controller.init(user);

               
        //RegisterController controller=loader.getController();
       
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
//////        }
//////        else
//////        {
//////            Alert a;
//////            a = new Alert(AlertType.ERROR);
//////            a.setContentText("Username and password not matching");
//////            a.show();
//////        }
        //controller.init(user);
        //this.init(user);
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
