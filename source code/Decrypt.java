// 
// Decompiled by Procyon v0.5.36
// 

package crypto;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Decrypt extends JFrame
{
    private JButton btnDecrypt;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JPasswordField passCode2;
    private JTextArea txtInput2;
    private JTextArea txtOutput2;
    
    public Decrypt() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.txtInput2 = new JTextArea();
        this.jLabel2 = new JLabel();
        this.passCode2 = new JPasswordField();
        this.btnDecrypt = new JButton();
        this.jScrollPane2 = new JScrollPane();
        this.txtOutput2 = new JTextArea();
        this.setDefaultCloseOperation(3);
        this.jLabel1.setFont(new Font("Tahoma", 0, 18));
        this.jLabel1.setText("Enter the Encrypted message:");
        this.txtInput2.setColumns(20);
        this.txtInput2.setLineWrap(true);
        this.txtInput2.setRows(5);
        this.jScrollPane1.setViewportView(this.txtInput2);
        this.jLabel2.setFont(new Font("Tahoma", 0, 18));
        this.jLabel2.setText("Enter the code:");
        this.btnDecrypt.setText("Decrypt");
        this.btnDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Decrypt.this.btnDecryptActionPerformed(evt);
            }
        });
        this.txtOutput2.setColumns(20);
        this.txtOutput2.setLineWrap(true);
        this.txtOutput2.setRows(5);
        this.jScrollPane2.setViewportView(this.txtOutput2);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jScrollPane2).addComponent(this.jScrollPane1, GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.passCode2, GroupLayout.Alignment.LEADING).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addComponent(this.btnDecrypt)).addGap(0, 384, 32767))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 131, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.passCode2, -2, -1, -2).addGap(18, 18, 18).addComponent(this.btnDecrypt).addGap(18, 18, 18).addComponent(this.jScrollPane2, -1, 181, 32767).addContainerGap()));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void btnDecryptActionPerformed(final ActionEvent evt) {
        final String msg = this.txtInput2.getText();
        final String code = this.passCode2.getText();
        if (msg.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please Enter the message");
            return;
        }
        if (code.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please enter the code");
            return;
        }
        if (code.length() > 5 || !this.checkNum(code)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid code");
            return;
        }
        final StringTokenizer st = new StringTokenizer(msg, "_");
        String res = "";
        int i = 0;
        while (st.hasMoreTokens()) {
            res = res + this.decode(st.nextToken(), code) + " ";
            ++i;
        }
        this.txtOutput2.setText(res);
    }
    
    public boolean checkNum(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
    
    public int toOct(int n) {
        String s = "";
        while (n >= 8) {
            s = n % 8 + s;
            n /= 8;
        }
        s = n + s;
        return Integer.parseInt(s);
    }
    
    public String toBin(int n) {
        String s = "";
        while (n >= 1) {
            s = n % 2 + s;
            n /= 2;
        }
        return s;
    }
    
    public int OtoD(int n) {
        int p = 0;
        int c = 0;
        while (n >= 1) {
            p += (int)Math.pow(8.0, c++) * (n % 10);
            n /= 10;
        }
        return p;
    }
    
    public int BtoD(final String s) {
        int res = 0;
        int c = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            final int n = s.charAt(i) - '0';
            res += (int)(Math.pow(2.0, c++) * n);
        }
        return res;
    }
    
    public String subBin(final String s, final int n) {
        return this.toBin(Math.abs(this.BtoD(s) - n));
    }
    
    public int subOct(final int m, final int n) {
        return this.toOct(Math.abs(this.OtoD(m) - n));
    }
    
    public String first(final String s, final String pass) {
        String res = "";
        final int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        final char[] ch = { 'X', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            for (int j = 0; j < ch.length; ++j) {
                if (c == ' ') {
                    res += " ";
                }
                else if (c == ch[j]) {
                    res += num[j];
                }
            }
        }
        return res;
    }
    
    public String second(String s, final String pass) {
        int x = Integer.parseInt(pass.substring(1, 3));
        final StringTokenizer st = new StringTokenizer(s);
        s = "";
        int i = 0;
        while (st.hasMoreTokens()) {
            if (x % 2 == 0) {
                s = s + this.toOct(Integer.parseInt(st.nextToken())) + " ";
            }
            else {
                s = s + this.toBin(Integer.parseInt(st.nextToken())) + " ";
            }
            --x;
            ++i;
        }
        return s;
    }
    
    public String third(String s, final String pass) {
        int k = Integer.parseInt(pass.substring(1, 3));
        final StringTokenizer st = new StringTokenizer(s);
        s = "";
        final int p = Integer.parseInt(pass.substring(3));
        int i = 0;
        while (st.hasMoreTokens()) {
            if (k % 2 == 0) {
                s = s + this.subOct(Integer.parseInt(st.nextToken()), p) + " ";
            }
            else {
                s = s + this.subBin(st.nextToken(), p) + " ";
            }
            --k;
            ++i;
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
    
    public String fourth(String s, final String pass) {
        final StringTokenizer st = new StringTokenizer(s);
        s = "";
        int n = Integer.parseInt(pass.substring(1, 3));
        int i = 0;
        while (st.hasMoreTokens()) {
            if (n % 2 == 0) {
                s = s + this.OtoD(Integer.parseInt(st.nextToken())) + " ";
            }
            else {
                s = s + this.BtoD(st.nextToken()) + " ";
            }
            --n;
            ++i;
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
    
    public String fifth(String s, final String pass) {
        final StringTokenizer st = new StringTokenizer(s);
        s = "";
        int i = 0;
        while (st.hasMoreTokens()) {
            s += (char)(Integer.parseInt(st.nextToken()) - (pass.charAt(0) - '0'));
            ++i;
        }
        return s;
    }
    
    public String decode(final String s, final String pass) {
        return this.fifth(this.fourth(this.third(this.second(this.first(s, pass), pass), pass), pass), pass);
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Decrypt().setVisible(true);
            }
        });
    }
}
