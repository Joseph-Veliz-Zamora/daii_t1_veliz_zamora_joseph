package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Usuario;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.service.UsuarioService;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/frmLogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nomusuario", auth.getName());
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/registro")
    public String registro() {
        return "auth/frmRegistro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombres,
                                   @RequestParam String apellidos,
                                   @RequestParam String email,
                                   @RequestParam String nomusuario,
                                   @RequestParam String password) {
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setNomusuario(nomusuario);
        usuario.setPassword("{noop}" + password);

        usuarioService.guardarUsuario(usuario);

        return "redirect:/auth/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "auth/home";
    }
}