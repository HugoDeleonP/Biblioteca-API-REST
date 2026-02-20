package com.example.biblioteca.controller;

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
    public Livro salvar(@RequestBody Livro livro){
        try{
            return livroService.salvar(livro);
        } catch (SQLException e){
            throw new RuntimeException("Erro");
        }

    }

    @GetMapping
    public List<Livro> buscarTodos(){
        try {
            return livroService.buscarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id){
        try {
            return livroService.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Livro atualizar(@RequestBody Livro livro, @PathVariable Long id){
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
