package co.edu.uniquindio.pr3.billeteravirtualapp.controller;

import co.edu.uniquindio.pr3.billeteravirtualapp.controller.service.ICuentaControllerService;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.List;

public class CuentaController implements ICuentaControllerService {
    ModelFactoryController modelFactoryController;

    public CuentaController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<CuentaDto> obtenerCuentas() {
        return modelFactoryController.obtenerCuentas();
    }

    @Override
    public boolean agregarCuenta(CuentaDto cuentaDto) {
        return modelFactoryController.agregarCuenta(cuentaDto);
    }

    @Override
    public boolean eliminarCuenta(String idCuenta) {
        return modelFactoryController.eliminarCuenta(idCuenta);
    }

    @Override
    public boolean actualizarCuenta(String idCuentaActual, CuentaDto cuentaDto) {
        return modelFactoryController.actualizarCuenta(idCuentaActual, cuentaDto);
    }

    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }


}
