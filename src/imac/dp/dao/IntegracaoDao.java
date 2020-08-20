/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Integracao;
import java.sql.Date;
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
public class IntegracaoDao {

    //VERIFICA SE JÁ EXISTE O CADASTRO DO FUNCIONÁRIO PARA A EMPRESA INFORMADA
    public boolean checkCadIntegra(String fr_codFuncionario, String fr_codEmpresa) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbintegra WHERE tbfuncionarios_codFuncionario = ? AND tbempresas_codEmp = ?");
            stmt.setString(1, fr_codFuncionario);
            stmt.setString(2, fr_codEmpresa);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //VALIDA O CÓDIGO DO FUNCIONÁRIO (VERIFICA SE O CADASTRO EXISTE NO BANCO)
    public boolean checkFuncionario(String fr_codFuncionario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbintegra WHERE tbfuncionarios_codFuncionario = ?");
            stmt.setString(1, fr_codFuncionario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //VALIDA CÓDIGO DA EMPRESA (VERIFICA SE O CADASTRO EXISTE NO BANCO)
    public boolean checkEmpresa(String fr_codEmpresa) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbintegra WHERE tbempresas_codEmp = ? ");
            stmt.setString(1, fr_codEmpresa);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //CADASTRA INTEGRAÇÃO
    public void createIntegracao(Integracao i) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tbintegra (codIntegra, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, tbfuncionarios_codFuncionario, tbempresas_codEmp) VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, i.getCodInt());
            stmt.setDate(2, i.getDataUltiInt());
            stmt.setDate(3, i.getDataVencInt());
            stmt.setDate(4, i.getDataUltiAso());
            stmt.setDate(5, i.getDataVencAso());
            stmt.setInt(6, i.getFr_codFuncionario());
            stmt.setInt(7, i.getFr_codEmpresa());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //BUSCA AS INTEGRAÇÕES CADASTRADAS, E TRAZ A INFORMAÇÃO DE DIAS EM ATRASO PELO DATEDIFF
    public List<Integracao> readIntegracao() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codIntegra, codFuncionario, nomeFuncionario, cpfFuncionario, setorFuncionario, cargoFuncionario, turnoFuncionario, codEmp, nomeEmp, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, DATEDIFF(NOW(), dataVencIntegra) as diasVenci FROM tbintegra inte JOIN tbfuncionarios fun ON fun.codFuncionario = inte.tbfuncionarios_codFuncionario JOIN tbempresas emp ON emp.codEmp = inte.tbempresas_codEmp ORDER BY dataVencIntegra");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integracao fu = new Integracao();
                fu.setCodInt(rs.getInt("codIntegra"));
                fu.setFr_codFuncionario(rs.getInt("codFuncionario"));
                fu.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                fu.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setFr_turnoFuncionario(rs.getString("turnoFuncionario"));
                fu.setFr_codEmpresa(rs.getInt("codEmp"));
                fu.setFr_nomeEmpresa(rs.getString("nomeEmp"));
                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
                fu.setDataVencAso(rs.getDate("dataVencAso"));
                fu.setDiasVencidos(rs.getInt("diasVenci"));

                Integracao.add(fu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;
    }

    //LISTA AS INTEGRAÇÕES QUE ESTÃO VENCIDAS
    public List<Integracao> readIntegracaoVencidas() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codIntegra, codFuncionario, nomeFuncionario, cpfFuncionario, setorFuncionario, cargoFuncionario, turnoFuncionario, codEmp, nomeEmp, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, DATEDIFF(NOW(), dataVencIntegra) as diasVenci FROM tbintegra inte JOIN tbfuncionarios fun ON fun.codFuncionario = inte.tbfuncionarios_codFuncionario JOIN tbempresas emp ON emp.codEmp = inte.tbempresas_codEmp WHERE dataVencIntegra BETWEEN CURDATE() - INTERVAL 365 DAY AND CURDATE() ORDER BY dataVencIntegra");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integracao fu = new Integracao();
                fu.setCodInt(rs.getInt("codIntegra"));
                fu.setFr_codFuncionario(rs.getInt("codFuncionario"));
                fu.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                fu.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setFr_turnoFuncionario(rs.getString("turnoFuncionario"));
                fu.setFr_codEmpresa(rs.getInt("codEmp"));
                fu.setFr_nomeEmpresa(rs.getString("nomeEmp"));
                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
                fu.setDataVencAso(rs.getDate("dataVencAso"));
                fu.setDiasVencidos(rs.getInt("diasVenci"));

                Integracao.add(fu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;
    }

    //LISTA INTEGRAÇÃO PARA CONFERÊNCIA NA TELA PRINCIPAL DE CADASTRO DE INTEGRAÇÃO
    public List<Integracao> readIntegracaoFront() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codIntegra, codFuncionario, nomeFuncionario, cpfFuncionario, setorFuncionario, cargoFuncionario, turnoFuncionario, codEmp, nomeEmp, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, DATEDIFF(NOW(), dataVencIntegra) as diasVenci FROM tbintegra inte JOIN tbfuncionarios fun ON fun.codFuncionario = inte.tbfuncionarios_codFuncionario JOIN tbempresas emp ON emp.codEmp = inte.tbempresas_codEmp ORDER BY dataVencIntegra");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integracao fu = new Integracao();
                fu.setCodInt(rs.getInt("codIntegra"));
                fu.setFr_codFuncionario(rs.getInt("codFuncionario"));
                fu.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                fu.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setFr_turnoFuncionario(rs.getString("turnoFuncionario"));
                fu.setFr_codEmpresa(rs.getInt("codEmp"));
                fu.setFr_nomeEmpresa(rs.getString("nomeEmp"));
                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
                fu.setDataVencAso(rs.getDate("dataVencAso"));
                fu.setDiasVencidos(rs.getInt("diasVenci"));

                Integracao.add(fu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;
    }

    //LISTA INTEGRAÇÃO PELO CÓDIGO DO FUNCIONÁRIO
    public List<Integracao> readIntegracaoForFuncionario(Integer desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codIntegra, codFuncionario, nomeFuncionario, cpfFuncionario, setorFuncionario, cargoFuncionario, turnoFuncionario, codEmp, nomeEmp, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, DATEDIFF(NOW(), dataVencIntegra) as diasVenci FROM tbintegra inte JOIN tbfuncionarios fun ON fun.codFuncionario = inte.tbfuncionarios_codFuncionario JOIN tbempresas emp ON emp.codEmp = inte.tbempresas_codEmp WHERE codFuncionario = ? ORDER BY dataVencIntegra");
            stmt.setInt(1, desc);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integracao fu = new Integracao();
                fu.setCodInt(rs.getInt("codIntegra"));
                fu.setFr_codFuncionario(rs.getInt("codFuncionario"));
                fu.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                fu.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setFr_turnoFuncionario(rs.getString("turnoFuncionario"));
                fu.setFr_codEmpresa(rs.getInt("codEmp"));
                fu.setFr_nomeEmpresa(rs.getString("nomeEmp"));
                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
                fu.setDataVencAso(rs.getDate("dataVencAso"));
                fu.setDiasVencidos(rs.getInt("diasVenci"));

                Integracao.add(fu);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar Integracao" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;

    }

    //LISTA TODAS AS INTEGRAÇÕES
//    public List<Integracao> readIntegracao(String desc) throws ClassNotFoundException {
//        java.sql.Connection con = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<Integracao> Integracao = new ArrayList<>();
//
//        try {
//            stmt = con.prepareStatement("SELECT codIntegra, fr_codFuncionario, fr_nomeFuncionario, fr_cpfFuncionario, fr_setorFuncionario, fr_cargoFuncionario, fr_turnoFuncionario, fr_codEmpresa, fr_nomeEmpresa, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, DATEDIFF(NOW(), dataVencIntegra) as diasVenci FROM tbintegra WHERE fr_nomeFuncionario LIKE ? ORDER BY dataVencIntegra");
//            stmt.setString(1, desc + "%");
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Integracao fu = new Integracao();
//                fu.setCodInt(rs.getInt("codIntegra"));
//                fu.setFr_codFuncionario(rs.getInt("fr_codFuncionario"));
//                fu.setFr_nomeFuncionario(rs.getString("fr_nomeFuncionario"));
//                fu.setFr_cpfFuncionario(rs.getString("fr_cpfFuncionario"));
//                fu.setFr_setorFuncionario(rs.getString("fr_setorFuncionario"));
//                fu.setFr_cargoFuncionario(rs.getString("fr_cargoFuncionario"));
//                fu.setFr_turnoFuncionario(rs.getString("fr_turnoFuncionario"));
//                fu.setFr_codEmpresa(rs.getInt("fr_codEmpresa"));
//                fu.setFr_nomeEmpresa(rs.getString("fr_nomeEmpresa"));
//                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
//                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
//                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
//                fu.setDataVencAso(rs.getDate("dataVencAso"));
//                fu.setDiasVencidos(rs.getInt("diasVenci"));
//
//                Integracao.add(fu);
//            }
//        } catch (SQLException error) {
//            System.out.println("Erro ao buscar Integracao" + error);
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt, rs);
//        }
//        return Integracao;
//
//    }
    //LISTA AS INTEGRAÇÕES PELO CÓDIGO DA EMPRESA
    public List<Integracao> readIntegracaoForCodEmpresa(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbintegra inte JOIN tbfuncionarios fun ON fun.codFuncionario = inte.tbfuncionarios_codFuncionario JOIN tbempresas emp ON emp.codEmp = inte.tbempresas_codEmp WHERE codEmp LIKE ? ORDER BY dataVencIntegra");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integracao fu = new Integracao();
                fu.setCodInt(rs.getInt("codIntegra"));
                fu.setFr_codFuncionario(rs.getInt("codFuncionario"));
                fu.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                fu.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setFr_turnoFuncionario(rs.getString("turnoFuncionario"));
                fu.setFr_codEmpresa(rs.getInt("codEmp"));
                fu.setFr_nomeEmpresa(rs.getString("nomeEmp"));
                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
                fu.setDataVencAso(rs.getDate("dataVencAso"));
                fu.setDiasVencidos(rs.getInt("diasVenci"));
                Integracao.add(fu);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar integracao" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;

    }

