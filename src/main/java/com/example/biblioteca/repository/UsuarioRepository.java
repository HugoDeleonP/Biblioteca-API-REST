package com.example.biblioteca.repository;

import com.example.biblioteca.infra.DatabaseConnect;
import com.example.biblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public Usuario salvar(Usuario usuario) throws SQLException{
        String sql = """
                INSERT INTO usuario
                (nome, email)
                VALUES
                (?, ?)
                """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                usuario.setId(rs.getLong(1));
            }

            return usuario;
        }
    }

    public List<Usuario> buscarTodos() throws SQLException{
        String sql = """
                SELECT id, nome, email
                FROM usuario
        """;

        List<Usuario> usuarios = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                usuarios.add(new Usuario(id, nome, email));
            }

            return usuarios;
        }
    }

    public Usuario buscarPorId(Long id) throws SQLException{
        String sql = """
                SELECT nome, email
                FROM usuario
                WHERE id = ?
        """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                String nome = rs.getString(1);
                String email = rs.getString(2);

                return new Usuario(id, nome, email);
            }

            return null;
        }
    }

    public Usuario atualizar(Usuario usuario) throws SQLException{
        String sql = """
                UPDATE usuario
                SET
                nome = ?,
                email = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());

            stmt.executeUpdate();

            return usuario;
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = """
                DELETE FROM usuario
                WHERE id = ?
                """;


        try(Connection conn = DatabaseConnect.connect()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();

        }
    }


}
