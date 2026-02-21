package com.example.biblioteca.service;

import com.example.biblioteca.dto.LivroRequisicaoDTO;
import com.example.biblioteca.dto.LivroRespostaDTO;
import com.example.biblioteca.mapper.LivroMapper;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper){
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public LivroRespostaDTO salvar(LivroRequisicaoDTO requisicaoDTO) throws SQLException {

        Livro livro = livroMapper.paraEntidade(requisicaoDTO);

        Livro livroBancoDados = livroRepository.salvar(livro);

        return livroMapper.paraRespostaDto(livroBancoDados);
    }

    public List<LivroRespostaDTO> buscarTodos() throws SQLException{

        List<Livro> livros = livroRepository.buscarTodos();

        /*

        List<LivroRespostaDTO> respostasDTO = new ArrayList<>();

        livros.forEach( livro -> {
            respostasDTO.add(livroMapper.paraRespostaDto(livro));
        });

        */

        return livros.stream()
                .map( livro -> livroMapper.paraRespostaDto(livro))
                .collect(Collectors.toList());
    }

    public LivroRespostaDTO buscarPorId(Long id) throws SQLException{

        Livro livro = livroRepository.buscarPorId(id);

        return livroMapper.paraRespostaDto(livro);
    }

    public LivroRespostaDTO atualizar(Livro livro, Long id) throws SQLException{

        livro.setId(id);

        Livro livroAtualizado = livroRepository.atualizar(livro);

        return livroMapper.paraRespostaDto(livroAtualizado);
    }

    public void deletar(Long id) throws SQLException{

        if(livroRepository.verificaLivroAtribuidoEmprestimo(id)){
            throw new RuntimeException("Não é possível deletar livro atribuido a um empréstimo");
        }

        livroRepository.deletar(id);



    }



}
