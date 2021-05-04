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
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Encrypt extends JFrame
{
    private JButton btnEncrypt;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JPasswordField passCode1;
    private JTextArea txtInput1;
    private JTextArea txtOutput1;
    
    public Encrypt() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.txtInput1 = new JTextArea();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.passCode1 = new JPasswordField();
        this.btnEncrypt = new JButton();
        this.jScrollPane2 = new JScrollPane();
        this.txtOutput1 = new JTextArea();
        this.setDefaultCloseOperation(2);
        this.txtInput1.setColumns(20);
        this.txtInput1.setLineWrap(true);
        this.txtInput1.setRows(5);
        this.jScrollPane1.setViewportView(this.txtInput1);
        this.jLabel1.setFont(new Font("Tahoma", 0, 18));
        this.jLabel1.setText("Enter the message to be Encrypted :");
        this.jLabel2.setFont(new Font("Tahoma", 0, 18));
        this.jLabel2.setText("Enter the code:");
        this.btnEncrypt.setText("Encrypt");
        this.btnEncrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Encrypt.this.btnEncryptActionPerformed(evt);
            }
        });
        this.txtOutput1.setColumns(20);
        this.txtOutput1.setLineWrap(true);
        this.txtOutput1.setRows(5);
        this.jScrollPane2.setViewportView(this.txtOutput1);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jScrollPane2).addComponent(this.jScrollPane1, GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.passCode1, GroupLayout.Alignment.LEADING).addComponent(this.jLabel2, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addComponent(this.btnEncrypt)).addGap(0, 331, 32767))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 132, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.passCode1, -2, -1, -2).addGap(18, 18, 18).addComponent(this.btnEncrypt).addGap(18, 18, 18).addComponent(this.jScrollPane2, -1, 180, 32767).addContainerGap()));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void btnEncryptActionPerformed(final ActionEvent evt) {
        final String msg = this.txtInput1.getText();
        final String code = this.passCode1.getText();
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
            res = res + this.encode(st.nextToken(), code) + " ";
            ++i;
        }
        this.txtOutput1.setText(res);
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
        String res = "";
        while (n >= 1) {
            res = n % 2 + res;
            n /= 2;
        }
        return res;
    }
    
    public String addBin(final int s, final String s2) {
        return this.toBin(s + this.BtoD(s2));
    }
    
    public int addOct(final int n, final int n2) {
        return this.toOct(n + this.OtoD(n2));
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
    
    public String first(final String s, final String pass) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            if (i < s.length() - 1) {
                s2 = s2 + (s.charAt(i) + (pass.charAt(0) - '0')) + ".";
            }
            else {
                s2 += s.charAt(i) + (pass.charAt(0) - '0');
            }
        }
        return s2;
    }
    
    public String second(String s, final String pass) {
        final StringTokenizer st = new StringTokenizer(s, ".");
        s = "";
        int n = Integer.parseInt(pass.substring(1, 3));
        final int l = st.countTokens();
        int i = 0;
        while (st.hasMoreTokens()) {
            if (i < l - 1) {
                if (n % 2 == 0) {
                    s = s + this.toOct(Integer.parseInt(st.nextToken())) + ".";
                }
                else {
                    s = s + this.toBin(Integer.parseInt(st.nextToken())) + ".";
                }
            }
            else if (n % 2 == 0) {
                s += this.toOct(Integer.parseInt(st.nextToken()));
            }
            else {
                s += this.toBin(Integer.parseInt(st.nextToken()));
            }
            --n;
            ++i;
        }
        return s;
    }
    
    public String third(String s, final String pass) {
        int k = Integer.parseInt(pass.substring(1, 3));
        final StringTokenizer st = new StringTokenizer(s, ".");
        s = "";
        final int p = Integer.parseInt(pass.substring(3));
        int i = 0;
        while (st.hasMoreTokens()) {
            if (k % 2 == 0) {
                s = s + this.addOct(p, Integer.parseInt(st.nextToken())) + " ";
            }
            else {
                s = s + this.addBin(p, st.nextToken()) + " ";
            }
            --k;
            ++i;
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
    
    public String fourth(String s, final String pass) {
        int x = Integer.parseInt(pass.substring(1, 3));
        final StringTokenizer st = new StringTokenizer(s);
        s = "";
        int i = 0;
        while (st.hasMoreTokens()) {
            final String xy = st.nextToken();
            if (x % 2 == 0) {
                s = s + this.OtoD(Integer.parseInt(xy)) + " ";
            }
            else {
                s = s + this.BtoD(xy) + " ";
            }
            --x;
            ++i;
        }
        return s;
    }
    
    public String fifth(final String s, final String pass) {
        String fin = "";
        final String alpha = "XABCDEFGHI";
        for (int i = 0; i < s.length(); ++i) {
            final char ch = s.charAt(i);
            if (ch == ' ') {
                fin += ch;
            }
            else {
                fin += alpha.charAt(s.charAt(i) - '0');
            }
        }
        return fin.substring(0, fin.length() - 1);
    }
    
    public String encode(final String s, final String pass) {
        return this.fifth(this.fourth(this.third(this.second(this.first(s, pass), pass), pass), pass), pass);
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
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Encrypt().setVisible(true);
            }
        });
    }
}
