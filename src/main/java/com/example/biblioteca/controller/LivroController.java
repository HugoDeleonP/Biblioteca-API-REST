package com.example.biblioteca.controller;

import com.example.biblioteca.dto.LivroRequisicaoDTO;
import com.example.biblioteca.dto.LivroRespostaDTO;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public LivroRespostaDTO salvar(@RequestBody LivroRequisicaoDTO requisicaoDTO){
        try{
            return livroService.salvar(requisicaoDTO);
        } catch (SQLException e){
            throw new RuntimeException("Erro");
        }

    }

    @GetMapping
    public ResponseEntity<String> buscarTodos(){
        try {
             livroService.buscarTodos();

             return new ResponseEntity<>("Livros encontrados com sucesso!", HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<>("Livros não encontrados", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public LivroRespostaDTO buscarPorId(@PathVariable Long id){
        try {
            return livroService.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public LivroRespostaDTO atualizar(@RequestBody Livro livro, @PathVariable Long id){
        try {
            return livroService.atualizar(livro, id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        try{
            livroService.deletar(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
