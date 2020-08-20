/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.ConnectionFactory;
import imac.dp.dao.EspecialidadeDao;
import imac.dp.model.Especialidade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author @andre_ments
 */
public class FrcadEspeciali extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmEspecialidade
     */
    public FrcadEspeciali() {
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
                EdCod.setEnabled(false);
                EdNome.requestFocus();
                break;
        }
    }

    public void limpaCampos() {
        EdCod.setText(null);
        EdNome.setText(null);

    }

    public void checkCod() throws SQLException {
        EspecialidadeDao pdao = new EspecialidadeDao();
        if (EdNome.getText().equals("") || EdNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                if (pdao.checkCod(EdCod.getText())) {

                    JOptionPane.showMessageDialog(null, "Especialidade já possui cadastro");
                    habilitarB(1);
                } else {
                    salvarEsp();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void salvarEsp() {

        Especialidade e = new Especialidade();
        EspecialidadeDao dao = new EspecialidadeDao();
        habilitarB(2);
        e.setCod(Integer.parseInt(EdCod.getText()));
        e.setNome(EdNome.getText());

        try {
            dao.createEsp(e);
            limpaCampos();
            habilitarB(1);
            listarEsp();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadEspeciali.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listarEsp() {

        habilitarB(1);

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        EspecialidadeDao pdao = new EspecialidadeDao();

        if (EdCod.getText().equals("")) {
            if (EdNome.getText().equals("")) {
                try {
                    for (Especialidade u : pdao.readEsp()) {
                        modelo.addRow(new Object[]{
                            u.getCod(),
                            u.getNome()
                        });
                    }

                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    for (Especialidade e : pdao.readEspForDesc(EdNome.getText())) {
                        modelo.addRow(new Object[]{
                            e.getCod(),
                            e.getNome()
                        });
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadEspeciali.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            listaEspCod();
        }

    }

    public void validarCodList() {
        EspecialidadeDao pdao = new EspecialidadeDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código da Especialidade!");
        } else {

            try {
                if (pdao.checkCod(EdCod.getText())) {
                    listaEspCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Especialidade não encontrada");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listaEspCod() {

        Especialidade e = new Especialidade();
        EspecialidadeDao pdao = new EspecialidadeDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                for (Especialidade usu : pdao.readEspForCod(EdCod.getText())) {
                    EdCod.setText(Integer.toString(usu.getCod()));
                    EdNome.setText(usu.getNome());

                    habilitarB(3);
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, " Usuário não encontrado");
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editarEsp() {

        if (tabela.getSelectedRow() != -1) {
            Especialidade e = new Especialidade();
            EspecialidadeDao dao = new EspecialidadeDao();

            if (EdNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
            } else {

                e.setNome(EdNome.getText());
                e.setCod((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                try {
                    dao.update(e);
                    limpaCampos();
                    listarEsp();

                    habilitarB(1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public void deletarEsp() {
        Especialidade e = new Especialidade();
        EspecialidadeDao dao = new EspecialidadeDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário para excluír");
        } else {
            e.setCod(Integer.parseInt(EdCod.getText()));
            try {

                String nome = EdNome.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o cadastro do procedimento  " + nome, "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    dao.delete(e);
                    habilitarB(1);
                    limpaCampos();
                    listarEsp();
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    public void relatorio() throws ClassNotFoundException {
        java.sql.Connection con = ConnectionFactory.getConnection();

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir o relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String src = "C:/hardsearch/DepartementoPessoal/relatorios/EspCadastradas.jasper";

            JasperPrint jasperPrint = null;

            try {
                jasperPrint = JasperFillManager.fillReport(src, null, con);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar relatório" + "\n" + e);
            }
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setVisible(true);
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
        jButton1 = new javax.swing.JButton();

        setResizable(true);
        setTitle("Manutenção de Especialidades - V1.0-20.0810");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Especialidade");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 60));

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
        jPanel1.add(BtBuscarNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, 30));
        jPanel1.add(EdNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 110, -1));

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
        jPanel4.add(BtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 90, 30));

        BtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair.setText("SAIR");
        BtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSairActionPerformed(evt);
            }
        });
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, 30));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 90, 30));

        BtSalvarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvarEdit.setText("SALVAR");
        BtSalvarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarEditActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvarEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 90, 30));

        BtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_red.png"))); // NOI18N
        BtCancelar.setText("CANCELAR");
        BtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(BtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 440, 50));

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 420, 119));

        BtNovo.setBackground(new java.awt.Color(102, 255, 102));
        BtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        BtNovo.setText("NOVO");
        BtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtNovoActionPerformed(evt);
            }
        });
        jPanel1.add(BtNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 90, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print.png"))); // NOI18N
        jButton1.setText("Relatório");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jTabbedPane1.addTab("Cadastrar Especialidade", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        editarEsp();
    }//GEN-LAST:event_BtSalvarEditActionPerformed

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed
        try {
            checkCod();
        } catch (SQLException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarB(1);
    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        deletarEsp();
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarNomeActionPerformed
        listarEsp();
    }//GEN-LAST:event_BtBuscarNomeActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (tabela.getSelectedRow() != -1) {
            EdCod.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());

            habilitarB(3);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Connection con = ConnectionFactory.getConnection();

            String src = "C:/hardsearch/DepartementoPessoal/relatorios/";

            JasperPrint jasperPrint = null;

            try {
                jasperPrint = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(FrcadEspeciali.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer view = new JasperViewer(jasperPrint, false);

            view.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadEspeciali.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed


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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
