package com.example.biblioteca.service;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.EmprestimoRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository){
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        Usuario usuario = usuarioRepository.buscarPorId(emprestimo.getUsuario_id());
        Livro livro = livroRepository.buscarPorId(emprestimo.getUsuario_id());

        return emprestimoRepository.salvar(emprestimo);
    }

    public List<Emprestimo> buscarTodos() throws SQLException {
        return emprestimoRepository.buscarTodos();

    }

    public Emprestimo buscarPorId(Long id) throws SQLException{
        return emprestimoRepository.buscarPorId(id);

    }

    public Emprestimo atualizarDataEmprestimo(Long id, Emprestimo emprestimo) throws SQLException{
        emprestimo.setId(id);

        return emprestimoRepository.atualizarDataEmprestimo(emprestimo);
    }

    public Emprestimo atualizarDataDevolucao(Long id, Emprestimo emprestimo) throws SQLException{
        emprestimo.setId(id);

        return emprestimoRepository.atualizarDataDevolucao(emprestimo);
    }

    public void deletar(Long id) throws SQLException{
        emprestimoRepository.deletar(id);
    }


}
