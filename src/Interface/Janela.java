/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Compilador.Comunicador;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean Luiz
 */
public class Janela extends javax.swing.JFrame {
    private Comunicador com;
    private String codigo;

    /**
     * Creates new form Janela janela de interface com o usuário (para analise
     * Léxica)
     */
    public Janela() {
        initComponents();       
        setTitle("Compilador JLZ - Componente Curricular Compiladores");
        getContentPane().setLayout(null);
        
        jInternalFrameCodigoFonte.setIconifiable(true);
        jInternalFrameCodigoFonte.setResizable(true);
        jInternalFrameCodigoFonte.setTitle("Código Fonte");
        jTextAreaCodigoFonte.setFont(new java.awt.Font("Monospaced", 1, 12));
        
        jInternalFrameTokens.setIconifiable(true);
        jInternalFrameTokens.setResizable(true);
        jInternalFrameTokens.setTitle("Tokens");
        jTextAreaTokens.setEditable(false);
        jTextAreaTokens.setFont(new java.awt.Font("Monospaced", 1, 12));
        jTextAreaTokens.setForeground(Color.GREEN);
        jTextAreaTokens.setText("Clique no botão Analisar...");
        
        jInternalFrameErros.setIconifiable(true);
        jInternalFrameErros.setResizable(true);
        jInternalFrameErros.setTitle("Erros");
        //jInternalFrameErros.setPreferredSize(new java.awt.Dimension(350, 550));
        jTextAreaErros.setEditable(false);
        jTextAreaErros.setFont(new java.awt.Font("Monospaced", 1, 12));
        jTextAreaErros.setForeground(Color.RED);       
    }
    
