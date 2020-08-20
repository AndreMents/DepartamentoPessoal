/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Integracao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author @andre_ments
 */
public class IntegracaoDao {

    public List<Integracao> readIntegracao() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integracao> Integracao = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codIntegra, DATEDIFF(NOW(), dataVencIntegra) as dataVencimento FROM tbintegra");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integracao par = new Integracao();
                par.setDiasVencidos(rs.getInt("dataVencimento"));
                Integracao.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Integracao;
    }
}
