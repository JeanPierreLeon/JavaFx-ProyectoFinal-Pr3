package co.edu.uniquindio.pr3.billeteravirtualapp.model;

import java.util.ArrayList;

public class Usuario extends Persona {

    private String idUsuario;
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
}
