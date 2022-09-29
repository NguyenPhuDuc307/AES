package Client;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import Data.DBAccess;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyenphuduc
 */
public class frmDecrypt extends javax.swing.JFrame {

    private String USERNAME;
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;

    /**
     * Creates new form frmClient
     */
    public frmDecrypt() {
        initComponents();
        txtString.requestFocus();
        txtString.setLineWrap(true);
        txtString.setWrapStyleWord(true);
        txtKetqua.setLineWrap(true);
        txtKetqua.setWrapStyleWord(true);
        this.USERNAME = frmLogin.USERNAME;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtString = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKetqua = new javax.swing.JTextArea();
        btnCopy = new javax.swing.JButton();
        btnPatse = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdem = new javax.swing.JTextArea();
        lb = new javax.swing.JLabel();
        btndatlai = new javax.swing.JButton();
        txtKey = new javax.swing.JPasswordField();
        checkKey = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel1.setText("Nhập văn bản mã hoá");

        jLabel2.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel2.setText("Kết quả văn bản được giải mã");

        btnSubmit.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnSubmit.setText("Giải mã");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Giải mã văn bản với thuật toán AES");

        txtString.setColumns(20);
        txtString.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtString.setRows(4);
        jScrollPane1.setViewportView(txtString);

        jLabel4.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel4.setText("Nhập chuỗi khoá");

        txtKetqua.setEditable(false);
        txtKetqua.setColumns(20);
        txtKetqua.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtKetqua.setRows(5);
        jScrollPane2.setViewportView(txtKetqua);

        btnCopy.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnCopy.setText("Sao chép");
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        btnPatse.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnPatse.setText("Dán");
        btnPatse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatseActionPerformed(evt);
            }
        });

        txtdem.setEditable(false);
        txtdem.setColumns(20);
        txtdem.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtdem.setRows(5);
        jScrollPane3.setViewportView(txtdem);

        lb.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        lb.setText("Đếm số lần xuất hiện ký tự");

        btndatlai.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btndatlai.setText("Đặt lại");
        btndatlai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlaiActionPerformed(evt);
            }
        });

        txtKey.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        checkKey.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        checkKey.setText("Hiện chuỗi khoá");
        checkKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkKeyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKey)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPatse)
                        .addGap(12, 12, 12)
                        .addComponent(btndatlai))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnSubmit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCopy))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lb)
                                .addGap(181, 181, 181))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(checkKey, javax.swing.GroupLayout.Alignment.TRAILING))))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnPatse)
                            .addComponent(btndatlai)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(checkKey))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnExit)
                    .addComponent(btnCopy))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        String IdUser = String.valueOf(DBAccess.getIdUserByUserName(USERNAME));
        String string = txtString.getText();
        String key = String.valueOf(txtKey.getPassword());

        // nối thông tinn gửi đi thành một chuỗi
        String inputString = StringHandling.StringHandling.stringSoncatenation(IdUser, "decrypt", key, string);

        // chuyển thông tin về dạng byte
        byte[] inputByte = inputString.getBytes(StandardCharsets.UTF_8);
        String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

        // kết quả được trả về tại đây
        String ketqua = "";
        try {
            // Socket nhận tham tham số là địa chỉ IP và Host
            socket = new Socket("127.0.0.1", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            out.println(inputBase64);

            // lấy ra kết quả từ server rồi giải mã về string
            ketqua = in.nextLine().trim();
            String outputString = new String(Base64.getDecoder().decode(ketqua), StandardCharsets.UTF_8);

            // kết quả được giải mã
            String decryptString = "";

            // giải mã thành công
            if (!outputString.equals("null")) {
                // ngắt chuỗi
                String[] arr = outputString.split("\n\n\n");

                // chuỗi đếm ký tự
                String coutChar = String.valueOf(arr[0].toCharArray());

                // chuỗi kết quả
                decryptString = String.valueOf(arr[1].toCharArray());
                
                txtKetqua.setText(decryptString);
                txtdem.setText(coutChar);
            }
            else{
                JOptionPane.showMessageDialog(null, "Chuỗi khoá không đúng!");
            }
            socket.close();
        } catch (Exception e) {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        // TODO add your handling code here:
        String myString = txtKetqua.getText();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(null, "Đã sao chép vào bộ nhớ đệm!");
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnPatseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPatseActionPerformed
        // TODO add your handling code here:
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(this);
        if (t == null) {
            return;
        }
        try {
            txtString.setText((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPatseActionPerformed

    private void btndatlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlaiActionPerformed
        // TODO add your handling code here:
        txtKetqua.setText("");
        txtKey.setText("");
        txtString.setText("");
        txtdem.setText("");
    }//GEN-LAST:event_btndatlaiActionPerformed

    private void checkKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkKeyActionPerformed
        // TODO add your handling code here:
        if (checkKey.isSelected()) {
            txtKey.setEchoChar((char) 0);
        } else {
            txtKey.setEchoChar('*');
        }
    }//GEN-LAST:event_checkKeyActionPerformed

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
            java.util.logging.Logger.getLogger(frmDecrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDecrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDecrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDecrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDecrypt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPatse;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btndatlai;
    private javax.swing.JCheckBox checkKey;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb;
    private javax.swing.JTextArea txtKetqua;
    private javax.swing.JPasswordField txtKey;
    private javax.swing.JTextArea txtString;
    private javax.swing.JTextArea txtdem;
    // End of variables declaration//GEN-END:variables
}
