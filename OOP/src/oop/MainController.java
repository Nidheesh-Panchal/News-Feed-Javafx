/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
 * FXML Controller class
 *
 * @author nilu1
 */
public class MainController implements Initializable {

    @FXML
    Button search_button;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        File f = new File("c:\\Users\\nilu1\\Desktop\\Code\\OOP\\magnifying_glass.jpg");
//        Image image = new Image(f.toURI().toString());
//        search_button.setGraphic(new ImageView(image));
        News news=new News();
        try {
            news.search("bitcoin");
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
