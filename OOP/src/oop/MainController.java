/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nilu1
 */
public class MainController implements Initializable {

    User user;
    @FXML
    Button search_button;
    @FXML
    TextField search_text;
    @FXML
    Button logout_button;
    @FXML
    Button settings_button;
    
    
    public void init(User getuser)
    {
        user=new User();
        user=getuser;
        System.out.println(user.getUsername());        
    }
    
    @FXML
    private void search_clicked(ActionEvent event) throws Exception {
        System.out.println("clicked");
        System.out.println(user.getUsername());
        String keyword=search_text.getText();
        keyword=keyword.replace(" ", "%20");
        if(keyword.equals(""))
            return;
        News.everything(keyword);
    }
    
    @FXML
    private void logout_clicked(ActionEvent event) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void settings_clicked(ActionEvent event) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("editsettings.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        EditsettingsController controller=loader.getController();
        controller.init(user);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Image image=new Image("file:C:\\Users\\nilu1\\Desktop\\Code\\OOP\\images\\magnifying_glass.jpg");
//        search_button.setGraphic(new ImageView(image));
//        search_button.setMaxSize(15, 15);
        search_button.setText("Search!");
 
        //FileInputStream file=new FileInputStream("C:\\Users\\nilu1\\Desktop\\Code\\OOP\\magnifying_glass.jpg");
        
//        Image image = new Image(getClass().getResourceAsStream("magnifying_glass.jpg"));
//        Button button5 = new Button();
//        button5.setGraphic(new ImageView(image));
    }    
    
}
