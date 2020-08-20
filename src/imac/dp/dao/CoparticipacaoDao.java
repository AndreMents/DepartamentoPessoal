/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Coparticipacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author @andre_ments
 */
public class CoparticipacaoDao {

    //checkCreate VERIFICA SE A COPARTICIPAÇÃO NÃO EXISTE
    public boolean checkCreate(String codCop) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbcoparti geradoPar where codCop=?");
            stmt.setString(1, codCop);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    public boolean checkCreatePro(String codCop) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM `tbrealiza` WHERE tbcoparti_codCop = ?");
            stmt.setString(1, codCop);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    public List<Coparticipacao> readGeradoPar(Integer codCop) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coparticipacao> Coparticipacao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("Select * from tbcoparti geradoPar where codCop = ?");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Coparticipacao co = new Coparticipacao();
                co.setGeradoPar(rs.getInt("geradoPar"));
                Coparticipacao.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Coparticipacao;
    }

    // LISTA O ´MAIOR CÓDIGO DA COPARTICIÁÇÃO CADASTRADA NO BANCO
    public List<Coparticipacao> readCodCop() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coparticipacao> Coparticipacao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT Max(codCop) FROM tbcoparti");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Coparticipacao co = new Coparticipacao();
                co.setCodCop(rs.getInt("Max(codCop)"));
                Coparticipacao.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Coparticipacao;
    }

    //REALIZA A SOMA DOS VALORES DOS PROCEDIMENTOS CADASTRADOS NA COPARTICIPAÇÃO PELO CÓDIGO DA COPARTICIPÁÇÃO
    public List<Coparticipacao> readSumCop(Integer desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coparticipacao> Coparticipacao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT SUM(pro.valorPro) FROM tbrealiza rel JOIN tbprocedi pro on pro.codPro = rel.tbprocedi_codPro WHERE tbcoparti_codCop = ?");
            stmt.setInt(1, desc);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Coparticipacao co = new Coparticipacao();

                co.setValorCop(rs.getDouble("SUM(pro.valorPro)"));

                Coparticipacao.add(co);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Coparticipacao;
    }

    public void createCop(Coparticipacao c) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbcoparti(codCop,tbfuncionarios_codFuncionario, geradoPar) VALUES(?,?, 1)");
            stmt.setInt(1, c.getCodCop());
            stmt.setInt(2, c.getFr_codFuncionario());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //INSERT DOS DADOS DA COPARTICIPAÇÃO NO BANCO
    public void realizaProcedi(Coparticipacao c) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbrealiza( tbprocedi_codPro, tbcoparti_codCop, tbcoparti_tbfuncionarios_codFuncionario, dataPro, medicoPro, localPro) VALUES(?,?,?,?,?,?)");

            stmt.setInt(1, c.getFr_codPro());
            stmt.setInt(2, c.getCodCop());
            stmt.setInt(3, c.getFr_codFuncionario());
            stmt.setDate(4, c.getDataPro());
            stmt.setString(5, c.getMedicoPro());
            stmt.setString(6, c.getLocalPro());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //LISTA TODOS AS COPARTICIPAÇÃOS CADASTRADAS NO BANCO
    public List<Coparticipacao> readCop() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coparticipacao> Coparticipacao = new ArrayList<>();
        try {

            stmt = con.prepareStatement("SELECT * FROM tbrealiza rel JOIN tbfuncionarios fun ON fun.codFuncionario = rel.tbcoparti_tbfuncionarios_codFuncionario JOIN tbprocedi pro ON pro.codPro = rel.tbprocedi_codPro JOIN tbcoparti cop ON cop.codCop = rel.tbcoparti_codCop");
            rs = stmt.executeQuery();
            while (rs.next()) {

                Coparticipacao co = new Coparticipacao();
                co.setCodCop(rs.getInt("codCop"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                co.setFr_codPro(rs.getInt("codPro"));
                co.setFr_nomePro(rs.getString("nomePro"));
                co.setFr_valorPro(rs.getDouble("valorPro"));
                co.setLocalPro(rs.getString("localPro"));
                co.setMedicoPro(rs.getString("medicoPro"));
                co.setDataPro(rs.getDate("dataPro"));
                

                Coparticipacao.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Coparticipacao;
    }

    //LISTA A COPARTICIPAÇÃO PELO CÓDIGO
    public List<Coparticipacao> readCopForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coparticipacao> Coparticipacao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbrealiza rel JOIN tbfuncionarios fun ON fun.codFuncionario = rel.tbcoparti_tbfuncionarios_codFuncionario JOIN tbprocedi pro ON pro.codPro = rel.tbprocedi_codPro JOIN tbcoparti cop ON cop.codCop = rel.tbcoparti_codCop WHERE codCop = ?");

            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();

            while (rs.next()) {

                Coparticipacao co = new Coparticipacao();
                co.setCod(rs.getInt("codRel"));
                co.setCodCop(rs.getInt("tbcoparti_codCop"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                co.setFr_codPro(rs.getInt("codPro"));
                co.setFr_nomePro(rs.getString("nomePro"));
                co.setFr_valorPro(rs.getDouble("valorPro"));
                co.setLocalPro(rs.getString("localPro"));
                co.setMedicoPro(rs.getString("medicoPro"));
                co.setDataPro(rs.getDate("dataPro"));
                co.setGeradoPar(rs.getInt("geradoPar"));
                Coparticipacao.add(co);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar funcionario" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Coparticipacao;

    }

    //ATUALIZA OS DADOS DA COPARTICIPAÇÃO
    public void update(Coparticipacao c) throws ClassNotFoundException, ParseException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbcoparti SET "
                    + "fr_codFuncionario = ?, "
                    + "fr_nomeFuncionario = ?, "
                    + "fr_cpfFuncionario = ?, "
                    + "fr_cargoFuncionario = ?, "
                    + "fr_setorFuncionario = ?,"
                    + "fr_codPro = ?,"
                    + "fr_nomePro = ?,"
                    + "fr_valorPro = ?,"
                    + "fr_parcelaPro = ?,"
                    + "localPro = ?,"
                    + "medicoPro = ?,"
                    + "dataPro = ?,"
                    + "dataVencPro = ? "
                    + "WHERE codCop = ? ");

            stmt.setInt(1, c.getFr_codFuncionario());
            stmt.setString(2, c.getFr_nomeFuncionario());
            stmt.setString(3, c.getFr_cpfFuncionario());
            stmt.setString(4, c.getFr_cargoFuncionario());
            stmt.setString(5, c.getFr_setorFuncionario());
            stmt.setInt(6, c.getFr_codPro());
            stmt.setString(7, c.getFr_nomePro());
            stmt.setDouble(8, c.getFr_valorPro());
            stmt.setInt(9, c.getFr_parcelaPro());
            stmt.setString(10, c.getLocalPro());
            stmt.setString(11, c.getMedicoPro());
            stmt.setDate(12, c.getDataPro());
            stmt.setDate(13, c.getDataVencPro());
            stmt.setInt(14, c.getCodCop());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA PROCEDIMENTOS CADASTRADOS NA COPARTICIPAÇÃO
    public void delete(Coparticipacao c) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbcoparti WHERE codCop =?");
            stmt.setInt(1, c.getCodCop());
            stmt.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA COPARTICIPAÇÃO
    public void deleteForCod(Coparticipacao c) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbrealiza WHERE codRel = ? ");
            stmt.setInt(1, c.getCod());
            stmt.executeUpdate();

        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
