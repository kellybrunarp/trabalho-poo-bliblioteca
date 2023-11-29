/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Livro;
import db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author labinfo10
 */
public class livroDAO {

    private Connection con;  //Conecta com banco
    private PreparedStatement stm; //prepara a ação no banco
    private ResultSet rs; //retorna dados do banco

    public boolean salvar(Livro livro) {
        try {
            String sql = "insert into livros values(?,?,?,?,?,?)";
            con = Conexao.getConnection();
            stm = con.prepareStatement(sql);
            stm.setLong(1, livro.getCodigo());
            stm.setString(2, livro.getTitulo());
            stm.setString(3, livro.getDescricao());
            stm.setString(4, livro.getAutor());
            stm.setString(5, livro.getGenero());
            stm.setInt(6, livro.getAno());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }

    public boolean alterar(Livro livro) {
        try {
            String sql = "update livros set titulo = ?, descricao = ?,autor=?, genero = ?, ano = ? where codigo = ?";
            con = Conexao.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getDescricao());
            stm.setString(3, livro.getAutor());
            stm.setString(4, livro.getGenero());
            stm.setInt(5, livro.getAno());
            stm.setLong(6, livro.getCodigo());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }

    public boolean excluir(long codigo) {
        try {
            String sql = "delete from livros where codigo = ?";
            con = Conexao.getConnection();
            stm = con.prepareStatement(sql);
            stm.setLong(1, codigo);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir dados " + ex.getMessage());
        }
        return false;
    }

    public List<Livro> consultaNomeLivro(String param) {
        try {
            String sql = "";
            if (param.equals("todos")) {
                sql = "select * from livros";
            } else {
                sql = "select * from livros where titulo like '%" + param + "%'";
            }
            con = Conexao.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            Livro livro = null;
            List<Livro> lista = new ArrayList<>();
            while (rs.next()) {
                livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));
                livro.setDescricao(rs.getString("descricao"));
                livro.setGenero(rs.getString("genero"));
                livro.setAutor(rs.getString("autor"));
                livro.setCodigo(rs.getInt("codigo"));
                livro.setAno(rs.getInt("ano"));
                lista.add(livro);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public boolean validaLivro(Long id) {
        try {
            String sql = "select * from livros where codigo = ?";
            con = Conexao.getConnection();
            stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            System.out.println(stm.executeQuery());
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar o livros"
                    + ex.getMessage());
        }
        return false;
    }
}
