package co.edu.uniquindio.pr3.billeteravirtualapp.viewController;

import co.edu.uniquindio.pr3.billeteravirtualapp.controller.CuentaController;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.CuentaDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.Optional;

public class CuentaViewController {

    CuentaController cuentaControllerService;
    ObservableList<CuentaDto> listaCuentasDto = FXCollections.observableArrayList();
    CuentaDto cuentaSeleccionada;

    @FXML
    private Button btnActualizarCuenta;

    @FXML
    private Button btnAgregarCuenta;

    @FXML
    private Button btnEliminarCuenta;

    @FXML
    private TableView<CuentaDto> TableCuentas;

    @FXML
    private TableColumn<CuentaDto, String> colIDCuenta;

    @FXML
    private TableColumn<CuentaDto, String> colNombreBanco;

    @FXML
    private TableColumn<CuentaDto, String> colTipoCuenta;

    @FXML
    private TableColumn<CuentaDto, String> colNumeroCuenta;

    @FXML
    private ComboBox<String> CBNombreBanco;

    @FXML
    private ComboBox<String> CBTipoCuenta;

    @FXML
    private TextField txtNumeroCuenta;

    @FXML
    void initialize() {
        cuentaControllerService = new CuentaController();
        initView();
        initCombo();
    }

    private void initCombo() {
        initCbNombreBanco();
        initCbTipoCuenta();
    }

    private void initCbTipoCuenta() {
        ObservableList<String> cbTipoCuenta = FXCollections.observableArrayList();
//        List<CuentaDto> listTipoCuenta = cuentaControllerService.obtenerTipoCuenta();
        for (CuentaDto cuentaDto:listaCuentasDto){
            cbTipoCuenta.add(cuentaDto.tipoCuenta());
        }
        CBTipoCuenta.setItems(cbTipoCuenta);
//        CBNombreBanco.getSelectionModel().select("--Seleccione--");

    }

    private void initCbNombreBanco() {
        ObservableList<String> cbNombreBanco = FXCollections.observableArrayList();
//        List<CuentaDto> listNombreBanco = cuentaControllerService.obtenerNombreBanco();
        for (CuentaDto cuentaDto:listaCuentasDto){
            cbNombreBanco.add(cuentaDto.nombreBanco());
        }
        CBNombreBanco.setItems(cbNombreBanco);
//        CBNombreBanco.getSelectionModel().select("--Seleccione--");
    }

    private void initView() {
        initDataBinding();
        obtenerCuentas();
        TableCuentas.getItems().clear();
        TableCuentas.setItems(listaCuentasDto);
        listenerSelection();
    }

