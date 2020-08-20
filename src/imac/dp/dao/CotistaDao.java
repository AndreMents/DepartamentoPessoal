/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Cotista;
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
public class CotistaDao {

    //VERIFICA SE JÁ EXISTE O CADASTRO DO FUNCIONÁRIO PARA A EMPRESA INFORMADA
    public boolean checkCadCotista(String codFuncionario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbcotista WHERE tbfuncionarios_codFuncionario = ?");
            stmt.setString(1, codFuncionario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CotistaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    //VALIDA O CÓDIGO DO FUNCIONÁRIO (VERIFICA SE O CADASTRO EXISTE NO BANCO)
    public boolean checkFuncionario(String codFuncionario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbcotista WHERE tbfuncionarios_codFuncionario = ?");
            stmt.setString(1, codFuncionario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CotistaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

    public List<Cotista> readMaiorCodCota() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT Max(codCota) FROM tbcotista");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cotista pa = new Cotista();
                pa.setCodCota(rs.getInt("Max(codCota)") + 1);
                Cotista.add(pa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoparticipacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Cotista;
    }

    //CADASTRA INTEGRAÇÃO
    public void createCota(Cotista i) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tbcotista (codCota, tbfuncionarios_codFuncionario,  dataAdmi, dataVenci, observa) VALUES  (?,?,?,?,?)");
            stmt.setInt(1, i.getCodCota());
            stmt.setInt(2, i.getFr_codFuncionario());
            stmt.setDate(3, i.getDataAdmi());
            stmt.setDate(4, i.getDataVenci());
            stmt.setString(5, i.getObserva());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //BUSCA AS INTEGRAÇÕES CADASTRADAS, E TRAZ A INFORMAÇÃO DE DIAS EM ATRASO PELO DATEDIFF
    public List<Cotista> readCotista() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codCota, codFuncionario, nomeFuncionario, cpfFuncionario, cargoFuncionario, dataAdmi, dataVenci, observa, DATEDIFF(NOW(), dataVenci) as dataVencimento FROM tbcotista cot JOIN tbfuncionarios fun ON fun.codFuncionario = cot.tbfuncionarios_codFuncionario ORDER BY dataVenci");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cotista co = new Cotista();
                co.setCodCota(rs.getInt("codCota"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setDataAdmi(rs.getDate("dataAdmi"));
                co.setDataVenci(rs.getDate("dataVenci"));
                co.setDiasVencidos(rs.getInt("dataVencimento"));
                co.setObserva(rs.getString("observa"));

                Cotista.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CotistaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Cotista;
    }

    //LISTA AS INTEGRAÇÕES QUE ESTÃO VENCIDAS
    public List<Cotista> readCotistaVencidas() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codCota, codFuncionario, nomeFuncionario, cpfFuncionario,  cargoFuncionario, dataAdmi, dataVenci, DATEDIFF(NOW(), dataVenci) as dataVencimento FROM tbcotista cota JOIN tbfuncionarios fun ON fun.codFuncionario = cota.tbfuncionarios_codFuncionario  WHERE dataVenci BETWEEN CURDATE() - INTERVAL 365 DAY AND CURDATE() ORDER BY dataVenci");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cotista co = new Cotista();
                co.setCodCota(rs.getInt("codCota"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setDataAdmi(rs.getDate("dataAdmi"));
                co.setDataVenci(rs.getDate("dataVenci"));
                co.setDiasVencidos(rs.getInt("dataVencimento"));

                Cotista.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CotistaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Cotista;
    }

    public List<Cotista> readCotaForData(Date dataInicial, Date dataFinal) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codCota, codFuncionario, nomeFuncionario, cpfFuncionario,  cargoFuncionario, dataAdmi, dataVenci, DATEDIFF(NOW(), dataVenci) as dataVencimento FROM tbcotista cota JOIN tbfuncionarios fun ON fun.codFuncionario = cota.tbfuncionarios_codFuncionario WHERE dataVenci BETWEEN ? AND ? ORDER BY dataVenci");
            stmt.setDate(1, (java.sql.Date) dataInicial);
            stmt.setDate(2, (java.sql.Date) dataFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cotista co = new Cotista();
                co.setCodCota(rs.getInt("codCota"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setDataAdmi(rs.getDate("dataAdmi"));
                co.setDataVenci(rs.getDate("dataVenci"));
                co.setDiasVencidos(rs.getInt("dataVencimento"));
                Cotista.add(co);
            }

        } catch (SQLException error) {

            System.out.println("Erro ao buscar Parcela" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Cotista;
    }

    //LISTA INTEGRAÇÃO PELO CÓDIGO DO FUNCIONÁRIO
    public List<Cotista> readCotistaForFuncionario(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codCota, codFuncionario, nomeFuncionario, cpfFuncionario, cargoFuncionario, dataAdmi, dataVenci, observa, DATEDIFF(NOW(), dataVenci) as dataVencimento FROM tbcotista cot JOIN tbfuncionarios fun ON fun.codFuncionario = cot.tbfuncionarios_codFuncionario WHERE nomeFuncionario LIKE ? ORDER BY dataVenci");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cotista co = new Cotista();
                co.setCodCota(rs.getInt("codCota"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setDataAdmi(rs.getDate("dataAdmi"));
                co.setDataVenci(rs.getDate("dataVenci"));
                co.setDiasVencidos(rs.getInt("dataVencimento"));

                Cotista.add(co);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar Cotista" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Cotista;

    }

    //LISTA TODAS AS INTEGRAÇÕES
    public List<Cotista> readCotista(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codCota, codFuncionario, nomeFuncionario, cpfFuncionario, cargoFuncionario, dataAdmi, dataVenci,  DATEDIFF(NOW(), dataVenci) as dataVencimento FROM tbcotista WHERE nomeFuncionario LIKE ? ORDER BY dataVenci");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cotista co = new Cotista();
                co.setCodCota(rs.getInt("codCota"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setDataAdmi(rs.getDate("dataAdmi"));
                co.setDataVenci(rs.getDate("dataVenci"));
                co.setDiasVencidos(rs.getInt("dataVencimento"));

                Cotista.add(co);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar Cotista" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Cotista;

    }

    public List<Cotista> readCotaForCod(String desc) throws ClassNotFoundException, SQLException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT codCota, codFuncionario, nomeFuncionario, cpfFuncionario, cargoFuncionario, dataAdmi, dataVenci, observa, DATEDIFF(NOW(), dataVenci) as dataVencimento FROM tbcotista WHERE codCota LIKE ?");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cotista co = new Cotista();
                co.setCodCota(rs.getInt("codCota"));
                co.setFr_codFuncionario(rs.getInt("codFuncionario"));
                co.setFr_nomeFuncionario(rs.getString("nomeFuncionario"));
                co.setFr_cpfFuncionario(rs.getString("cpfFuncionario"));
                co.setFr_cargoFuncionario(rs.getString("cargoFuncionario"));
                co.setDataAdmi(rs.getDate("dataAdmi"));
                co.setDataVenci(rs.getDate("dataVenci"));
                co.setDiasVencidos(rs.getInt("dataVencimento"));
                co.setObserva(rs.getString("observa"));

                Cotista.add(co);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar Cotista" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return Cotista;

    }

    public List<Cotista> readObservaForCod(String desc) throws ClassNotFoundException, SQLException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cotista> Cotista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT observa FROM tbcotista WHERE codCota LIKE ?");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cotista co = new Cotista();
                co.setObserva(rs.getString("observa"));

                Cotista.add(co);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar Observação" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return Cotista;

    }

    //ATUALIZA INTEGRAÇÃO
    public void updateCotista(Cotista i) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbcotista SET  dataAdmi = ?, dataVenci = ?, observa = ? WHERE codCota = ?");
            stmt.setDate(1, i.getDataAdmi());
            stmt.setDate(2, i.getDataVenci());
            stmt.setString(3, i.getObserva());
            stmt.setInt(4, i.getCodCota());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA A INTEGRAÇÃO
    public void deleteCotista(Cotista i) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbcotista WHERE codCota =?");
            stmt.setInt(1, i.getCodCota());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
