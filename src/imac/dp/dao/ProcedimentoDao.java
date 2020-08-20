/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Procedimento;
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
public class ProcedimentoDao {

    //VERIFICA SE O CÓDIGO DO PROCEDIMENTO EXISTE NO BANCO
    public boolean checkCod(String codPro) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbprocedi where codPro=?");
            stmt.setString(1, codPro);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //INSERT DOS DADOS DO PROCEDIMENTO NO BANCO
    public void createPro(Procedimento p) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbprocedi(codPro,nomePro, valorPro, parcelaPro)" + "VALUES(?,?,?,?)");
            stmt.setInt(1, p.getCodPro());
            stmt.setString(2, p.getNomePro());
            stmt.setDouble(3, p.getValorPro());
            stmt.setInt(4, p.getParcelaPro());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //LISTA TODOS OS PROCEDIMENTO CADASTRADOS NO BANCO
    public List<Procedimento> readPro() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Procedimento> Procedimento = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbprocedi");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Procedimento p = new Procedimento();
                p.setCodPro(rs.getInt("codPro"));
                p.setNomePro(rs.getString("nomePro"));
                p.setValorPro(rs.getDouble("valorPro"));
                p.setParcelaPro(rs.getInt("parcelaPro"));

                Procedimento.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Procedimento;
    }

    //LISTA OS PROCEDIMENTOS PELO SEU NOME
    public List<Procedimento> readProForDesc(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Procedimento> Procedimento = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbprocedi WHERE nomePro LIKE ?");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Procedimento p = new Procedimento();

                p.setCodPro(rs.getInt("codPro"));
                p.setNomePro(rs.getString("nomePro"));
                p.setValorPro(rs.getDouble("valorPro"));
                p.setParcelaPro(rs.getInt("parcelaPro"));

                Procedimento.add(p);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Procedimento;

    }

    //LISTA OS PROCEDIMENTOS PELO CÓDIGO
    public List<Procedimento> readProForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Procedimento> Procedimento = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbprocedi WHERE codPro LIKE ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();
            Procedimento p = new Procedimento();

            while (rs.next()) {

                p.setCodPro(rs.getInt("codPro"));
                p.setNomePro(rs.getString("nomePro"));
                p.setValorPro(rs.getDouble("valorPro"));
                p.setParcelaPro(rs.getInt("parcelaPro"));
                Procedimento.add(p);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Procedimento;

    }

    //ATUALIZA OS DADOS DO PROCEDIMENTO
    public void update(Procedimento p) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbprocedi SET nomePro = ?, valorPro = ?, parcelaPro = ? WHERE codPro = ?");

            stmt.setString(1, p.getNomePro());
            stmt.setDouble(2, p.getValorPro());
            stmt.setInt(3, p.getParcelaPro());
            stmt.setInt(4, p.getCodPro());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA O PROCEDIMENTO
    public void delete(Procedimento p) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbprocedi WHERE codPro=?");
            stmt.setInt(1, p.getCodPro());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
