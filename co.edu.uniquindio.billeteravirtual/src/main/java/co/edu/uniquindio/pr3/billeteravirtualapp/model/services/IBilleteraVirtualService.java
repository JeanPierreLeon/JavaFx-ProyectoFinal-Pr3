package co.edu.uniquindio.pr3.billeteravirtualapp.model.services;

import co.edu.uniquindio.pr3.billeteravirtualapp.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;

import java.util.ArrayList;

public interface IBilleteraVirtualService {
    public Usuario crearUsuario(String idUsuario, String nombreCompleto, String correo, String direccion, Double saldoTotal, String cuentasAsociadas ) throws UsuarioException;
    public Boolean eliminarUsuario(String idUsuario)throws UsuarioException;
    boolean actualizarUsuario(String idUsuarioActual, Usuario usuario) throws UsuarioException;
    public boolean  verificarUsuarioExistente(String idUsuario) throws UsuarioException;
    public Usuario obtenerUsuario(String idUsuario);
    public ArrayList<Usuario> obtenerUsuarios();
}
