package com.generation.Ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.Ecommerce.dto.ChangePassword;
import com.generation.Ecommerce.dto.Rol;
import com.generation.Ecommerce.model.Usuario;
import com.generation.Ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	public final UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> getAllUsers() {
		return usuarioRepository.findAll();
	}

	public Usuario getUser(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El usuario con el id [" + id + "] no existe"));
	}

	public Usuario deleteUsuario(Long id) {
		Usuario tmpUser = null;
		if (usuarioRepository.existsById(id)) {
			tmpUser = usuarioRepository.findById(id).get();
			usuarioRepository.deleteById(id);
		}
		return tmpUser;
	}

	public Usuario addUsuario(Usuario usuario, Rol rol) {
		Optional<Usuario> tmpUser = usuarioRepository.findByCorreo(usuario.getCorreo());
		if (tmpUser.isEmpty()) {
			usuario.setContrasena(encoder.encode(usuario.getContrasena()));
			usuario.setRol(rol);
			return usuarioRepository.save(usuario);
		} else {
			System.out.println("El usuario con el correo " + usuario.getCorreo() + " ya existe");
			return null;
		}
	}

	public Usuario updateUser(Long id, ChangePassword changePassword) {
		Usuario tmpUser = null;
		if (usuarioRepository.existsById(id)) {
			tmpUser = usuarioRepository.findById(id).get();
			if (encoder.matches(changePassword.getPassword(), tmpUser.getContrasena())) {
				tmpUser.setContrasena(encoder.encode(changePassword.getNpassword()));
				usuarioRepository.save(tmpUser);
			} else {
				System.out.println("updateUser - El password del usuario [" + id + "] no coincide");
				tmpUser = null;
			}
		}
		return tmpUser;
	}

	public boolean validateUser(Usuario usuario, Rol rol) {
		Optional<Usuario> userByEmail = usuarioRepository.findByCorreo(usuario.getCorreo());
		if (userByEmail.isPresent()) {
			Usuario tmpUser = userByEmail.get();
			if (encoder.matches(usuario.getContrasena(), tmpUser.getContrasena()) && tmpUser.getRol().equals(rol)) {
				return true;
			}
		}
		return false;
	}

	public Rol getUserRole(Usuario usuario) {
		Optional<Usuario> userByEmail = usuarioRepository.findByCorreo(usuario.getCorreo());
		if (userByEmail.isPresent()) {
			return userByEmail.get().getRol();
		}
		return null;
	}
}
