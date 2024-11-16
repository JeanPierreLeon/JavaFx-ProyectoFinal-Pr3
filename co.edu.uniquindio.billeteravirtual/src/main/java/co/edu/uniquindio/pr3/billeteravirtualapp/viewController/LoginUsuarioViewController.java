package co.edu.uniquindio.pr3.billeteravirtualapp.viewController;

import co.edu.uniquindio.pr3.billeteravirtualapp.BilleteraVirtualApplication;
import co.edu.uniquindio.pr3.billeteravirtualapp.controller.Login_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUsuarioViewController {
    Login_Controller loginController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    void onIniciar(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    void onRegistrarse(ActionEvent event) {
        registrarUsuario();
    }

    @FXML
    void initialize() {
        loginController = new Login_Controller();
    }


    private void iniciarSesion() {
        String user = txtUser.getText().trim();
        String password = txtPassword.getText().trim();

        if (verificarDatos()) {
            if (loginController.iniciarSesion(user, password)) {
                NuevaVista();
                mostrarMensaje("Inicio De Sesion", "Sesion Iniciada", "Sesion iniciada correctamente.", Alert.AlertType.INFORMATION);

            } else {
                mostrarMensaje("Error", "Error De Inicio De sesion", "Usuario o contraseña invalida.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos Nulos", "Rellene los campos solicitados", Alert.AlertType.ERROR);
        }
    }

    private void registrarUsuario() {
        String user = txtUser.getText().trim();
        String password = txtPassword.getText().trim();

        if (verificarDatos()) {
            if(loginController.registrarUsuario(user,password)){
                NuevaVista();
                mostrarMensaje("Registro","Registro Exitoso","Se registro correctamente el usuario " + user, Alert.AlertType.INFORMATION);
            }else {
                mostrarMensaje("Error","Error De Registro","El usuario ya existe.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos Nulos", "Rellene los campos solicitados.", Alert.AlertType.ERROR);
        }
    }

    private void NuevaVista() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BilleteraVirtualApplication.class.getResource("BilleteraVirtual.fxml"));
            Parent root = fxmlLoader.load();
            Stage nuevaVentana = new Stage();
            Scene scene = new Scene(root);
            nuevaVentana.setTitle("Billetera");
            nuevaVentana.setScene(scene);
            nuevaVentana.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verificarDatos() {
        if(txtUser.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            return false;
        }else {
            return true;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

}
