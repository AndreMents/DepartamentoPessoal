/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Contrato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suporte-01
 */
public class ContratoDao {

    public List<Contrato> readContrato() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Contrato> Contrato = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT DATEDIFF(NOW(), dataVenc30dd)  as dataVenc30dd , DATEDIFF(NOW(), dataVenc90dd) as dataVenc90dd FROM tbcontrato WHERE statusContra = 2");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Contrato par = new Contrato();
                par.setDiasRestantes30dd(rs.getInt("dataVenc30dd"));
                par.setDiasRestantes90dd(rs.getInt("dataVenc90dd"));
                Contrato.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Contrato;
    }

}
