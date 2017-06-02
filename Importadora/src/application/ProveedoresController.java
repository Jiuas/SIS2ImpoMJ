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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ProveedoresController implements  Initializable {

    @FXML
    private Button bttnCancelar;

    @FXML
    private ComboBox<?> cBoxPaises;

    @FXML
    private Button bttnAgregar;

    @FXML
    private TextField txtFProveedor;
    
    public void agregar(ActionEvent e){
        if(!txtFProveedor.getText().equals("")){
            System.out.println();
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setHeaderText("");
            mensaje.setTitle("Registro completado!");
            mensaje.setContentText("El registro se completo con exito!");
            mensaje.showAndWait();
        }else{
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setHeaderText("Ocurrió un error durante el proceso de registro");
            mensaje.setTitle("Error al registrar");
            mensaje.setContentText("Por favor compruebe que todos los campos fueron llenados correctamente");
            mensaje.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFProveedor.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char letra= event.getCharacter().charAt(0);
                if(!Character.isLetter(letra)){
                    event.consume();
                }
            }
        });
        
    }   
}
