/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Usuario;
import imac.dp.view.FrMenu;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author @andre_ments
 */
public class UsuarioDao {
    
    //VERIFICAR USUÁRIO E SENHA PARA REALIZAR O LOGIN

    
    
    public boolean checkLogin(String loginUsuario, String senhaUsuario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {

            stmt = con.prepareStatement("Select * from tbusuarios where loginUsuario=? and binary senhaUsuario=?");
            stmt.setString(1, loginUsuario);
            stmt.setString(2, senhaUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();
                FrMenu Fr = new FrMenu();
                String login = u.getLoginUsuario();
//              Fr.usuarioLogado = rs.getString("login");
                Fr.setVisible(true);

                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    // VERIFICA SE O LOGIN DO USUÁRIO NÃO EXISTE
    public boolean checkCreate(String loginUsuario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbusuarios where loginUsuario=?");
            stmt.setString(1, loginUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    // VERIFICA SE O CÓDIGO DO USUÁRIO EXISTE
    public boolean checkCod(String codUsuario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbusuarios where codUsuario=?");
            stmt.setString(1, codUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //INSERT DOS DADOS DO USUÁRIO NO BANCO
    public void createUser(Usuario u) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbusuarios(codUsuario,nomeUsuario, setorUsuario,cargoUsuario, loginUsuario, senhaUsuario)" + "VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, u.getCodUsuario());
            stmt.setString(2, u.getNomeUsuario());
            stmt.setString(3, u.getSetorUsuario());
            stmt.setString(4, u.getCargoUsuario());
            stmt.setString(5, u.getLoginUsuario());
            stmt.setString(6, u.getSenhaUsuario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //LISTA TODOS OS USUÁRIOS CADASTRADOS NO BANCO
    public List<Usuario> readUser() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> Usuarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbusuarios");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCodUsuario(rs.getInt("codUsuario"));
                usu.setNomeUsuario(rs.getString("nomeUsuario"));
                usu.setSetorUsuario(rs.getString("setorUsuario"));
                usu.setCargoUsuario(rs.getString("cargoUsuario"));
                usu.setLoginUsuario(rs.getString("loginUsuario"));
                usu.setSenhaUsuario(rs.getString("senhaUsuario"));

                Usuarios.add(usu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Usuarios;
    }

    //LISTA OS USUÁRIOS PELO SEU NOME
    public List<Usuario> readUserForDesc(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> Usuario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbusuarios WHERE nomeUsuario LIKE ?");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();

                u.setCodUsuario(rs.getInt("codUsuario"));
                u.setNomeUsuario(rs.getString("nomeUsuario"));
                u.setSetorUsuario(rs.getString("setorUsuario"));
                u.setCargoUsuario(rs.getString("cargoUsuario"));
                u.setLoginUsuario(rs.getString("loginUsuario"));
                u.setSenhaUsuario(rs.getString("senhaUsuario"));
                Usuario.add(u);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar usuários" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Usuario;

    }

    //LISTA OS USUÁRIO PELO CÓDIGO
    public List<Usuario> readUserForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> Usuario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbusuarios WHERE codUsuario LIKE ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();
            Usuario u = new Usuario();

            while (rs.next()) {

                u.setCodUsuario(rs.getInt("codUsuario"));
                u.setNomeUsuario(rs.getString("nomeUsuario"));
                u.setSetorUsuario(rs.getString("setorUsuario"));
                u.setCargoUsuario(rs.getString("cargoUsuario"));
                u.setLoginUsuario(rs.getString("loginUsuario"));
                u.setSenhaUsuario(rs.getString("senhaUsuario"));
                Usuario.add(u);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar usuários" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Usuario;

    }

    //ATUALIZA OS DADOS DO USUÁRIO
    public void update(Usuario u) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbusuarios SET nomeUsuario = ?, setorUsuario = ?, cargoUsuario = ?, loginUsuario = ?, senhaUsuario = ? WHERE codUsuario = ?");
            stmt.setString(1, u.getNomeUsuario());
            stmt.setString(2, u.getSetorUsuario());
            stmt.setString(3, u.getCargoUsuario());
            stmt.setString(4, u.getLoginUsuario());
            stmt.setString(5, u.getSenhaUsuario());
            stmt.setInt(6, u.getCodUsuario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA O USUÁRIO PELO NÚMERO DO CÓDIGO
    public void delete(Usuario u) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbusuarios WHERE codUsuario =?");
            stmt.setInt(1, u.getCodUsuario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
