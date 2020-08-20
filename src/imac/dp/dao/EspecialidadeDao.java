/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Especialidade;
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
public class EspecialidadeDao {

    //VERIFICA SE O CÓDIGO DA ESPECIALIDADE EXISTE
    public boolean checkCod(String codEsp) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbesp where codEsp=?");
            stmt.setString(1, codEsp);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //INSERT DOS DADOS DA ESPECIALIDADE NO BANCO
    public void createEsp(Especialidade e) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbesp(codEsp,nomeEsp)" + "VALUES(?,?)");
            stmt.setInt(1, e.getCod());
            stmt.setString(2, e.getNome());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //LISTA TODOS AS ESPECIALIDADE CADASTRADAS NO BANCO
    public List<Especialidade> readEsp() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Especialidade> Especialidade = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbesp");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Especialidade e = new Especialidade();
                e.setCod(rs.getInt("codEsp"));
                e.setNome(rs.getString("nomeEsp"));

                Especialidade.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Especialidade;
    }

    //LISTA A ESPECIALIDADE PELO SEU NOME
    public List<Especialidade> readEspForDesc(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Especialidade> Especialidade = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbesp WHERE nomeEsp LIKE ?");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Especialidade e = new Especialidade();

                e.setCod(rs.getInt("codEsp"));
                e.setNome(rs.getString("nomeEsp"));

                Especialidade.add(e);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Especialidade;

    }

    //LISTA AS ESPECIALIDADES PELO CÓDIGO
    public List<Especialidade> readEspForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Especialidade> Especialidade = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbesp WHERE codEsp LIKE ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();
            Especialidade e = new Especialidade();

            while (rs.next()) {

                e.setCod(rs.getInt("codEsp"));
                e.setNome(rs.getString("nomeEsp"));

                Especialidade.add(e);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Especialidade;

    }

    //ATUALIZA OS DADOS DA ESPECIALIDADE
    public void update(Especialidade e) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbesp SET nomeEsp = ? WHERE codEsp = ?");
            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getCod());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA A ESPECIALIDADE PELO NÚMERO DO CÓDIGO
    public void delete(Especialidade e) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbesp WHERE codEsp=?");
            stmt.setInt(1, e.getCod());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
