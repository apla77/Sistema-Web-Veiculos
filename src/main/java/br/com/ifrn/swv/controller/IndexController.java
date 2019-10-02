package br.com.ifrn.swv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.ifrn.swv.model.Usuario;
import br.com.ifrn.swv.service.SessionService;
import br.com.ifrn.swv.service.UsuarioService;


@Controller
public class IndexController {
	
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private SessionService<Usuario> serviceSession;

	@PostMapping("/home") 
	public String home() { 
		
		Usuario usuarioByEmail = serviceUsuario.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		serviceSession.criarSession("usuario", usuarioByEmail);
		return "home";
	} 
	 
	@GetMapping("/")
	public String index() {
		serviceSession.clearSession();
		return "home";
	}
	
	@GetMapping("/entrar")
	public String entrar() {
		return "login";
	}
}
