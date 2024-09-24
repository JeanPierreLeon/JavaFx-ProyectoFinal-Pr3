package co.edu.uniquindio.pr3.billeteravirtualapp.controller;

import co.edu.uniquindio.pr3.billeteravirtualapp.controller.service.IUsuarioControllerService;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService {
    ModelFactoryController modelFactoryController;

    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerUsuarios() {
        return modelFactoryController.obtenerUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {
        return modelFactoryController.eliminarUsuario(idUsuario);
    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(idUsuarioActual, usuarioDto);
    }
}
