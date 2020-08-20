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
public class Integracao {

    private int codInt;
    private int fr_codFuncionario;
    private String fr_nomeFuncionario;
    private String fr_cpfFuncionario;
    private String fr_cargoFuncionario;
    private String fr_setorFuncionario;
    private String fr_turnoFuncionario;
    private int fr_codEmpresa;
    private String fr_nomeEmpresa;
    private Date dataUltiInt;
    private Date dataVencInt;
    private Date dataUltiAso;
    private Date dataVencAso;
    private int diasVencidos;

    public Integracao() {

    }

    public int getCodInt() {
        return codInt;
    }

    public void setCodInt(int codInt) {
        this.codInt = codInt;
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

    public String getFr_setorFuncionario() {
        return fr_setorFuncionario;
    }

    public void setFr_setorFuncionario(String fr_setorFuncionario) {
        this.fr_setorFuncionario = fr_setorFuncionario;
    }

    public String getFr_turnoFuncionario() {
        return fr_turnoFuncionario;
    }

    public void setFr_turnoFuncionario(String fr_turnoFuncionario) {
        this.fr_turnoFuncionario = fr_turnoFuncionario;
    }

    public int getFr_codEmpresa() {
        return fr_codEmpresa;
    }

    public void setFr_codEmpresa(int fr_codEmpresa) {
        this.fr_codEmpresa = fr_codEmpresa;
    }

    public String getFr_nomeEmpresa() {
        return fr_nomeEmpresa;
    }

    public void setFr_nomeEmpresa(String fr_nomeEmpresa) {
        this.fr_nomeEmpresa = fr_nomeEmpresa;
    }

    public Date getDataUltiInt() {
        return dataUltiInt;
    }

    public void setDataUltiInt(Date dataUltiInt) {
        this.dataUltiInt = dataUltiInt;
    }

    public Date getDataVencInt() {
        return dataVencInt;
    }

    public void setDataVencInt(Date dataVencInt) {
        this.dataVencInt = dataVencInt;
    }

    public Date getDataUltiAso() {
        return dataUltiAso;
    }

    public void setDataUltiAso(Date dataUltiAso) {
        this.dataUltiAso = dataUltiAso;
    }

    public Date getDataVencAso() {
        return dataVencAso;
    }

    public void setDataVencAso(Date dataVencAso) {
        this.dataVencAso = dataVencAso;
    }

    public int getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
    }

    @Override
    public String toString() {
        return "Integracao{" + "codInt=" + codInt + ", fr_codFuncionario=" + fr_codFuncionario + ", fr_nomeFuncionario=" + fr_nomeFuncionario + ", fr_cpfFuncionario=" + fr_cpfFuncionario + ", fr_cargoFuncionario=" + fr_cargoFuncionario + ", fr_setorFuncionario=" + fr_setorFuncionario + ", fr_turnoFuncionario=" + fr_turnoFuncionario + ", fr_codEmpresa=" + fr_codEmpresa + ", fr_nomeEmpresa=" + fr_nomeEmpresa + ", dataUltiInt=" + dataUltiInt + ", dataVencInt=" + dataVencInt + ", dataUltiAso=" + dataUltiAso + ", dataVencAso=" + dataVencAso + ", diasVencidos=" + diasVencidos + '}';
    }

}
