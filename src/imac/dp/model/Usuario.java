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
public class Usuario {

    private int codUsuario;
    private String nomeUsuario;
    private String setorUsuario;
    private String cargoUsuario;
    private String loginUsuario;
    private String senhaUsuario;

    public Usuario() {

    }

    public Usuario(int codUsuario, String nomeUsuario, String setorUsuario, String cargoUsuario, String loginUsuario, String senhaUsuario) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.setorUsuario = setorUsuario;
        this.cargoUsuario = cargoUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSetorUsuario() {
        return setorUsuario;
    }

    public void setSetorUsuario(String setorUsuario) {
        this.setorUsuario = setorUsuario;
    }

    public String getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario + ", setorUsuario=" + setorUsuario + ", cargoUsuario=" + cargoUsuario + ", loginUsuario=" + loginUsuario + ", senhaUsuario=" + senhaUsuario + '}';
    }

}
