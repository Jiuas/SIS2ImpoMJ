package application;


import java.net.URL;
import java.sql.SQLException;
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
    
    public void registrar (ActionEvent e) throws SQLException{
        
        Alert mensaje;
        try{
            if( txtFieldNombre.getText().equals("")){
                mensaje =  new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Error al ingresar datos");
                mensaje.setHeaderText("");
                mensaje.setContentText("El campo Nombre no puede estar vacio");
                mensaje.showAndWait();
            } else{
            String precio = txtFielPrecio.getText();
            String nombre = txtFieldNombre.getText();
            Main.conector.st.executeUpdate("INSERT  INTO pais (nombre_pais, costo_pais)"
                    + " VALUES ("+ "'"+nombre+"',"+ precio+") ");
            System.out.println();
            mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setHeaderText("");
            mensaje.setTitle("Registro completado!");
            mensaje.setContentText("El registro se completo con exito!");
            mensaje.showAndWait();
            System.out.println("prueba");

            }
        }catch(SQLException i)

        {
            i.printStackTrace();
            mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setHeaderText("UPS! a habido un error durante el proceso de registro");
            mensaje.setTitle("Error al registrar");
            mensaje.setContentText("Por favor revise que todos los campos fueron llenados correctamente");
            mensaje.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char letra = event.getCharacter().charAt(0);
                if (!Character.isLetter(letra)) {
                    event.consume();
                }
            }
        });
        txtFielPrecio.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char letra = event.getCharacter().charAt(0);

                if (!Character.isDigit(letra) && letra != '.') {
                    event.consume();
                }

            }
        });
    }
}
