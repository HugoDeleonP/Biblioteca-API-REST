package com.example.biblioteca.service;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) throws SQLException {

        return livroRepository.salvar(livro);
    }

    public List<Livro> buscarTodos() throws SQLException{

        return livroRepository.buscarTodos();
    }

    public Livro buscarPorId(Long id) throws SQLException{

        return livroRepository.buscarPorId(id);
    }

    public Livro atualizar(Livro livro, Long id) throws SQLException{

        livro.setId(id);

        return livroRepository.atualizar(livro);
    }

    public void deletar(Long id) throws SQLException{

        livroRepository.deletar(id);


    }



}