    private void initDataBinding() {
        colIDCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().idCuenta())));
        colNombreBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreBanco()));
        colTipoCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoCuenta()));
        colNumeroCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().numCuenta())));
    }

    private void obtenerCuentas() {
        listaCuentasDto.addAll(cuentaControllerService.obtenerCuentas());
    }

    private void listenerSelection() {
        TableCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cuentaSeleccionada = newSelection;
            mostrarInformacionCuenta(cuentaSeleccionada);
        });
    }

    private void mostrarInformacionCuenta(CuentaDto cuentaSeleccionada) {
        if(cuentaSeleccionada != null){

            CBNombreBanco.setValue(cuentaSeleccionada.nombreBanco());
            CBTipoCuenta.setValue(cuentaSeleccionada.tipoCuenta());
            txtNumeroCuenta.setText(String.valueOf(cuentaSeleccionada.numCuenta()));
        }
    }

    @FXML
    void agregarCuentaAction(ActionEvent event) {
        crearCuenta();
    }

    @FXML
    void actualizarCuentaAction(ActionEvent event) {
        actualizarCuenta();
    }

    @FXML
    void eliminarCuentaAction(ActionEvent event) {
        eliminarCuenta();
    }

    private void actualizarCuenta() {
        boolean cuentaActualizada = false;
        // 1. Capturar los datos
        String idCuentaActual = String.valueOf(cuentaSeleccionada.idCuenta());
        CuentaDto cuentaDto = construirCuentaDto();

        // 2. Validar la cuenta seleccionada
        if(cuentaSeleccionada != null){
            // 3. Validar la información
            if(datosValidos(cuentaDto)){
                cuentaActualizada = cuentaControllerService.actualizarCuenta(idCuentaActual, cuentaDto);
                if(cuentaActualizada){
                    listaCuentasDto.remove(cuentaSeleccionada);
                    listaCuentasDto.add(cuentaDto);
                    TableCuentas.refresh();
                    mostrarMensaje("Notificación Cuenta", "Cuenta actualizada", "La cuenta se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposCuenta();
                } else {
                    mostrarMensaje("Notificación Cuenta", "Cuenta no actualizada", "La cuenta no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            } else {
                mostrarMensaje("Notificación Cuenta", "Cuenta no actualizada", "Los datos ingresados son inválidos", Alert.AlertType.ERROR);
            }
        }
    }

    private void crearCuenta() {
        // 1. Capturar los datos
        CuentaDto cuentaDto = construirCuentaDto();

        // 2. Validar la información
        if(datosValidos(cuentaDto)){
            if(cuentaControllerService.agregarCuenta(cuentaDto)){
                listaCuentasDto.add(cuentaDto);
                mostrarMensaje("Notificación Cuenta", "Cuenta creada", "La cuenta se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposCuenta();
                registrarAcciones("Cuenta Agregada",1,"Agregar Cuenta");
            } else {
                mostrarMensaje("Notificación Cuenta", "Cuenta no creada", "La cuenta no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación Cuenta", "Cuenta no creada", "Los datos ingresados son inválidos", Alert.AlertType.ERROR);
        }
    }

    private CuentaDto construirCuentaDto() {
        return new CuentaDto(0,
                CBNombreBanco.getValue(),
                txtNumeroCuenta.getText(),
                CBTipoCuenta.getValue()


        );
    }

    private void limpiarCamposCuenta() {
        CBNombreBanco.setValue(null);
        CBTipoCuenta.setValue(null);
        txtNumeroCuenta.setText("");
    }

    private boolean datosValidos(CuentaDto cuentaDto) {
        String mensaje = "";
//        if(cuentaDto.idCuenta() == 0 || cuentaDto.idCuenta().equals(""))
//            mensaje += "El nombre del banco es inválido \n";
//        if(cuentaDto.numCuenta() == null || cuentaDto.numCuenta().equals(""))
//            mensaje += "El tipo de cuenta es inválido \n";
        if(cuentaDto.nombreBanco() == null || cuentaDto.nombreBanco().equals(""))
            mensaje += "El número de cuenta es inválido \n";

        if(mensaje.equals("")){
            return true;
        } else {
            mostrarMensaje("Notificación Cuenta", "Datos inválidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        cuentaControllerService.registrarAcciones(mensaje,nivel, accion);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }

    private void eliminarCuenta() {
        boolean cuentaEliminada = false;
        if(cuentaSeleccionada != null){
            if(mostrarMensajeConfirmacion("¿Estás seguro de eliminar la cuenta?")){
                cuentaEliminada = cuentaControllerService.eliminarCuenta(String.valueOf(cuentaSeleccionada.idCuenta()));
                if(cuentaEliminada){
                    listaCuentasDto.remove(cuentaSeleccionada);
                    cuentaSeleccionada = null;
                    TableCuentas.getSelectionModel().clearSelection();
                    limpiarCamposCuenta();
                    mostrarMensaje("Notificación Cuenta", "Cuenta eliminada", "La cuenta se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Notificación Cuenta", "Cuenta no eliminada", "La cuenta no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Notificación Cuenta", "Cuenta no seleccionada", "Selecciona una cuenta de la lista", Alert.AlertType.WARNING);
        }
    }
}

