package co.edu.uniquindio.pr3.billeteravirtualapp.controller;

import co.edu.uniquindio.pr3.billeteravirtualapp.controller.service.IModelFactoryControllerService;
import co.edu.uniquindio.pr3.billeteravirtualapp.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.mappers.BilleteraVirtualMapper;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.pr3.billeteravirtualapp.utils.BilleteraVirtualUtils;

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
        System.out.println("invocación clase singleton");
        cargarDatosBase();
    }

    private void cargarDatosBase() {
        billetera = BilleteraVirtualUtils.inicializarDatos();
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
}
