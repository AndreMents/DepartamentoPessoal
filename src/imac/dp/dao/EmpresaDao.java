/*
 * To change this license header, choose License Headers in Empject Empperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Empresa;
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
public class EmpresaDao {

    //VERIFICA SE O CÓDIGO DA EMPRESA EXISTE
    public boolean checkCod(String codEmp) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbempresas where codEmp=?");
            stmt.setString(1, codEmp);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //INSERT DOS DADOS DA EMPRESA NO BANCO
    public void createEmp(Empresa p) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbempresas(codEmp,nomeEmp)" + "VALUES(?,?)");
            stmt.setInt(1, p.getCodEmp());
            stmt.setString(2, p.getNomeEmp());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //LISTA TODOS DA EMPRESA CADASTRADAS NO BANCO
    public List<Empresa> readEmp() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> Empresa = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbempresas");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Empresa p = new Empresa();
                p.setCodEmp(rs.getInt("codEmp"));
                p.setNomeEmp(rs.getString("nomeEmp"));

                Empresa.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Empresa;
    }

    //LISTA OS USUÁRIOS PELO SEU NOME
    public List<Empresa> readEmpForDesc(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> Empresa = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbempresas WHERE nomeEmp LIKE ?");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Empresa p = new Empresa();

                p.setCodEmp(rs.getInt("codEmp"));
                p.setNomeEmp(rs.getString("nomeEmp"));

                Empresa.add(p);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Empresa;

    }

    //LISTA A EMPRESA PELO CÓDIGO
    public List<Empresa> readEmpForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empresa> Empresa = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbempresas WHERE codEmp LIKE ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();
            Empresa p = new Empresa();

            while (rs.next()) {

                p.setCodEmp(rs.getInt("codEmp"));
                p.setNomeEmp(rs.getString("nomeEmp"));
                Empresa.add(p);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Empresa;

    }

    //ATUALIZA OS DADOS DA EMPRESA
    public void update(Empresa p) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbempresas SET nomeEmp = ? WHERE codEmp = ?");

            stmt.setString(1, p.getNomeEmp());
            stmt.setInt(2, p.getCodEmp());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA A EMPRESA
    public void delete(Empresa p) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbempresas WHERE codEmp=?");
            stmt.setInt(1, p.getCodEmp());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
