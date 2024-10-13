package co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto;

public record UsuarioDto(

        int idUsuario,
        String nombreCompleto,
        String correoElectronico,
        int telefono,
        String direccion,
        double saldoTotal,
        String cuentasAsociadas) {

}
