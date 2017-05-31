/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class RegistroPaisesController implements  Initializable{

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private TextField txtFielPrecio;
    
    public void registrar (ActionEvent e){
        if(!txtFielPrecio.getText().equals("")
           && !txtFieldNombre.getText().equals("")){
            System.out.println("puto");
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        }else
        {
              System.out.println("no puto");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char letra= event.getCharacter().charAt(0);
                if(!Character.isLetter(letra)){
                    event.consume();
                }
            }
        });
        txtFielPrecio.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char letra= event.getCharacter().charAt(0);
                
                if(!Character.isDigit(letra) &&  letra!='.'){
                    event.consume();
                }
               
            }
        });
    }    
}