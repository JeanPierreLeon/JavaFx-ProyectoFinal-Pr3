package co.edu.uniquindio.pr3.billeteravirtualapp.controller.service;

import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.List;

public interface ICuentaControllerService {
    List<CuentaDto> obtenerCuentas();

    boolean agregarCuenta(CuentaDto cuentaDto);

    boolean eliminarCuenta(String idCuenta);

    boolean actualizarCuenta(String idCuentaActual, CuentaDto cuentaDto);

    void registrarAcciones(String mensaje, int nivel, String accion);
}
