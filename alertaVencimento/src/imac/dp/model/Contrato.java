/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.model;

import java.sql.Date;

/**
 *
 * @author Suporte-01
 */
public class Contrato {

    private int codContra;
    private int diasRestantes30dd;
    private int diasRestantes90dd;
    private int statusContra;

    public Contrato() {
    }

    public int getCodContra() {
        return codContra;
    }

    public void setCodContra(int codContra) {
        this.codContra = codContra;
    }

    public int getDiasRestantes30dd() {
        return diasRestantes30dd;
    }

    public void setDiasRestantes30dd(int diasRestantes30dd) {
        this.diasRestantes30dd = diasRestantes30dd;
    }

    public int getDiasRestantes90dd() {
        return diasRestantes90dd;
    }

    public void setDiasRestantes90dd(int diasRestantes90dd) {
        this.diasRestantes90dd = diasRestantes90dd;
    }

    public int getStatusContra() {
        return statusContra;
    }

    public void setStatusContra(int statusContra) {
        this.statusContra = statusContra;
    }

    @Override
    public String toString() {
        return "Contrato{" + "codContra=" + codContra + ", diasRestantes30dd=" + diasRestantes30dd + ", diasRestantes90dd=" + diasRestantes90dd + ", statusContra=" + statusContra + '}';
    }

}
