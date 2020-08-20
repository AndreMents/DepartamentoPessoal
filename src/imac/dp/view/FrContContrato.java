/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.ContratoDao;
import imac.dp.dao.FuncionarioDao;
import imac.dp.model.Contrato;
import imac.dp.model.Funcionario;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suporte-01
 */
public class FrContContrato extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrControlContrato
     */
    public FrContContrato() {
        initComponents();
        habilitarB(1);
        camposVisibleNull();

    }

    public void habilitarB(int op) {
        switch (op) {
            case 1:

                BtNovo.setEnabled(true);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(true);
                BtEditar.setVisible(false);
                BtEditar.setEnabled(false);

                BtExcluir.setVisible(true);
                BtExcluir.setEnabled(false);
                BtBuscarCodFuncionario.setEnabled(false);
                EdCodContrato.setEditable(true);

                EdCodFuncio.setEnabled(false);
                EdCodFuncio.setEditable(false);
                EdNomeFuncio.setEnabled(false);
                EdCargoFuncio.setEnabled(false);
                EdSetorFuncio.setEnabled(false);
                EdTurnoFuncio.setEnabled(false);
                EdCpfFuncio.setEnabled(false);
                JdcDataAdmi.setEnabled(false);
                EdNomeRespon.setEnabled(false);
                JtObserva.setEnabled(false);

                EdCodContrato.requestFocus();
                break;

            case 2:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(true);
                BtSalvar.setVisible(true);
                BtEditar.setVisible(false);
                BtEditar.setEnabled(false);
                BtExcluir.setEnabled(false);

                BtBuscarCodFuncionario.setEnabled(true);
                BtCancelar.setEnabled(true);
                EdCodFuncio.setEditable(true);
                EdCodContrato.setEditable(false);

                EdNomeFuncio.setEnabled(true);
                EdCargoFuncio.setEnabled(true);
                EdSetorFuncio.setEnabled(true);
                EdTurnoFuncio.setEnabled(true);
                EdCpfFuncio.setEnabled(true);
                JdcDataAdmi.setEnabled(true);
                EdNomeRespon.setEnabled(true);
                JtObserva.setEnabled(true);
                EdCodFuncio.setEnabled(true);

                EdCodFuncio.requestFocus();
                break;

            case 3:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(false);
                BtEditar.setVisible(true);
                BtEditar.setEnabled(true);

                BtExcluir.setEnabled(true);
                BtCancelar.setEnabled(true);
                BtBuscarCodFuncionario.setEnabled(false);

                

                EdNomeFuncio.setEnabled(true);
                EdCargoFuncio.setEnabled(true);
                EdSetorFuncio.setEnabled(true);
                EdTurnoFuncio.setEnabled(true);
                EdCpfFuncio.setEnabled(true);
                JdcDataTerm30dd.setEnabled(true);
                JdcDataTerm90dd.setEnabled(true);
                JdcDataAdmi.setEnabled(true);
                JtObserva.setEnabled(true);
                EdNomeRespon.setEnabled(true);
                
                EdCodFuncio.setEditable(false);
                EdNomeFuncio.setEditable(false);
                EdCargoFuncio.setEditable(false);
                EdSetorFuncio.setEditable(false);
                EdTurnoFuncio.setEditable(false);
                EdCpfFuncio.setEditable(false);
                JtObserva.setEditable(true);
                EdNomeRespon.setEditable(true);

                break;
        }
    }

    public void limpaCampos() {
        EdCodFuncio.setText(null);
        EdNomeFuncio.setText(null);
        EdSetorFuncio.setText(null);
        EdCargoFuncio.setText(null);
        EdCpfFuncio.setText(null);
        EdCargoFuncio.setText(null);
        EdTurnoFuncio.setText(null);
        EdNomeRespon.setText(null);
        JtObserva.setText(null);
        EdRestam30dd.setText(null);
        EdRestam90dd.setText(null);
        EdCodContrato.setText(null);

        JdcDataAdmi.setDate(null);
        JdcDataTerm30dd.setDate(null);
        JdcDataTerm90dd.setDate(null);

    }

    public void camposVisibleNull() {
        EdStatus30dd.setVisible(false);
        EdStatusContra.setVisible(false);
        EdDataBanco30dd.setVisible(false);
        EdDataBanco90dd.setVisible(false);
        EdStatus90dd.setVisible(false);
        CbxData90dd.setVisible(false);

    }

    public void buscarCodContra() {

        ContratoDao pdao = new ContratoDao();

        try {
            for (Contrato p : pdao.readMaiorCodContra()) {

                int codPar;
                codPar = p.getCodContra();
                EdCodContrato.setText(Integer.toString(codPar));
            }

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validaCadastroContrato() {
        ContratoDao pdao = new ContratoDao();

        try {
            String codFuncio = EdCodFuncio.getText();

            if (pdao.checkCadContra(codFuncio)) {

                JOptionPane.showMessageDialog(null, "Funcionário já possui cadastrado cadastrado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

            } else if (JdcDataAdmi.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Você deve preencher as datas!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            } else {
                salvarContrato();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validaEdicaoContrato() {
        ContratoDao pdao = new ContratoDao();

        try {
            String codFuncio = EdCodFuncio.getText();

            if (JdcDataAdmi.getDate() == null) {

                JOptionPane.showMessageDialog(null, "Você deve informar a data!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

            } else if (pdao.checkCadContra(codFuncio)) {
                editarContrato();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvarContrato() {
        if (EdCodContrato.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do Funcionário!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {

            Contrato co = new Contrato();
            ContratoDao dao = new ContratoDao();

            co.setCodContra(Integer.parseInt(EdCodContrato.getText()));
            co.setFr_codFuncionario(Integer.parseInt(EdCodFuncio.getText()));
            co.setFr_nomeFuncionario(EdNomeFuncio.getText());
            co.setResponsContra(EdNomeRespon.getText());
            co.setObservaContra(JtObserva.getText());
            co.setStatusContra(2);
            co.setStatusContra30dd(2);
            co.setStatusContra90dd(2);
            java.util.Date dataVcContrato = JdcDataAdmi.getDate();
            long dtContrato = dataVcContrato.getTime();
            java.sql.Date dateVencContrato = new java.sql.Date(dtContrato);
            co.setDataAdmiContra(dateVencContrato);
            co.setDataVenc30dd(dateVencContrato);
            co.setDataVenc90dd(dateVencContrato);

            try {
                dao.createContrato(co);
                gerarUpdateData();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrContContrato.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void gerarUpdateData() throws ClassNotFoundException {
        ContratoDao dao = new ContratoDao();
        Contrato pa = new Contrato();

        pa.setCodContra(Integer.parseInt(EdCodContrato.getText()));
        try {
            dao.updateDateVenc30dd(pa);
            dao.updateDateVenc90dd(pa);
            habilitarB(1);
            limpaCampos();
            listarContrato();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listarContrato() throws ClassNotFoundException {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        ContratoDao pdao = new ContratoDao();

        if (CbxFinalizado.isSelected()) {

            for (Contrato c : pdao.readContratoFechado()) {
                modelo.addRow(new Object[]{
                    c.getCodContra(),
                    c.getFr_codFuncionario(),
                    c.getFr_nomeFuncionario(),
                    c.getDataAdmiContra(),
                    c.getDataVenc30dd(),
                    c.getDataVenc90dd()});
            }
        } else {
            for (Contrato c : pdao.readContratoAberto()) {

                modelo.addRow(new Object[]{
                    c.getCodContra(),
                    c.getFr_codFuncionario(),
                    c.getFr_nomeFuncionario(),
                    c.getDataAdmiContra(),
                    c.getDataVenc30dd(),
                    c.getDataVenc90dd()});
            }
        }
    }

    public void upadteContrato30dd() throws ClassNotFoundException {
        if (EdStatus30dd.getText().equals("1")) {
            JOptionPane.showMessageDialog(null, "Período já avaliado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            if (tabela.getSelectedRow() != -1) {
                Contrato co = new Contrato();
                ContratoDao dao = new ContratoDao();

                if (EdCodContrato.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Você deve informar o código do contrato campos", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    co.setStatusContra30dd(1);

                    co.setCodContra((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                    try {
                        dao.updateContrato30dd(co);
                        status();

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FrcadUsuario.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
    }

    public void upadteContrato30ddFor2() throws ClassNotFoundException {
        if (!EdStatus90dd.getText().equals("2")) {
            JOptionPane.showMessageDialog(null, "Você não pode remover os 30 Dias, sem remover os 90 Dias", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            if (tabela.getSelectedRow() != -1) {
                Contrato co = new Contrato();
                ContratoDao dao = new ContratoDao();

                if (EdCodContrato.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Você deve informar o código do contrato", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    co.setStatusContra30dd(2);

                    co.setCodContra((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                    try {
                        dao.updateContrato30dd(co);
                        status();

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FrcadUsuario.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
    }

    public void upadteContrato90dd() throws ClassNotFoundException {
        if (EdStatus90dd.getText().equals("1")) {
            JOptionPane.showMessageDialog(null, "Período já avaliado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!EdStatus30dd.getText().equals("1")) {
                JOptionPane.showMessageDialog(null, "Você não pode renovar os 90 Dias, sem renovar os 30 Dias", "ERRO", JOptionPane.ERROR_MESSAGE);
            } else {
                if (tabela.getSelectedRow() != -1) {
                    Contrato co = new Contrato();
                    ContratoDao dao = new ContratoDao();

                    if (EdCodContrato.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Você deve informar o código do contrato campos", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    } else {
                        co.setStatusContra90dd(1);

                        co.setCodContra((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                        try {
                            dao.updateContrato90dd(co);
                            atualizarStatusContraConcluido();
                            listarContrato();
                            status();

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(FrcadUsuario.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }

            }
        }
    }

    public void upadteContrato90ddFor2() throws ClassNotFoundException {
        if (EdStatus90dd.getText().equals("2")) {
            JOptionPane.showMessageDialog(null, "Período não avaliado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            if (tabela.getSelectedRow() != -1) {
                Contrato co = new Contrato();
                ContratoDao dao = new ContratoDao();

                if (EdCodContrato.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Você deve informar o código do contrato", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    co.setStatusContra90dd(2);

                    co.setCodContra((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                    try {
                        dao.updateContrato90dd(co);
                        atualizarStatusContraRemovido();
                        listarContrato();
                        status();

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FrcadUsuario.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }
    }

    public void atualizarStatusContraConcluido() throws ClassNotFoundException {
        Contrato co = new Contrato();
        ContratoDao dao = new ContratoDao();
        co.setStatusContra(1);
        if (tabela.getSelectedRow() != -1) {
            try {
                co.setCodContra((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                dao.updateStatusContrato(co);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void atualizarStatusContraRemovido() throws ClassNotFoundException {
        Contrato co = new Contrato();
        ContratoDao dao = new ContratoDao();
        co.setStatusContra(2);
        if (tabela.getSelectedRow() != -1) {
            try {
                co.setCodContra((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                dao.updateStatusContrato(co);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void dateDIFF() throws ClassNotFoundException {
        ContratoDao pdao = new ContratoDao();
        for (Contrato co : pdao.readContratoDIFF(EdCodContrato.getText())) {

            EdRestam30dd.setText(Integer.toString(co.getDiasRestantes30dd() * -1));
            EdRestam90dd.setText(Integer.toString(co.getDiasRestantes90dd() * -1));
        }
    }

    public void deletarContrato() throws ClassNotFoundException {
        Contrato i = new Contrato();
        ContratoDao dao = new ContratoDao();

        if (EdCodContrato.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário para excluír", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            i.setCodContra(Integer.parseInt(EdCodContrato.getText()));
            try {
                String nome = EdNomeFuncio.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        "Deseja mesmo excluír o cadastro do contrato do funcionário  " + nome, "ATENÇÃO", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);

                if (input == 0) {
                    dao.deleteContrato(i);
                    habilitarB(1);
                    limpaCampos();
                    listarContrato();

                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrContContrato.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void validarCodFuncio() {
        FuncionarioDao pdao = new FuncionarioDao();

        if (EdCodFuncio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do funcionário!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                if (pdao.checkCod(EdCodFuncio.getText())) {
                    listarFuncioCod();

                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);

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

    public void status() throws ClassNotFoundException {
        ContratoDao pdao = new ContratoDao();
        dateDIFF();
        try {
            for (Contrato co : pdao.readStatusContraFoCodContra(EdCodContrato.getText())) {

                EdStatus30dd.setText(Integer.toString(co.getStatusContra30dd()));

                EdStatus90dd.setText(Integer.toString(co.getStatusContra90dd()));

                EdStatusContra.setText(Integer.toString(co.getStatusContra90dd()));

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String Status30dd;
        String Status90dd;

        int datet = 0;
        int data30 = Integer.parseInt(EdRestam30dd.getText());
        int data90 = Integer.parseInt(EdRestam90dd.getText());

        if (data30 >= datet && EdStatus30dd.getText().equals("2")) {
            Status30dd = "NÃO AVALIADO";
            JbStatus30.setText(Status30dd);
            jP30dd.setBackground(Color.decode("#aecffc"));

        } else if (data30 < datet && EdStatus30dd.getText().equals("2")) {
            Status30dd = "VENCIDO!";
            JbStatus30.setText(Status30dd);
            jP30dd.setBackground(Color.decode("#fcb3ae"));
        } else {
            Status30dd = "AVALIADO";
            JbStatus30.setText(Status30dd);
            jP30dd.setBackground(Color.decode("#a9f5d5"));
        }

        if (data90 >= datet && EdStatus90dd.getText().equals("2")) {

            Status90dd = "NÃO AVALIADO";
            JbStatus90.setText(Status90dd);
            jP90dd.setBackground(Color.decode("#aecffc"));

        } else if (data90 < datet && EdStatus90dd.getText().equals("2")) {
            Status90dd = "VENCIDO!";
            JbStatus90.setText(Status90dd);
            jP90dd.setBackground(Color.decode("#fcb3ae"));
        } else {
            Status90dd = "AVALIADO";
            JbStatus90.setText(Status90dd);
            jP90dd.setBackground(Color.decode("#a9f5d5"));
        }

        String StatusContra;

        switch (EdStatusContra.getText()) {
            case "1":
                StatusContra = "CONCLUÍDO";
                LbStatusContrato.setText(StatusContra);
                LbStatusContrato.setBackground(Color.decode("#fcb3ae"));
                break;
            case "2":
                StatusContra = "EM ANÁLISE";
                LbStatusContrato.setText(StatusContra);
                LbStatusContrato.setBackground(Color.decode("#fcb3ae"));
                break;
            default:
                StatusContra = "EM ANÁLISE";
                LbStatusContrato.setText(StatusContra);
                LbStatusContrato.setBackground(Color.decode("#fcb3ae"));
                break;
        }
    }

    public void listarContratoEdit() {

        if (EdCodContrato.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Você deve informar o código do contrato para editar", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            ContratoDao cdao = new ContratoDao();

            try {
                for (Contrato c : cdao.readContForCod(EdCodContrato.getText())) {
                    EdCodFuncio.setText(Integer.toString(c.getFr_codFuncionario()));
                    JdcDataAdmi.setDate(c.getDataAdmiContra());
                    EdNomeRespon.setText(c.getResponsContra());
                    JtObserva.setText(c.getObservaContra());
                    validarCodFuncio();

                    if (c.getStatusContra() == 1) {
                        JOptionPane.showMessageDialog(null, "Este contrato já está finalizado\nPara editar o cadastro, você deve romover a avaliação de 90 dias", "ERRO", JOptionPane.ERROR_MESSAGE);
                        habilitarB(1);
                        LbStatusContrato.setText("CONCLUÍDO");
                    } else {
                        habilitarB(3);
                    }

                }
            } catch (ClassNotFoundException ex) {

                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void editarContrato() {
        Contrato co = new Contrato();
        ContratoDao dao = new ContratoDao();

        try {
            for (Contrato c : dao.readContForCod(EdCodContrato.getText())) {

                co.setCodContra(Integer.parseInt(EdCodContrato.getText()));
                co.setFr_codFuncionario(Integer.parseInt(EdCodFuncio.getText()));
                co.setFr_nomeFuncionario(EdNomeFuncio.getText());
                co.setResponsContra(EdNomeRespon.getText());
                co.setObservaContra(JtObserva.getText());
                co.setStatusContra(2);
                co.setStatusContra30dd(2);
                co.setStatusContra90dd(2);
                java.util.Date dataVcContrato = JdcDataAdmi.getDate();
                long dtContrato = dataVcContrato.getTime();
                java.sql.Date dateVencContrato = new java.sql.Date(dtContrato);
                co.setDataAdmiContra(dateVencContrato);
                co.setDataVenc30dd(dateVencContrato);
                co.setDataVenc90dd(dateVencContrato);

                try {

                    if (c.getStatusContra() == 1) {
                        int select;
                        select = JOptionPane.showConfirmDialog(null, "Este contrato já está finalizado\nCaso prosseguir com a edição, os dados de renovação serão pedidos"
                                + "\nDeseja realmente continuar?", "ATENÇÃO", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                        if (select == 0) {
                            dao.updateContrato(co);
                            gerarUpdateData();
                        }
                    } else {
                        dao.updateContrato(co);
                        gerarUpdateData();
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class
                            .getName()).log(Level.SEVERE, null, ex);

                }

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class.getName()).log(Level.SEVERE, null, ex);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        jP30dd = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JdcDataTerm30dd = new com.toedter.calendar.JDateChooser();
        EdRestam30dd = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        EdStatus30dd = new javax.swing.JTextField();
        JbStatus30 = new javax.swing.JLabel();
        BtRemoverContra30dd = new javax.swing.JButton();
        BtRenovarContra30dd = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        BtCancelar = new javax.swing.JButton();
        BtSair = new javax.swing.JButton();
        BtExcluir = new javax.swing.JButton();
        BtNovo = new javax.swing.JButton();
        BtSalvar = new javax.swing.JButton();
        BtEditar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        EdBuscarCod = new javax.swing.JTextField();
        BtBuscarCodFuncio = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        CbxFinalizado = new javax.swing.JCheckBox();
        CbxData90dd = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JdcDataAdmi = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        EdNomeRespon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtObserva = new javax.swing.JTextArea();
        EdCodContrato = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        BtBuscarCodCont = new javax.swing.JButton();
        EdDataBanco30dd = new javax.swing.JTextField();
        EdDataBanco90dd = new javax.swing.JTextField();
        jP90dd = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        JbStatus90 = new javax.swing.JLabel();
        EdRestam90dd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JdcDataTerm90dd = new com.toedter.calendar.JDateChooser();
        BtRemoverContra90dd = new javax.swing.JButton();
        BtRenovarContra90dd = new javax.swing.JButton();
        EdStatus90dd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        LbStatusContrato = new javax.swing.JLabel();
        EdStatusContra = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Controle de Contratos - V1.0-20.0813.0");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 310, 220));

        jP30dd.setBackground(new java.awt.Color(255, 255, 255));
        jP30dd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jP30dd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Data 30 Dias: ");
        jP30dd.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jP30dd.add(JdcDataTerm30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, -1));
        jP30dd.add(EdRestam30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 50, -1));

        jLabel35.setText("Filtrar por: ");
        jP30dd.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Restam: ");
        jP30dd.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 50, 20));
        jP30dd.add(EdStatus30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 20, -1));

        JbStatus30.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 13)); // NOI18N
        JbStatus30.setText("?");
        jP30dd.add(JbStatus30, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 100, 20));

        BtRemoverContra30dd.setBackground(new java.awt.Color(255, 51, 51));
        BtRemoverContra30dd.setText("Remover");
        BtRemoverContra30dd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRemoverContra30ddActionPerformed(evt);
            }
        });
        jP30dd.add(BtRemoverContra30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 80, 30));

        BtRenovarContra30dd.setBackground(new java.awt.Color(0, 204, 51));
        BtRenovarContra30dd.setText("Renovar");
        BtRenovarContra30dd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRenovarContra30ddActionPerformed(evt);
            }
        });
        jP30dd.add(BtRenovarContra30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 80, 30));

        jLabel18.setText("Dias");
        jP30dd.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jLabel19.setText("Status:");
        jP30dd.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, 20));

        jPanel1.add(jP30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 320, 120));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód. Contrato", "Cód. Funcinário", "Nome", "Data Admissão", "Data Term. 30d", "Data Term. 90d"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 650, 170));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_red.png"))); // NOI18N
        BtCancelar.setText("CANCELAR");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(BtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 120, 33));

        BtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair.setText("SAIR");
        BtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSairActionPerformed(evt);
            }
        });
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 78, 33));

        BtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_red.png"))); // NOI18N
        BtExcluir.setText("EXCLUÍR");
        BtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(BtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, 33));

        BtNovo.setBackground(new java.awt.Color(102, 255, 102));
        BtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        BtNovo.setText("NOVO");
        BtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtNovoActionPerformed(evt);
            }
        });
        jPanel4.add(BtNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 33));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 100, 33));

        BtEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtEditar.setText("Salvar");
        BtEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtEditarActionPerformed(evt);
            }
        });
        jPanel4.add(BtEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 33));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 670, 60));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel11.setText("Cód. Funcionário: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 20));

        EdBuscarCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdBuscarCodActionPerformed(evt);
            }
        });
        EdBuscarCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdBuscarCodKeyPressed(evt);
            }
        });
        jPanel1.add(EdBuscarCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 100, -1));

        BtBuscarCodFuncio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodFuncio.setText("Buscar");
        BtBuscarCodFuncio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodFuncioActionPerformed(evt);
            }
        });
        jPanel1.add(BtBuscarCodFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        jLabel34.setText("Filtrar por: ");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, -1, 20));

        CbxFinalizado.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxFinalizado.setText("Contra. Concluídos");
        CbxFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxFinalizadoActionPerformed(evt);
            }
        });
        jPanel1.add(CbxFinalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, 40));

        CbxData90dd.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxData90dd.setText("Venc. 90 Dias");
        CbxData90dd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxData90ddMouseClicked(evt);
            }
        });
        CbxData90dd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxData90ddActionPerformed(evt);
            }
        });
        jPanel1.add(CbxData90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, -1, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setText("Data admissão: ");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));
        jPanel8.add(JdcDataAdmi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 140, -1));

        jLabel17.setText("Responsável:");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel9.setText("Observações: ");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        jPanel8.add(EdNomeRespon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 200, -1));

        JtObserva.setColumns(20);
        JtObserva.setRows(5);
        jScrollPane1.setViewportView(JtObserva);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 280, 80));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 320, 200));
        jPanel1.add(EdCodContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 70, -1));

        jLabel10.setText("Cód. Contrato: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, 20));

        BtBuscarCodCont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodCont.setText("Buscar");
        BtBuscarCodCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodContActionPerformed(evt);
            }
        });
        jPanel1.add(BtBuscarCodCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));
        jPanel1.add(EdDataBanco30dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 70, 10));
        jPanel1.add(EdDataBanco90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 70, 10));

        jP90dd.setBackground(new java.awt.Color(255, 255, 255));
        jP90dd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jP90dd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel6.setText("Data 90 Dias: ");
        jP90dd.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        JbStatus90.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 13)); // NOI18N
        JbStatus90.setText("?");
        jP90dd.add(JbStatus90, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 100, 20));
        jP90dd.add(EdRestam90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 50, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Restam: ");
        jP90dd.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 50, 20));
        jP90dd.add(JdcDataTerm90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, -1));

        BtRemoverContra90dd.setBackground(new java.awt.Color(255, 51, 51));
        BtRemoverContra90dd.setText("Remover");
        BtRemoverContra90dd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRemoverContra90ddActionPerformed(evt);
            }
        });
        jP90dd.add(BtRemoverContra90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 80, 30));

        BtRenovarContra90dd.setBackground(new java.awt.Color(0, 204, 51));
        BtRenovarContra90dd.setText("Renovar");
        BtRenovarContra90dd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRenovarContra90ddActionPerformed(evt);
            }
        });
        jP90dd.add(BtRenovarContra90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 80, 30));
        jP90dd.add(EdStatus90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 20, -1));

        jLabel8.setText("Dias");
        jP90dd.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jLabel20.setText("Status:");
        jP90dd.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, 20));

        jPanel1.add(jP90dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 320, 120));

        LbStatusContrato.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        LbStatusContrato.setText("STATUS");
        jPanel1.add(LbStatusContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));
        jPanel1.add(EdStatusContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 100, -1));

        jTabbedPane1.addTab("Contrato de Trabalho", jPanel1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 670, -1));

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("Controle de Contrato de Trabalho");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed
        validaCadastroContrato();

    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        try {
            deletarContrato();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        habilitarB(1);
        limpaCampos();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtNovoActionPerformed
        habilitarB(2);
        buscarCodContra();
    }//GEN-LAST:event_BtNovoActionPerformed

    private void EdCodFuncioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdCodFuncioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validarCodFuncio();
        }
    }//GEN-LAST:event_EdCodFuncioKeyPressed

    private void BtBuscarCodFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodFuncionarioActionPerformed
        validarCodFuncio();
    }//GEN-LAST:event_BtBuscarCodFuncionarioActionPerformed

    private void EdBuscarCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdBuscarCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdBuscarCodActionPerformed

    private void EdBuscarCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdBuscarCodKeyPressed

    }//GEN-LAST:event_EdBuscarCodKeyPressed

    private void BtBuscarCodFuncioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodFuncioActionPerformed
        try {
            listarContrato();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtBuscarCodFuncioActionPerformed

    private void CbxFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxFinalizadoActionPerformed
        CbxData90dd.setSelected(false);
    }//GEN-LAST:event_CbxFinalizadoActionPerformed

    private void CbxData90ddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxData90ddMouseClicked

    }//GEN-LAST:event_CbxData90ddMouseClicked

    private void CbxData90ddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxData90ddActionPerformed
        CbxFinalizado.setSelected(false);
    }//GEN-LAST:event_CbxData90ddActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        int seectedRow = tabela.getSelectedRow();
        if (tabela.getSelectedRow() != -1) {
            try {
                EdCodContrato.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
                EdDataBanco30dd.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
                EdDataBanco90dd.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());

                java.util.Date date30dd = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 4).toString());
                JdcDataTerm30dd.setDate(date30dd);

                java.util.Date date90dd = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 5).toString());
                JdcDataTerm90dd.setDate(date90dd);

            } catch (ParseException ex) {
                Logger.getLogger(FrContContrato.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                status();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrContContrato.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_tabelaMouseClicked

    private void BtEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEditarActionPerformed
        validaEdicaoContrato();
    }//GEN-LAST:event_BtEditarActionPerformed

    private void BtBuscarCodContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodContActionPerformed
        listarContratoEdit();
    }//GEN-LAST:event_BtBuscarCodContActionPerformed

    private void BtRenovarContra30ddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRenovarContra30ddActionPerformed
        try {
            upadteContrato30dd();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRenovarContra30ddActionPerformed

    private void BtRenovarContra90ddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRenovarContra90ddActionPerformed
        try {
            upadteContrato90dd();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRenovarContra90ddActionPerformed

    private void BtRemoverContra30ddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRemoverContra30ddActionPerformed
        try {
            upadteContrato30ddFor2();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRemoverContra30ddActionPerformed

    private void BtRemoverContra90ddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRemoverContra90ddActionPerformed
        try {
            upadteContrato90ddFor2();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContContrato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRemoverContra90ddActionPerformed

    private void tabelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            try {
                deletarContrato();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrContContrato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabelaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtBuscarCodCont;
    private javax.swing.JButton BtBuscarCodFuncio;
    private javax.swing.JButton BtBuscarCodFuncionario;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtEditar;
    private javax.swing.JButton BtExcluir;
    private javax.swing.JButton BtNovo;
    private javax.swing.JButton BtRemoverContra30dd;
    private javax.swing.JButton BtRemoverContra90dd;
    private javax.swing.JButton BtRenovarContra30dd;
    private javax.swing.JButton BtRenovarContra90dd;
    private javax.swing.JButton BtSair;
    private javax.swing.JButton BtSalvar;
    private javax.swing.JCheckBox CbxData90dd;
    private javax.swing.JCheckBox CbxFinalizado;
    private javax.swing.JTextField EdBuscarCod;
    private javax.swing.JTextField EdCargoFuncio;
    private javax.swing.JTextField EdCodContrato;
    private javax.swing.JTextField EdCodFuncio;
    private javax.swing.JFormattedTextField EdCpfFuncio;
    private javax.swing.JTextField EdDataBanco30dd;
    private javax.swing.JTextField EdDataBanco90dd;
    private javax.swing.JTextField EdNomeFuncio;
    private javax.swing.JTextField EdNomeRespon;
    private javax.swing.JTextField EdRestam30dd;
    private javax.swing.JTextField EdRestam90dd;
    private javax.swing.JTextField EdSetorFuncio;
    private javax.swing.JTextField EdStatus30dd;
    private javax.swing.JTextField EdStatus90dd;
    private javax.swing.JTextField EdStatusContra;
    private javax.swing.JTextField EdTurnoFuncio;
    private javax.swing.JLabel JbStatus30;
    private javax.swing.JLabel JbStatus90;
    private com.toedter.calendar.JDateChooser JdcDataAdmi;
    private com.toedter.calendar.JDateChooser JdcDataTerm30dd;
    private com.toedter.calendar.JDateChooser JdcDataTerm90dd;
    private javax.swing.JTextArea JtObserva;
    private javax.swing.JLabel LbStatusContrato;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP30dd;
    private javax.swing.JPanel jP90dd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
