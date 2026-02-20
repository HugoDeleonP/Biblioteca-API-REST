package com.example.biblioteca.repository;

import com.example.biblioteca.infra.DatabaseConnect;
import com.example.biblioteca.model.Emprestimo;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepository {


    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException{
        String sql = """
                INSERT INTO emprestimo
                (livro_id, usuario_id, data_emprestimo)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, emprestimo.getLivro_id());
            stmt.setLong(2, emprestimo.getUsuario_id());
            stmt.setDate(3, Date.valueOf(emprestimo.getData_emprestimo()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                emprestimo.setId(rs.getLong(1));
            }

            return emprestimo;

        }
    }

    public List<Emprestimo> buscarTodos() throws SQLException {
        String sql = """
                SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao
                FROM emprestimo
        """;

        List<Emprestimo> emprestimos = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                Long livro_id = rs.getLong("livro_id");
                Long usuario_id = rs.getLong("usuario_id");
                LocalDate data_emprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate data_devolucao = rs.getDate("data_devolucao").toLocalDate();

                emprestimos.add(new Emprestimo(id, livro_id, usuario_id, data_emprestimo, data_devolucao));
            }

            return emprestimos;
        }
    }

    public Emprestimo buscarPorId(Long id) throws SQLException{
        String sql = """
                SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao
                FROM emprestimo
                WHERE id = ?
        """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Long livro_id = rs.getLong("livro_id");
                Long usuario_id = rs.getLong("usuario_id");
                LocalDate data_emprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate data_devolucao = rs.getDate("data_devolucao").toLocalDate();

                return new Emprestimo(id, livro_id, usuario_id, data_emprestimo, data_devolucao);
            }

            return null;
        }
    }

    public Emprestimo atualizarDataEmprestimo(Emprestimo emprestimo) throws SQLException{

        String sql = """
                UPDATE emprestimo
                SET
                data_emprestimo = ?
                WHERE id = ?
        """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, Date.valueOf(emprestimo.getData_emprestimo()));
            stmt.setLong(2, emprestimo.getId());

            stmt.executeUpdate();

            return emprestimo;
        }

    }

    public Emprestimo atualizarDataDevolucao(Emprestimo emprestimo) throws SQLException{

        String sql = """
                UPDATE emprestimo
                SET
                data_devolucao = ?
                WHERE id = ?
        """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, Date.valueOf(emprestimo.getData_devolucao()));
            stmt.setLong(2, emprestimo.getId());

            stmt.executeUpdate();


            return emprestimo;
        }

    }



    public void deletar(Long id) throws SQLException{
        String sql = """
                DELETE FROM emprestimo
                WHERE id = ?
        """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }


}
