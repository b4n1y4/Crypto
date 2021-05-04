// 
// Decompiled by Procyon v0.5.36
// 

package crypto;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CryptoGraphy extends JFrame
{
    private JButton btnOpenDecrypter;
    private JButton btnOpenEncrypter;
    private JLabel jLabel1;
    private JLabel jLabel2;
    
    public CryptoGraphy() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.btnOpenEncrypter = new JButton();
        this.btnOpenDecrypter = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("CryptoGram");
        this.setBackground(new Color(153, 255, 153));
        this.setResizable(false);
        this.jLabel1.setText("  Hello, this is a cryptogram developed by Ritesh Gupta to enctypt a message ");
        this.jLabel2.setText("  according to a Code.");
        this.btnOpenEncrypter.setText("Open Encrypter");
        this.btnOpenEncrypter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CryptoGraphy.this.btnOpenEncrypterActionPerformed(evt);
            }
        });
        this.btnOpenDecrypter.setText("Open Decrypter");
        this.btnOpenDecrypter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CryptoGraphy.this.btnOpenDecrypterActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -1, 380, 32767).addComponent(this.jLabel2, GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(74, 74, 74).addComponent(this.btnOpenEncrypter).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.btnOpenDecrypter).addGap(82, 82, 82)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(53, 53, 53).addComponent(this.jLabel1, -2, 23, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addGap(54, 54, 54).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.btnOpenEncrypter).addComponent(this.btnOpenDecrypter)).addContainerGap(127, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void btnOpenEncrypterActionPerformed(final ActionEvent evt) {
        new Encrypt().setVisible(true);
    }
    
    private void btnOpenDecrypterActionPerformed(final ActionEvent evt) {
        new Decrypt().setVisible(true);
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(CryptoGraphy.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(CryptoGraphy.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(CryptoGraphy.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(CryptoGraphy.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CryptoGraphy().setVisible(true);
            }
        });
    }
}
