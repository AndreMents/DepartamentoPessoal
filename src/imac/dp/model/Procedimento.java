/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.model;

/**
 *
 * @author @andre_ments
 */
public class Procedimento {

    private int codPro;
    private String nomePro;
    private double valorPro;
    private int parcelaPro;

    public Procedimento() {

    }

    public int getCodPro() {
        return codPro;
    }

    public void setCodPro(int codPro) {
        this.codPro = codPro;
    }

    public String getNomePro() {
        return nomePro;
    }

    public void setNomePro(String nomePro) {
        this.nomePro = nomePro;
    }

    public double getValorPro() {
        return valorPro;
    }

    public void setValorPro(double valorPro) {
        this.valorPro = valorPro;
    }

    public int getParcelaPro() {
        return parcelaPro;
    }

    public void setParcelaPro(int parcelaPro) {
        this.parcelaPro = parcelaPro;
    }

    @Override
    public String toString() {
        return "Procedimento{" + "codPro=" + codPro + ", nomePro=" + nomePro + ", valorPro=" + valorPro + ", parcelaPro=" + parcelaPro + '}';
    }

}
