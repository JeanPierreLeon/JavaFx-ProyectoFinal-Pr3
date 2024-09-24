package co.edu.uniquindio.pr3.billeteravirtualapp.viewController;

import co.edu.uniquindio.pr3.billeteravirtualapp.controller.UsuarioController;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.util.Optional;


public class UsuarioViewController {
    UsuarioController usuarioControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    private Button btnActualizarUsuario;

    @FXML
    private Button btnAgregarUsuario;

    @FXML
    private Button btnEliminarUsuario;

    @FXML
    private Button btnNuevoUsuario;

    @FXML
    private TableView<UsuarioDto> TableUsuarios;

    @FXML
    private TableColumn<UsuarioDto, String> colCorreoElectronico;

    @FXML
    private TableColumn<UsuarioDto, String> colCuentasAsociadas;

    @FXML
    private TableColumn<UsuarioDto, String> colDireccion;

    @FXML
    private TableColumn<UsuarioDto, String> colIDUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> colNombreCompleto;

    @FXML
    private TableColumn<UsuarioDto, String> colSaldoTotal;

    @FXML
    private TableColumn<UsuarioDto, String> colTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtCuentasAsociadas;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIDUsuario;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private TextField txtSaldoTotal;

    @FXML
    private TextField txtTelefono;


    @FXML
    void initialize() {
        usuarioControllerService = new UsuarioController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerUsuarios();
        TableUsuarios.getItems().clear();
        TableUsuarios.setItems(listaUsuariosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colIDUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().idUsuario())));
        colNombreCompleto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCompleto()));
        colCorreoElectronico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correoElectronico()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().telefono())));
        colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
        colSaldoTotal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().saldoTotal())));
        colCuentasAsociadas.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().cuentasAsociadas())));
    }

    private void obtenerUsuarios() {
        listaUsuariosDto.addAll(usuarioControllerService.obtenerUsuarios());
    }

    private void listenerSelection() {
        TableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }

    private void mostrarInformacionUsuario(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtIDUsuario.setText(String.valueOf(usuarioSeleccionado.idUsuario()));
            txtNombreCompleto.setText(usuarioSeleccionado.nombreCompleto());
            txtCorreo.setText(usuarioSeleccionado.correoElectronico());
            txtTelefono.setText(String.valueOf(usuarioSeleccionado.telefono()));
            txtDireccion.setText(usuarioSeleccionado.direccion());
            txtSaldoTotal.setText(String.valueOf(usuarioSeleccionado.saldoTotal()));
            txtCuentasAsociadas.setText(String.valueOf(usuarioSeleccionado.cuentasAsociadas()));

        }
    }

    @FXML
    void nuevoUsuarioAction(ActionEvent event) {
        txtIDUsuario.setText("Ingrese el id del usuario");
        txtNombreCompleto.setText("Ingrese el Nombre Completo del usuario");
        txtCorreo.setText("Ingrese el correo del usuario");
        txtTelefono.setText("Ingrese el telefono");
        txtDireccion.setText("Ingrese la direccion");
        txtSaldoTotal.setText("Ingrese el saldo total");
        txtCuentasAsociadas.setText("Ingrese las cuentas asociadas");
    }

    @FXML
    void agregarUsuarioAction(ActionEvent event) {
        crearUsuario();
    }


    @FXML
    void eliminarUsuarioAction(ActionEvent event) {
        eliminarUsuario();
    }

    @FXML
    void actualizarUsuarioAction(ActionEvent event) {
        actualizarUsuario();
    }

    private void actualizarUsuario() {
        boolean usuarioActualizado = false;
        //1. Capturar los datos
        String idUsuarioActual =usuarioSeleccionado.idUsuario();
        UsuarioDto usuarioDto = construirUsuarioDto();
        //2. verificar el empleado seleccionado
        if(usuarioSeleccionado != null){
            //3. Validar la información
            if(datosValidos(usuarioSeleccionado)){
                usuarioActualizado = usuarioControllerService.actualizarUsuario(idUsuarioActual,usuarioDto);
                if(usuarioActualizado){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    listaUsuariosDto.add(usuarioDto);
                    TableUsuarios.refresh();
                    mostrarMensaje("Notificación empleado", "Empleado actualizado", "El empleado se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposUsuario();
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no actualizado", "El empleado no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private void crearUsuario() {
        //1. Capturar los datos
        UsuarioDto usuarioDto = construirUsuarioDto();
        //2. Validar la información
        if(datosValidos(usuarioDto)){
            if(usuarioControllerService.agregarUsuario(usuarioDto)){
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
                txtIDUsuario.getText(),
                txtNombreCompleto.getText(),
                txtCorreo.getText(),
                Integer.parseInt(txtTelefono.getText()),
                txtDireccion.getText(),
                Double.parseDouble(txtSaldoTotal.getText()),
                txtCuentasAsociadas.getText()

        );
    }

    private void limpiarCamposUsuario() {
        txtIDUsuario.setText("");
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtSaldoTotal.setText("");
        txtCuentasAsociadas.setText("");

    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.nombreCompleto() == null || usuarioDto.nombreCompleto().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioDto.correoElectronico() == null || usuarioDto.correoElectronico() .equals(""))
            mensaje += "El apellido es invalido \n" ;
        if(usuarioDto.direccion() == null || usuarioDto.direccion().equals(""))
            mensaje += "El documento es invalido \n" ;
        if(usuarioDto.cuentasAsociadas() == null || usuarioDto.cuentasAsociadas().equals(""))
            mensaje += "La direccion es invalida \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private void eliminarUsuario() {
        boolean usuarioEliminado = false;
        if(usuarioSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al empleado?")){
                usuarioEliminado = usuarioControllerService.eliminarUsuario(String.valueOf(usuarioSeleccionado.idUsuario()));
                if(usuarioEliminado == true){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    usuarioSeleccionado = null;
                    TableUsuarios.getSelectionModel().clearSelection();
                    limpiarCamposUsuario();
                    mostrarMensaje("Notificación empleado", "Empleado eliminado", "El empleado se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no eliminado", "El empleado no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no seleccionado", "Seleccionado un empleado de la lista", Alert.AlertType.WARNING);
        }
    }



//    private UsuarioDto construirUsuarioDto() {
//        try {
//            int idUsuario = Integer.parseInt(txtIDUsuario.getText());
//            int telefono = Integer.parseInt(txtTelefono.getText());
//            double cuentasAsociadas = Double.parseDouble(txtCuentasAsociadas.getText());
//
//            return new UsuarioDto(
//                    Integer.parseInt(txtIDUsuario.getText()),
//                    txtNombreCompleto.getText(),
//                    txtCorreo.getText(),
//                    Integer.parseInt(txtTelefono.getText()),
//                    txtDireccion.getText(),
//                    Double.parseDouble(txtSaldoTotal.getText()),
//                    txtCuentasAsociadas.getText()
//            );
//        } catch (NumberFormatException e) {
//            // Aquí manejas el error, por ejemplo, mostrando una alerta al usuario
//            mostrarAlertaError("Entrada inválida", "Por favor, ingrese un número válido.");
//            return null; // O alguna otra acción adecuada en caso de error
//        }
//    }
//
//    private void mostrarAlertaError(String titulo, String mensaje) {
//        Alert alerta = new Alert(Alert.AlertType.ERROR);
//        alerta.setTitle(titulo);
//        alerta.setHeaderText(null);
//        alerta.setContentText(mensaje);
//        alerta.showAndWait();
//    }



}





