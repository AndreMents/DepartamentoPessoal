/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.CoparticipacaoDao;
import imac.dp.dao.FuncionarioDao;
import imac.dp.dao.ParcelaDao;
import imac.dp.dao.ProcedimentoDao;
import imac.dp.model.Coparticipacao;
import imac.dp.model.Funcionario;
import imac.dp.model.Parcela;
import imac.dp.model.Procedimento;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class FrContParticipacao extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrContParticipacao
     */
    public String LbvalorTT;

    public FrContParticipacao() {
        initComponents();
        habilitarB(1);
        EdsNaoVisiveis();
    }

    public void habilitarB(int op) {
        switch (op) {
            case 1:

                BtNovo.setEnabled(true);
                BtGerarParcela.setEnabled(false);
                BtGerarParcela.setVisible(true);
                BtExcluir.setVisible(true);
                BtExcluir.setEnabled(false);
                BtSalvar.setEnabled(false);
                BtCalcular.setEnabled(false);
                BtCancelar.setEnabled(false);

                //buscar
                EdCodCop.setEnabled(true);
                EdCod.setVisible(false);
                BtBuscarCodFuncio.setEnabled(false);
                BtBuscarCodPro.setEnabled(false);
                //funcio

                EdNomeFuncio.setEnabled(false);
                EdCpfFuncio.setEnabled(false);
                EdCargoFuncio.setEnabled(false);
                EdSetorFuncio.setEnabled(false);
                EdNomeFuncio.setEnabled(false);
                EdCodFuncio.setEnabled(false);

                //pro
                EdCargoFuncio.setEnabled(false);
                EdNomePro.setEnabled(false);
                EdLocalPro.setEnabled(false);
                EdMedicoPro.setEnabled(false);
                EdValorPro.setEnabled(false);
                EdCodPro.setEnabled(false);
                EdValorCoparti.setEnabled(false);
                EdValorCoparti.setEditable(false);

                //data
                JDcDataPro.setEnabled(false);
                JDcDataVencPar.setEnabled(false);
                EdParcelaCoparti.setEnabled(false);
                EdResultCopartiParcelado.setEnabled(false);

                EdNomeFuncio.requestFocus();
                break;

            case 2:
                ultimoCodCop();

                EdCod.setVisible(false);

                BtNovo.setEnabled(false);
                BtGerarParcela.setEnabled(true);
                BtGerarParcela.setVisible(true);
                BtExcluir.setVisible(true);
                BtExcluir.setEnabled(false);
                BtRemoverPro.setEnabled(true);
                BtSalvar.setEnabled(true);
                BtCalcular.setEnabled(true);
                BtCancelar.setEnabled(true);

                //buscar
                BtBuscarCodFuncio.setEnabled(true);
                BtBuscarCodPro.setEnabled(true);
                //funcio
                EdCodFuncio.setEnabled(true);
                EdCodCop.setEnabled(true);
                EdNomeFuncio.setEnabled(true);
                EdCpfFuncio.setEnabled(true);
                EdCargoFuncio.setEnabled(true);
                EdSetorFuncio.setEnabled(true);
                EdNomeFuncio.setEnabled(true);

                //pro
                EdCargoFuncio.setEnabled(true);
                EdNomePro.setEnabled(true);
                EdLocalPro.setEnabled(true);
                EdMedicoPro.setEnabled(true);
                EdValorPro.setEnabled(true);
                EdCodPro.setEnabled(true);
                EdValorCoparti.setEnabled(true);
                EdValorCoparti.setEditable(false);

                //data
                JDcDataPro.setEnabled(true);
                JDcDataVencPar.setEnabled(true);
                EdParcelaCoparti.setEnabled(true);
                EdResultCopartiParcelado.setEnabled(true);

                EdNomeFuncio.requestFocus();

                EdCodPro.setEnabled(true);
                BtAdcPro.setEnabled(true);
                BtRemoverPro.setEnabled(true);
                BtGerarParcela.setEnabled(true);
                BtSalvar.setEnabled(true);
                tabelaProcedi.setEnabled(true);

                break;

            case 3:

                EdCod.setVisible(false);

                BtNovo.setEnabled(false);
                BtGerarParcela.setEnabled(true);
                BtGerarParcela.setVisible(true);
                BtSalvar.setEnabled(true);
                BtExcluir.setVisible(true);
                BtExcluir.setEnabled(true);
                BtRemoverPro.setEnabled(true);
                BtCalcular.setEnabled(true);
                BtCancelar.setEnabled(true);

                //buscar
                BtBuscarCodFuncio.setEnabled(true);
                BtBuscarCodPro.setEnabled(true);

                //funcio
                EdCodCop.setEnabled(false);
                EdNomeFuncio.setEnabled(true);
                EdCpfFuncio.setEnabled(true);
                EdCargoFuncio.setEnabled(true);
                EdSetorFuncio.setEnabled(true);
                EdNomeFuncio.setEnabled(true);
                EdCodFuncio.setEnabled(false);

                //pro
                EdCargoFuncio.setEnabled(true);
                EdNomePro.setEnabled(true);
                EdLocalPro.setEnabled(true);
                EdMedicoPro.setEnabled(true);
                EdValorPro.setEnabled(true);
                EdCodPro.setEnabled(true);
                EdValorCoparti.setEnabled(true);
                EdValorCoparti.setEditable(false);

                //data
                JDcDataPro.setEnabled(true);
                JDcDataVencPar.setEnabled(true);
                EdParcelaCoparti.setEnabled(true);
                EdResultCopartiParcelado.setEnabled(true);

                break;

        }
    }

    public void EdsNaoVisiveis() {
        EdStatusOff.setVisible(false);
        EdCodPar.setVisible(false);
        EdnumMesPar.setVisible(false);
    }

    public void adicionar() {

        JDcDataPro.setDate(null);
        JDcDataVencPar.setDate(null);
        EdCodPro.setText(null);
        EdNomePro.setText(null);
        EdLocalPro.setText(null);
        EdValorPro.setText(null);
        EdMedicoPro.setText(null);
        EdParcelaCoparti.setText(null);

    }

    public void limpaCampos() {
        EdCodFuncio.setText(null);
        EdNomeFuncio.setText(null);
        EdSetorFuncio.setText(null);
        EdCargoFuncio.setText(null);
        EdCpfFuncio.setText(null);
        JDcDataVencPar.setDate(null);
        EdValorCoparti.setText(null);
        EdCodCop.setText(null);
        EdCodPro.setText(null);
        EdNomePro.setText(null);
        EdLocalPro.setText(null);
        EdValorPro.setText(null);
        EdMedicoPro.setText(null);
        EdParcelaCoparti.setText(null);
        EdResultCopartiParcelado.setText(null);

    }

    public void limpaCamposPar() {

        EdCodFuncionario.setText(null);
        EdCodParcela.setText(null);
        EdCodCoparticipacao.setText(null);
        EdValorParcela.setText(null);
        EdNomeFuncionario.setText(null);
    }

    public void parcelasGeradas() {
        EdCodPro.setEnabled(false);
        BtAdcPro.setEnabled(false);
        BtRemoverPro.setEnabled(false);
        BtGerarParcela.setEnabled(false);
        BtSalvar.setEnabled(false);
        BtExcluir.setEnabled(false);
        tabelaProcedi.setEnabled(false);
        BtBuscarCodPro.setEnabled(false);
        BtCalcular.setEnabled(false);
        EdNomeFuncio.setEnabled(false);
        EdCpfFuncio.setEnabled(false);
        EdCargoFuncio.setEnabled(false);
        EdSetorFuncio.setEnabled(false);
        EdNomePro.setEnabled(false);
        EdValorPro.setEnabled(false);
        EdLocalPro.setEnabled(false);
        EdMedicoPro.setEnabled(false);
        JDcDataPro.setEnabled(false);
        EdValorCoparti.setEnabled(false);
        EdParcelaCoparti.setEnabled(false);
        EdResultCopartiParcelado.setEnabled(false);
        JDcDataVencPar.setEnabled(false);
        BtBuscarCodFuncio.setEnabled(false);
        BtBuscarCodPro.setEnabled(false);

    }

    public void parcelasNGeradas() {
        EdCodPro.setEnabled(true);
        BtAdcPro.setEnabled(true);
        BtRemoverPro.setEnabled(true);
        BtGerarParcela.setEnabled(true);
        BtSalvar.setEnabled(true);
        BtExcluir.setEnabled(true);
        tabelaProcedi.setEnabled(true);
        BtBuscarCodPro.setEnabled(true);
        BtCalcular.setEnabled(true);
        EdNomeFuncio.setEnabled(true);
        EdCpfFuncio.setEnabled(true);
        EdCargoFuncio.setEnabled(true);
        EdSetorFuncio.setEnabled(true);
        EdNomePro.setEnabled(true);
        EdValorPro.setEnabled(true);
        EdLocalPro.setEnabled(true);
        EdMedicoPro.setEnabled(true);
        JDcDataPro.setEnabled(true);
        EdValorCoparti.setEnabled(true);
        EdParcelaCoparti.setEnabled(true);
        EdResultCopartiParcelado.setEnabled(true);
        JDcDataVencPar.setEnabled(true);
        BtBuscarCodFuncio.setEnabled(true);
        BtBuscarCodPro.setEnabled(true);

    }

    public void ultimoCodCop() {
        CoparticipacaoDao dao = new CoparticipacaoDao();

        try {
            for (Coparticipacao c : dao.readCodCop()) {

                int codCop;
                codCop = c.getCodCop() + 1;
                EdCodCop.setText(Integer.toString(codCop));

            }

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }

//COPARTICIPAÇÃO 
    public void checkCreateCop() throws SQLException, ParseException {

        if (EdCodCop.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO\n Não foi gerado um código de coparticipação!");

        } else {
            createCop();
        }
    }

    public void createCop() throws ParseException, SQLException {

        try {

            Coparticipacao c = new Coparticipacao();
            CoparticipacaoDao dao = new CoparticipacaoDao();
            c.setCodCop(Integer.parseInt(EdCodCop.getText()));
            c.setFr_codFuncionario(Integer.parseInt(EdCodFuncio.getText()));

            dao.createCop(c);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);

        }

    }

    public void checkCreateRealiza() throws SQLException, ParseException {

        if (EdCodPro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve informar o código da coparticipação!");

        } else {
            realizaProcedi();
        }
    }

    public void realizaProcedi() throws ParseException, SQLException {

        Coparticipacao c = new Coparticipacao();
        CoparticipacaoDao dao = new CoparticipacaoDao();
        c.setCodCop(Integer.parseInt(EdCodCop.getText()));
        c.setFr_codFuncionario(Integer.parseInt(EdCodFuncio.getText()));
        c.setLocalPro(EdLocalPro.getText());
        c.setMedicoPro(EdMedicoPro.getText());
        java.util.Date datePar = JDcDataPro.getDate();
        long dtP = datePar.getTime();
        java.sql.Date dateParcela = new java.sql.Date(dtP);
        c.setDataPro(dateParcela);
        c.setFr_codPro(Integer.parseInt(EdCodPro.getText()));

        try {
            dao.realizaProcedi(c);
            adicionar();
            listarCop();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }

    public void validaCop() throws ParseException, SQLException {
        CoparticipacaoDao pdao = new CoparticipacaoDao();

        if (EdCodCop.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Atenção!\nPreencha o código da coparticipação para buscar!");

        } else {
            try {
                if (pdao.checkCreate(EdCodCop.getText())) {
                    listarCop();
                } else {
                    JOptionPane.showMessageDialog(null, "Atenção!\nCódigo coparticipação não encontrado");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
        }

    }

    public void listarCop() throws ParseException, SQLException {

        DefaultTableModel modelo = (DefaultTableModel) tabelaProcedi.getModel();
        modelo.setNumRows(0);
        CoparticipacaoDao pdao = new CoparticipacaoDao();

        try {
            for (Coparticipacao c : pdao.readCopForCod(EdCodCop.getText())) {
                modelo.addRow(new Object[]{
                    c.getCod(),
                    c.getFr_codFuncionario(),
                    c.getFr_nomeFuncionario(),
                    c.getFr_cpfFuncionario(),
                    c.getFr_codPro(),
                    c.getFr_nomePro(),
                    c.getFr_valorPro(),
                    c.getDataPro()
                });
                habilitarB(3);

                EdCodFuncio.setText(Integer.toString(c.getFr_codFuncionario()));
                int GeradaParcela = c.getGeradoPar();
                if (GeradaParcela == 1) {
                    LbStatusGeradaPar.setText("NÃO GERADA");
                    LbStatusGeradaPar.setForeground(Color.decode("#ff0000"));
                    parcelasNGeradas();

                } else if (GeradaParcela == 2) {
                    LbStatusGeradaPar.setText("GERADA");
                    LbStatusGeradaPar.setForeground(Color.decode("#0f943b"));
                    parcelasGeradas();

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro");
                }

                buscarValorCop();
                buscaCodFuncio();

            }

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }

    public void limparTabelaCop() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaProcedi.getModel();
        modelo.setNumRows(0);
    }

    public void buscarValorCop() {
        CoparticipacaoDao pdao = new CoparticipacaoDao();
        try {
            for (Coparticipacao c : pdao.readSumCop(Integer.parseInt(EdCodCop.getText()))) {

                EdValorCoparti.setText(Double.toString(c.getValorCop()));

            }
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }

    public void calcularParcelas() {
        if (EdValorCoparti.getText().equals("") || EdParcelaCoparti.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Atenção\nVocê deve preencher o número de parcelas");

        } else {
            try {
                Double valorParcel = Double.parseDouble(EdValorCoparti.getText());
                int numeroParcela = Integer.parseInt(EdParcelaCoparti.getText());
                Double resultado = (valorParcel / numeroParcela);
                double d = resultado;
                BigDecimal bd = new BigDecimal(d).setScale(2, RoundingMode.HALF_EVEN);
                EdResultCopartiParcelado.setText(Double.toString(bd.doubleValue()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Atenção\nPara gerar a parcela você deve preencher todos os campos");
            }

        }

    }

    public void validaParcela() throws ClassNotFoundException {
        if (EdParcelaCoparti.getText().equals("") || EdValorCoparti.getText().equals("") || EdResultCopartiParcelado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Atenção\nPara gerar a parcela você deve preencher todos os campos");
        } else if (JDcDataVencPar.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Atenção\nVocê deve preencher a data de vencimento da parcela!");
        } else {

            gerarParcela();
        }
    }

    public void gerarParcela() throws ClassNotFoundException {
        int par = 0;
        int parP = Integer.parseInt(EdParcelaCoparti.getText());
        Parcela p = new Parcela();
        ParcelaDao dao = new ParcelaDao();

        while (par < parP) {

            int codFuncio = Integer.parseInt(EdCodFuncio.getText());
            p.setFr_codCop(Integer.parseInt(EdCodCop.getText()));
            p.setFr_codFuncionario(codFuncio);
            p.setFr_nomeFuncionario(EdNomeFuncio.getText());
            p.setValorParcela(Double.parseDouble(EdResultCopartiParcelado.getText()));

            java.util.Date GerardateVencPar = JDcDataVencPar.getDate();
            long dtVP = GerardateVencPar.getTime();
            java.sql.Date GerardateVencParcela = new java.sql.Date(dtVP);
            p.setDataVencPar(GerardateVencParcela);
            p.setStatusParcela(1);

            try {
                dao.createParcela(p);
                geradoParcela();

                BtNovo.setEnabled(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }

            EdnumMesPar.setText(Integer.toString(par));
            gerarUpdateDataParcela();
            par++;
        }
        limparTabelaCop();
        limpaCampos();
        habilitarB(1);
        JOptionPane.showMessageDialog(null, "Salvo com sucesso" + "\nForam geradas = " + par + " parcelas de desconto");

    }

    public void gerarUpdateDataParcela() throws ClassNotFoundException {
        ParcelaDao pdao = new ParcelaDao();

        try {
            for (Parcela p : pdao.readMaxCodPar()) {

                int codPar;
                codPar = p.getCodParcela();
                EdCodPar.setText(Integer.toString(codPar));
            }

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

        Parcela pa = new Parcela();
        ParcelaDao dao = new ParcelaDao();

        pa.setCodParcela(Integer.parseInt(EdCodPar.getText()));
        pa.setNumMesParcela(EdnumMesPar.getText());
        try {
            dao.updateDateParcela(pa);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }

    public void geradoParcela() throws ClassNotFoundException {
        ParcelaDao pdao = new ParcelaDao();
        Coparticipacao cop = new Coparticipacao();

        cop.setGeradoPar(2);
        cop.setCodCop(Integer.parseInt(EdCodCop.getText()));

        try {
            pdao.updateGeradoParcela(cop);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }

    public void delatadoParcela() throws ClassNotFoundException {
        ParcelaDao pdao = new ParcelaDao();
        Coparticipacao cop = new Coparticipacao();

        cop.setGeradoPar(1);
        cop.setCodCop(Integer.parseInt(EdCodCoparticipacao.getText()));

        try {
            pdao.updateGeradoParcela(cop);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }

    public void listar() {

        CoparticipacaoDao pdao = new CoparticipacaoDao();

        if (EdCodCop.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código!");
        } else {
            try {
                for (Coparticipacao c : pdao.readCopForCod(EdCodCop.getText())) {

                    EdCodCop.setText(Integer.toString(c.getCodCop()));
                    EdCodFuncio.setText(Integer.toString(c.getFr_codFuncionario()));
                    EdNomeFuncio.setText(c.getFr_nomeFuncionario());
                    EdCpfFuncio.setText(c.getFr_cpfFuncionario());
                    EdCargoFuncio.setText(c.getFr_cargoFuncionario());
                    EdSetorFuncio.setText(c.getFr_setorFuncionario());
                    EdCodPro.setText(Integer.toString(c.getFr_codPro()));
                    EdNomePro.setText(c.getFr_nomePro());
                    EdValorPro.setText(Double.toString(c.getFr_valorPro()));
                    EdParcelaCoparti.setText(Integer.toString(c.getFr_parcelaPro()));
                    EdLocalPro.setText(c.getLocalPro());
                    EdMedicoPro.setText(c.getMedicoPro());
                    JDcDataPro.setDate(c.getDataPro());
                    JDcDataVencPar.setDate(c.getDataVencPro());

                    habilitarB(3);
                }

            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
        }

    }

    public void editarCop() throws ParseException {
        Coparticipacao c = new Coparticipacao();
        CoparticipacaoDao dao = new CoparticipacaoDao();

        if (EdNomeFuncio.getText().equals("")
                || EdNomeFuncio.getText().equals("")
                || EdCpfFuncio.getText().equals("")
                || EdSetorFuncio.getText().equals("")
                || EdCargoFuncio.getText().equals("")
                || EdCodPro.getText().equals("")
                || EdNomePro.getText().equals("")
                || EdNomePro.getText().equals("")
                || EdValorPro.getText().equals("")
                || EdParcelaCoparti.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
        } else {

            try {
                c.setFr_codFuncionario(Integer.parseInt(EdCodFuncio.getText()));
                c.setFr_nomeFuncionario(EdNomeFuncio.getText());
                c.setFr_cpfFuncionario(EdCpfFuncio.getText());
                c.setFr_cargoFuncionario(EdCargoFuncio.getText());
                c.setFr_setorFuncionario(EdSetorFuncio.getText());
                c.setFr_codPro(Integer.parseInt(EdCodPro.getText()));
                c.setFr_nomePro(EdNomePro.getText());
                c.setFr_valorPro(Double.parseDouble(EdValorPro.getText()));
                c.setFr_parcelaPro(Integer.parseInt(EdParcelaCoparti.getText()));
                c.setLocalPro(EdLocalPro.getText());
                c.setMedicoPro(EdMedicoPro.getText());

                java.util.Date datePar = JDcDataPro.getDate();
                long dtP = datePar.getTime();
                java.sql.Date dateParcela = new java.sql.Date(dtP);
                c.setDataPro(dateParcela);

                java.util.Date dateVencPar = JDcDataVencPar.getDate();
                long dtVP = dateVencPar.getTime();
                java.sql.Date dateVencParcela = new java.sql.Date(dtVP);
                c.setDataVencPro(dateVencParcela);

                c.setCodCop(Integer.parseInt(EdCodCop.getText()));

                dao.update(c);
                limpaCampos();
                habilitarB(1);

            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }

        }
    }

    public void editNGeradoParcela() throws ClassNotFoundException {
        ParcelaDao pdao = new ParcelaDao();
        Coparticipacao cop = new Coparticipacao();

        cop.setGeradoPar(1);
        cop.setCodCop(Integer.parseInt(EdCodCoparticipacao.getText()));

        try {
            pdao.updateGeradoParcela(cop);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }

    public void deletarCop() throws ClassNotFoundException {
        Coparticipacao c = new Coparticipacao();
        CoparticipacaoDao dao = new CoparticipacaoDao();

        if (dao.checkCreatePro(EdCodCop.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Você deve revomer os precedimentos lançados para deletar a coparticipação!");
        } else {

            if (EdCodCop.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Informe o código da coparticipação para excluír");
            } else {
                c.setCodCop(Integer.parseInt(EdCodCop.getText()));
                try {

                    String nomepro = EdNomePro.getText();
                    String nome = EdNomeFuncio.getText();

                    int input = JOptionPane.showConfirmDialog(null,
                            " ATENÇÃO!" + "\nDeseja mesmo excluír a ficha de coparticipação do : " + nomepro + "\nCadastrado na conta do funcionário: " + nome + "?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (input == 0) {
                        dao.delete(c);
                        habilitarB(1);
                        limpaCampos();

                    } else {
                        JOptionPane.showMessageDialog(null, "Exclusão cancelada");

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
                }

            }

        }

    }

    public void cancelarCop() throws ClassNotFoundException {
        Coparticipacao c = new Coparticipacao();
        CoparticipacaoDao dao = new CoparticipacaoDao();

        if (LbStatusGeradaPar.getText().equals("GERADA")) {
            habilitarB(1);
            limpaCampos();
            limparTabelaCop();
        } else {

            int input = JOptionPane.showConfirmDialog(null, "Deseja cancelar o lançamento?!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (!dao.checkCreatePro(EdCodCop.getText())) {
                    c.setCodCop(Integer.parseInt(EdCodCop.getText()));
                    dao.delete(c);
                    habilitarB(1);
                    limpaCampos();
                    limparTabelaCop();
                    
                } else {
                    habilitarB(1);
                    limpaCampos();
                    limparTabelaCop();

                }
            }
        }

    }

    public void removerProcedimento() throws ParseException, SQLException {
        Coparticipacao c = new Coparticipacao();
        CoparticipacaoDao dao = new CoparticipacaoDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecionar o procedimento para remover da ficha");
        } else {
            c.setCod(Integer.parseInt(EdCod.getText()));
            try {
                dao.deleteForCod(c);
                listarCop();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }

        }

    }

    public void buscaCodFuncio() throws ParseException, SQLException {
        FuncionarioDao pdao = new FuncionarioDao();

        if (EdCodFuncio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do funcionário!");
        } else {

            try {
                if (pdao.checkCod(EdCodFuncio.getText())) {
                    listarFuncioCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado");

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
        }
    }

    public void validarCodCadastro() throws ParseException, SQLException {
        FuncionarioDao pdao = new FuncionarioDao();

        if (EdCodFuncio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do funcionário!");
        } else {

            try {
                if (pdao.checkCod(EdCodFuncio.getText())) {
                    listarFuncioCod();
                    checkCreateCop();
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado");

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
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

        }
    }

    public void validarCodList() {
        ProcedimentoDao pdao = new ProcedimentoDao();

        if (EdCodPro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código da Especialidade!");
        } else {

            try {
                if (pdao.checkCod(EdCodPro.getText())) {
                    listaProCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Especialidade não encontrada");

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
        }
    }

    public void listaProCod() {

        ProcedimentoDao pdao = new ProcedimentoDao();

        try {
            for (Procedimento usu : pdao.readProForCod(EdCodPro.getText())) {
                EdNomePro.setText(usu.getNomePro());
                EdValorPro.setText(Double.toString(usu.getValorPro()));

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, " Procedimento não encontrado");
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);

        }
    }

    public void deletarParcela() throws ParseException {
        Parcela p = new Parcela();
        ParcelaDao dao = new ParcelaDao();

        if (EdCodParcela.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código da parcela para excluír");
        } else {
            p.setCodParcela(Integer.parseInt(EdCodParcela.getText()));
            try {

                String valor = EdValorParcela.getText();
                String codFuncio = EdCodFuncionario.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o procedimento " + valor + "\nCadastrado na conta do funcionário Nº: " + codFuncio + "?", null, JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    dao.delete(p);
                    delatadoParcela();
                    editNGeradoParcela();
                    habilitarB(1);
                    limpaCamposPar();
                    listarParcelas();
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");

                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }

        }
    }

    public void limparTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        ParcelaDao dao = new ParcelaDao();
        try {
            for (Parcela p : dao.readParForStatusPaga()) {
                modelo.addRow(new Object[]{});

            }
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }

    public void listarParcelas() throws ParseException, ClassNotFoundException {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        ParcelaDao dao = new ParcelaDao();
        DecimalFormat df = new DecimalFormat("R$ ##.##");

        // BUSCA TODAS AS PARCELAS PAGAS
        if (EdBuscarCod.getText().equals("")) {
            if (CbxPaga.isSelected()) {

                try {
                    for (Parcela p : dao.readParForStatusPaga()) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            "0",
                            p.getStatusParcela()});

                    }
                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
                }

                //BUSCA TODAS AS PARCELAS NÃO PAGAS
            } else if (CbxNPaga.isSelected()) {
                try {
                    for (Parcela p : dao.readParForStatusNPaga()) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
                }

            } else if (CbxFiltroDatas.isSelected()) {

                if (JdcFiltraUltiParInicial.getDate() == null & JdcFiltraUltiParFinal.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Você deve informar as datas do filtro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    java.util.Date dataIni = JdcFiltraUltiParInicial.getDate();
                    long dtInicial = dataIni.getTime();
                    java.sql.Date dateParcelaInicial = new java.sql.Date(dtInicial);

                    java.util.Date dataFin = JdcFiltraUltiParFinal.getDate();
                    long dtFinal = dataFin.getTime();
                    java.sql.Date dateParcelaFinal = new java.sql.Date(dtFinal);

                    for (Parcela p : dao.readParForDataAndNPaga(dateParcelaInicial, dateParcelaFinal)) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});
                    }
                }
            } else if (CbxFiltroDatas.isSelected() & CbxPaga.isSelected()) {
                if (JdcFiltraUltiParInicial.getDate() == null & JdcFiltraUltiParFinal.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Você deve informar as datas do filtro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    java.util.Date dataIni = JdcFiltraUltiParInicial.getDate();
                    long dtInicial = dataIni.getTime();
                    java.sql.Date dateParcelaInicial = new java.sql.Date(dtInicial);

                    java.util.Date dataFin = JdcFiltraUltiParFinal.getDate();
                    long dtFinal = dataFin.getTime();
                    java.sql.Date dateParcelaFinal = new java.sql.Date(dtFinal);

                    for (Parcela p : dao.readParForDataAndPaga(dateParcelaInicial, dateParcelaFinal)) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});
                    }
                }
            } else if (CbxFiltroDatas.isSelected() & CbxNPaga.isSelected()) {
                if (JdcFiltraUltiParInicial.getDate() == null & JdcFiltraUltiParFinal.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Você deve informar as datas do filtro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    java.util.Date dataIni = JdcFiltraUltiParInicial.getDate();
                    long dtInicial = dataIni.getTime();
                    java.sql.Date dateParcelaInicial = new java.sql.Date(dtInicial);

                    java.util.Date dataFin = JdcFiltraUltiParFinal.getDate();
                    long dtFinal = dataFin.getTime();
                    java.sql.Date dateParcelaFinal = new java.sql.Date(dtFinal);

                    for (Parcela p : dao.readParForDataAndNPaga(dateParcelaInicial, dateParcelaFinal)) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});
                    }
                }
            } else {
                try {
                    for (Parcela p : dao.readParcela()) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});

                    }
                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrContParticipacao.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (!EdBuscarCod.getText().equals("")) {
            if (CbxPaga.isSelected()) {
                try {
                    for (Parcela p : dao.readParForUserAndStatusPaga(EdBuscarCod.getText())) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});

                    }
                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrContParticipacao.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else if (CbxNPaga.isSelected()) {
                try {
                    for (Parcela p : dao.readParForUserAndStatusNPaga(EdBuscarCod.getText())) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrContParticipacao.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    for (Parcela p : dao.readParForCod(EdBuscarCod.getText())) {
                        modelo.addRow(new Object[]{
                            p.getCodParcela(),
                            p.getFr_codCop(),
                            p.getFr_codFuncionario(),
                            p.getFr_nomeFuncionario(),
                            df.format(p.getValorParcela()),
                            p.getDataVencPar(),
                            p.getDiasAtrasoPar() * -1,
                            p.getStatusParcela()});

                    }
                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrContParticipacao.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void pagarParcela() throws ParseException {

        if (tabela.getSelectedRow() != -1) {
            Parcela p = new Parcela();
            ParcelaDao dao = new ParcelaDao();

            if (EdStatusOff.getText().equals("2")) {
                JOptionPane.showMessageDialog(null, "Está parcela já está paga!");

            } else {
                p.setStatusParcela(2);
                p.setCodParcela(Integer.parseInt(EdCodParcela.getText()));
                try {
                    String valor = EdValorParcela.getText();
                    String nomeFuncio = EdNomeFuncionario.getText();
                    String numeroPar = EdCodParcela.getText();
                    int input = JOptionPane.showConfirmDialog(null,
                            " ATENÇÃO!"
                            + "\n"
                            + "\n Deseja mesmo realizar o pagamento da parcela Nº " + numeroPar + " no valor de: R$ " + valor
                            + "\n Cadastrada na conta do funcionário: " + nomeFuncio + " ?"
                            + "\n", null, JOptionPane.YES_NO_CANCEL_OPTION);
                    if (input == 0) {
                        dao.updateParcela(p);
                        listarParcelas();
                        limpaCampos();
                        habilitarB(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Pagamento cancelado!");

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);

                }
            }

        }
    }

    public void removerPargamento() throws ParseException {

        if (tabela.getSelectedRow() != -1) {
            Parcela p = new Parcela();
            ParcelaDao dao = new ParcelaDao();

            if (EdStatusOff.getText().equals("1")) {
                JOptionPane.showMessageDialog(null, "Está parcela ainda não foi paga!");

            } else {

                p.setStatusParcela(1);
                p.setCodParcela(Integer.parseInt(EdCodParcela.getText()));
                try {
                    String valor = EdValorParcela.getText();
                    String nomeFuncio = EdNomeFuncionario.getText();
                    String numeroPar = EdCodParcela.getText();
                    int input = JOptionPane.showConfirmDialog(null,
                            " ATENÇÃO!"
                            + "\n"
                            + "\n Deseja mesmo remover o pagamento da parcela Nº " + numeroPar + " no valor de: R$ " + valor
                            + "\n Cadastrada na conta do funcionário: " + nomeFuncio + " ?"
                            + "\n", null, JOptionPane.YES_NO_CANCEL_OPTION);
                    if (input == 0) {
                        dao.updateParcela(p);
                        listarParcelas();
                        limpaCampos();
                        habilitarB(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Atualização cancelada!");

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);

                }
            }

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BtExcluir = new javax.swing.JButton();
        BtNovo = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        BtSair = new javax.swing.JButton();
        BtSalvar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JDcDataPro = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        EdSetorFuncio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EdCodFuncio = new javax.swing.JTextField();
        BtBuscarCodFuncio = new javax.swing.JButton();
        EdNomeFuncio = new javax.swing.JTextField();
        EdCargoFuncio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        EdCpfFuncio = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        EdValorCoparti = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        EdParcelaCoparti = new javax.swing.JTextField();
        EdResultCopartiParcelado = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        JDcDataVencPar = new com.toedter.calendar.JDateChooser();
        BtGerarParcela = new javax.swing.JButton();
        BtCalcular = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        LbStatusGeradaPar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        EdCodCop = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProcedi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        BtBuscar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        EdCodPro = new javax.swing.JTextField();
        BtBuscarCodPro = new javax.swing.JButton();
        EdNomePro = new javax.swing.JTextField();
        EdLocalPro = new javax.swing.JTextField();
        EdMedicoPro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        EdValorPro = new javax.swing.JTextField();
        BtRemoverPro = new javax.swing.JButton();
        BtAdcPro = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        EdCod = new javax.swing.JTextField();
        EdCodPar = new javax.swing.JTextField();
        EdnumMesPar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        BtBuscarCodFuncio1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        EdBuscarCod = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        EdCodParcela = new javax.swing.JTextField();
        EdCodFuncionario = new javax.swing.JTextField();
        EdCodCoparticipacao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        EdNomeFuncionario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        BtExcluir1 = new javax.swing.JButton();
        BtCancelar1 = new javax.swing.JButton();
        BtSair1 = new javax.swing.JButton();
        EdStatusOff = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        CbxPaga = new javax.swing.JCheckBox();
        CbxNPaga = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        JdcDatavenc = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        EdValorParcela = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LbstatusParcela = new javax.swing.JLabel();
        EdDiasEmAtraso = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        JdcFiltraUltiParInicial = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        JdcFiltraUltiParFinal = new com.toedter.calendar.JDateChooser();
        CbxFiltroDatas = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Controle de Coparticipações - V1.0-20.0810");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_red.png"))); // NOI18N
        BtExcluir.setText("EXCLUÍR");
        BtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(BtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 100, 33));

        BtNovo.setBackground(new java.awt.Color(102, 255, 102));
        BtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        BtNovo.setText("NOVO");
        BtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtNovoActionPerformed(evt);
            }
        });
        jPanel4.add(BtNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 33));

        BtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_red.png"))); // NOI18N
        BtCancelar.setText("CANCELAR");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(BtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 120, 33));

        BtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair.setText("SAIR");
        BtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSairActionPerformed(evt);
            }
        });
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 78, 33));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, 33));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1010, 60));

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel1.setText("Cadastrar novo Procedimento Médico");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(685, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Data Procedimento: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, 20));
        jPanel2.add(JDcDataPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 120, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, 270, 50));

        jLabel11.setText("Configurar parcelas:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, 30));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Nome: ");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel9.setText("CPF: ");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        EdSetorFuncio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdSetorFuncioActionPerformed(evt);
            }
        });
        jPanel6.add(EdSetorFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 277, -1));

        jLabel10.setText("Cargo: ");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel2.setText("Cód. Funcionário: ");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 20, -1, -1));

        EdCodFuncio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdCodFuncioActionPerformed(evt);
            }
        });
        EdCodFuncio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdCodFuncioKeyPressed(evt);
            }
        });
        jPanel6.add(EdCodFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 14, 110, -1));

        BtBuscarCodFuncio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodFuncio.setText("Buscar");
        BtBuscarCodFuncio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodFuncioActionPerformed(evt);
            }
        });
        jPanel6.add(BtBuscarCodFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        EdNomeFuncio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdNomeFuncioActionPerformed(evt);
            }
        });
        jPanel6.add(EdNomeFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 277, -1));
        jPanel6.add(EdCargoFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 277, -1));

        jLabel13.setText("Setor:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        try {
            EdCpfFuncio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel6.add(EdCpfFuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 102, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 350, 180));

        jLabel16.setText("Funcionário:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 70, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setText("Valor total: ");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 20));
        jPanel7.add(EdValorCoparti, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, -1));

        jLabel32.setText("Parcelar em:");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 20));

        EdParcelaCoparti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdParcelaCopartiKeyPressed(evt);
            }
        });
        jPanel7.add(EdParcelaCoparti, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 120, -1));

        EdResultCopartiParcelado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdResultCopartiParceladoKeyPressed(evt);
            }
        });
        jPanel7.add(EdResultCopartiParcelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 120, -1));

        jLabel39.setText("Valor Parcelado: ");
        jPanel7.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel21.setText("Data Vencimento: ");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, 20));
        jPanel7.add(JDcDataVencPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 120, -1));

        BtGerarParcela.setText("GERAR");
        BtGerarParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtGerarParcelaActionPerformed(evt);
            }
        });
        jPanel7.add(BtGerarParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 70, 30));

        BtCalcular.setText("Calcular");
        BtCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCalcularActionPerformed(evt);
            }
        });
        jPanel7.add(BtCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jLabel22.setText("Status Parcelas:");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 50, -1, -1));

        LbStatusGeradaPar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LbStatusGeradaPar.setText("NÃO GERADA");
        jPanel7.add(LbStatusGeradaPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 52, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 540, 120));

        jLabel12.setText("Datas: ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, -1, -1));

        EdCodCop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdCodCopKeyPressed(evt);
            }
        });
        jPanel1.add(EdCodCop, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 70, -1));

        jLabel14.setText("Cod.Cop: ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, 20));

        tabelaProcedi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cód. Funcio", "Funcionário", "CPF", "Cód. Procedi.", "Procedimento", "Valor", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProcedi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProcediMouseClicked(evt);
            }
        });
        tabelaProcedi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaProcediKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaProcedi);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 990, 120));

        jLabel15.setText("Dados do Procedimento:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));

        BtBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscar.setText("Buscar");
        BtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(BtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 50, -1, 30));

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel20.setText("Cód. Procedimento: ");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 15, -1, -1));

        EdCodPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdCodProActionPerformed(evt);
            }
        });
        EdCodPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdCodProKeyPressed(evt);
            }
        });
        jPanel8.add(EdCodPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 14, 90, -1));

        BtBuscarCodPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodPro.setText("Buscar");
        BtBuscarCodPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodProActionPerformed(evt);
            }
        });
        jPanel8.add(BtBuscarCodPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 90, -1));

        EdNomePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdNomeProActionPerformed(evt);
            }
        });
        jPanel8.add(EdNomePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 260, -1));
        jPanel8.add(EdLocalPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 260, -1));
        jPanel8.add(EdMedicoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 260, -1));

        jLabel29.setText("Nome: ");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel35.setText("Local:");
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel36.setText("Médico: ");
        jPanel8.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jLabel37.setText("Valor: ");
        jPanel8.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));
        jPanel8.add(EdValorPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 120, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 340, 180));

        BtRemoverPro.setBackground(new java.awt.Color(255, 102, 102));
        BtRemoverPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menos_white.png"))); // NOI18N
        BtRemoverPro.setText("Remover");
        BtRemoverPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRemoverProActionPerformed(evt);
            }
        });
        jPanel1.add(BtRemoverPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, 110, 40));

        BtAdcPro.setBackground(new java.awt.Color(0, 153, 0));
        BtAdcPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mais_white.png"))); // NOI18N
        BtAdcPro.setText("Adcionar");
        BtAdcPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtAdcProActionPerformed(evt);
            }
        });
        jPanel1.add(BtAdcPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, 110, 40));

        jLabel17.setText("Ficha de procedimentos: ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        jPanel1.add(EdCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 70, -1));
        jPanel1.add(EdCodPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 70, -1));
        jPanel1.add(EdnumMesPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 190, 80, -1));

        jTabbedPane1.addTab("Controle de Procedimentos", jPanel1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("Controle de Parcelas");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(829, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, -1));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Parcela", "Cod. Coparticipação", "Cod. Funcionário", "Nome Funcionário", "Valor Parcela", "Data Vencimento", "Dias Vencimento", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(7).setMinWidth(0);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(0);
            tabela.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 990, 290));

        BtBuscarCodFuncio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarCodFuncio1.setText("Buscar");
        BtBuscarCodFuncio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarCodFuncio1ActionPerformed(evt);
            }
        });
        jPanel5.add(BtBuscarCodFuncio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Cód. Funcionário: ");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 100, 20));

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
        jPanel5.add(EdBuscarCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 60, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel23.setText("Cód. Parcela: ");
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 80, 20));
        jPanel11.add(EdCodParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 110, -1));

        EdCodFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdCodFuncionarioActionPerformed(evt);
            }
        });
        jPanel11.add(EdCodFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 110, -1));
        jPanel11.add(EdCodCoparticipacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 110, -1));

        jLabel6.setText("Nome Funcionário:");
        jPanel11.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));

        jLabel18.setText("Cód. Coparticipação:");
        jPanel11.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));
        jPanel11.add(EdNomeFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 270, -1));

        jLabel7.setText("Cód. Funcionário:");
        jPanel11.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 430, 140));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_red.png"))); // NOI18N
        BtExcluir1.setText("EXCLUÍR");
        BtExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtExcluir1ActionPerformed(evt);
            }
        });
        jPanel10.add(BtExcluir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 33));

        BtCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_red.png"))); // NOI18N
        BtCancelar1.setText("CANCELAR");
        BtCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelar1ActionPerformed(evt);
            }
        });
        jPanel10.add(BtCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 120, 33));

        BtSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair1.setText("SAIR");
        BtSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSair1ActionPerformed(evt);
            }
        });
        jPanel10.add(BtSair1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 78, 33));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1010, 60));
        jPanel5.add(EdStatusOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 240, 50, 20));

        jLabel34.setText("Filtrar por: ");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, -1, 20));

        CbxPaga.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxPaga.setText("PAGAS");
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
        jPanel5.add(CbxPaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, -1, 40));

        CbxNPaga.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        CbxNPaga.setText("NÃO PAGAS");
        CbxNPaga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxNPagaMouseClicked(evt);
            }
        });
        CbxNPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxNPagaActionPerformed(evt);
            }
        });
        jPanel5.add(CbxNPaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, 40));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setText("Dias Vencimento:");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, 20));

        jLabel33.setText("Vencimento: ");
        jPanel12.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, 20));
        jPanel12.add(JdcDatavenc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 130, -1));

        jLabel19.setText("Valor Parcela: ");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 20));

        EdValorParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdValorParcelaActionPerformed(evt);
            }
        });
        jPanel12.add(EdValorParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 130, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/money.png"))); // NOI18N
        jButton1.setText("Pagar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 100, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove_money.png"))); // NOI18N
        jButton2.setText("Remover");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 100, 30));

        LbstatusParcela.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        LbstatusParcela.setText("STATUS");
        jPanel12.add(LbstatusParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 20));
        jPanel12.add(EdDiasEmAtraso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 80, -1));

        jLabel26.setText("Status do Pagamento: ");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 510, 110));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setText("De:");
        jPanel13.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 20));
        jPanel13.add(JdcFiltraUltiParInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 140, -1));

        jLabel27.setText("Até:");
        jPanel13.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));
        jPanel13.add(JdcFiltraUltiParFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 140, -1));

        jPanel5.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 185, 200, 80));

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
        jPanel5.add(CbxFiltroDatas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, -1, -1));

        jTabbedPane1.addTab("Controle Coparticipação", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1012, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtAdcProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtAdcProActionPerformed
        try {
            checkCreateRealiza();
        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        } catch (SQLException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtAdcProActionPerformed

    private void BtRemoverProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRemoverProActionPerformed
        try {
            removerProcedimento();
        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        } catch (SQLException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtRemoverProActionPerformed

    private void EdNomeProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdNomeProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdNomeProActionPerformed

    private void BtBuscarCodProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodProActionPerformed
        validarCodList();
    }//GEN-LAST:event_BtBuscarCodProActionPerformed

    private void EdCodProKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdCodProKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validarCodList();
        }
    }//GEN-LAST:event_EdCodProKeyPressed

    private void EdCodProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdCodProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdCodProActionPerformed

    private void BtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarActionPerformed
        try {
            validaCop();
        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        } catch (SQLException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtBuscarActionPerformed

    private void tabelaProcediKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaProcediKeyPressed

    }//GEN-LAST:event_tabelaProcediKeyPressed

    private void tabelaProcediMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProcediMouseClicked
        if (tabelaProcedi.getSelectedRow() != -1) {
            jTabbedPane1.setSelectedIndex(0);
            EdCod.setText(tabelaProcedi.getValueAt(tabelaProcedi.getSelectedRow(), 0).toString());

            habilitarB(3);

        }
    }//GEN-LAST:event_tabelaProcediMouseClicked

    private void BtGerarParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtGerarParcelaActionPerformed
        try {
            validaParcela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtGerarParcelaActionPerformed

    private void EdResultCopartiParceladoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdResultCopartiParceladoKeyPressed

    }//GEN-LAST:event_EdResultCopartiParceladoKeyPressed

    private void EdParcelaCopartiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdParcelaCopartiKeyPressed
        if (EdParcelaCoparti.getText().equals("")) {

        } else {
            calcularParcelas();
        }
    }//GEN-LAST:event_EdParcelaCopartiKeyPressed

    private void EdNomeFuncioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdNomeFuncioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdNomeFuncioActionPerformed

    private void BtBuscarCodFuncioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodFuncioActionPerformed
        try {
            validarCodCadastro();
        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        } catch (SQLException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtBuscarCodFuncioActionPerformed

    private void EdCodFuncioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdCodFuncioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                validarCodCadastro();
            } catch (ParseException ex) {
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            } catch (SQLException ex) {
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
            EdCodPro.requestFocus();
        }
    }//GEN-LAST:event_EdCodFuncioKeyPressed

    private void EdCodFuncioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdCodFuncioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdCodFuncioActionPerformed

    private void EdSetorFuncioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdSetorFuncioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdSetorFuncioActionPerformed

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed
        ParcelaDao pdao = new ParcelaDao();

        try {
            if (pdao.checkCreateParForCop(EdCodCop.getText())) {

                habilitarB(1);
                limpaCampos();
                limparTabelaCop();

            } else {
                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!\nNão foram geradas parcelas para está ficha de coparticipação!\n Deseja sair mesmo assim?", "Atenção!", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    habilitarB(1);
                    limpaCampos();
                    limparTabelaCop();
                } else {

                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        try {
            cancelarCop();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }

    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtNovoActionPerformed
        habilitarB(2);
        EdCodFuncio.requestFocus();
    }//GEN-LAST:event_BtNovoActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        try {
            deletarCop();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCalcularActionPerformed
        calcularParcelas();
    }//GEN-LAST:event_BtCalcularActionPerformed

    private void EdCodCopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdCodCopKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (EdCodCop.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Você deve informar o código da coparticipação");
            } else {
                try {
                    validaCop();
                } catch (ParseException ex) {
                    Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
                } catch (SQLException ex) {
                    Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
                }
            }
        }
    }//GEN-LAST:event_EdCodCopKeyPressed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

        if (tabela.getSelectedRow() != -1) {
            jTabbedPane1.setSelectedIndex(1);

            EdCodParcela.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdCodCoparticipacao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            EdCodFuncionario.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            EdNomeFuncionario.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
            EdValorParcela.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
            EdDiasEmAtraso.setText(tabela.getValueAt(tabela.getSelectedRow(), 6).toString());

            try {
                DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                int seectedRow = tabela.getSelectedRow();
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(seectedRow, 5).toString());
                //                JOptionPane.showMessageDialog(null, date);
                JdcDatavenc.setDate(date);

                int diasAtraso;
                diasAtraso = Integer.parseInt(EdDiasEmAtraso.getText());
                int status;
                status = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 7).toString());

                if (status == 1 && diasAtraso >= 0) {
                    LbstatusParcela.setText("NÃO PAGO");
                    LbstatusParcela.setForeground(Color.decode("#000000"));

                } else if (status == 1 && diasAtraso < 0) {
                    LbstatusParcela.setText("VENCIDO");
                    LbstatusParcela.setForeground(Color.decode("#ff0000"));
                } else {
                    LbstatusParcela.setText("PAGA");
                    LbstatusParcela.setForeground(Color.decode("#0f943b"));
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }

        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void tabelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            try {
                deletarParcela();

            } catch (ParseException ex) {
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
        }
    }//GEN-LAST:event_tabelaKeyPressed

    private void BtBuscarCodFuncio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarCodFuncio1ActionPerformed
        try {
            listarParcelas();

        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtBuscarCodFuncio1ActionPerformed

    private void EdBuscarCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdBuscarCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdBuscarCodActionPerformed

    private void EdBuscarCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdBuscarCodKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                listarParcelas();

            } catch (ParseException ex) {
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
            }
        }
    }//GEN-LAST:event_EdBuscarCodKeyPressed

    private void EdCodFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdCodFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdCodFuncionarioActionPerformed

    private void BtExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluir1ActionPerformed
        try {
            deletarParcela();

        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_BtExcluir1ActionPerformed

    private void BtCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelar1ActionPerformed
        limpaCamposPar();
        limparTabela();
    }//GEN-LAST:event_BtCancelar1ActionPerformed

    private void BtSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSair1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtSair1ActionPerformed

    private void CbxPagaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxPagaMouseClicked

    }//GEN-LAST:event_CbxPagaMouseClicked

    private void CbxPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxPagaActionPerformed
        CbxNPaga.setSelected(false);
    }//GEN-LAST:event_CbxPagaActionPerformed

    private void CbxNPagaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxNPagaMouseClicked

    }//GEN-LAST:event_CbxNPagaMouseClicked

    private void CbxNPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxNPagaActionPerformed
        CbxPaga.setSelected(false);
    }//GEN-LAST:event_CbxNPagaActionPerformed

    private void EdValorParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdValorParcelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EdValorParcelaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            pagarParcela();

        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            removerPargamento();

        } catch (ParseException ex) {
            Logger.getLogger(FrContParticipacao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro na aplicação, contate Administrador! \nCódigo erro: " + ex, "Erro", JOptionPane.YES_OPTION);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CbxFiltroDatasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxFiltroDatasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxFiltroDatasMouseClicked

    private void CbxFiltroDatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxFiltroDatasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxFiltroDatasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtAdcPro;
    private javax.swing.JButton BtBuscar;
    private javax.swing.JButton BtBuscarCodFuncio;
    private javax.swing.JButton BtBuscarCodFuncio1;
    private javax.swing.JButton BtBuscarCodPro;
    private javax.swing.JButton BtCalcular;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtCancelar1;
    private javax.swing.JButton BtExcluir;
    private javax.swing.JButton BtExcluir1;
    private javax.swing.JButton BtGerarParcela;
    private javax.swing.JButton BtNovo;
    private javax.swing.JButton BtRemoverPro;
    private javax.swing.JButton BtSair;
    private javax.swing.JButton BtSair1;
    private javax.swing.JButton BtSalvar;
    private javax.swing.JCheckBox CbxFiltroDatas;
    private javax.swing.JCheckBox CbxNPaga;
    private javax.swing.JCheckBox CbxPaga;
    private javax.swing.JTextField EdBuscarCod;
    private javax.swing.JTextField EdCargoFuncio;
    private javax.swing.JTextField EdCod;
    private javax.swing.JTextField EdCodCop;
    private javax.swing.JTextField EdCodCoparticipacao;
    private javax.swing.JTextField EdCodFuncio;
    private javax.swing.JTextField EdCodFuncionario;
    private javax.swing.JTextField EdCodPar;
    private javax.swing.JTextField EdCodParcela;
    private javax.swing.JTextField EdCodPro;
    private javax.swing.JFormattedTextField EdCpfFuncio;
    private javax.swing.JTextField EdDiasEmAtraso;
    private javax.swing.JTextField EdLocalPro;
    private javax.swing.JTextField EdMedicoPro;
    private javax.swing.JTextField EdNomeFuncio;
    private javax.swing.JTextField EdNomeFuncionario;
    private javax.swing.JTextField EdNomePro;
    private javax.swing.JTextField EdParcelaCoparti;
    private javax.swing.JTextField EdResultCopartiParcelado;
    private javax.swing.JTextField EdSetorFuncio;
    private javax.swing.JTextField EdStatusOff;
    private javax.swing.JTextField EdValorCoparti;
    private javax.swing.JTextField EdValorParcela;
    private javax.swing.JTextField EdValorPro;
    private javax.swing.JTextField EdnumMesPar;
    private com.toedter.calendar.JDateChooser JDcDataPro;
    private com.toedter.calendar.JDateChooser JDcDataVencPar;
    private com.toedter.calendar.JDateChooser JdcDatavenc;
    private com.toedter.calendar.JDateChooser JdcFiltraUltiParFinal;
    private com.toedter.calendar.JDateChooser JdcFiltraUltiParInicial;
    private javax.swing.JLabel LbStatusGeradaPar;
    private javax.swing.JLabel LbstatusParcela;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelaProcedi;
    // End of variables declaration//GEN-END:variables
}
