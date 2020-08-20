/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Funcionario;
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
public class FuncionarioDao {

    //VALIDA O CPF DO FUNIONÁRIO (VERIFICA SE O MESMO NÃO EXISTE NO BANCO, PARA NÃO EXISTIR MAIS DE UM FUNIONÁRIO COM O MEMO CPF)
    public boolean checkCpf(String cpfFuncionario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbfuncionarios where cpfFuncionario=?");
            stmt.setString(1, cpfFuncionario);
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

    //INSERT DO FUNIONÁRIO NO BANCO
    public void createFuncionario(Funcionario f) throws ClassNotFoundException {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbfuncionarios(codFuncionario,nomeFuncionario, cpfFuncionario, setorFuncionario, cargoFuncionario, turnoFuncionario)" + "VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, f.getCodFuncionario());
            stmt.setString(2, f.getNomeFuncionario());
            stmt.setString(3, f.getCpfFuncionario());
            stmt.setString(4, f.getSetorFuncionario());
            stmt.setString(5, f.getCargoFuncionario());
            stmt.setString(6, f.getTurnoFuncionario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
    }

    //VERIFICA SE O CÓDIGO DO FUNCIONÁRIO NÃO EXISTE
    public boolean checkCod(String codFuncionario) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("Select * from tbfuncionarios where codFuncionario=?");
            stmt.setString(1, codFuncionario);
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

    //LISTA TODOS FUNCIONÁRIOS
    public List<Funcionario> readFuncionario() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> Funcionario = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbFuncionarios");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario fu = new Funcionario();
                fu.setCodFuncionario(rs.getInt("codFuncionario"));
                fu.setNomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setCpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setSetorFuncionario(rs.getString("setorFuncionario"));
                fu.setCargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setTurnoFuncionario(rs.getString("turnoFuncionario"));

                Funcionario.add(fu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Funcionario;
    }

    //LISTA FUNCIONÁRIO PELO NOME
    public List<Funcionario> readFuncionarioForDesc(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> Funcionario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbfuncionarios WHERE nomeFuncionario LIKE ?");
            stmt.setString(1, desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario fu = new Funcionario();

                fu.setCodFuncionario(rs.getInt("codFuncionario"));
                fu.setNomeFuncionario(rs.getString("nomeFuncionario"));
                fu.setCpfFuncionario(rs.getString("cpfFuncionario"));
                fu.setSetorFuncionario(rs.getString("setorFuncionario"));
                fu.setCargoFuncionario(rs.getString("cargoFuncionario"));
                fu.setTurnoFuncionario(rs.getString("turnoFuncionario"));

                Funcionario.add(fu);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar Funcionario" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Funcionario;

    }

    //LISTA FUNCIONÁRIO PELO CÓDIGO
    public List<Funcionario> readFuncionarioForCod(String desc) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> Funcionario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbfuncionarios WHERE codFuncionario LIKE ?");
            stmt.setInt(1, Integer.parseInt(desc));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();

                f.setCodFuncionario(rs.getInt("codFuncionario"));
                f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                f.setCpfFuncionario(rs.getString("cpfFuncionario"));
                f.setSetorFuncionario(rs.getString("setorFuncionario"));
                f.setCargoFuncionario(rs.getString("cargoFuncionario"));
                f.setTurnoFuncionario(rs.getString("turnoFuncionario"));
                Funcionario.add(f);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao buscar funcionario" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Funcionario;

    }

    //ATUALIZA O FUNCIONÁRIO
    public void updateFuncionario(Funcionario f) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbfuncionarios SET nomeFuncionario = ?, cpfFuncionario = ?, setorFuncionario= ?, cargoFuncionario= ?, turnoFuncionario = ? WHERE codFuncionario= ?");
            stmt.setString(1, f.getNomeFuncionario());
            stmt.setString(2, f.getCpfFuncionario());
            stmt.setString(3, f.getSetorFuncionario());
            stmt.setString(4, f.getCargoFuncionario());
            stmt.setString(5, f.getTurnoFuncionario());
            stmt.setInt(6, f.getCodFuncionario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException error) {
            System.out.print("Erro ao atualizar" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //DELETA O FUNCIONÁRIO
    public void deleteFuncionario(Funcionario f) throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbfuncionarios WHERE codFuncionario =?");
            stmt.setInt(1, f.getCodFuncionario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException error) {
            System.out.println("Erro ao excluir" + error);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
