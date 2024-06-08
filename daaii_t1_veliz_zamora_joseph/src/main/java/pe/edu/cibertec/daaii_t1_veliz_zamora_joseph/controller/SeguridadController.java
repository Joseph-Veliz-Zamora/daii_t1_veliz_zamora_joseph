package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Usuario;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.dto.ResultadoDto;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.dto.UsuarioDto;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.service.UsuarioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class SeguridadController {
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public String frmMantUsuario(Model model) {
        model.addAttribute("listaUsuarios",
                usuarioService.listarUsuario());
        return "seguridad/formusuario";
    }

    @PostMapping("/usuario")
    @ResponseBody
    public ResultadoDto registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        String mensaje = "Usuario registrado correctamente";
        boolean respuesta = true;
        try {
            Usuario usuario = new Usuario();
            usuario.setNombres(usuarioDto.getNombres());
            usuario.setApellidos(usuarioDto.getApellidos());
            if (usuarioDto.getIdusuario() > 0) {
                usuario.setIdusuario(usuarioDto.getIdusuario());
                usuario.setActivo(usuarioDto.getActivo());
                usuarioService.actualizarUsuario(usuario);
            } else {
                usuario.setNomusuario(usuarioDto.getNomusuario());
                usuario.setEmail(usuarioDto.getEmail());
                usuarioService.guardarUsuario(usuario);
            }
        } catch (Exception ex) {
            mensaje = "Usuario no registrado, error en la BD";
            respuesta = false;
        }
        return ResultadoDto.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Usuario frmMantUsuario(@PathVariable("id") int id) {
        return usuarioService.buscarUsuarioXIdUsuario(id);
    }

    @GetMapping("/usuario/lista")
    @ResponseBody
    public List<Usuario> listaUsuario() {
        return usuarioService.listarUsuario();
    }

    @PostMapping("/cambiar-password")
    @ResponseBody
    public ResultadoDto cambiarPassword(@RequestParam String username,
                                        @RequestParam String nuevaContraseña) {
        usuarioService.cambiarPassword(username, nuevaContraseña);
        return ResultadoDto.builder()
                .mensaje("Contraseña cambiada correctamente")
                .respuesta(true)
                .build();
    }


    @GetMapping("/cambiar-password")
    public String mostrarFormularioCambiarPassword() {
        return "seguridad/frmpassword";
    }

    @GetMapping("/frmpassword")
    public String mostrarFormularioPassword() {
        return "seguridad/frmpassword";
    }
}