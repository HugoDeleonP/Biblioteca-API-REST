package com.example.biblioteca.controller;

import com.example.biblioteca.dto.UsuarioRequisicaoDTO;
import com.example.biblioteca.dto.UsuarioRespostaDTO;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService ){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioRespostaDTO salvar(@RequestBody UsuarioRequisicaoDTO usuarioRequisicaoDTO){
        try{
            return usuarioService.salvar(usuarioRequisicaoDTO);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<UsuarioRespostaDTO> buscarTodos(){
        try{
            return usuarioService.buscarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public UsuarioRespostaDTO buscarTodos(@PathVariable Long id){
        try{
            return usuarioService.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public UsuarioRespostaDTO atualizar(@RequestBody Usuario usuario, @PathVariable Long id){
        try{
            return usuarioService.atualizar(usuario, id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        try{
            usuarioService.deletar(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
