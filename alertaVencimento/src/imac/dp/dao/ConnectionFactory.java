/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author @andre_ments
 */
public class ConnectionFactory {

    public static Connection con;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bd_dp";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws ClassNotFoundException {
//        try {
//            Class.forName(DRIVER);
//            return (Connection) DriverManager.getConnection(URL, USER, PASS);
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new RuntimeException("Erro na conexao; "+ex.getMessage());
//        }
        try {
            System.setProperty("jdbc.Drivers", DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Atenção! \nHá um problema na conexão com o banco de dados, contate administrador do sistema.\n" + "Erro:  " + ex.getMessage());
        }
        return con;

    }

    public static Connection closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
