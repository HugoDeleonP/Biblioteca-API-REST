package com.example.biblioteca.service;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) throws SQLException {
        return usuarioRepository.salvar(usuario);
    }

    public List<Usuario> buscarTodos() throws SQLException{
        return usuarioRepository.buscarTodos();
    }

    public Usuario buscarPorId(Long id) throws SQLException{
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario atualizar(Usuario usuario, Long id) throws SQLException{
        usuario.setId(id);

        return usuarioRepository.atualizar(usuario);
    }

    public void deletar(Long id) throws SQLException {
        usuarioRepository.deletar(id);
    }


}
