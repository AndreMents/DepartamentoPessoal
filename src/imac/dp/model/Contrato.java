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
    private int fr_codFuncionario;
    private String fr_nomeFuncionario;
    private Date dataAdmiContra;
    private String responsContra;
    private String observaContra;
    private Date dataVenc30dd;
    private Date dataVenc90dd;
    private int statusContra;
    private int statusContra30dd;
    private int statusContra90dd;
    private int diasRestantes30dd;
    private int diasRestantes90dd;

    public Contrato() {
    }

    public int getCodContra() {
        return codContra;
    }

    public void setCodContra(int codContra) {
        this.codContra = codContra;
    }

    public int getFr_codFuncionario() {
        return fr_codFuncionario;
    }

    public void setFr_codFuncionario(int fr_codFuncionario) {
        this.fr_codFuncionario = fr_codFuncionario;
    }

    public String getFr_nomeFuncionario() {
        return fr_nomeFuncionario;
    }

    public void setFr_nomeFuncionario(String fr_nomeFuncionario) {
        this.fr_nomeFuncionario = fr_nomeFuncionario;
    }

    public Date getDataAdmiContra() {
        return dataAdmiContra;
    }

    public void setDataAdmiContra(Date dataAdmiContra) {
        this.dataAdmiContra = dataAdmiContra;
    }

    public String getResponsContra() {
        return responsContra;
    }

    public void setResponsContra(String responsContra) {
        this.responsContra = responsContra;
    }

    public String getObservaContra() {
        return observaContra;
    }

    public void setObservaContra(String observaContra) {
        this.observaContra = observaContra;
    }

    public Date getDataVenc30dd() {
        return dataVenc30dd;
    }

    public void setDataVenc30dd(Date dataVenc30dd) {
        this.dataVenc30dd = dataVenc30dd;
    }

    public Date getDataVenc90dd() {
        return dataVenc90dd;
    }

    public void setDataVenc90dd(Date dataVenc90dd) {
        this.dataVenc90dd = dataVenc90dd;
    }

    public int getStatusContra() {
        return statusContra;
    }

    public void setStatusContra(int statusContra) {
        this.statusContra = statusContra;
    }

    public int getStatusContra30dd() {
        return statusContra30dd;
    }

    public void setStatusContra30dd(int statusContra30dd) {
        this.statusContra30dd = statusContra30dd;
    }

    public int getStatusContra90dd() {
        return statusContra90dd;
    }

    public void setStatusContra90dd(int statusContra90dd) {
        this.statusContra90dd = statusContra90dd;
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

    @Override
    public String toString() {
        return "Contrato{" + "codContra=" + codContra + ", fr_codFuncionario=" + fr_codFuncionario + ", fr_nomeFuncionario=" + fr_nomeFuncionario + ", dataAdmiContra=" + dataAdmiContra + ", responsContra=" + responsContra + ", observaContra=" + observaContra + ", dataVenc30dd=" + dataVenc30dd + ", dataVenc90dd=" + dataVenc90dd + ", statusContra=" + statusContra + ", statusContra30dd=" + statusContra30dd + ", statusContra90dd=" + statusContra90dd + ", diasRestantes30dd=" + diasRestantes30dd + ", diasRestantes90dd=" + diasRestantes90dd + '}';
    }

}
