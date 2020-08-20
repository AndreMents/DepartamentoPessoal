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
public class Parcela {

    private int codParcela;
    private int diasAtrasoPar;
    private int diasVencPar;

    public Parcela() {

    }

    public int getCodParcela() {
        return codParcela;
    }

    public void setCodParcela(int codParcela) {
        this.codParcela = codParcela;
    }

    public int getDiasAtrasoPar() {
        return diasAtrasoPar;
    }

    public void setDiasAtrasoPar(int diasAtrasoPar) {
        this.diasAtrasoPar = diasAtrasoPar;
    }

    public int getDiasVencPar() {
        return diasVencPar;
    }

    public void setDiasVencPar(int diasVencPar) {
        this.diasVencPar = diasVencPar;
    }

    @Override
    public String toString() {
        return "Parcela{" + "codParcela=" + codParcela + ", diasAtrasoPar=" + diasAtrasoPar + ", diasVencPar=" + diasVencPar + '}';
    }

}
