/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author nilu1
 */
public class WebController implements Initializable {

    @FXML
    AnchorPane web_pain;
    @FXML
    VBox vbox;
    
    public void init(String url) throws Exception {
        WebView webview = new WebView();
        webview.getEngine().load(url);
        vbox.getChildren().add(webview);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
