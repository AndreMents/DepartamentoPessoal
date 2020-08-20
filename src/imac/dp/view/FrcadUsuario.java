/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imac.dp.view;

import imac.dp.dao.UsuarioDao;
import imac.dp.model.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author @andre_ments
 */
public class FrcadUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrcadUsuario
     */
    public FrcadUsuario() {
        initComponents();
        habilitarB(1);
        
    }

    //HABILITA OU DESABILITA ITENS DO FRONT
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
                EdSetor.setEnabled(false);
                EdLogin.setEnabled(false);
                EdSenha.setEnabled(false);
                EdCargo.setEnabled(false);
                EdNome.requestFocus();
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
                EdLogin.setEnabled(true);
                EdSenha.setEnabled(true);
                EdCargo.setEnabled(true);
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
                EdCod.setEnabled(false);
                EdSetor.setEnabled(true);
                EdLogin.setEnabled(false);
                EdSenha.setEnabled(true);
                EdCargo.setEnabled(true);
                EdNome.requestFocus();
                break;
        }
    }

    //LIMPA CAMPOS DO FRONT
    public void limpaCampos() {
        EdCod.setText(null);
        EdNome.setText(null);
        EdSetor.setText(null);
        EdCargo.setText(null);
        EdLogin.setText(null);
        EdSenha.setText(null);
        EdCargo.setText(null);
    }

    //VERIFICAR SE O USUÁRIO JÁ POSSUI CADASTRO
    public void checkCreate() throws SQLException {
        UsuarioDao pdao = new UsuarioDao();
        if (EdNome.getText().equals("") || EdSetor.getText().equals("") || EdLogin.getText().equals("") || EdSenha.getText().equals("")
                || EdCargo.getText().equals("") || EdNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                if (pdao.checkCreate(EdLogin.getText())) {

                    JOptionPane.showMessageDialog(null, "Usuário já possui cadastro");
                    habilitarB(1);
                } else {
                    salvarUsuario();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //CACADASTRA USUÁRIO
    public void salvarUsuario() {

        Usuario u = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        habilitarB(2);
        u.setCodUsuario(Integer.parseInt(EdCod.getText()));
        u.setNomeUsuario(EdNome.getText());
        u.setSetorUsuario(EdSetor.getText());
        u.setCargoUsuario(EdCargo.getText());
        u.setLoginUsuario(EdLogin.getText());
        u.setSenhaUsuario(EdSenha.getText());

        try {
            dao.createUser(u);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpaCampos();
    }

    //LISTA USUÁRIO
    public void listarUsuarios() {

        habilitarB(1);

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        UsuarioDao pdao = new UsuarioDao();

        if (EdBuscarNome.getText().equals("")) {
            try {
                for (Usuario u : pdao.readUser()) {
                    modelo.addRow(new Object[]{
                        u.getCodUsuario(),
                        u.getNomeUsuario(),
                        u.getSetorUsuario(),
                        u.getCargoUsuario(),
                        u.getLoginUsuario(),
                        u.getSenhaUsuario()

                    });
                }

            } catch (ClassNotFoundException ex) {

                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                for (Usuario u : pdao.readUserForDesc(EdBuscarNome.getText())) {
                    modelo.addRow(new Object[]{
                        u.getCodUsuario(),
                        u.getNomeUsuario(),
                        u.getSetorUsuario(),
                        u.getCargoUsuario(),
                        u.getLoginUsuario(),
                        u.getSenhaUsuario()
                    });
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //VALIDA O CÓDIGO DO USUÁRIO
    public void validarCodList() {
        UsuarioDao pdao = new UsuarioDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                if (pdao.checkCod(EdCod.getText())) {
                    listaUsuarioCod();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //LISTA USUÁRIO POR CÓDIGO
    public void listaUsuarioCod() {

        UsuarioDao pdao = new UsuarioDao();

        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário!");
        } else {

            try {
                for (Usuario usu : pdao.readUserForCod(EdCod.getText())) {
                    EdCod.setText(Integer.toString(usu.getCodUsuario()));
                    EdNome.setText(usu.getNomeUsuario());
                    EdSetor.setText(usu.getSetorUsuario());
                    EdCargo.setText(usu.getCargoUsuario());
                    EdLogin.setText(usu.getLoginUsuario());
                    EdSenha.setText(usu.getSenhaUsuario());
                    habilitarB(3);
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, " Usuário não encontrado");
                Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //EDITA USUÁRIO
    public void editarUsuario() {

        if (tabela.getSelectedRow() != -1) {
            Usuario u = new Usuario();
            UsuarioDao dao = new UsuarioDao();

            if (EdNome.getText().equals("") || EdSetor.getText().equals("") || EdLogin.getText().equals("") || EdSenha.getText().equals("")
                    || EdCargo.getText().equals("") || EdNome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO\n Você deve preencher todos os campos");
            } else {

                u.setNomeUsuario(EdNome.getText());
                u.setCargoUsuario(EdCargo.getText());
                u.setSetorUsuario(EdSetor.getText());
                u.setLoginUsuario(EdLogin.getText());
                u.setSenhaUsuario(EdSenha.getText());
                u.setCodUsuario((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                try {
                    dao.update(u);

                    limpaCampos();
                    listarUsuarios();

                    habilitarB(1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }
    
    //DELETA USUÁRIO                }
    public void deletarUsuario() {
        Usuario u = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        
        if (EdCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código do usuário para excluír");
        } else {

            u.setCodUsuario(Integer.parseInt(EdCod.getText()));
            try {

                String nome = EdNome.getText();

                int input = JOptionPane.showConfirmDialog(null,
                        " ATENÇÃO!" + "\nDeseja mesmo excluír o cadastro do usuário  " + nome, null, JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    dao.delete(u);
                    habilitarB(1);
                    limpaCampos();
                    listarUsuarios();
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
        jLabel2 = new javax.swing.JLabel();
        EdCod = new javax.swing.JTextField();
        BtBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        EdNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EdSetor = new javax.swing.JTextField();
        EdCargo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EdLogin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        EdSenha = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        BtExcluir = new javax.swing.JButton();
        BtNovo = new javax.swing.JButton();
        BtCancelar = new javax.swing.JButton();
        BtSair = new javax.swing.JButton();
        BtSalvar = new javax.swing.JButton();
        BtSalvarEdit = new javax.swing.JButton();
        CbNivelUsuario = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
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
        setTitle("Manutenção de Usuário - V1.0-20.0810");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel2.setText("Código: ");

        BtBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-localizar-e-substituir-24 (1).png"))); // NOI18N
        BtBuscar.setText("Buscar");
        BtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel4.setText("Setor: ");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setText("Cargo: ");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel6.setText("Login: ");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel7.setText("Senha: ");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel4.add(BtSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 78, 33));

        BtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvar.setText("SALVAR");
        BtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 100, 33));

        BtSalvarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-blue.png"))); // NOI18N
        BtSalvarEdit.setText("SALVAR");
        BtSalvarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSalvarEditActionPerformed(evt);
            }
        });
        jPanel4.add(BtSalvarEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 100, 33));

        CbNivelUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuário Comum", "Usuário Avançado", "Administrador" }));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel11.setText("Nivel Usuário: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EdCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EdCod, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BtBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CbNivelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(EdNome, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(EdSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(EdCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtBuscar)
                    .addComponent(CbNivelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(EdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(EdSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(EdCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(EdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(EdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Cadastrar Usuário", jPanel1);

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
                "Código", "Nome", "Setor", "Cargo", "Login", "Senha"
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

        jLabel9.setText("Dois cliques para editar*");

        jLabel10.setText("Usuários cadastrados:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listar Usuários", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 550, 330));

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Usuário");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtSairActionPerformed

    private void BtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtExcluirActionPerformed
        deletarUsuario();
    }//GEN-LAST:event_BtExcluirActionPerformed

    private void BtSalvarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarEditActionPerformed
        editarUsuario();

    }//GEN-LAST:event_BtSalvarEditActionPerformed

    private void BtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarNomeActionPerformed
        listarUsuarios();
    }//GEN-LAST:event_BtBuscarNomeActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        //INSERE OS DADOS DO USUÁRIO QUE ESTÁ NA TABELA PARA OS CAMPOS
        if (tabela.getSelectedRow() != -1) {
            jTabbedPane1.setSelectedIndex(0);
            EdCod.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
            EdNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            EdSetor.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            EdCargo.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
            EdLogin.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
            EdSenha.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());

            habilitarB(3);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void BtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSalvarActionPerformed
        try {
            checkCreate();
        } catch (SQLException ex) {
            Logger.getLogger(FrcadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarB(1);
    }//GEN-LAST:event_BtSalvarActionPerformed

    private void BtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtNovoActionPerformed
        habilitarB(2);
        EdCod.requestFocus();
    }//GEN-LAST:event_BtNovoActionPerformed

    private void BtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCancelarActionPerformed
        habilitarB(1);
        limpaCampos();
    }//GEN-LAST:event_BtCancelarActionPerformed

    private void BtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtBuscarActionPerformed
        validarCodList();

    }//GEN-LAST:event_BtBuscarActionPerformed

    private void EdBuscarNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdBuscarNomeKeyPressed
        listarUsuarios();
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
    private javax.swing.JComboBox CbNivelUsuario;
    private javax.swing.JTextField EdBuscarNome;
    private javax.swing.JTextField EdCargo;
    private javax.swing.JTextField EdCod;
    private javax.swing.JTextField EdLogin;
    private javax.swing.JTextField EdNome;
    private javax.swing.JPasswordField EdSenha;
    private javax.swing.JTextField EdSetor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
