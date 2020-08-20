/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.ContratoDao;
import imac.dp.dao.IntegracaoDao;
import imac.dp.dao.ParcelaDao;
import imac.dp.model.Contrato;
import imac.dp.model.Integracao;
import imac.dp.model.Parcela;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte-01
 */
public class AlertaDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        ParcelaDao pdao = new ParcelaDao();

        for (Parcela pa : pdao.readParcela()) {
            int diasVenc = pa.getDiasAtrasoPar()*-1;

            if (diasVenc < 0) {
                java.awt.Desktop.getDesktop().open(new File("C:\\Hardsearch\\alertaMail\\parce_vencida\\alerta_parcela.py"));
                break;
            } else {

            }
        }

        IntegracaoDao idao = new IntegracaoDao();

        for (Integracao in : idao.readIntegracao()) {
            int diasVenc = in.getDiasVencidos()*-1;

            if (diasVenc < 30) {
                java.awt.Desktop.getDesktop().open(new File("C:\\Hardsearch\\alertaMail\\integra_vencida\\alerta_integra.py"));
                break;
            } else {

            }
        }
        ContratoDao cdao = new ContratoDao();

        for (Contrato pa : cdao.readContrato()) {
            int diasVenc30 = (pa.getDiasRestantes30dd()*-1);
            int diasVenc90 = (pa.getDiasRestantes90dd()*-1);

            if (diasVenc30 < 0 || diasVenc90 < 0) {
                
                java.awt.Desktop.getDesktop().open(new File("C:\\Hardsearch\\alertaMail\\contra_a_vencer\\alerta_contra.py"));
                break;
            } else {

            }
        }

    }
}
