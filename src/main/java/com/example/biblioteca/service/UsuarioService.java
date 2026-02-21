package com.example.biblioteca.service;

import com.example.biblioteca.dto.UsuarioRequisicaoDTO;
import com.example.biblioteca.dto.UsuarioRespostaDTO;
import com.example.biblioteca.mapper.UsuarioMapper;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioRespostaDTO salvar(UsuarioRequisicaoDTO usuarioRequisicaoDTO) throws SQLException {

        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequisicaoDTO);
        Usuario usuarioBancoDados = usuarioRepository.salvar(usuario);

        return usuarioMapper.paraRespostaDTO(usuarioBancoDados);
    }

    public List<UsuarioRespostaDTO> buscarTodos() throws SQLException{

        return usuarioRepository.buscarTodos()
                .stream()
                .map(usuarioMapper::paraRespostaDTO)
                .collect(Collectors.toList());
    }

    public UsuarioRespostaDTO buscarPorId(Long id) throws SQLException{
        return usuarioMapper.paraRespostaDTO(usuarioRepository.buscarPorId(id));
    }

    public UsuarioRespostaDTO atualizar(Usuario usuario, Long id) throws SQLException{
        usuario.setId(id);

        return usuarioMapper.paraRespostaDTO(usuarioRepository.atualizar(usuario));
    }

    public void deletar(Long id) throws SQLException {
        usuarioRepository.deletar(id);
    }


}
