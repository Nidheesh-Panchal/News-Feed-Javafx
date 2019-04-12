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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author nilu1
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label_text;
    
    private User user;
    
    @FXML
    private void login_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        user=new User();
        user.setUsername("Nidheesh");
        RegisterController controller=loader.getController();
        controller.init(user);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
