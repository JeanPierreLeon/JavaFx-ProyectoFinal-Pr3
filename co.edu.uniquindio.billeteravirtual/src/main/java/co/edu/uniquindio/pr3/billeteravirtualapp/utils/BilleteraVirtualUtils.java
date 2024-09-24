package co.edu.uniquindio.pr3.billeteravirtualapp.utils;

import co.edu.uniquindio.pr3.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;

public class BilleteraVirtualUtils {


    public static BilleteraVirtual inicializarDatos() {
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario("001");
        usuario.setNombreCompleto("Santiago Ramirez");
        usuario.setCorreoElectronico("Santiago@gmail.com");
        usuario.setTelefono(314444444);
        usuario.setDireccion("Cra 90 Calle 23");
        usuario.setSaldoTotal(100.000);
        usuario.setCuentasAsociadas("Nequi, DaviPlata, Paypal");
        billeteraVirtual.getListaUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setIdUsuario("002");
        usuario.setNombreCompleto("Santiago Muñoz");
        usuario.setCorreoElectronico("Muñoz@gmail.com");
        usuario.setTelefono(31333333);
        usuario.setDireccion("Cra 20 Calle 30");
        usuario.setSaldoTotal(35.000);
        usuario.setCuentasAsociadas("Nequi, DaviPlata, Paypal");
        billeteraVirtual.getListaUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setIdUsuario("003");
        usuario.setNombreCompleto("Pablo Escobar");
        usuario.setCorreoElectronico("Patron@gmail.com");
        usuario.setTelefono(316666666);
        usuario.setDireccion("Cra 66 Calle 66");
        usuario.setSaldoTotal(99999999);
        usuario.setCuentasAsociadas("Nequi, DaviPlata, Paypal");
        billeteraVirtual.getListaUsuarios().add(usuario);
        System.out.println("Información de la billetera creada");
        return billeteraVirtual;

}
}
