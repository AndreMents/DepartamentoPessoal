/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.model;

import java.sql.Date;

/**
 *
 * @author @andre_ments
 */
public class Cotista {

    private int codCota;
    private int fr_codFuncionario;
    private String fr_nomeFuncionario;
    private String fr_cpfFuncionario;
    private String fr_cargoFuncionario;
    private Date dataAdmi;
    private Date dataVenci;
    private int diasVencidos;
    private String observa;

    public Cotista() {

    }

    public int getCodCota() {
        return codCota;
    }

    public void setCodCota(int codCota) {
        this.codCota = codCota;
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

    public String getFr_cpfFuncionario() {
        return fr_cpfFuncionario;
    }

    public void setFr_cpfFuncionario(String fr_cpfFuncionario) {
        this.fr_cpfFuncionario = fr_cpfFuncionario;
    }

    public String getFr_cargoFuncionario() {
        return fr_cargoFuncionario;
    }

    public void setFr_cargoFuncionario(String fr_cargoFuncionario) {
        this.fr_cargoFuncionario = fr_cargoFuncionario;
    }

    public Date getDataAdmi() {
        return dataAdmi;
    }

    public void setDataAdmi(Date dataAdmi) {
        this.dataAdmi = dataAdmi;
    }

    public Date getDataVenci() {
        return dataVenci;
    }

    public void setDataVenci(Date dataVenci) {
        this.dataVenci = dataVenci;
    }

    public int getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
    }

    public String getObserva() {
        return observa;
    }

    public void setObserva(String observa) {
        this.observa = observa;
    }

    @Override
    public String toString() {
        return "Cotista{" + "codCota=" + codCota + ", fr_codFuncionario=" + fr_codFuncionario + ", fr_nomeFuncionario=" + fr_nomeFuncionario + ", fr_cpfFuncionario=" + fr_cpfFuncionario + ", fr_cargoFuncionario=" + fr_cargoFuncionario + ", dataAdmi=" + dataAdmi + ", dataVenci=" + dataVenci + ", diasVencidos=" + diasVencidos + ", observa=" + observa + '}';
    }

}
