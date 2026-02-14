package com.example.biblioteca.controller;

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
    public Usuario salvar(@RequestBody Usuario usuario){
        try{
            return usuarioService.salvar(usuario);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> buscarTodos(){
        try{
            return usuarioService.buscarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Usuario buscarTodos(@PathVariable Long id){
        try{
            return usuarioService.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@RequestBody Usuario usuario, @PathVariable Long id){
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
