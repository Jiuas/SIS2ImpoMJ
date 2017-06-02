/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import static javax.management.Query.value;

public class ProveedoresController implements  Initializable {

    @FXML
    private Button bttnCancelar;

    @FXML
    private ComboBox<String> cBoxPaises;

    @FXML
    private Button bttnAgregar;

    @FXML
    private TextField txtFProveedor;
    
    public void agregar(ActionEvent e) throws SQLException{
        Alert mensaje;
        try{
            if(txtFProveedor.getText().equals("")){
                mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("El campo Nombre del Proveedor no puede estar vacio");
                mensaje.setTitle("Erro al ingresar datos");
                mensaje.setHeaderText("");
                mensaje.showAndWait();
        }else{
            System.out.println();
            ResultSet idPais= Main.conector.consultar("select id_pais from pais "
                    + "where pais.nombre_pais = '"+cBoxPaises.getValue()+"';");
            idPais.next();
            System.out.println("coso _"+idPais.getString(1));
            Main.conector.st.executeUpdate("INSERT INTO proveedor (nombre_prov, id_pais)"
                    + "VALUES ('"+txtFProveedor.getText()+"',"+idPais.getString(1)+");");
             mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setHeaderText("");
            mensaje.setTitle("Registro completado!");
            mensaje.setContentText("El registro se completo con exito!");
            mensaje.showAndWait();}
        }catch (SQLException i){
            i.printStackTrace();
            mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setHeaderText("Ocurrió un error durante el proceso de registro");
            mensaje.setTitle("Error al registrar");
            mensaje.setContentText("Por favor compruebe que todos los campos fueron llenados correctamente");
            mensaje.showAndWait();
        }
    }
        private ObservableList<String> llenarComboPais() { 
            ObservableList<String> items=FXCollections.observableArrayList();
            try {    
                ResultSet rs=null; 
                rs = Main.conector.consultar("SELECT pais.nombre_pais FROM pais");
                System.out.println("104");
                while (rs.next()) {
                    items.add(rs.getString(1));
                }
        
            } catch(Exception err){err.printStackTrace();} 
            return items;
        }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaPaises = llenarComboPais();
        cBoxPaises.setItems(listaPaises);
        cBoxPaises.setValue(listaPaises.get(0));
        System.out.println("Pais es:" +cBoxPaises.getValue());
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
