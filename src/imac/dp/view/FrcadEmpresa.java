/*
 * To change this license header, choose License Headers in Empject Empperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.EmpresaDao;
import imac.dp.model.Empresa;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author @andre_ments
 */
public class FrcadEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmEmpresa
     */
    public FrcadEmpresa() {
        initComponents();
        habilitarB(1);
    }

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
                EdNome.requestFocus();
                EdNome.setEnabled(false);
                EdBuscaNome.setEnabled(true);
                break;

            case 2:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(true);
                BtSalvar.setVisible(true);
                BtSalvarEdit.setEnabled(false);
                BtSalvarEdit.setVisible(false);
                BtExcluir.setEnabled(false);
                BtBuscarNome.setEnabled(false);
                BtCancelar.setEnabled(true);
                EdCod.setEnabled(true);
                EdNome.setEnabled(true);
                EdNome.requestFocus();
                EdBuscaNome.setEnabled(false);
                break;

            case 3:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(false);
                BtSalvarEdit.setEnabled(true);
                BtSalvarEdit.setVisible(true);
                BtExcluir.setEnabled(true);
                BtBuscarNome.setEnabled(false);
                BtCancelar.setEnabled(true);
                EdCod.setEnabled(true);
                EdCod.setEditable(false);
                EdNome.setEnabled(true);
                EdBuscaNome.setEnabled(false);
                EdNome.requestFocus();
                break;
        }
    }

    public void limpaCampos() {
        EdCod.setText(null);
        EdNome.setText(null);
        EdBuscaNome.setText(null);

    }

    public void checkCod() throws SQLException {
        EmpresaDao pdao = new EmpresaDao();
        if (EdCod.getText().equals("")
                || EdNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                if (pdao.checkCod(EdCod.getText())) {

                    JOptionPane.showMessageDialog(null, "Empresa já possui cadastro");

                } else {
                    salvarEmp();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void salvarEmp() {

        Empresa e = new Empresa();
        EmpresaDao dao = new EmpresaDao();
        habilitarB(2);
        e.setCodEmp(Integer.parseInt(EdCod.getText()));
        e.setNomeEmp(EdNome.getText());

        try {
            dao.createEmp(e);
            limpaCampos();
            habilitarB(1);
            listarEmp();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listarEmp() {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        EmpresaDao pdao = new EmpresaDao();

        if (EdCod.getText().equals("")) {
            if (EdBuscaNome.getText().equals("")) {
                try {
                    for (Empresa p : pdao.readEmp()) {
                        modelo.addRow(new Object[]{
                            p.getCodEmp(),
                            p.getNomeEmp()

                        });
                    }

                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    for (Empresa p : pdao.readEmpForDesc(EdBuscaNome.getText())) {
                        modelo.addRow(new Object[]{
                            p.getCodEmp(),
                            p.getNomeEmp()
                        });
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            listaEmpCod();
        }

    }

    public void validarCodList() {
        EmpresaDao pdao = new EmpresaDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código da Empresa!");
        } else {

            try {
                if (pdao.checkCod(EdCod.getText())) {
                    listaEmpCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Empresa não encontrada");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listaEmpCod() {

        Empresa p = new Empresa();
        EmpresaDao pdao = new EmpresaDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                for (Empresa usu : pdao.readEmpForCod(EdCod.getText())) {
                    EdCod.setText(Integer.toString(usu.getCodEmp()));
                    EdNome.setText(usu.getNomeEmp());

                    habilitarB(3);
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, " Empresa não encontrado");
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editarEmp() {

        if (tabela.getSelectedRow() != -1) {
            Empresa e = new Empresa();
            EmpresaDao dao = new EmpresaDao();

            if (EdNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
            } else {

                e.setNomeEmp(EdNome.getText());
                e.setCodEmp((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                try {
                    dao.update(e);
                    limpaCampos();
                    listarEmp();

                    habilitarB(1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public void deletarEmp() {
        Empresa e = new Empresa();
        EmpresaDao dao = new EmpresaDao();
        habilitarB(1);
        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do procedimento para excluír");
        } else {
            e.setCodEmp(Integer.parseInt(EdCod.getText()));
            try {

                String nome = EdNome.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o cadastro do procedimento  " + nome, "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    dao.delete(e);
                    habilitarB(1);
                    limpaCampos();
                    listarEmp();
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
        EdBuscaNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Manutenção de Empresas - V1.0-20.0813");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(189, 214, 239));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Empresa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 60));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel2.setText("Código: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        jPanel1.add(EdCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 80, -1));

        BtBuscarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarNome.setText("Buscar");
        BtBuscarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarNomeActionPerformed(evt);
            }
        });
        jPanel1.add(BtBuscarNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 90, 30));
        jPanel1.add(EdNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 290, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 40, -1));

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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 450, 50));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
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
            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 430, 110));

        BtNovo.setBackground(new java.awt.Color(102, 255, 102));
        BtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        BtNovo.setText("NOVO");
        BtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtNovoActionPerformed(evt);
            }
        });
        jPanel1.add(BtNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 90, 30));

        EdBuscaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdBuscaNomeKeyPressed(evt);
            }
        });
        jPanel1.add(EdBuscaNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 230, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Nome:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, -1));

        jTabbedPane1.addTab("Cadastrar Empresa", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
        editarEmp();
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
        deletarEmp();
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarNomeActionPerformed
        listarEmp();
    }//GEN-LAST:event_BtBuscarNomeActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (tabela.getSelectedRow() != -1) {
            EdCod.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());

            habilitarB(3);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void EdBuscaNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdBuscaNomeKeyPressed
        listarEmp();
    }//GEN-LAST:event_EdBuscaNomeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtBuscarNome;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtExcluir;
    private javax.swing.JButton BtNovo;
    private javax.swing.JButton BtSair;
    private javax.swing.JButton BtSalvar;
    private javax.swing.JButton BtSalvarEdit;
    private javax.swing.JTextField EdBuscaNome;
    private javax.swing.JTextField EdCod;
    private javax.swing.JTextField EdNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
