package co.edu.uniquindio.pr3.billeteravirtualapp.controller;

public class Login_Controller {
    ModelFactoryController modelFactory;

    public Login_Controller() {
        modelFactory = ModelFactoryController.getInstance();
    }

    public boolean iniciarSesion(String user, String password) {
        return modelFactory.iniciarSesion(user,password);
    }

    public boolean registrarUsuario(String user, String password) {
        return modelFactory.registrarUsuario(user,password);
    }
}
