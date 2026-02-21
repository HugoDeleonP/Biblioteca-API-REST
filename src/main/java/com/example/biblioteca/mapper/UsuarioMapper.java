package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.UsuarioRequisicaoDTO;
import com.example.biblioteca.dto.UsuarioRespostaDTO;
import com.example.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario paraEntidade(UsuarioRequisicaoDTO requisicaoDTO){
        return new Usuario(
                requisicaoDTO.nome(),
                requisicaoDTO.email()
        );
    }

    public UsuarioRespostaDTO paraRespostaDTO(Usuario usuario){
        return new UsuarioRespostaDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