    private void analisar() {
       com = new Comunicador(codigo);
       jTextAreaTokens.setText(com.getTokens());
       jTextAreaErros.setText(com.getErros());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jInternalFrameCodigoFonte = new javax.swing.JInternalFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaCodigoFonte = new javax.swing.JTextArea();
        jButtonAnalisar = new javax.swing.JButton();
        jButtonLimparCodigoFonte = new javax.swing.JButton();
        jButtonEditarCodigoFonte = new javax.swing.JButton();
        jInternalFrameTokens = new javax.swing.JInternalFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaTokens = new javax.swing.JTextArea();
        jButtonLimparTokens = new javax.swing.JButton();
        jInternalFrameErros = new javax.swing.JInternalFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaErros = new javax.swing.JTextArea();
        jButtonLimparErros = new javax.swing.JButton();
        jMenuBarra = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemNovoFonte = new javax.swing.JMenuItem();
        jMenuItemAbrirFonte = new javax.swing.JMenuItem();
        jMenuItemSalvarFonte = new javax.swing.JMenuItem();
        jMenuEditar = new javax.swing.JMenu();
        jMenuItemLimparTudo = new javax.swing.JMenuItem();
        jMenuInfo = new javax.swing.JMenu();
        jMenuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrameCodigoFonte.setVisible(true);
        jInternalFrameCodigoFonte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jInternalFrameCodigoFonteMouseEntered(evt);
            }
        });

        jTextAreaCodigoFonte.setColumns(20);
        jTextAreaCodigoFonte.setRows(5);
        jScrollPane4.setViewportView(jTextAreaCodigoFonte);

        jButtonAnalisar.setText("Analisar");
        jButtonAnalisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalisarActionPerformed(evt);
            }
        });

        jButtonLimparCodigoFonte.setText("Limpar");
        jButtonLimparCodigoFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparCodigoFonteActionPerformed(evt);
            }
        });

        jButtonEditarCodigoFonte.setText("Editar");
        jButtonEditarCodigoFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarCodigoFonteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrameCodigoFonteLayout = new javax.swing.GroupLayout(jInternalFrameCodigoFonte.getContentPane());
        jInternalFrameCodigoFonte.getContentPane().setLayout(jInternalFrameCodigoFonteLayout);
        jInternalFrameCodigoFonteLayout.setHorizontalGroup(
            jInternalFrameCodigoFonteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameCodigoFonteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonLimparCodigoFonte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditarCodigoFonte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(jButtonAnalisar)
                .addContainerGap())
        );
        jInternalFrameCodigoFonteLayout.setVerticalGroup(
            jInternalFrameCodigoFonteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCodigoFonteLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrameCodigoFonteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnalisar)
                    .addComponent(jButtonLimparCodigoFonte)
                    .addComponent(jButtonEditarCodigoFonte)))
        );

        jInternalFrameTokens.setVisible(true);
        jInternalFrameTokens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jInternalFrameTokensMouseEntered(evt);
            }
        });

        jTextAreaTokens.setColumns(20);
        jTextAreaTokens.setRows(5);
        jScrollPane5.setViewportView(jTextAreaTokens);

        jButtonLimparTokens.setText("Limpar");
        jButtonLimparTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparTokensActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrameTokensLayout = new javax.swing.GroupLayout(jInternalFrameTokens.getContentPane());
        jInternalFrameTokens.getContentPane().setLayout(jInternalFrameTokensLayout);
        jInternalFrameTokensLayout.setHorizontalGroup(
            jInternalFrameTokensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameTokensLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLimparTokens)
                .addContainerGap())
        );
        jInternalFrameTokensLayout.setVerticalGroup(
            jInternalFrameTokensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameTokensLayout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLimparTokens))
        );

        jInternalFrameErros.setVisible(true);
        jInternalFrameErros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jInternalFrameErrosMouseEntered(evt);
            }
        });

        jTextAreaErros.setColumns(20);
        jTextAreaErros.setRows(5);
        jScrollPane6.setViewportView(jTextAreaErros);

        jButtonLimparErros.setText("Limpar");
        jButtonLimparErros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparErrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrameErrosLayout = new javax.swing.GroupLayout(jInternalFrameErros.getContentPane());
        jInternalFrameErros.getContentPane().setLayout(jInternalFrameErrosLayout);
        jInternalFrameErrosLayout.setHorizontalGroup(
            jInternalFrameErrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameErrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLimparErros)
                .addContainerGap())
        );
        jInternalFrameErrosLayout.setVerticalGroup(
            jInternalFrameErrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameErrosLayout.createSequentialGroup()
                .addComponent(jScrollPane6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLimparErros))
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jInternalFrameCodigoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrameTokens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrameErros))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrameCodigoFonte)
            .addComponent(jInternalFrameTokens)
            .addComponent(jInternalFrameErros)
        );

        jMenuArquivo.setText("Arquivo");

        jMenuItemNovoFonte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNovoFonte.setText("Novo Fonte...");
        jMenuItemNovoFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoFonteActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemNovoFonte);

        jMenuItemAbrirFonte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrirFonte.setText("Abrir Fonte...");
        jMenuItemAbrirFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirFonteActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemAbrirFonte);

        jMenuItemSalvarFonte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalvarFonte.setText("Salvar...");
        jMenuItemSalvarFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalvarFonteActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemSalvarFonte);

        jMenuBarra.add(jMenuArquivo);

        jMenuEditar.setText("Editar");

        jMenuItemLimparTudo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemLimparTudo.setText("Limpar Tudo");
        jMenuItemLimparTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLimparTudoActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemLimparTudo);

        jMenuBarra.add(jMenuEditar);

        jMenuInfo.setText("Informações");

        jMenuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSobre.setText("Sobre");
        jMenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSobreActionPerformed(evt);
            }
        });
        jMenuInfo.add(jMenuItemSobre);

        jMenuBarra.add(jMenuInfo);

        setJMenuBar(jMenuBarra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemLimparTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLimparTudoActionPerformed
        // TODO add your handling code here:
        jTextAreaCodigoFonte.setText("");
        jTextAreaErros.setText("");
        jTextAreaTokens.setText("");
        
        jTextAreaCodigoFonte.requestFocus();
    }//GEN-LAST:event_jMenuItemLimparTudoActionPerformed

    private void jMenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSobreActionPerformed
        // TODO add your handling code here:
        String sobre = "UNOESC - Curso de Engenharia de Computação \n\n Acadêmico: Jean Luiz Zanatta    Cod. 202199 \n\n Professor: Cristiano Rodrigo Azevedo \n\n Trabalho Apresentado à Disciplina de Compiladores";
        JOptionPane.showMessageDialog(null,sobre);
    }//GEN-LAST:event_jMenuItemSobreActionPerformed

    private void jMenuItemNovoFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoFonteActionPerformed
        // TODO add your handling code here:
        jTextAreaCodigoFonte.setText("");
    }//GEN-LAST:event_jMenuItemNovoFonteActionPerformed

    private void jMenuItemAbrirFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirFonteActionPerformed
        // TODO add your handling code here:
        //cria layout do tipo jfilechooser p/ abrir o arquivo 
        JFileChooser fChooser = new JFileChooser();      
        fChooser.showOpenDialog(this);
        //armazena arquivo selecionado
        File f = fChooser.getSelectedFile();
        String str = "";
        try {
            if(f != null) {
                //le dados do arquivo
                BufferedReader in = new BufferedReader(new FileReader(f));
                while(in.ready()) {
                    str = str + in.readLine() + "\n";
                }
                in.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo!", "Erro: " + f.getName(), JOptionPane.ERROR_MESSAGE);
        }
        //seta dados do arquivo no text area
        if(!str.equals("")) {
            codigo = str;
            jTextAreaCodigoFonte.setText(codigo);
        }
    }//GEN-LAST:event_jMenuItemAbrirFonteActionPerformed

    private void jButtonLimparCodigoFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparCodigoFonteActionPerformed
        // TODO add your handling code here:
        jTextAreaCodigoFonte.setText("");
    }//GEN-LAST:event_jButtonLimparCodigoFonteActionPerformed

    private void jButtonAnalisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalisarActionPerformed
        // TODO add your handling code here:
        //limpa campos token e erros
        jTextAreaErros.setText("");
        jTextAreaTokens.setText("");
        //o campo código é editavel
        if(jTextAreaCodigoFonte.isEditable()) {
            //armazena código
            codigo = jTextAreaCodigoFonte.getText();
            //lógica p mostrar numero de linhas no campo
            String[] linhas = codigo.split("\n", -2);
            String codigoComLinhas = "";
            for(int i = 0; i < linhas.length; i++) {  
                //armazena linha(quantidade) : linha(valor)
                codigoComLinhas = codigoComLinhas + String.valueOf(i) + ": " + linhas[i] + "\n";
            }
            //seta no campo o codigo com as linhas
            jTextAreaCodigoFonte.setText(codigoComLinhas);
            //campo torna-se não editavel
            jTextAreaCodigoFonte.setEditable(false);
        }
        //chama método que faz análise do código
        analisar();
    }//GEN-LAST:event_jButtonAnalisarActionPerformed

    private void jButtonLimparTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparTokensActionPerformed
        // TODO add your handling code here:
        jTextAreaTokens.setText("");
    }//GEN-LAST:event_jButtonLimparTokensActionPerformed

    private void jButtonLimparErrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparErrosActionPerformed
        // TODO add your handling code here:
        jTextAreaErros.setText("");
    }//GEN-LAST:event_jButtonLimparErrosActionPerformed

    private void jButtonEditarCodigoFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarCodigoFonteActionPerformed
        // TODO add your handling code here:
        //tornando a linha editavel novamente
        if (!jTextAreaCodigoFonte.isEditable()) {
            jTextAreaCodigoFonte.setEditable(true);
            //seta código no campo novamente sem o numero linhas
            jTextAreaCodigoFonte.setText(codigo);
        }
    }//GEN-LAST:event_jButtonEditarCodigoFonteActionPerformed

    private void jInternalFrameCodigoFonteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrameCodigoFonteMouseEntered
        // TODO add your handling code here:
        jInternalFrameCodigoFonte.toFront();
    }//GEN-LAST:event_jInternalFrameCodigoFonteMouseEntered

    private void jInternalFrameTokensMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrameTokensMouseEntered
        // TODO add your handling code here:
        jInternalFrameTokens.toFront();
    }//GEN-LAST:event_jInternalFrameTokensMouseEntered

    private void jInternalFrameErrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrameErrosMouseEntered
        // TODO add your handling code here:
        jInternalFrameErros.toFront();
    }//GEN-LAST:event_jInternalFrameErrosMouseEntered

    private void jMenuItemSalvarFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalvarFonteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemSalvarFonteActionPerformed
          
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Janela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnalisar;
    private javax.swing.JButton jButtonEditarCodigoFonte;
    private javax.swing.JButton jButtonLimparCodigoFonte;
    private javax.swing.JButton jButtonLimparErros;
    private javax.swing.JButton jButtonLimparTokens;
    private javax.swing.JInternalFrame jInternalFrameCodigoFonte;
    private javax.swing.JInternalFrame jInternalFrameErros;
    private javax.swing.JInternalFrame jInternalFrameTokens;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBarra;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenu jMenuInfo;
    private javax.swing.JMenuItem jMenuItemAbrirFonte;
    private javax.swing.JMenuItem jMenuItemLimparTudo;
    private javax.swing.JMenuItem jMenuItemNovoFonte;
    private javax.swing.JMenuItem jMenuItemSalvarFonte;
    private javax.swing.JMenuItem jMenuItemSobre;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextAreaCodigoFonte;
    private javax.swing.JTextArea jTextAreaErros;
    private javax.swing.JTextArea jTextAreaTokens;
    // End of variables declaration//GEN-END:variables
}