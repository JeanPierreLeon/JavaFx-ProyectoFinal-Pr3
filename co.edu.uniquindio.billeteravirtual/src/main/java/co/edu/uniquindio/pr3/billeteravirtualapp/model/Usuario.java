package co.edu.uniquindio.pr3.billeteravirtualapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idUsuario;
    private String contraseña;
    private double saldoTotal;
    private String cuentasAsociadas;

    public Usuario() {

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public String getCuentasAsociadas() {
        return cuentasAsociadas;
    }

    public void setCuentasAsociadas(String cuentasAsociadas) {
        this.cuentasAsociadas = cuentasAsociadas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", saldoTotal=" + saldoTotal +
                ", cuentasAsociadas='" + cuentasAsociadas + '\'' +
                '}';
    }
}
