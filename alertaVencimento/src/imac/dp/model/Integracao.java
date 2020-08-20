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
public class Integracao {

    private int codInt;
    private int diasVencidos;

    public Integracao() {

    }

    public int getCodInt() {
        return codInt;
    }

    public void setCodInt(int codInt) {
        this.codInt = codInt;
    }

    public int getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
    }

    @Override
    public String toString() {
        return "Integracao{" + "codInt=" + codInt + ", diasVencidos=" + diasVencidos + '}';
    }

}
