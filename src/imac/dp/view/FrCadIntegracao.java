/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.EmpresaDao;
import imac.dp.dao.FuncionarioDao;
import imac.dp.dao.IntegracaoDao;
import imac.dp.model.Empresa;
import imac.dp.model.Funcionario;
import imac.dp.model.Integracao;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author @andre_ments
 */
public class FrCadIntegracao extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrcadUsuario
     */
    public FrCadIntegracao() throws ClassNotFoundException {
        initComponents();
        habilitarB(1);
        listarInteVencida();
    }

    public void habilitarB(int op) {
        switch (op) {
            case 1:

                BtNovo.setEnabled(true);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(true);
                BtRenovar.setEnabled(false);
                BtExcluir.setVisible(true);
                BtExcluir.setEnabled(false);
                BtBuscarCodFuncionario.setEnabled(true);
                BtBuscarCodEmpresa.setEnabled(true);
                EdCodIntegra.setEditable(false);
                temp.setVisible(false);
                EdStatusIntegracao.setEditable(false);

                EdCodFuncio.setEnabled(true);
                EdCodFuncio.setEditable(true);
                EdNomeFuncio.setEnabled(false);
                EdCargoFuncio.setEnabled(false);
                EdSetorFuncio.setEnabled(false);
                EdTurnoFuncio.setEnabled(false);
                EdCpfFuncio.setEnabled(false);

                EdCodEmpresa.setEditable(true);
                EdNomeEmpresa.setEnabled(false);

                JdcUltiIntegra.setEnabled(false);
                JdcVencIntegra.setEnabled(false);
                JdcUltiAso.setEnabled(false);
                JdcVencAso.setEnabled(false);

                EdCodFuncio.requestFocus();
                break;

            case 2:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(true);
                BtSalvar.setVisible(true);
                BtRenovar.setEnabled(false);
                BtExcluir.setEnabled(false);
                BtBuscarCodEmpresa.setEnabled(true);
                BtBuscarCodFuncionario.setEnabled(true);
                BtCancelar.setEnabled(true);
                EdCodFuncio.setEditable(true);
                EdCodEmpresa.setEditable(true);

                EdCodIntegra.setEditable(false);

                EdNomeFuncio.setEnabled(true);
                EdCargoFuncio.setEnabled(true);
                EdSetorFuncio.setEnabled(true);
                EdTurnoFuncio.setEnabled(true);
                EdCpfFuncio.setEnabled(true);

                EdNomeEmpresa.setEnabled(true);

                JdcUltiIntegra.setEnabled(true);
                JdcVencIntegra.setEnabled(true);
                JdcUltiAso.setEnabled(true);
                JdcVencAso.setEnabled(true);

                EdNomeFuncio.requestFocus();
                break;

            case 3:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(true);
                BtRenovar.setEnabled(true);
                BtExcluir.setEnabled(true);
                BtCancelar.setEnabled(true);
                BtBuscarCodFuncionario.setEnabled(false);
                BtBuscarCodEmpresa.setEnabled(false);
                EdCodIntegra.setEditable(false);

                EdCodFuncio.setEditable(false);
                EdCodEmpresa.setEditable(false);
                EdNomeFuncio.setEnabled(true);
                EdCargoFuncio.setEnabled(true);
                EdSetorFuncio.setEnabled(true);
                EdTurnoFuncio.setEnabled(true);
                EdCpfFuncio.setEnabled(true);

                EdNomeFuncio.setEditable(false);
                EdCargoFuncio.setEditable(false);
                EdSetorFuncio.setEditable(false);
                EdTurnoFuncio.setEditable(false);
                EdCpfFuncio.setEditable(false);

                EdNomeEmpresa.setEnabled(true);
                EdNomeEmpresa.setEditable(false);

                JdcUltiIntegra.setEnabled(true);
                JdcVencIntegra.setEnabled(true);
                JdcUltiAso.setEnabled(true);
                JdcVencAso.setEnabled(true);

                EdNomeFuncio.requestFocus();
                break;
        }
    }

    public void limpaCampos() {
        EdCodFuncio.setText(null);
        EdNomeFuncio.setText(null);
        EdNomeEmpresa.setText(null);
        EdSetorFuncio.setText(null);
        EdCargoFuncio.setText(null);
        EdCpfFuncio.setText(null);
        EdCargoFuncio.setText(null);
        EdTurnoFuncio.setText(null);
        EdCodEmpresa.setText(null);
        EdNomeEmpresa.setText(null);

        JdcUltiIntegra.setDate(null);
        JdcVencIntegra.setDate(null);
        JdcUltiAso.setDate(null);
        JdcVencAso.setDate(null);
    }

    public void validaCadastroIntegra() {
        IntegracaoDao pdao = new IntegracaoDao();

        try {
            String codFuncio = EdCodFuncio.getText();
            String codEmpresa = EdCodEmpresa.getText();

            if (pdao.checkCadIntegra(codFuncio, codEmpresa)) {

                JOptionPane.showMessageDialog(tabelafront, "Atenção\nFuncionário já possui cadastrado de integração nesta empresa!");

            } else if (JdcUltiIntegra.getDate() == null || JdcVencIntegra.getDate() == null || JdcUltiAso.getDate() == null || JdcVencAso.getDate() == null) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher as datas!");
            } else {
                salvarIntegracao();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvarIntegracao() {
        if (EdCodEmpresa.getText().equals("") || EdNomeEmpresa.getText().equals("") || EdSetorFuncio.getText().equals("") || EdCargoFuncio.getText().equals("") || EdCpfFuncio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {

            Integracao i = new Integracao();
            IntegracaoDao dao = new IntegracaoDao();

            i.setFr_codFuncionario(Integer.parseInt(EdCodFuncio.getText()));
            i.setFr_nomeFuncionario(EdNomeFuncio.getText());
            i.setFr_setorFuncionario(EdSetorFuncio.getText());
            i.setFr_cpfFuncionario(EdCpfFuncio.getText());
            i.setFr_cargoFuncionario(EdCargoFuncio.getText());
            i.setFr_turnoFuncionario(EdTurnoFuncio.getText());
            i.setFr_codEmpresa(Integer.parseInt(EdCodEmpresa.getText()));
            i.setFr_nomeEmpresa(EdNomeEmpresa.getText());

            java.util.Date UltiIntegra = JdcUltiIntegra.getDate();
            long dtIntegra = UltiIntegra.getTime();
            java.sql.Date dateUltiIntegra = new java.sql.Date(dtIntegra);
            i.setDataUltiInt(dateUltiIntegra);

            java.util.Date VencIntegra = JdcVencIntegra.getDate();
            long dtVenciIntegra = VencIntegra.getTime();
            java.sql.Date dateVencIntegra = new java.sql.Date(dtVenciIntegra);
            i.setDataVencInt(dateVencIntegra);

            java.util.Date UltiAso = JdcUltiAso.getDate();
            long dtUltiAso = UltiAso.getTime();
            java.sql.Date dateUltiAso = new java.sql.Date(dtUltiAso);
            i.setDataUltiAso(dateUltiAso);

            java.util.Date dataVencAso = JdcVencAso.getDate();
            long dtVencAso = dataVencAso.getTime();
            java.sql.Date vencimentoAso = new java.sql.Date(dtVencAso);
            i.setDataVencAso(vencimentoAso);

            try {
                dao.createIntegracao(i);
                habilitarB(1);
                limpaCampos();
                listarInteVencida();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrCadIntegracao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void listarIntegracao() throws ClassNotFoundException {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        IntegracaoDao pdao = new IntegracaoDao();

        if (CbxFiltroDatas.isSelected()) {
            if (JdcFiltraUltiIntegraInicial.getDate() == null || JdcFiltraUltiIntegraFinal.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Você deve informar as datas do filtro!");
            } else {
                java.util.Date dataIni = JdcFiltraUltiIntegraInicial.getDate();
                long dtInicial = dataIni.getTime();
                java.sql.Date dateIntegraInicial = new java.sql.Date(dtInicial);

                java.util.Date dataFin = JdcFiltraUltiIntegraFinal.getDate();
                long dtFinal = dataFin.getTime();
                java.sql.Date dateIntegraFinal = new java.sql.Date(dtFinal);

                for (Integracao i : pdao.readParForData(dateIntegraInicial, dateIntegraFinal)) {
                    modelo.addRow(new Object[]{
                        i.getCodInt(),
                        i.getFr_codFuncionario(),
                        i.getFr_nomeFuncionario(),
                        i.getFr_cpfFuncionario(),
                        i.getFr_setorFuncionario(),
                        i.getFr_cargoFuncionario(),
                        i.getFr_turnoFuncionario(),
                        i.getFr_codEmpresa(),
                        i.getFr_nomeEmpresa(),
                        i.getDataUltiInt(),
                        i.getDataVencInt(),
                        i.getDataUltiAso(),
                        i.getDataVencAso(),
                        (i.getDiasVencidos() * -1)});
                }
            }

        } else if (CbxVencidas.isSelected()) {

            for (Integracao i : pdao.readIntegracaoVencidas()) {
                modelo.addRow(new Object[]{
                    i.getCodInt(),
                    i.getFr_codFuncionario(),
                    i.getFr_nomeFuncionario(),
                    i.getFr_cpfFuncionario(),
                    i.getFr_setorFuncionario(),
                    i.getFr_cargoFuncionario(),
                    i.getFr_turnoFuncionario(),
                    i.getFr_codEmpresa(),
                    i.getFr_nomeEmpresa(),
                    i.getDataUltiInt(),
                    i.getDataVencInt(),
                    i.getDataUltiAso(),
                    i.getDataVencAso(),
                    (i.getDiasVencidos() * -1)});
            }
        } else if (!EdBuscarFuncio.getText().equals("")) {
            for (Integracao i : pdao.readIntegracaoForFuncionario(Integer.parseInt(EdBuscarFuncio.getText()))) {
                modelo.addRow(new Object[]{
                    i.getCodInt(),
                    i.getFr_codFuncionario(),
                    i.getFr_nomeFuncionario(),
                    i.getFr_cpfFuncionario(),
                    i.getFr_setorFuncionario(),
                    i.getFr_cargoFuncionario(),
                    i.getFr_turnoFuncionario(),
                    i.getFr_codEmpresa(),
                    i.getFr_nomeEmpresa(),
                    i.getDataUltiInt(),
                    i.getDataVencInt(),
                    i.getDataUltiAso(),
                    i.getDataVencAso(),
                    (i.getDiasVencidos() * -1)});
            }

        } else {

            for (Integracao i : pdao.readIntegracao()) {
                modelo.addRow(new Object[]{
                    i.getCodInt(),
                    i.getFr_codFuncionario(),
                    i.getFr_nomeFuncionario(),
                    i.getFr_cpfFuncionario(),
                    i.getFr_setorFuncionario(),
                    i.getFr_cargoFuncionario(),
                    i.getFr_turnoFuncionario(),
                    i.getFr_codEmpresa(),
                    i.getFr_nomeEmpresa(),
                    i.getDataUltiInt(),
                    i.getDataVencInt(),
                    i.getDataUltiAso(),
                    i.getDataVencAso(),
                    (i.getDiasVencidos() * -1)});

            }

        }

    }

//
//        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
//        modelo.setNumRows(0);
//        IntegracaoDao pdao = new IntegracaoDao();
//
//        if (CbxVencidas.isSelected()) {
//
//            for (Integracao i : pdao.readIntegracaoVencidas()) {
//                modelo.addRow(new Object[]{
//                    i.getCodInt(),
//                    i.getFr_codFuncionario(),
//                    i.getFr_nomeFuncionario(),
//                    i.getFr_cpfFuncionario(),
//                    i.getFr_setorFuncionario(),
//                    i.getFr_cargoFuncionario(),
//                    i.getFr_turnoFuncionario(),
//                    i.getFr_codEmpresa(),
//                    i.getFr_nomeEmpresa(),
//                    i.getDataUltiInt(),
//                    i.getDataVencInt(),
//                    i.getDataUltiAso(),
//                    i.getDataVencAso(),
//                    (i.getDiasVencidos() * -1)});
//            }
//
//        } else {
//
//            if (EdBuscarNome.getText() != "") {
//                for (Integracao i : pdao.readIntegracaoForFuncionario(EdBuscarNome.getText())) {
//                    modelo.addRow(new Object[]{
//                        i.getCodInt(),
//                        i.getFr_codFuncionario(),
//                        i.getFr_nomeFuncionario(),
//                        i.getFr_cpfFuncionario(),
//                        i.getFr_setorFuncionario(),
//                        i.getFr_cargoFuncionario(),
//                        i.getFr_turnoFuncionario(),
//                        i.getFr_codEmpresa(),
//                        i.getFr_nomeEmpresa(),
//                        i.getDataUltiInt(),
//                        i.getDataVencInt(),
//                        i.getDataUltiAso(),
//                        i.getDataVencAso(),
//                        (i.getDiasVencidos() * -1)});
//
//                }
//
//            } else {
//                for (Integracao i : pdao.readIntegracao()) {
//                    modelo.addRow(new Object[]{
//                        i.getCodInt(),
//                        i.getFr_codFuncionario(),
//                        i.getFr_nomeFuncionario(),
//                        i.getFr_cpfFuncionario(),
//                        i.getFr_setorFuncionario(),
//                        i.getFr_cargoFuncionario(),
//                        i.getFr_turnoFuncionario(),
//                        i.getFr_codEmpresa(),
//                        i.getFr_nomeEmpresa(),
//                        i.getDataUltiInt(),
//                        i.getDataVencInt(),
//                        i.getDataUltiAso(),
//                        i.getDataVencAso(),
//                        i.getDiasVencidos()});
//                    JOptionPane.showMessageDialog(tabelafront, "Listou esse");
//                }
//            }
//        }
//    }
    public void listarInteVencida() throws ClassNotFoundException {

        DefaultTableModel modelo = (DefaultTableModel) tabelafront.getModel();
        modelo.setNumRows(0);
        IntegracaoDao pdao = new IntegracaoDao();
        Integracao in = new Integracao();
        if (in.getDiasVencidos() <= 0) {
            for (Integracao i : pdao.readIntegracao()) {
                modelo.addRow(new Object[]{
                    i.getFr_nomeFuncionario(),
                    i.getFr_nomeEmpresa(),
                    (i.getDiasVencidos() * -1)
                });
            }

        }

    }

    public void listaIntegracaoCodEmpresa() {

        IntegracaoDao pdao = new IntegracaoDao();

        if (CbxVencidas.isSelected()) {

            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");

            try {
                for (Integracao fu : pdao.readIntegracaoVencidas()) {

                    EdNomeEmpresa.setText(fu.getFr_nomeFuncionario());
                    EdSetorFuncio.setText(fu.getFr_setorFuncionario());
                    EdCargoFuncio.setText(fu.getFr_cargoFuncionario());
                    EdCpfFuncio.setText(fu.getFr_cpfFuncionario());
                    habilitarB(3);

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrCadIntegracao.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        }
        if (EdCodEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                for (Integracao fu : pdao.readIntegracaoForCodEmpresa(EdCodEmpresa.getText())) {

                    EdNomeEmpresa.setText(fu.getFr_nomeFuncionario());
                    EdSetorFuncio.setText(fu.getFr_setorFuncionario());
                    EdCargoFuncio.setText(fu.getFr_cargoFuncionario());
                    EdCpfFuncio.setText(fu.getFr_cpfFuncionario());
                    habilitarB(3);

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrCadIntegracao.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editarIntegracao() throws ClassNotFoundException {

        if (tabela.getSelectedRow() != -1) {
            Integracao i = new Integracao();
            IntegracaoDao dao = new IntegracaoDao();

            if (EdNomeEmpresa.getText().equals("") || EdSetorFuncio.getText().equals("") || EdCpfFuncio.getText().equals("")
                    || EdCargoFuncio.getText().equals("") || EdNomeEmpresa.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
            } else {

                java.util.Date UltiIntegra = JdcUltiIntegra.getDate();
                long dtIntegra = UltiIntegra.getTime();
                java.sql.Date dateUltiIntegra = new java.sql.Date(dtIntegra);
                i.setDataUltiInt(dateUltiIntegra);

                java.util.Date VencIntegra = JdcVencIntegra.getDate();
                long dtVenciIntegra = VencIntegra.getTime();
                java.sql.Date dateVencIntegra = new java.sql.Date(dtVenciIntegra);
                i.setDataVencInt(dateVencIntegra);

                java.util.Date UltiAso = JdcUltiAso.getDate();
                long dtUltiAso = UltiAso.getTime();
                java.sql.Date dateUltiAso = new java.sql.Date(dtUltiAso);
                i.setDataUltiAso(dateUltiAso);

                java.util.Date dataVencAso = JdcVencAso.getDate();
                long dtVencAso = dataVencAso.getTime();
                java.sql.Date vencimentoAso = new java.sql.Date(dtVencAso);
                i.setDataVencAso(vencimentoAso);

                i.setCodInt((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                try {
                    dao.updateIntegracao(i);
                    limpaCampos();
                    listarIntegracao();
                    habilitarB(1);
                    listarInteVencida();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public void deletarIntegracao() throws ClassNotFoundException {
        Integracao i = new Integracao();
        IntegracaoDao dao = new IntegracaoDao();

        if (EdCodIntegra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário para excluír");
        } else {
            i.setCodInt(Integer.parseInt(EdCodIntegra.getText()));
            try {
                String nome = EdNomeFuncio.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o cadastro da integração do funcionário  " + nome, "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);

                if (input == 0) {
                    dao.deleteIntegracao(i);
                    habilitarB(1);
                    limpaCampos();
                    listarInteVencida();
                    listarIntegracao();

                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");

                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrCadIntegracao.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void validarCodFuncio() {
        FuncionarioDao pdao = new FuncionarioDao();

        if (EdCodFuncio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do funcionário!");
        } else {

            try {
                if (pdao.checkCod(EdCodFuncio.getText())) {
                    listarFuncioCod();
                    habilitarB(2);
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado");

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listarFuncioCod() throws ClassNotFoundException {

        FuncionarioDao pdao = new FuncionarioDao();

        for (Funcionario fu : pdao.readFuncionarioForCod(EdCodFuncio.getText())) {
            EdNomeFuncio.setText(fu.getNomeFuncionario());
            EdSetorFuncio.setText(fu.getSetorFuncionario());
            EdCargoFuncio.setText(fu.getCargoFuncionario());
            EdCpfFuncio.setText(fu.getCpfFuncionario());
            EdTurnoFuncio.setText(fu.getTurnoFuncionario());

        }
    }

    public void validarCodEmpresa() {
        EmpresaDao pdao = new EmpresaDao();

        if (EdCodEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código da empresa!");
        } else {

            try {
                if (pdao.checkCod(EdCodEmpresa.getText())) {
                    listarEmpresaCod();
                    habilitarB(2);
                } else {
                    JOptionPane.showMessageDialog(null, "Empresa não encontrada!");

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listarEmpresaCod() throws ClassNotFoundException {

        EmpresaDao pdao = new EmpresaDao();

        for (Empresa em : pdao.readEmpForCod(EdCodEmpresa.getText())) {
            EdNomeEmpresa.setText(em.getNomeEmp());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BtNovo = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        BtSair = new javax.swing.JButton();
        BtExcluir = new javax.swing.JButton();
        BtSalvar = new javax.swing.JButton();
        BtRenovar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        EdNomeEmpresa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BtBuscarCodEmpresa = new javax.swing.JButton();
        EdCodEmpresa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        EdTurnoFuncio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        EdCargoFuncio = new javax.swing.JTextField();
        EdSetorFuncio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        EdNomeFuncio = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        BtBuscarCodFuncionario = new javax.swing.JButton();
        EdCodFuncio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        EdCpfFuncio = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JdcUltiIntegra = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        JdcVencIntegra = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        JdcVencAso = new com.toedter.calendar.JDateChooser();
        JdcUltiAso = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelafront = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        EdCodIntegra = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        EdStatusIntegracao = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        temp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        BtBuscarNome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        CbxPaga = new javax.swing.JCheckBox();
        CbxNPaga = new javax.swing.JCheckBox();
        jLabel34 = new javax.swing.JLabel();
        EdBuscarFuncio = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        JdcFiltraUltiIntegraInicial = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        JdcFiltraUltiIntegraFinal = new com.toedter.calendar.JDateChooser();
        CbxVencidas = new javax.swing.JCheckBox();
        jLabel35 = new javax.swing.JLabel();
        CbxFiltroDatas = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Controle de Integrações - V1.0-20.0810");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(218, 121, 251));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Controle de Integração");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(678, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 60));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtNovo.setBackground(new java.awt.Color(102, 255, 102));
        BtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        BtNovo.setText("NOVO");
        BtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtNovoActionPerformed(evt);
            }
        });
        jPanel4.add(BtNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 33));

        BtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_red.png"))); // NOI18N
        BtCancelar.setText("CANCELAR");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(BtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 33));

        BtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair.setText("SAIR");
        BtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSairActionPerformed(evt);
            }
        });
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 78, 33));

        BtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_red.png"))); // NOI18N
        BtExcluir.setText("EXCLUÍR");
        BtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(BtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 110, 33));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, 33));

        BtRenovar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-black.png"))); // NOI18N
        BtRenovar.setText("RENOVAR");
        BtRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRenovarActionPerformed(evt);
            }
        });
        jPanel4.add(BtRenovar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 110, 33));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 435, 940, 52));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(EdNomeEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 200, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        BtBuscarCodEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodEmpresa.setText("Buscar");
        BtBuscarCodEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodEmpresaActionPerformed(evt);
            }
        });
        jPanel5.add(BtBuscarCodEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        EdCodEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdCodEmpresaKeyPressed(evt);
            }
        });
        jPanel5.add(EdCodEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 78, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel2.setText("Cód. Empresa: ");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 310, 110));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Turno: ");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel6.add(EdTurnoFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 140, -1));

        jLabel12.setText("CPF: ");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 20));

        jLabel13.setText("Cargo: ");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        jPanel6.add(EdCargoFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 140, -1));
        jPanel6.add(EdSetorFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 140, -1));

        jLabel14.setText("Setor: ");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));
        jPanel6.add(EdNomeFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 210, -1));

        jLabel15.setText("Nome:");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        BtBuscarCodFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodFuncionario.setText("Buscar");
        BtBuscarCodFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodFuncionarioActionPerformed(evt);
            }
        });
        jPanel6.add(BtBuscarCodFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        EdCodFuncio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdCodFuncioKeyPressed(evt);
            }
        });
        jPanel6.add(EdCodFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 78, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel16.setText("Cód. Funcionário: ");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        try {
            EdCpfFuncio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel6.add(EdCpfFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 140, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 310, 220));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Data Ultima Integração: ");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel7.add(JdcUltiIntegra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 140, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setText("Data Venci. Integração: ");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel7.add(JdcVencIntegra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 140, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel6.setText("Data Venci. ASO: ");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));
        jPanel7.add(JdcVencAso, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 140, -1));
        jPanel7.add(JdcUltiAso, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 140, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel11.setText("Data Ultimo ASO: ");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 310, 150));

        jLabel17.setText("Dados do Cliente");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 20));

        jLabel18.setText("Dados do funcionário");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jLabel19.setText("Integrações a vencer:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, 20));

        tabelafront.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome Funcionário", "Empresa", "Dias Vencimento"
            }
        ));
        tabelafront.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelafrontMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelafront);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 460, 160));

        jLabel21.setText("Datas");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, 20));
        jPanel1.add(EdCodIntegra, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 50, -1));

        jLabel10.setText("Cód. Integração");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, 20));

        EdStatusIntegracao.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        EdStatusIntegracao.setForeground(new java.awt.Color(255, 0, 0));
        EdStatusIntegracao.setText("Status");
        jPanel1.add(EdStatusIntegracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(707, 190, 80, -1));

        jLabel20.setText("Status da Integração: ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, -1, 20));
        jPanel1.add(temp, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, 60, -1));

        jTabbedPane1.addTab("Cadastrar Nova Integração", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel8.setText("Código Funcionário:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        BtBuscarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarNome.setText("Buscar");
        BtBuscarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarNomeActionPerformed(evt);
            }
        });
        jPanel2.add(BtBuscarNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Cód. Funcionário", "Nome Funcionário", "CPF", "Setor", "Cargo", "Turno", "Cód. Empresa", "Nome Empresa", "Ultima Integração", "Vencimento Integração", "Ultimo Aso", "Vencimento Aso", "Dias Vencimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 920, 350));

        jLabel9.setText("Duplo clique para editar*");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, -1, -1));

        CbxPaga.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxPaga.setText("Vencidas");
        CbxPaga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxPagaMouseClicked(evt);
            }
        });
        CbxPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxPagaActionPerformed(evt);
            }
        });
        jPanel2.add(CbxPaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        CbxNPaga.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxNPaga.setText("Não vencidas");
        CbxNPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxNPagaActionPerformed(evt);
            }
        });
        jPanel2.add(CbxNPaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));

        jLabel34.setText("Filtrar por: ");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, 20));
        jPanel2.add(EdBuscarFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 60, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setText("De:");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 20));
        jPanel8.add(JdcFiltraUltiIntegraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 140, -1));

        jLabel22.setText("Até:");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));
        jPanel8.add(JdcFiltraUltiIntegraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 140, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 200, 75));

        CbxVencidas.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxVencidas.setText("VENCIDAS");
        CbxVencidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxVencidasMouseClicked(evt);
            }
        });
        CbxVencidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxVencidasActionPerformed(evt);
            }
        });
        jPanel2.add(CbxVencidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel35.setText("Filtrar por: ");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        CbxFiltroDatas.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxFiltroDatas.setText("DATA VENCIMENTO");
        CbxFiltroDatas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxFiltroDatasMouseClicked(evt);
            }
        });
        CbxFiltroDatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxFiltroDatasActionPerformed(evt);
            }
        });
        jPanel2.add(CbxFiltroDatas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        jTabbedPane1.addTab("Listar Integrações", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 940, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtNovoActionPerformed
        habilitarB(2);
        EdCodEmpresa.requestFocus();
    }//GEN-LAST:event_BtNovoActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        habilitarB(1);
        limpaCampos();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        try {
            deletarIntegracao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrCadIntegracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed

        validaCadastroIntegra();
    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRenovarActionPerformed
        try {
            editarIntegracao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrCadIntegracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRenovarActionPerformed

    private void BtBuscarCodEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodEmpresaActionPerformed
        validarCodEmpresa();
    }//GEN-LAST:event_BtBuscarCodEmpresaActionPerformed

    private void EdCodEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdCodEmpresaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validarCodEmpresa();
        }
    }//GEN-LAST:event_EdCodEmpresaKeyPressed

    private void BtBuscarCodFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodFuncionarioActionPerformed
        validarCodFuncio();
    }//GEN-LAST:event_BtBuscarCodFuncionarioActionPerformed

    private void EdCodFuncioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdCodFuncioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validarCodFuncio();
            EdCodEmpresa.requestFocus();
        }
    }//GEN-LAST:event_EdCodFuncioKeyPressed

    private void tabelafrontMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelafrontMouseClicked
        if (tabelafront.getSelectedRow() != -1) {

            temp.setText(tabelafront.getValueAt(tabelafront.getSelectedRow(), 2).toString());
            int stt;
            stt = Integer.parseInt(temp.getText()) * -1;
            if (stt <= 0) {
                EdStatusIntegracao.setText("EM DIA");
                EdStatusIntegracao.setForeground(Color.BLUE);
            } else {
                EdStatusIntegracao.setText("VENCIDA!");
                EdStatusIntegracao.setForeground(Color.RED);
            }

        }
    }//GEN-LAST:event_tabelafrontMouseClicked

    private void BtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarNomeActionPerformed
        try {
            listarIntegracao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrCadIntegracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtBuscarNomeActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (tabela.getSelectedRow() != -1) {
            jTabbedPane1.setSelectedIndex(0);
            EdCodIntegra.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdCodFuncio.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            EdNomeFuncio.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            EdCpfFuncio.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
            EdSetorFuncio.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
            EdCargoFuncio.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
            EdTurnoFuncio.setText(tabela.getValueAt(tabela.getSelectedRow(), 6).toString());
            EdCodEmpresa.setText(tabela.getValueAt(tabela.getSelectedRow(), 7).toString());
            EdNomeEmpresa.setText(tabela.getValueAt(tabela.getSelectedRow(), 8).toString());
            //            JdcUltiIntegra.setDate(tabela.getValueAt(tabela.getSelectedRow(), 10));
            //            JdcVencIntegra.setDate(tabela.getValueAt(tabela.getSelectedRow(), 11));
            //            JdcUltiAso.setDate(tabela.getValueAt(tabela.getSelectedRow(), 12));
            //            JdcVencAso.setDate(tabela.getValueAt(tabela.getSelectedRow(), 13));

            try {
                DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                int seectedRow = tabela.getSelectedRow();
                java.util.Date dateUltiInte;
                java.util.Date dateVencInte;
                java.util.Date dateUltiAso;
                java.util.Date dateVencAso;

                dateUltiInte = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 9).toString());
                JdcUltiIntegra.setDate(dateUltiInte);

                dateVencInte = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 10).toString());
                JdcVencIntegra.setDate(dateVencInte);

                dateUltiAso = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 11).toString());
                JdcUltiAso.setDate(dateUltiAso);

                dateVencAso = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 12).toString());
                JdcVencAso.setDate(dateVencAso);

            } catch (ParseException ex) {
                Logger.getLogger(FrCadIntegracao.class.getName()).log(Level.SEVERE, null, ex);
            }
            //

            habilitarB(3);

        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void CbxPagaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxPagaMouseClicked

    }//GEN-LAST:event_CbxPagaMouseClicked

    private void CbxPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxPagaActionPerformed
        CbxNPaga.setSelected(false);
    }//GEN-LAST:event_CbxPagaActionPerformed

    private void CbxNPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxNPagaActionPerformed
        CbxPaga.setSelected(false);
    }//GEN-LAST:event_CbxNPagaActionPerformed

    private void CbxVencidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxVencidasMouseClicked

    }//GEN-LAST:event_CbxVencidasMouseClicked

    private void CbxVencidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxVencidasActionPerformed
        CbxNPaga.setSelected(false);
    }//GEN-LAST:event_CbxVencidasActionPerformed

    private void CbxFiltroDatasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxFiltroDatasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxFiltroDatasMouseClicked

    private void CbxFiltroDatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxFiltroDatasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxFiltroDatasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtBuscarCodEmpresa;
    private javax.swing.JButton BtBuscarCodFuncionario;
    private javax.swing.JButton BtBuscarNome;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtExcluir;
    private javax.swing.JButton BtNovo;
    private javax.swing.JButton BtRenovar;
    private javax.swing.JButton BtSair;
    private javax.swing.JButton BtSalvar;
    private javax.swing.JCheckBox CbxFiltroDatas;
    private javax.swing.JCheckBox CbxNPaga;
    private javax.swing.JCheckBox CbxPaga;
    private javax.swing.JCheckBox CbxVencidas;
    private javax.swing.JTextField EdBuscarFuncio;
    private javax.swing.JTextField EdCargoFuncio;
    private javax.swing.JTextField EdCodEmpresa;
    private javax.swing.JTextField EdCodFuncio;
    private javax.swing.JTextField EdCodIntegra;
    private javax.swing.JFormattedTextField EdCpfFuncio;
    private javax.swing.JTextField EdNomeEmpresa;
    private javax.swing.JTextField EdNomeFuncio;
    private javax.swing.JTextField EdSetorFuncio;
    private javax.swing.JTextField EdStatusIntegracao;
    private javax.swing.JTextField EdTurnoFuncio;
    private com.toedter.calendar.JDateChooser JdcFiltraUltiIntegraFinal;
    private com.toedter.calendar.JDateChooser JdcFiltraUltiIntegraInicial;
    private com.toedter.calendar.JDateChooser JdcUltiAso;
    private com.toedter.calendar.JDateChooser JdcUltiIntegra;
    private com.toedter.calendar.JDateChooser JdcVencAso;
    private com.toedter.calendar.JDateChooser JdcVencIntegra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelafront;
    private javax.swing.JTextField temp;
    // End of variables declaration//GEN-END:variables
}
