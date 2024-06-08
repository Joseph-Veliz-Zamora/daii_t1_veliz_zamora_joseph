package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Rol;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Usuario;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.repository.RolRepository;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.repository.UsuarioRepository;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.util.RandomPassword;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final BCryptPasswordEncoder passwordEncoder;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private RandomPassword randomPassword;
    private ValidarPassword validarPassword;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(passwordEncoder.encode(randomPassword.generar(8)));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(
                usuario.getNombres(),usuario.getApellidos(),
                usuario.getActivo(),usuario.getIdusuario()
        );
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }
    public void cambiarPassword(String username, String nuevaContraseña) {
        if (!validarPassword.validarPassword(nuevaContraseña)) {
            throw new RuntimeException("La contraseña no cumple con los requisitos mínimos");
        }
        Usuario usuario = usuarioRepository.findByNomusuario(username);
        if (usuario != null) {
            String contraseñaCodificada = passwordEncoder.encode(nuevaContraseña);
            usuario.setPassword(contraseñaCodificada);
            usuarioRepository.save(usuario);
        }
    }
}
