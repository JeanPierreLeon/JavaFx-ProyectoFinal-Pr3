package co.edu.uniquindio.pr3.billeteravirtualapp.controller;

import co.edu.uniquindio.pr3.billeteravirtualapp.controller.service.IModelFactoryControllerService;
import co.edu.uniquindio.pr3.billeteravirtualapp.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.mappers.BilleteraVirtualMapper;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.pr3.billeteravirtualapp.utils.Persistencia;
import co.edu.uniquindio.pr3.billeteravirtualapp.utils.UsuarioUtils;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController implements IModelFactoryControllerService {
    BilleteraVirtual billetera;
    BilleteraVirtualMapper mapper = BilleteraVirtualMapper.INSTANCE;

    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
        cargarDatosBase();
        salvarDatosPrueba();

        //2. Cargar los datos de los archivos
//       cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
//		cargarResourceBinario();
//		guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
//        cargarResourceXML();
//		guardarResourceXML();


        //Siempre se debe verificar si la raiz del recurso es null

        if(billetera == null){
            cargarDatosBase();
            guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

    private void cargarDatosDesdeArchivos() {
        billetera = new BilleteraVirtual();
        try {
            Persistencia.cargarDatosArchivos(billetera);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarUsuarios(getBilletera().getListaUsuarios());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosBase() {
        billetera = UsuarioUtils.inicializarDatos();
    }


    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return  mapper.getUsuariosDto(billetera.getListaUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try{
            if(!billetera.verificarUsuarioExistente(String.valueOf((usuarioDto.idUsuario())))) {
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getBilletera().agregarUsuario(usuario);
                guardarResourceXML();
                salvarDatosPrueba();

            }
            return true;
        }catch (UsuarioException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {
        boolean flagExiste = false;
        try {
            flagExiste = getBilletera().eliminarUsuario(idUsuario);
            guardarResourceXML();
        } catch (UsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto) {
        try {
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getBilletera().actualizarUsuario(idUsuarioActual, usuario);
            guardarResourceXML();
            salvarDatosPrueba();
            return true;
        } catch (UsuarioException e) {
            e.printStackTrace();
            return false;
        }
    }
    public BilleteraVirtual getBilletera() {
        return billetera;
    }

    public void setBilletera(BilleteraVirtual billetera) {
        this.billetera = billetera;
    }

    private void cargarResourceXML() {
        billetera = Persistencia.cargarRecursoBilleteraXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoBilleteraXML(billetera);
    }

    private void cargarResourceBinario() {
        billetera = Persistencia.cargarRecursoBilleteraBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoBilleteraBinario(billetera);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}
