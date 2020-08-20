/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Coparticipacao;
import imac.dp.model.Parcela;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author @andre_ments
 */
public class ParcelaDao {

    //VERIFICA SE A PARCELA EXISTE NO BANCO
    public boolean checkCreate(String codParcela) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbparcela where codParcela=?");
            stmt.setString(1, codParcela);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //LISTA PARCELA POR CODIGO
    public boolean checkCreateParForCop(String codParcela) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbparcela where tbcoparti_codCop=?");
            stmt.setString(1, codParcela);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //BUSCAR O MAIOR CÓDIGO DE PARCELA CADASTRADO NO BANCO
    public List<Parcela> readMaxCodPar() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT Max(codParcela) FROM tbparcela");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Parcela pa = new Parcela();
                pa.setCodParcela(rs.getInt("Max(codParcela)"));
                Parcela.add(pa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;
    }

    //INSERT DOS DADOS DA PARCELA NO BANCO
    public void createParcela(Parcela p) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbparcela(codParcela, tbcoparti_codCop, tbcoparti_tbfuncionarios_codFuncionario, valorParcela, dataVencParcela, statusParcela)" + "VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, p.getCodParcela());
            stmt.setInt(2, p.getFr_codCop());
            stmt.setInt(3, p.getFr_codFuncionario());
            stmt.setDouble(4, p.getValorParcela());
            stmt.setDate(5, p.getDataVencPar());
            stmt.setInt(6, p.getStatusParcela());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    public void updateGeradoParcela(Coparticipacao cop) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbcoparti SET geradoPar = ? WHERE codCop = ?");
            stmt.setInt(1, cop.getGeradoPar());
            stmt.setInt(2, cop.getCodCop());
            stmt.executeUpdate();

        } catch (SQLException error) {
            System.out.print("Erro ao gerar data da parcela:" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //ATUALIZA A DATA DA PARCELA NO BANCO BANCO, ISSO OCORRE QUANDO A COPARTICIPAÇÃO POSSUI MAIS DE UMA PARCELA
    public void updateDateParcela(Parcela pa) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbparcela SET dataVencParcela = ADDDATE(dataVencParcela, INTERVAL ? MONTH) WHERE codParcela = ?");
            stmt.setString(1, pa.getNumMesParcela());
            stmt.setInt(2, pa.getCodParcela());
            stmt.executeUpdate();

        } catch (SQLException error) {
            System.out.print("Erro ao gerar data da parcela:" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //LISTA TODOS AS PARCELAS CADASTRADOS NO BANCO
    public List<Parcela> readParcela() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                Parcela.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;
    }

    public List<Parcela> readParForDataAndPaga(Date dataInicial, Date dataFinal) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, codFuncionario, nomeFuncionario, codCop, valorParcela, dataVencParcela, statusParcela, DATEDIFF(NOW(), dataVencParcela ) as diasVencPar FROM tbparcela WHERE statusParcela = 2 AND dataVencParcela BETWEEN ? AND ?");
            stmt.setDate(1, (java.sql.Date) dataInicial);
            stmt.setDate(2, (java.sql.Date) dataFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                Parcela.add(par);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;

    }

    public List<Parcela> readParForDataAndNPaga(Date dataInicial, Date dataFinal) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, codFuncionario, nomeFuncionario, codCop, valorParcela, dataVencParcela, statusParcela, DATEDIFF(NOW(), dataVencParcela ) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun ON fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario JOIN tbcoparti cop ON cop.codCop = par.tbcoparti_codCop WHERE statusParcela = 1 AND dataVencParcela BETWEEN ? AND ?");
            stmt.setDate(1, (java.sql.Date) dataInicial);
            stmt.setDate(2, (java.sql.Date) dataFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                Parcela.add(par);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;

    }

    //LISTA PARCELAS COM STATUS DE NÃO PAGA(1) PELO CÓDIGO DO FUNCIONÁRIO
    //LISTA OS USUÁRIO PELO CÓDIGO DO FUNCIONÁRIO
    public List<Parcela> readParForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario WHERE codFuncionario = ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                Parcela.add(par);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;

    }

    public List<Parcela> readParForUserAndStatusPaga(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario WHERE codFuncionario = ? AND statusParcela = 2");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                Parcela.add(par);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;

    }

    //LISTA PARCELAS COM STATUS DE NÃO PAGA(1) PELO CÓDIGO DO FUNCIONÁRIO
    public List<Parcela> readParForUserAndStatusNPaga(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario WHERE codFuncionario = ? AND statusParcela = 1");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Parcela par = new Parcela();

                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                Parcela.add(par);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;

    }

    //LSITA TODAS AS PARCELAS COM STATUS PAGA(2)
    public List<Parcela> readParForStatusPaga() throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario WHERE statusParcela = 2");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));

                Parcela.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;
    }

    //LISTA TODAS AS PARCELAS COM O STATUS NÃO PAGA(1)
    public List<Parcela> readParForStatusNPaga() throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario WHERE statusParcela = 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                par.setStatusParcela(rs.getInt("statusParcela"));

                Parcela.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;
    }

    //SOMA VALOR TOTAL DAS PARCELAS POR CÓDIGO DO FUNCIONÁRIO
    public List<Parcela> readValorParForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT SUM(valorParcela) FROM tbparcela WHERE tbcoparti_tbfuncionarios_codFuncionario = ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();
            Parcela par = new Parcela();

            while (rs.next()) {
                par.setValorTotalPar(rs.getDouble("SUM(valorParcela)"));
                Parcela.add(par);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;

    }

    public List<Parcela> readParForStatusNPagaAlert() throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, tbcoparti_codCop, fun.codFuncionario, fun.nomeFuncionario, valorParcela, dataVencParcela , statusParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela par JOIN tbfuncionarios fun on fun.codFuncionario = par.tbcoparti_tbfuncionarios_codFuncionario WHERE statusParcela = 1 ORDER BY dataVencParcela");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setFr_codFuncionario(rs.getInt("codFuncionario"));
                par.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                par.setFr_codCop(rs.getInt("tbcoparti_codCop"));
                par.setValorParcela(rs.getDouble("valorParcela"));
                par.setDataVencPar(rs.getDate("dataVencParcela"));
                par.setStatusParcela(rs.getInt("statusParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                Parcela.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;
    }

    //ATUALIZA OS DADOS DA PARCELA
    public void updateParcela(Parcela p) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbparcela SET statusParcela = ? WHERE codParcela = ?");
            stmt.setInt(1, p.getStatusParcela());
            stmt.setInt(2, p.getCodParcela());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pagamento atualizado!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA A PARCELA
    public void delete(Parcela p) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbparcela WHERE codParcela=?");
            stmt.setInt(1, p.getCodParcela());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
