package co.edu.uniquindio.pr3.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Cuenta;
import co.edu.uniquindio.pr3.billeteravirtualapp.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface BilleteraVirtualMapper {
    BilleteraVirtualMapper INSTANCE = Mappers.getMapper(BilleteraVirtualMapper.class);

    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

//    @Named("mappingToEmpeladoDto")
//    EmpleadoDto mappingToEmpeladoDto(Empleado empleado);


    @Mapping(target = "idCuenta", source = "cuenta.nombreBanco")
    @IterableMapping(qualifiedByName = "cunetaToCuentaDto")
    CuentaDto clienteToClienteDto(Cuenta cuenta);
}
