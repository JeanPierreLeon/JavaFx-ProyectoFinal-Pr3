package co.edu.uniquindio.pr3.billeteravirtualapp.model;

import co.edu.uniquindio.pr3.billeteravirtualapp.exceptions.CuentaException;
import co.edu.uniquindio.pr3.billeteravirtualapp.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.services.IBilleteraVirtualService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BilleteraVirtual implements IBilleteraVirtualService, Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    ArrayList<Cuenta> listaCuentas = new ArrayList<>();
    ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
    ArrayList<Categoria> listaCategorias = new ArrayList<>();
    ArrayList<Presupuesto> listaPresupuestos = new ArrayList<>();

    public BilleteraVirtual() {
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public ArrayList<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(ArrayList<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioException {
        getListaUsuarios().add(nuevoUsuario);
    }

    public void agregarCuenta(Cuenta nuevaCuenta) throws CuentaException {
        getListaCuentas().add(nuevaCuenta);
    }

    @Override
    public Usuario crearUsuario(String idUsuario, String nombreCompleto, String correo, String direccion, Double saldoTotal, String cuentasAsociadas) throws UsuarioException {
        Usuario nuevoUsuario = null;
        boolean usuarioExiste = verificarUsuarioExistente(idUsuario);
        if(usuarioExiste){
            throw new UsuarioException("El usuario con id de usuario: "+ idUsuario +" ya existe");
        }else{
            nuevoUsuario = new Usuario();
            nuevoUsuario.setIdUsuario((idUsuario));
            nuevoUsuario.setNombreCompleto(nombreCompleto);
            nuevoUsuario.setCorreoElectronico(correo);
            nuevoUsuario.setDireccion(direccion);
            nuevoUsuario.setSaldoTotal(saldoTotal);
            nuevoUsuario.setCuentasAsociadas(cuentasAsociadas);
            getListaUsuarios().add(nuevoUsuario);
        }
        return nuevoUsuario;
    }

    @Override
    public Boolean eliminarUsuario(String idUsuario) throws UsuarioException {
        Usuario usuario = null;
        boolean flagExiste = false;
        usuario = obtenerUsuario(idUsuario);
        if(usuario == null)
            throw new UsuarioException("El empleado a eliminar no existe");
        else{
            getListaUsuarios().remove(usuario);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, Usuario usuario) throws UsuarioException {
        Usuario usuarioActual = obtenerUsuario(idUsuarioActual);
        if(usuarioActual == null)
            throw new UsuarioException("El usuario a actualizar no existe");
        else{
            usuarioActual.setIdUsuario(usuario.getIdUsuario());
            usuarioActual.setNombreCompleto(usuario.getNombreCompleto());
            usuarioActual.setCorreoElectronico(usuario.getCorreoElectronico());
            usuarioActual.setTelefono(usuario.getTelefono());
            usuarioActual.setDireccion(usuario.getDireccion());
            usuarioActual.setSaldoTotal(usuario.getSaldoTotal());
            usuarioActual.setCuentasAsociadas(usuario.getCuentasAsociadas());
            return true;
        }
    }

    @Override
    public boolean verificarUsuarioExistente(String idUsuario) throws UsuarioException {
        if(usuarioExiste(idUsuario)){
            throw new UsuarioException("El usuario con id de usuario: "+idUsuario+" ya existe");
        }else{
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getIdUsuario().equalsIgnoreCase(idUsuario)){
                usuarioEncontrado = usuario;
                break;
            }
        }
        return usuarioEncontrado;
    }

    @Override
    public ArrayList<Usuario> obtenerUsuarios() {
        // TODO Auto-generated method stub
        return getListaUsuarios();
    }

    @Override
    public Cuenta crearCuenta(String idCuenta, String nombreBanco, int numCuenta, String tipoCuenta) throws UsuarioException {
        Cuenta nuevoCuenta = null;
        boolean cuentaExiste = verificarUsuarioExistente(idCuenta);
        if(cuentaExiste){
            throw new CuentaException("La cuenta con id : "+ idCuenta +" ya existe");
        }else{
            nuevoCuenta = new Cuenta();
            nuevoCuenta.setIdCuenta((idCuenta));
            nuevoCuenta.setNombreBanco(nombreBanco);
            nuevoCuenta.setTipoCuenta(tipoCuenta);
            nuevoCuenta.setNumCuenta(String.valueOf(numCuenta));
            getListaCuentas().add(nuevoCuenta);
        }
        return nuevoCuenta;
    }

    @Override
    public Boolean eliminarCuenta(String idCuenta) throws UsuarioException {
        Cuenta cuenta = null;
        boolean flagExiste = false;
        cuenta = obtenerCuenta(idCuenta);
        if(cuenta == null)
            throw new CuentaException("La cuenta a eliminar no existe");
        else{
            getListaCuentas().remove(cuenta);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarCuenta(String idCuentaActual, Cuenta cuenta) throws UsuarioException {
        Cuenta cuentaActual = obtenerCuenta(idCuentaActual);
        if(cuentaActual == null)
            throw new UsuarioException("El usuario a actualizar no existe");
        else{
            cuentaActual.setIdCuenta(cuenta.getIdCuenta());
            cuentaActual.setNombreBanco(cuenta.getNombreBanco());
            cuentaActual.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaActual.setNumCuenta(cuenta.getNumCuenta());
            return true;
        }
    }

    @Override
    public boolean verificarCuentaExistente(String idCuenta) throws UsuarioException {
        if(cuentaExiste(idCuenta)){
            throw new CuentaException("El usuario con id de usuario: "+idCuenta+" ya existe");
        }else{
            return false;
        }
    }

    private boolean cuentaExiste(String idCuenta) {
        boolean cuentaEncontrado = false;
        for (Cuenta cuenta : getListaCuentas()) {
            if(cuenta.getIdCuenta().equalsIgnoreCase((idCuenta))){
                cuentaEncontrado = true;
                break;
            }
        }
        return cuentaEncontrado;
    }

    @Override
    public Cuenta obtenerCuenta(String idCuenta) {
        Cuenta cuentaEncontrado = null;
        for (Cuenta cuenta : getListaCuentas()) {
            if(cuenta.getIdCuenta().equalsIgnoreCase(idCuenta)){
                cuentaEncontrado = cuenta;
                break;
            }
        }
        return cuentaEncontrado;
    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        // TODO Auto-generated method stub
        return getListaCuentas();
    }

    public boolean usuarioExiste(String idUsuario) {
        boolean usuarioEncontrado = false;
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getIdUsuario().equalsIgnoreCase((idUsuario))){
                usuarioEncontrado = true;
                break;
            }
        }
        return usuarioEncontrado;
    }



}
