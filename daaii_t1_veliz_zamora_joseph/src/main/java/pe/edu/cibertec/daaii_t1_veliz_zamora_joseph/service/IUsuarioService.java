package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.service;

import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Usuario;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
}
