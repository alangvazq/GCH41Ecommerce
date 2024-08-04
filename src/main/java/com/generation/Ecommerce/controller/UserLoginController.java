package com.generation.Ecommerce.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.Ecommerce.config.JwtFilter;
import com.generation.Ecommerce.dto.Rol;
import com.generation.Ecommerce.dto.UserToken;
import com.generation.Ecommerce.model.Usuario;
import com.generation.Ecommerce.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(path="/api/login")
public class UserLoginController {
private final UsuarioService usuarioService;
	
	@Autowired
	public UserLoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public UserToken loginUser(@RequestBody Usuario usuario) throws ServletException {
		Rol userRol = usuarioService.getUserRole(usuario);
		if(usuarioService.validateUser(usuario, userRol)) {
			System.out.println("Usuario válido" + usuario.getContrasena());
			return new UserToken(generateToken(usuario.getCorreo(), userRol));
		}
		throw new ServletException("Nombre de ususario o contraseña incorrectos.");
	}
	
	private String generateToken(String username, Rol rol) {
		Calendar calendar = Calendar.getInstance();//Fecha hora actual
		calendar.add(Calendar.HOUR, 10); // Desarrollo
		//calendar.add(Calendar.MINUTE, 30);// Producción
		return Jwts.builder().setSubject(username).claim("role", rol.name())
		.setIssuedAt(new Date())
		.setExpiration(calendar.getTime())
		.signWith(SignatureAlgorithm.HS256, JwtFilter.secret)
		.compact();
	}//generateToken
}
