package co.edu.uniquindio.pr3.billeteravirtualapp.controller.service;

import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioControllerService {
    List<UsuarioDto> obtenerUsuarios();

    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean eliminarUsuario(String idUsuario);

    boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto);

    void registrarAcciones(String mensaje, int nivel, String accion);
}
