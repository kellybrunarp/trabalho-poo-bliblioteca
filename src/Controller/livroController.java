/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.livroDAO;
import Model.Livro;
import java.util.List;

/**
 *
 * @author labinfo10
 */
public class livroController {
    
public boolean Salvar(Livro livro) {
        livroDAO livroDAO = new livroDAO();
        return livroDAO.salvar(livro);
    }

    public boolean alterar(Livro livro){
            livroDAO livroDAO = new livroDAO();
            return livroDAO.alterar(livro);
    }
    public boolean excluir(int id){
            livroDAO livroDAO = new livroDAO();
            return livroDAO.excluir(id);
    }
    public List<Livro> ListaLivros(String param) {
        livroDAO livroDAO = new livroDAO();
        return livroDAO.consultaNomeLivro(param);
    }
    public boolean validaLivro(Long id) {
        livroDAO livroDAO = new livroDAO();
        return livroDAO.validaLivro(id);
    }
    
}
