package co.edu.uniquindio.pr3.billeteravirtualapp.utils;

import co.edu.uniquindio.pr3.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;

public class UsuarioUtils {


    public static BilleteraVirtual inicializarDatos() {
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(String.valueOf(888));
        usuario.setNombreCompleto("Santiago xdxdxdxd");
        usuario.setCorreoElectronico("Santiago@gmail.com");
        usuario.setTelefono(314444444);
        usuario.setDireccion("Cra 90 Calle 23");
        usuario.setSaldoTotal(10000);
        usuario.setCuentasAsociadas("Nequi, DaviPlata, Paypal");
        billeteraVirtual.getListaUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setIdUsuario(String.valueOf(222));
        usuario.setNombreCompleto("Santiago Lol");
        usuario.setCorreoElectronico("Muñoz@gmail.com");
        usuario.setTelefono(313333333);
        usuario.setDireccion("Cra 20 Calle 30");
        usuario.setSaldoTotal(35000);
        usuario.setCuentasAsociadas("Nequi, DaviPlata, Paypal");
        billeteraVirtual.getListaUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setIdUsuario(String.valueOf(333));
        usuario.setNombreCompleto("Pablo Escobar");
        usuario.setCorreoElectronico("Patron@gmail.com");
        usuario.setTelefono(316666666);
        usuario.setDireccion("Cra 66 Calle 66");
        usuario.setSaldoTotal(99999);
        usuario.setCuentasAsociadas("Nequi, DaviPlata, Paypal");
        billeteraVirtual.getListaUsuarios().add(usuario);
        System.out.println("Información de la billetera creada");
        return billeteraVirtual;

}
}
