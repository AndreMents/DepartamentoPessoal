/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.ProcedimentoDao;
import imac.dp.model.Procedimento;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author @andre_ments
 */
public class FrcadProcedi extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmProcedimento
     */
    public FrcadProcedi() {
        initComponents();
        habilitarB(1);
    }

    //HABILITA OU DESABILITAS ITENS DO FRONT
    public void habilitarB(int op) {
        switch (op) {
            case 1:

                BtNovo.setEnabled(true);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(true);
                BtSalvarEdit.setEnabled(false);
                BtSalvarEdit.setVisible(false);
                BtExcluir.setVisible(true);
                BtExcluir.setEnabled(false);
                BtBuscarNome.setEnabled(true);
                EdCod.setEnabled(true);
                EdParcela.setEnabled(false);
                EdValor.setEnabled(false);
                EdNome.requestFocus();
                break;

            case 2:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(true);
                BtSalvar.setVisible(true);
                BtSalvarEdit.setEnabled(false);
                BtSalvarEdit.setVisible(false);
                BtExcluir.setEnabled(false);
                BtBuscarNome.setEnabled(true);
                BtCancelar.setEnabled(true);
                EdCod.setEnabled(true);
                EdParcela.setEnabled(true);
                EdValor.setEnabled(true);
                EdNome.requestFocus();
                break;

            case 3:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(false);
                BtSalvarEdit.setEnabled(true);
                BtSalvarEdit.setVisible(true);
                BtExcluir.setEnabled(true);
                BtBuscarNome.setEnabled(true);
                BtCancelar.setEnabled(true);
                EdParcela.setEnabled(true);
                EdValor.setEnabled(true);
                EdCod.setEnabled(false);
                EdNome.requestFocus();
                break;
        }
    }

    //LIMPA CAMPOS DO FRONT
    public void limpaCampos() {
        EdCod.setText(null);
        EdNome.setText(null);
        EdParcela.setText(null);
        EdValor.setText(null);

    }

    //VERIFICA SE O PROCEDIMENTO POSSUI CADASTRO
    public void checkCod() throws SQLException {
        ProcedimentoDao pdao = new ProcedimentoDao();
        if (EdCod.getText().equals("")
                || EdNome.getText().equals("")
                || EdValor.getText().equals("")
                || EdParcela.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                if (pdao.checkCod(EdCod.getText())) {

                    JOptionPane.showMessageDialog(null, "Procedimento já possui cadastro");

                } else {
                    salvarPro();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //CADASTRA NOVO PROCEDIMENTO
    public void salvarPro() {

        Procedimento e = new Procedimento();
        ProcedimentoDao dao = new ProcedimentoDao();
        habilitarB(2);
        e.setCodPro(Integer.parseInt(EdCod.getText()));
        e.setNomePro(EdNome.getText());
        e.setValorPro(Double.parseDouble(EdValor.getText()));
        e.setParcelaPro(Integer.parseInt(EdParcela.getText()));

        try {
            dao.createPro(e);
            limpaCampos();
            habilitarB(1);
            listarPro();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadProcedi.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    //LISTA PROCEDIMETNOS
    public void listarPro() {

        habilitarB(1);

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        ProcedimentoDao pdao = new ProcedimentoDao();

        if (EdCod.getText().equals("")) {
            if (EdNome.getText().equals("")) {
                try {
                    for (Procedimento p : pdao.readPro()) {
                        modelo.addRow(new Object[]{
                            p.getCodPro(),
                            p.getNomePro(),
                            p.getValorPro(),
                            p.getParcelaPro()

                        });
                    }

                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    for (Procedimento p : pdao.readProForDesc(EdNome.getText())) {
                        modelo.addRow(new Object[]{
                            p.getCodPro(),
                            p.getNomePro(),
                            p.getValorPro(),
                            p.getParcelaPro()
                        });
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadProcedi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            listaProCod();
        }

    }

    //VERIFICA O CÓDIGO CA PROCEDIMENTO CADASTRADO
    public void validarCodList() {
        ProcedimentoDao pdao = new ProcedimentoDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código da Procedimento!");
        } else {

            try {
                if (pdao.checkCod(EdCod.getText())) {
                    listaProCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Procedimento não encontrada");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //LISTA PROCEDIMENTO POR CÓDIGO
    public void listaProCod() {

        Procedimento p = new Procedimento();
        ProcedimentoDao pdao = new ProcedimentoDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                for (Procedimento usu : pdao.readProForCod(EdCod.getText())) {
                    EdCod.setText(Integer.toString(usu.getCodPro()));
                    EdNome.setText(usu.getNomePro());
                    EdValor.setText(Double.toString(usu.getValorPro()));
                    EdParcela.setText(Integer.toString(usu.getParcelaPro()));

                    habilitarB(3);
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, " Procedimento não encontrado");
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //EDITA PROCEDIMENTO
    public void editarPro() {

        if (tabela.getSelectedRow() != -1) {
            Procedimento e = new Procedimento();
            ProcedimentoDao dao = new ProcedimentoDao();

            if (EdNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
            } else {

                e.setNomePro(EdNome.getText());
                e.setValorPro(Double.parseDouble(EdValor.getText()));
                e.setParcelaPro(Integer.parseInt(EdParcela.getText()));
                e.setCodPro((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                try {
                    dao.update(e);
                    limpaCampos();
                    listarPro();

                    habilitarB(1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    //DELETA PROCEDIMENTO
    public void deletarPro() {
        Procedimento e = new Procedimento();
        ProcedimentoDao dao = new ProcedimentoDao();
        habilitarB(1);
        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do procedimento para excluír");
        } else {
            e.setCodPro(Integer.parseInt(EdCod.getText()));
            try {

                String nome = EdNome.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o cadastro do procedimento  " + nome, null, JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    dao.delete(e);
                    habilitarB(1);
                    limpaCampos();
                    listarPro();
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EdCod = new javax.swing.JTextField();
        BtBuscarNome = new javax.swing.JButton();
        EdNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        BtExcluir = new javax.swing.JButton();
        BtSair = new javax.swing.JButton();
        BtSalvar = new javax.swing.JButton();
        BtSalvarEdit = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        BtNovo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        EdValor = new javax.swing.JTextField();
        EdParcela = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Manutenção de Procedimentos - V1.0-20.0810");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Procedimento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 60));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel2.setText("Código: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        jPanel1.add(EdCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 70, -1));

        BtBuscarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarNome.setText("Buscar");
        BtBuscarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarNomeActionPerformed(evt);
            }
        });
        jPanel1.add(BtBuscarNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 90, 30));
        jPanel1.add(EdNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 370, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_red.png"))); // NOI18N
        BtExcluir.setText("EXCLUÍR");
        BtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(BtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 100, 30));

        BtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair.setText("SAIR");
        BtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSairActionPerformed(evt);
            }
        });
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 80, 30));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 100, 30));

        BtSalvarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvarEdit.setText("SALVAR");
        BtSalvarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarEditActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvarEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 100, 30));

        BtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_red.png"))); // NOI18N
        BtCancelar.setText("CANCELAR");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(BtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 460, 55));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Nome", "Valor", "Nº Parcelas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setMinWidth(50);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(0).setMaxWidth(50);
            tabela.getColumnModel().getColumn(1).setMinWidth(150);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabela.getColumnModel().getColumn(2).setMinWidth(50);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(2).setMaxWidth(50);
            tabela.getColumnModel().getColumn(3).setMinWidth(70);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabela.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 179, 440, 150));

        BtNovo.setBackground(new java.awt.Color(102, 255, 102));
        BtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        BtNovo.setText("NOVO");
        BtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtNovoActionPerformed(evt);
            }
        });
        jPanel1.add(BtNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 100, 30));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Valor: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 40, -1));
        jPanel1.add(EdValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 110, -1));
        jPanel1.add(EdParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 100, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setText("Nº Parcelas: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 70, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel6.setText("Nº Vezes");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel7.setText("R$");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, 20));

        jTabbedPane1.addTab("Cadastrar Procedimento", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtNovoActionPerformed
        habilitarB(2);
        EdCod.requestFocus();
    }//GEN-LAST:event_BtNovoActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        habilitarB(1);
        limpaCampos();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtSalvarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarEditActionPerformed
        editarPro();
    }//GEN-LAST:event_BtSalvarEditActionPerformed

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed
        try {
            checkCod();
        } catch (SQLException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        deletarPro();
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarNomeActionPerformed
        listarPro();
    }//GEN-LAST:event_BtBuscarNomeActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (tabela.getSelectedRow() != -1) {
            EdCod.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            EdValor.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            EdParcela.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());

            habilitarB(3);
        }
    }//GEN-LAST:event_tabelaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtBuscarNome;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtExcluir;
    private javax.swing.JButton BtNovo;
    private javax.swing.JButton BtSair;
    private javax.swing.JButton BtSalvar;
    private javax.swing.JButton BtSalvarEdit;
    private javax.swing.JTextField EdCod;
    private javax.swing.JTextField EdNome;
    private javax.swing.JTextField EdParcela;
    private javax.swing.JTextField EdValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
