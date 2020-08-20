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
public class Funcionario {

    private int codFuncionario;
    private String nomeFuncionario;
    private String cpfFuncionario;
    private String cargoFuncionario;
    private String setorFuncionario;
    private String turnoFuncionario;

    public Funcionario() {

    }

    public Funcionario(int codFuncionario, String nomeFuncionario, String cpfFuncionario, String cargoFuncionario, String setorFuncionario, String turnoFuncionario) {
        this.codFuncionario = codFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cpfFuncionario = cpfFuncionario;
        this.cargoFuncionario = cargoFuncionario;
        this.setorFuncionario = setorFuncionario;
        this.turnoFuncionario = turnoFuncionario;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public String getSetorFuncionario() {
        return setorFuncionario;
    }

    public void setSetorFuncionario(String setorFuncionario) {
        this.setorFuncionario = setorFuncionario;
    }

    public String getTurnoFuncionario() {
        return turnoFuncionario;
    }

    public void setTurnoFuncionario(String turnoFuncionario) {
        this.turnoFuncionario = turnoFuncionario;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "codFuncionario=" + codFuncionario + ", nomeFuncionario=" + nomeFuncionario + ", cpfFuncionario=" + cpfFuncionario + ", cargoFuncionario=" + cargoFuncionario + ", setorFuncionario=" + setorFuncionario + ", turnoFuncionario=" + turnoFuncionario + '}';
    }

}
