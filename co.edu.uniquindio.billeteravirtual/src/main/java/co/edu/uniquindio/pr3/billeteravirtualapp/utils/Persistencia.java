package co.edu.uniquindio.pr3.billeteravirtualapp.utils;


import co.edu.uniquindio.pr3.billeteravirtualapp.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Persistencia {


    public static final String RUTA_USERS_PROPERTIES = "src/main/resources/propiedades/config.properties";
    public static final String RUTA_ARCHIVO_USUARIOS = "C:\\Users\\jeanp\\Documents\\Programacion 3\\JavaFx-ProyectoFinal-Pr3\\co.edu.uniquindio.billeteravirtual\\src\\main\\resources\\persistencia\\archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "C:\\Users\\jeanp\\Documents\\Programacion 3\\JavaFx-ProyectoFinal-Pr3\\co.edu.uniquindio.billeteravirtual\\src\\main\\resources\\persistencia\\log\\BilleteraLog.txt";
    public static final String RUTA_ARCHIVO_MODELO_BILLETERA_BINARIO = "C:\\Users\\jeanp\\Documents\\Programacion 3\\JavaFx-ProyectoFinal-Pr3\\co.edu.uniquindio.billeteravirtual\\src\\main\\resources\\persistencia\\model.dat";
    public static final String RUTA_ARCHIVO_MODELO_BILLETERA_XML = "C:\\Users\\jeanp\\Documents\\Programacion 3\\JavaFx-ProyectoFinal-Pr3\\co.edu.uniquindio.billeteravirtual\\src\\main\\resources\\persistencia\\model.xml";


    public static void cargarDatosArchivos(BilleteraVirtual billeteraVirtual) throws FileNotFoundException, IOException {
        //cargar archivo de usuarios
        ArrayList<Usuario> usuariosCargados = cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
        if(usuariosCargados.size() > 0)
            billeteraVirtual.getListaUsuarios().addAll(usuariosCargados);

        //cargar archivos empleados

        //cargar archivo transcciones

        //cargar archivo empleados

        //cargar archivo prestamo

    }

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param
     * @throws IOException
     */
    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Usuario usuario:listaUsuarios)
        {
            contenido+= usuario.getIdUsuario()+","+usuario.getNombreCompleto()+","+usuario.getCorreoElectronico()+","+usuario.getTelefono()
                    +","+usuario.getDireccion()+","+usuario.getSaldoTotal()+","+usuario.getCuentasAsociadas()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, true);
    }


//	----------------------LOADS------------------------

    /**
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Usuario> cargarUsuarios(String RUTA_ARCHIVO_USUARIOS) throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(linea.split(",")[0]);
            usuario.setNombreCompleto(linea.split(",")[1]);
            usuario.setCorreoElectronico(linea.split(",")[2]);
            usuario.setTelefono(Integer.parseInt(linea.split(",")[3]));
            usuario.setDireccion(linea.split(",")[4]);
            usuario.setSaldoTotal(Double.parseDouble(linea.split(",")[5]));
            usuario.setCuentasAsociadas(linea.split(",")[6]);
            usuarios.add(usuario);
        }
        return usuarios;
    }




    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }



    private static boolean validarUsuario(String idUsuario, String contraseña) throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);

        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
        {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if(usuarioAux.getIdUsuario().equalsIgnoreCase(idUsuario) && usuarioAux.getContraseña().equalsIgnoreCase(contraseña)) {
                return true;
            }
        }
        return false;
    }

    public static boolean iniciarSesion(String user, String password){
        return ArchivoUtil.iniciarSesion(user,password,RUTA_USERS_PROPERTIES);
    }


    public static boolean registroUsuario(String user, String password){
        return ArchivoUtil.registrarUsuario(user,password,RUTA_USERS_PROPERTIES);
    }



//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param ruta
     * @throws IOException
     */

    public static void guardarObjetos(ArrayList<Usuario> listaUsuarios, String ruta) throws IOException  {
        String contenido = "";

        for(Usuario usuarioAux:listaUsuarios) {
            contenido+= usuarioAux.getIdUsuario()+","+usuarioAux.getNombreCompleto()+","+usuarioAux.getCorreoElectronico()+usuarioAux.getTelefono()
                    +","+usuarioAux.getDireccion()+","+usuarioAux.getSaldoTotal()+","+usuarioAux.getCuentasAsociadas()+"\n";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, true);
    }





    //------------------------------------SERIALIZACIÓN  y XML


    public static BilleteraVirtual cargarRecursoBilleteraBinario() {

        BilleteraVirtual billeteraVirtual = null;

        try {
            billeteraVirtual = (BilleteraVirtual)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BILLETERA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return billeteraVirtual;
    }

    public static void guardarRecursoBilleteraBinario(BilleteraVirtual billeteraVirtual) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_BILLETERA_BINARIO, billeteraVirtual);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static BilleteraVirtual cargarRecursoBilleteraXML() {

        BilleteraVirtual billetera = null;

        try {
            billetera = (BilleteraVirtual)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BILLETERA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return billetera;

    }



    public static void guardarRecursoBilleteraXML(BilleteraVirtual billeteraVirtual) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_BILLETERA_XML, billeteraVirtual);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }










}
