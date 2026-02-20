package com.example.biblioteca.repository;

import com.example.biblioteca.infra.DatabaseConnect;
import com.example.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {

    public Livro salvar(Livro livro) throws SQLException {
        String sql = """
                INSERT INTO livro
                (titulo, autor, ano_publicacao)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                livro.setId(rs.getLong(1));
                return livro;
            }

            return null;
        }
    }

    public List<Livro> buscarTodos() throws SQLException{
        String sql = """
                SELECT id, titulo, autor, ano_publicacao
                FROM livro
                """;

        List<Livro> livros = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano_publicacao = rs.getInt( "ano_publicacao");

                Livro livroBuscado = new Livro(id, titulo, autor, ano_publicacao);
                livros.add(livroBuscado);
            }

            return livros;
        }
    }

    public Livro buscarPorId(Long id) throws SQLException{

        String sql = """
                SELECT titulo, autor, ano_publicacao
                FROM livro
                WHERE id = ?
                """;


        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano_publicacao = rs.getInt( "ano_publicacao");

                return new Livro(id, titulo, autor, ano_publicacao);
            }

            return null;
        }

    }

    public Livro atualizar(Livro livro) throws SQLException{
        String sql = """
                UPDATE livro
                SET
                titulo = ?,
                autor = ?,
                ano_publicacao = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.setLong(4, livro.getId());

            stmt.executeUpdate();

            return livro;
        }
    }

    public void deletar(Long id) throws SQLException{
        String sql = """
                DELETE FROM livro
                WHERE id = ?
                """;


        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean verificaLivroAtribuidoEmprestimo(Long id) throws SQLException{
        String sql = """
                
                SELECT COUNT(id) as quantidade
                FROM livro
                WHERE id = ?;
                
                """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getInt("quantidade") > 1;
            }
        }

        return false;
    }
}
