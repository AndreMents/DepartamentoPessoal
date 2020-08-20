/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.FuncionarioDao;
import imac.dp.model.Funcionario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author @andre_ments
 */
public class FrcadFuncionario extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrcadUsuario
     */
    public FrcadFuncionario() {
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
                BtBuscar.setEnabled(true);
                EdNome.setEnabled(false);
                EdCod.setEnabled(true);
                EdCod.setEditable(true);
                EdSetor.setEnabled(false);
                EdCpf.setEnabled(false);
                EdCargo.setEnabled(false);
                EdNome.requestFocus();
                EdTurno.setEnabled(false);
                break;

            case 2:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(true);
                BtSalvar.setVisible(true);
                BtSalvarEdit.setEnabled(false);
                BtSalvarEdit.setVisible(false);
                BtExcluir.setEnabled(false);
                BtBuscar.setEnabled(true);
                BtCancelar.setEnabled(true);
                EdNome.setEnabled(true);
                EdCod.setEnabled(true);
                EdSetor.setEnabled(true);
                EdCpf.setEnabled(true);
                EdCargo.setEnabled(true);
                EdTurno.setEnabled(true);
                EdNome.requestFocus();
                break;

            case 3:
                BtNovo.setEnabled(false);
                BtSalvar.setEnabled(false);
                BtSalvar.setVisible(false);
                BtSalvarEdit.setEnabled(true);
                BtSalvarEdit.setVisible(true);
                BtExcluir.setEnabled(true);
                BtBuscar.setEnabled(true);
                BtCancelar.setEnabled(true);
                EdNome.setEnabled(true);
                EdCod.setEnabled(true);
                EdCod.setEditable(false);
                EdSetor.setEnabled(true);
                EdCpf.setEnabled(true);
                EdCargo.setEnabled(true);
                EdTurno.setEnabled(true);
                EdNome.requestFocus();
                break;
        }
    }

    public void limpaCampos() {
        EdCod.setText(null);
        EdNome.setText(null);
        EdSetor.setText(null);
        EdCargo.setText(null);
        EdCpf.setText(null);
        EdCargo.setText(null);
        EdTurno.setText(null);
    }

    public void validarCpfCadastro() {
        FuncionarioDao pdao = new FuncionarioDao();

        try {
            if (pdao.checkCpf(EdCpf.getText())) {
                JOptionPane.showMessageDialog(null, "Este CPF já foi cadastrado!");
            } else {
                salvarFuncionario();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvarFuncionario() {
        if (EdCod.getText().equals("") || EdNome.getText().equals("") || EdSetor.getText().equals("") || EdCargo.getText().equals("") || EdCpf.getText().equals("") || EdTurno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {

            Funcionario f = new Funcionario();
            FuncionarioDao dao = new FuncionarioDao();
            habilitarB(2);
            f.setCodFuncionario(Integer.parseInt(EdCod.getText()));
            f.setNomeFuncionario(EdNome.getText());
            f.setSetorFuncionario(EdSetor.getText());
            f.setCpfFuncionario(EdCpf.getText());
            f.setCargoFuncionario(EdCargo.getText());
            f.setTurnoFuncionario(EdTurno.getText());

            try {
                dao.createFuncionario(f);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpaCampos();
        }
    }

    public void validarCodCadastro() {
        FuncionarioDao pdao = new FuncionarioDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                if (pdao.checkCod(EdCod.getText())) {
                    listaFuncionarioCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listarFuncionario() throws ClassNotFoundException {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        FuncionarioDao pdao = new FuncionarioDao();

        if (EdBuscarNome.getText().equals("")) {
            for (Funcionario f : pdao.readFuncionario()) {
                modelo.addRow(new Object[]{
                    f.getCodFuncionario(),
                    f.getNomeFuncionario(),
                    f.getCpfFuncionario(),
                    f.getSetorFuncionario(),
                    f.getCargoFuncionario(),
                    f.getTurnoFuncionario()

                });

            }
        } else {
            try {
                for (Funcionario f : pdao.readFuncionarioForDesc(EdBuscarNome.getText())) {
                    modelo.addRow(new Object[]{
                        f.getCodFuncionario(),
                        f.getNomeFuncionario(),
                        f.getCpfFuncionario(),
                        f.getSetorFuncionario(),
                        f.getCargoFuncionario(),
                        f.getTurnoFuncionario()

                    });

                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void listaFuncionarioCod() {

        FuncionarioDao pdao = new FuncionarioDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                for (Funcionario fu : pdao.readFuncionarioForCod(EdCod.getText())) {

                    EdNome.setText(fu.getNomeFuncionario());
                    EdSetor.setText(fu.getSetorFuncionario());
                    EdCargo.setText(fu.getCargoFuncionario());
                    EdCpf.setText(fu.getCpfFuncionario());
                    EdTurno.setText(fu.getTurnoFuncionario());
                    habilitarB(3);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void editarFuncionario() throws ClassNotFoundException {

        if (tabela.getSelectedRow() != -1) {
            Funcionario f = new Funcionario();
            FuncionarioDao dao = new FuncionarioDao();

            if (EdNome.getText().equals("") || EdSetor.getText().equals("") || EdCpf.getText().equals("")
                    || EdCargo.getText().equals("") || EdNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
            } else {

                f.setNomeFuncionario(EdNome.getText());
                f.setCargoFuncionario(EdCargo.getText());
                f.setSetorFuncionario(EdSetor.getText());
                f.setCpfFuncionario(EdCpf.getText());
                f.setTurnoFuncionario(EdTurno.getText());
                f.setCodFuncionario((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                try {
                    dao.updateFuncionario(f);
                    limpaCampos();
                    listarFuncionario();
                    habilitarB(1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public void deletarFuncionario() throws ClassNotFoundException {
        Funcionario f = new Funcionario();
        FuncionarioDao dao = new FuncionarioDao();
        habilitarB(1);
        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário para excluír");
        } else {
            f.setCodFuncionario(Integer.parseInt(EdCod.getText()));
            try {
                String nome = EdNome.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o cadastro do funcionário  " + nome, "Atenção", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    dao.deleteFuncionario(f);
                    habilitarB(1);
                    limpaCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }

            listarFuncionario();

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
        jLabel2 = new javax.swing.JLabel();
        EdCod = new javax.swing.JTextField();
        BtBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        EdNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EdSetor = new javax.swing.JTextField();
        EdCargo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        BtNovo = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        BtSair = new javax.swing.JButton();
        BtExcluir = new javax.swing.JButton();
        BtSalvar = new javax.swing.JButton();
        BtSalvarEdit = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        EdCpf = new javax.swing.JFormattedTextField();
        EdTurno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        EdBuscarNome = new javax.swing.JTextField();
        BtBuscarNome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Manutenção de Funcionário - V1.0-20.0810");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel2.setText("Código: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, -1, -1));
        jPanel1.add(EdCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 12, 78, -1));

        BtBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscar.setText("Buscar");
        BtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(BtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 11, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, -1, -1));
        jPanel1.add(EdNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 52, 241, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Setor: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 91, -1, -1));
        jPanel1.add(EdSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 129, -1));
        jPanel1.add(EdCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 90, 137, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setText("Cargo: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 91, -1, -1));

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
        jPanel4.add(BtCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 120, 33));

        BtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit_black.png"))); // NOI18N
        BtSair.setText("SAIR");
        BtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSairActionPerformed(evt);
            }
        });
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 90, 33));

        BtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_red.png"))); // NOI18N
        BtExcluir.setText("EXCLUÍR");
        BtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(BtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, 33));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, 33));

        BtSalvarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvarEdit.setText("SALVAR");
        BtSalvarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarEditActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvarEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 90, 33));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 560, 52));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel11.setText("CPF: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 53, -1, -1));

        try {
            EdCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(EdCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 52, 105, -1));
        jPanel1.add(EdTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 129, 129, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel6.setText("Turno:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 128, -1, 20));

        jTabbedPane1.addTab("Cadastrar Funcionário", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel8.setText("Nome:");

        EdBuscarNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdBuscarNomeKeyPressed(evt);
            }
        });

        BtBuscarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscarNome.setText("Buscar");
        BtBuscarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarNomeActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Nome", "CPF", "Setor", "Cargo", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            tabela.getColumnModel().getColumn(0).setMinWidth(40);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabela.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel9.setText("Dois cliques para editar*");

        jLabel10.setText("Usuários cadastrados:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EdBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtBuscarNome))
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(EdBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtBuscarNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listar Funcionário", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 560, 320));

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Funcionário");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(311, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtNovoActionPerformed
        habilitarB(2);
        EdCod.requestFocus();
    }//GEN-LAST:event_BtNovoActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        habilitarB(1);
        limpaCampos();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed
        validarCpfCadastro();
    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtSalvarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarEditActionPerformed
        try {
            editarFuncionario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtSalvarEditActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        try {
            deletarFuncionario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarActionPerformed
        validarCodCadastro();
    }//GEN-LAST:event_BtBuscarActionPerformed

    private void BtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarNomeActionPerformed
        try {
            listarFuncionario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtBuscarNomeActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (tabela.getSelectedRow() != -1) {
            jTabbedPane1.setSelectedIndex(0);
            EdCod.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            EdCpf.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            EdSetor.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
            EdCargo.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
            EdTurno.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
            habilitarB(3);

        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void EdBuscarNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdBuscarNomeKeyPressed
        try {
            listarFuncionario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EdBuscarNomeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtBuscar;
    private javax.swing.JButton BtBuscarNome;
    private javax.swing.JButton BtCancelar;
    private javax.swing.JButton BtExcluir;
    private javax.swing.JButton BtNovo;
    private javax.swing.JButton BtSair;
    private javax.swing.JButton BtSalvar;
    private javax.swing.JButton BtSalvarEdit;
    private javax.swing.JTextField EdBuscarNome;
    private javax.swing.JTextField EdCargo;
    private javax.swing.JTextField EdCod;
    private javax.swing.JFormattedTextField EdCpf;
    private javax.swing.JTextField EdNome;
    private javax.swing.JTextField EdSetor;
    private javax.swing.JTextField EdTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
