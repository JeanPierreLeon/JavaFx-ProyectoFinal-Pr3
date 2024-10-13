package co.edu.uniquindio.pr3.billeteravirtualapp.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginUsuarioViewController {

    @FXML
    private TextField txtResultadoLogin;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtContraseñaLogin;

    @FXML
    private TextField txtUsuarioLogin;

    @FXML
    void LoginAction(ActionEvent event) {
        txtResultadoLogin.setText("logeado correctamente");
    }

}
