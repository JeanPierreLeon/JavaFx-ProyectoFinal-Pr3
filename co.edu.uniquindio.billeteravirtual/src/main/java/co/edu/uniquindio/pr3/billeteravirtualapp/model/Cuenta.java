package co.edu.uniquindio.pr3.billeteravirtualapp.model;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contador;
    private String idCuenta;
    private String nombreBanco;
    private String numCuenta;
    private String tipoCuenta;

    public Cuenta() {
        this.idCuenta = String.valueOf(contador++);
    }

    public Cuenta(String idCuenta, String nombreBanco, String numCuenta, String tipoCuenta) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numCuenta = numCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = String.valueOf(idCuenta);
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = String.valueOf(numCuenta);
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
