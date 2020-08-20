/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.dao;

import imac.dp.model.Parcela;
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
public class ParcelaDao {

    //LISTA TODOS AS PARCELAS CADASTRADOS NO BANCO
    public List<Parcela> readParcela() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Parcela> Parcela = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT codParcela, DATEDIFF(NOW(), dataVencParcela) as diasVencPar FROM tbparcela WHERE statusParcela = 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Parcela par = new Parcela();
                par.setCodParcela(rs.getInt("codParcela"));
                par.setDiasAtrasoPar(rs.getInt("diasVencPar"));
                Parcela.add(par);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Parcela;
    }

}
