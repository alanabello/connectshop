package com.connectshopp.usuarios.service;

import com.connectshopp.usuarios.dto.DireccionRequest;
import com.connectshopp.usuarios.exception.BusinessException;
import com.connectshopp.usuarios.exception.ResourceNotFoundException;
import com.connectshopp.usuarios.model.Direccion;
import com.connectshopp.usuarios.model.RolUsuario;
import com.connectshopp.usuarios.model.Usuario;
import com.connectshopp.usuarios.repository.RolUsuarioRepository;
import com.connectshopp.usuarios.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolUsuarioRepository rolUsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolUsuarioRepository rolUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    @Transactional
    public Usuario crear(Long rolId, Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException("Ya existe un usuario con el email " + usuario.getEmail());
        }

        RolUsuario rol = rolUsuarioRepository.findById(rolId)
            .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Direccion agregarDireccion(Long usuarioId, DireccionRequest request) {
        Usuario usuario = buscarPorId(usuarioId);

        Direccion direccion = new Direccion();
        direccion.setCalle(request.calle());
        direccion.setCiudad(request.ciudad());
        direccion.setRegion(request.region());

        usuario.agregarDireccion(direccion);
        usuarioRepository.save(usuario);
        return direccion;
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscar(String email, Long rolId) {
        if (email != null && !email.isBlank()) {
            return usuarioRepository.findByEmail(email).map(List::of).orElseGet(List::of);
        }
        if (rolId != null) {
            return usuarioRepository.findByRolId(rolId);
        }
        return usuarioRepository.findAll();
    }
}