    public List<Integracao> readParForData(Date dataInicial, Date dataFinal) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codIntegra, codFuncionario, nomeFuncionario, cpfFuncionario, setorFuncionario, cargoFuncionario, turnoFuncionario, codEmp, nomeEmp, dataUltiIntegra, dataVencIntegra, dataUltiAso, dataVencAso, DATEDIFF(NOW(), dataVencIntegra) as diasVenci FROM tbintegra inte JOIN tbfuncionarios fun ON fun.codFuncionario = inte.tbfuncionarios_codFuncionario JOIN tbempresas emp ON emp.codEmp = inte.tbempresas_codEmp WHERE dataVencIntegra BETWEEN ? AND ? ORDER BY dataVencIntegra");
            stmt.setDate(1, (java.sql.Date) dataInicial);
            stmt.setDate(2, (java.sql.Date) dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integracao fu = new Integracao();
                fu.setCodInt(rs.getInt("codIntegra"));
                fu.setFr_codFuncionario(rs.getInt("codFuncionario"));
                fu.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setFr_setorFuncionario(rs.getString("setorFuncionario"));
                fu.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setFr_turnoFuncionario(rs.getString("turnoFuncionario"));
                fu.setFr_codEmpresa(rs.getInt("codEmp"));
                fu.setFr_nomeEmpresa(rs.getString("nomeEmp"));
                fu.setDataUltiInt(rs.getDate("dataUltiIntegra"));
                fu.setDataVencInt(rs.getDate("dataVencIntegra"));
                fu.setDataUltiAso(rs.getDate("dataUltiAso"));
                fu.setDataVencAso(rs.getDate("dataVencAso"));
                fu.setDiasVencidos(rs.getInt("diasVenci"));
                Integracao.add(fu);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;

    }

    //ATUALIZA INTEGRAÇÃO
    public void updateIntegracao(Integracao i) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbintegra SET dataUltiIntegra = ?, dataVencIntegra = ?, dataUltiAso= ?, dataVencAso= ? WHERE codIntegra = ?");
            stmt.setDate(1, i.getDataUltiInt());
            stmt.setDate(2, i.getDataVencInt());
            stmt.setDate(3, i.getDataUltiAso());
            stmt.setDate(4, i.getDataVencAso());
            stmt.setInt(5, i.getCodInt());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA A INTEGRAÇÃO
    public void deleteIntegracao(Integracao i) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbintegra WHERE codIntegra =?");
            stmt.setInt(1, i.getCodInt());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
