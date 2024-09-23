package co.edu.uniquindio.pr3.billeteravirtualapp.model;

import java.util.ArrayList;

public class Usuario extends Persona {

    private int idUsuario;
    private double saldoTotal;
    ArrayList<Cuenta> listaCuentasAsociadas = new ArrayList<>();



    public Usuario() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public ArrayList<Cuenta> getListaCuentasAsociadas() {
        return listaCuentasAsociadas;
    }

    public void setListaCuentasAsociadas(ArrayList<Cuenta> listaCuentasAsociadas) {
        this.listaCuentasAsociadas = listaCuentasAsociadas;
    }
}
