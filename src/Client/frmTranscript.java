/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import Entity.Student;
import Entity.Transcript;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenphuduc
 */
public final class frmTranscript extends javax.swing.JFrame {

    /**
     * Creates new form frmClass
     */
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;

    private int action = 0;

    public frmTranscript() {
        initComponents();

        tb_Subject.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Mã sinh viên", "Tên sinh viên", "Mã lớp học", "Mã môn học", "Tên môn học", "Giảng viên nhập điểm", "Điểm cuối kỳ"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        
        tb_Student.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Mã sinh viên", "Mã lớp học", "Tên sinh viên"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tb_Subject.getTableHeader().setFont(new Font("#9Slide03 SFU Futura_03", Font.PLAIN, 16));
        tb_Student.getTableHeader().setFont(new Font("#9Slide03 SFU Futura_03", Font.PLAIN, 16));

        btnSua.setEnabled(false);
        btnDelete.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);

        show_transcript();
    }

    public static List<Transcript> listTranscripts = new ArrayList<>();
    
    public static List<Student> listStudents = new ArrayList<>();

    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }

    public void show_transcript() {

        // nối thông tin gửi đi thành một chuỗi
        String inputString = StringHandling.StringHandling.stringSoncatenation("getAllTranscripts", "null");

        // chuyển thông tin về dạng byte
        byte[] inputByte = inputString.getBytes(StandardCharsets.UTF_8);
        String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

        // kết quả trả về tại đây
        String ketqua = "";

        try {
            // Socket nhận tham tham số là địa chỉ IP và Host
            socket = new Socket("127.0.0.1", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            out.println(inputBase64);

            // lấy ra kết quả
            ketqua = String.valueOf(in.nextLine());

            // lấy chuỗi string từ chuỗi byte
            String ketquaString = new String(Base64.getDecoder().decode(ketqua), StandardCharsets.UTF_8);
            
            String arrketquaString[] = ketquaString.split("\n\n");

            if (!arrketquaString[0].isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) tb_Subject.getModel();
                StringHandling.StringHandling.getDefaultTableModel_Transcript(model, arrketquaString[0]);

                StringHandling.StringHandling.getList_Transcript(listTranscripts, arrketquaString[0]);
            }
            
            if (!arrketquaString[1].isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) tb_Student.getModel();
                

                StringHandling.StringHandling.getList_Student1(listStudents, arrketquaString[1]);
                StringHandling.StringHandling.getDefaultTableModel_Student(model, listStudents);
            }
            socket.close();
        } catch (IOException e) {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTinChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Student = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Subject = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý lớp học");

        jDesktopPane1.setForeground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(255, 204, 102));
        btnThem.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 102));
        btnSua.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(255, 204, 102));
        btnLuu.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 204, 102));
        btnHuy.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Số tín chỉ");

        txtTinChi.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtTinChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTinChiKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("#9Slide03 SFU Futura_07", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("QUẢN LÝ BẢNG ĐIỂM");

        btnDelete.setBackground(new java.awt.Color(255, 204, 102));
        btnDelete.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tb_Student.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        tb_Student.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_Student.setCellSelectionEnabled(true);
        tb_Student.setRowHeight(30);
        tb_Student.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_StudentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Student);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        tb_Subject.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        tb_Subject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_Subject.setRowHeight(30);
        tb_Subject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SubjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Subject);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jDesktopPane1.setLayer(btnThem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnLuu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnHuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTinChi, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(27, 27, 27)
                                .addComponent(txtTinChi, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(741, 741, 741))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnLuu)
                    .addComponent(btnHuy)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTinChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        btnThem.setEnabled(true);
        btnDelete.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);
        btnSua.setEnabled(false);
        txtTinChi.setText("");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        String Num = txtTinChi.getText();
        // nối thông tin gửi đi thành một chuỗi
        String inputString = "";
        if (action == 1) {
            inputString = StringHandling.StringHandling.stringSoncatenation("addSubject", "");
        }
        if (action == 2) {
            inputString = StringHandling.StringHandling.stringSoncatenation("updateSubject", "");
        }

        // chuyển thông tin về dạng byte
        byte[] inputByte = inputString.getBytes(StandardCharsets.UTF_8);
        String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

        // kết quả trả về tại đây
        String ketqua = "";

        try {
            // Socket nhận tham tham số là địa chỉ IP và Host
            socket = new Socket("127.0.0.1", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            out.println(inputBase64);

            // lấy ra kết quả
            ketqua = String.valueOf(in.nextLine());
            // lấy chuỗi string từ chuỗi byte
            String ketquaString = new String(Base64.getDecoder().decode(ketqua), StandardCharsets.UTF_8);
            // nếu đăng nhập thành công
            JOptionPane.showMessageDialog(null, ketquaString);

            show_transcript();
            socket.close();

            btnThem.setEnabled(true);
            btnDelete.setVisible(false);
            btnLuu.setVisible(false);
            btnHuy.setVisible(false);
            txtTinChi.setText("");
        } catch (IOException e) {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        btnLuu.setVisible(true);
        btnThem.setEnabled(false);
        btnHuy.setVisible(true);
        btnSua.setEnabled(false);
        btnDelete.setEnabled(true);

        action = 2;
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        btnLuu.setVisible(true);
        btnHuy.setVisible(true);
        btnSua.setEnabled(false);

        txtTinChi.setText("");
        action = 1;
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTinChiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTinChiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTinChiKeyTyped

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá?", "Thông báo",
                JOptionPane.OK_CANCEL_OPTION);

        if (input == 0) {
            // nối thông tin gửi đi thành một chuỗi
            String inputString = StringHandling.StringHandling.stringSoncatenation("deleteSubject", "");

            // chuyển thông tin về dạng byte
            byte[] inputByte = inputString.getBytes(StandardCharsets.UTF_8);
            String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

            // kết quả trả về tại đây
            String ketqua = "";

            try {
                // Socket nhận tham tham số là địa chỉ IP và Host
                socket = new Socket("127.0.0.1", 8888);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new Scanner(socket.getInputStream());
                out.println(inputBase64);

                // lấy ra kết quả
                ketqua = String.valueOf(in.nextLine());
                // lấy chuỗi string từ chuỗi byte
                String ketquaString = new String(Base64.getDecoder().decode(ketqua), StandardCharsets.UTF_8);
                // nếu đăng nhập thành công
                JOptionPane.showMessageDialog(null, ketquaString);

                show_transcript();
                socket.close();

                btnThem.setEnabled(true);
                btnDelete.setVisible(false);
                btnLuu.setVisible(false);
                btnHuy.setVisible(false);
                txtTinChi.setText("");
            } catch (IOException e) {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tb_SubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SubjectMouseClicked
        // TODO add your handling code here:
        btnSua.setEnabled(true);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(true);

        // lấy dòng dl hiện tại mình đang nhấn chuột
        int row = this.tb_Subject.getSelectedRow();
        //lấy EmployeeID vừa nhận và ssu đó đổi sang string
        String StudentId = (this.tb_Subject.getModel()).getValueAt(row, 0).toString();
        String SubjectId = (this.tb_Subject.getModel()).getValueAt(row, 3).toString();
        String EmployeeId = (this.tb_Subject.getModel()).getValueAt(row, 5).toString();

        txtTinChi.setText(tb_Subject.getValueAt(row, 3).toString());

        Transcript transcript = StringHandling.StringHandling.getTranscript(listTranscripts, StudentId, SubjectId, EmployeeId);
    }//GEN-LAST:event_tb_SubjectMouseClicked

    private void tb_StudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_StudentMouseClicked
        // TODO add your handling code here:

        // lấy dòng dl hiện tại mình đang nhấn chuột
        int row = this.tb_Student.getSelectedRow();

    }//GEN-LAST:event_tb_StudentMouseClicked

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
            java.util.logging.Logger.getLogger(frmTranscript.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTranscript.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTranscript.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTranscript.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTranscript().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb_Student;
    private javax.swing.JTable tb_Subject;
    private javax.swing.JTextField txtTinChi;
    // End of variables declaration//GEN-END:variables
}
