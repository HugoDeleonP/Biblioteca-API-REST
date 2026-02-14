package com.example.biblioteca.controller;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    public EmprestimoController(EmprestimoService emprestimoService){
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public Emprestimo salvar(@RequestBody Emprestimo emprestimo){
        try{
            return emprestimoService.salvar(emprestimo);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Emprestimo> buscarTodos(){
        try{
            return emprestimoService.buscarTodos();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable Long id){
        try{
            return emprestimoService.buscarPorId(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Emprestimo atualizarDataEmprestimo(@PathVariable Long id, Emprestimo emprestimo){
        try{
            return emprestimoService.atualizarDataEmprestimo(id, emprestimo);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}/devolucao")
    public Emprestimo atualizarDataDevolucao(@PathVariable Long id, Emprestimo emprestimo){
        try{
            return emprestimoService.atualizarDataDevolucao(id, emprestimo);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        try{
            emprestimoService.deletar(id);
            return new ResponseEntity<>("Deletado com sucesso!", HttpStatus.OK);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
