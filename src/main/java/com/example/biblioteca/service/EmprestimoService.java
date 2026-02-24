package com.example.biblioteca.service;

import com.example.biblioteca.dto.EmprestimoRequisicaoDTO;
import com.example.biblioteca.dto.EmprestimoRespostaDTO;
import com.example.biblioteca.mapper.EmprestimoMapper;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.EmprestimoRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    private final EmprestimoMapper emprestimoMapper;
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository, EmprestimoMapper emprestimoMapper){
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    public EmprestimoRespostaDTO salvar(EmprestimoRequisicaoDTO emprestimoRequisicaoDTO) throws SQLException {

        Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequisicaoDTO);

        return emprestimoMapper.paraRespostaDto(emprestimoRepository.salvar(emprestimo));
    }

    public List<EmprestimoRespostaDTO> buscarTodos() throws SQLException {

        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();

        return emprestimos
                .stream().map(
                        emprestimo -> emprestimoMapper.paraRespostaDto(emprestimo)
                )
                .collect(Collectors.toList());

    }

    public EmprestimoRespostaDTO buscarPorId(Long id) throws SQLException{

        return emprestimoMapper.paraRespostaDto(emprestimoRepository.buscarPorId(id));

    }

    public EmprestimoRespostaDTO atualizarDataEmprestimo(Long id, Emprestimo emprestimo) throws SQLException{
        emprestimo.setId(id);

        return emprestimoMapper.paraRespostaDto(emprestimoRepository.atualizarDataEmprestimo(emprestimo));
    }

    public EmprestimoRespostaDTO atualizarDataDevolucao(Long id, Emprestimo emprestimo) throws SQLException{
        emprestimo.setId(id);

        return emprestimoMapper.paraRespostaDto(emprestimoRepository.atualizarDataDevolucao(emprestimo));
    }

    public void deletar(Long id) throws SQLException{
        emprestimoRepository.deletar(id);
    }


}
