/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nguyenphuduc
 */
public final class frmClass extends javax.swing.JFrame {

    /**
     * Creates new form frmClass
     */
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;

    private String CLASSID;
    private boolean ENABLE;
    private int action = 0;

    public frmClass() {
        initComponents();

        txtMa.setEnabled(false);

        tb_Class.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Mã lớp học", "Tên lớp học", "Trạng thái"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tb_Class.getTableHeader().setFont(new Font("#9Slide03 SFU Futura_03", Font.PLAIN, 18));
        TableColumnModel column = tb_Class.getColumnModel();
        column.getColumn(0).setPreferredWidth(10);
        column.getColumn(1).setPreferredWidth(250);

        EnableTxt(false);
        btnSua.setEnabled(false);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);

        r1.setEnabled(false);
        r2.setEnabled(false);

        show_class();
    }

    public static List<Entity.Class> listClass = new ArrayList<>();

    private void EnableTxt(boolean b) {
        txtTen.setEnabled(b);
        txtMa.setEnabled(b);

//        r1.setEnabled(b);
//        r2.setEnabled(b);
        if (!b) {
            lbTB.setText("Đã khoá chỉnh sửa");
            lbTB.setForeground(Color.RED);
        } else {
            lbTB.setText("Cho phép chỉnh sửa");
            lbTB.setForeground(Color.GREEN);
        }
    }

    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }

    public void show_class() {

        // nối thông tin gửi đi thành một chuỗi
        String inputString = StringHandling.StringHandling.stringSoncatenation("getAllClass", "null");

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

            DefaultTableModel model = (DefaultTableModel) tb_Class.getModel();
            StringHandling.StringHandling.getDefaultTableModel_Class(model, ketquaString);

            StringHandling.StringHandling.getList_Class(listClass, ketquaString);

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
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Class = new javax.swing.JTable();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        lbTB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        btnXoa.setBackground(new java.awt.Color(255, 204, 102));
        btnXoa.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnXoa.setText("Ngưng");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã lớp");

        txtMa.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        txtTen.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên lớp");

        tb_Class.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        tb_Class.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lớp học", "Tên lớp học", "Trạng thái"
            }
        ));
        tb_Class.setRowHeight(30);
        tb_Class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ClassMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Class);

        r1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        r1.setForeground(new java.awt.Color(255, 255, 255));
        r1.setText("Đang học");
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        r2.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        r2.setForeground(new java.awt.Color(255, 255, 255));
        r2.setText("Đã ngưng");
        r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Trạng thái");

        lbTB.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 2, 14)); // NOI18N
        lbTB.setForeground(new java.awt.Color(255, 51, 0));
        lbTB.setText("Đã khoá chỉnh sửa");

        jDesktopPane1.setLayer(btnThem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXoa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnLuu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnHuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtMa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(r1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(r2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbTB, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtTen))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHuy))))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTB)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(r1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLuu)
                    .addComponent(btnHuy))
                .addGap(11, 11, 11)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r1)
                    .addComponent(r2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(lbTB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tb_ClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClassMouseClicked
        // TODO add your handling code here:
        btnSua.setEnabled(true);
        btnXoa.setEnabled(false);
        btnXoa.setVisible(true);

        // lấy dòng dl hiện tại mình đang nhấn chuột
        int row = this.tb_Class.getSelectedRow();
        //lấy EmployeeID vừa nhận và ssu đó đổi sang string
        String ClassId = (this.tb_Class.getModel()).getValueAt(row, 0).toString();

        txtMa.setText(tb_Class.getValueAt(row, 0).toString());
        txtTen.setText(tb_Class.getValueAt(row, 1).toString());

        if (tb_Class.getValueAt(row, 2).toString().equals("Đang học")) {
            btnXoa.setText("Ngưng");
        } else {
            btnXoa.setText("Tiếp tục");
        }

        Entity.Class cl = StringHandling.StringHandling.getClass(listClass, ClassId);

        CLASSID = cl.getClassIdString();
        ENABLE = cl.isEnable();

        switch (tb_Class.getValueAt(row, 2).toString()) {
            case "Đang học" -> {
                r1.setSelected(true);
                r2.setSelected(false);
            }
            default -> {
                r1.setSelected(false);
                r2.setSelected(true);
            }
        }
    }//GEN-LAST:event_tb_ClassMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        EnableTxt(true);
        btnLuu.setVisible(true);
        btnHuy.setVisible(true);
        r1.setSelected(false);
        r2.setSelected(false);
        btnSua.setEnabled(false);

        txtMa.setText("");
        txtTen.setText("");

        action = 1;

        ENABLE = true;
    }//GEN-LAST:event_btnThemActionPerformed

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        // TODO add your handling code here:
        r1.setSelected(true);
        r2.setSelected(false);
        if (r1.isSelected()) {
            ENABLE = true;
        } else
            ENABLE = true;
    }//GEN-LAST:event_r1ActionPerformed

    private void r2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2ActionPerformed
        // TODO add your handling code here:
        r1.setSelected(false);
        r2.setSelected(true);
        if (r2.isSelected()) {
            ENABLE = false;
        } else
            ENABLE = true;
    }//GEN-LAST:event_r2ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        EnableTxt(false);
        btnThem.setEnabled(true);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);
        btnSua.setEnabled(false);
        txtMa.setText("");
        txtTen.setText("");
        r1.setSelected(false);
        r2.setSelected(false);
        r1.setEnabled(false);
        r2.setEnabled(false);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        EnableTxt(true);
        btnLuu.setVisible(true);
        btnThem.setEnabled(false);
        btnHuy.setVisible(true);
        r1.setEnabled(true);
        r2.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(true);

        txtMa.setEnabled(false);

        ENABLE = true;
        action = 2;
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        String ClassId = txtMa.getText();
        String ClassName = txtTen.getText();

        if (!ClassId.isEmpty() && !ClassName.isEmpty()) {

            // nối thông tin gửi đi thành một chuỗi
            String inputString = "";
            if (action == 1) {
                inputString = StringHandling.StringHandling.stringSoncatenation("addClass", ClassId, ClassName);
            }
            if (action == 2) {
                inputString = StringHandling.StringHandling.stringSoncatenation("updateClass", ClassId, ClassName, String.valueOf(ENABLE));
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

                show_class();
                socket.close();

                EnableTxt(false);
                btnThem.setEnabled(true);
                btnXoa.setVisible(false);
                btnLuu.setVisible(false);
                btnHuy.setVisible(false);
                txtMa.setText("");
                txtTen.setText("");
                r1.setSelected(false);
                r2.setSelected(false);
                r1.setEnabled(false);
                r2.setEnabled(false);
            } catch (IOException e) {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (!txtMa.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn?", "Thông báo",
                    JOptionPane.OK_CANCEL_OPTION);

            if (input == 0) {
                String ClassId = txtMa.getText();
                String ClassName = txtTen.getText();
                boolean trangThai = ENABLE;

                if (trangThai == true) {
                    trangThai = false;
                } else {
                    trangThai = true;
                }

                if (!ClassId.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("updateClass", ClassId, ClassName, String.valueOf(trangThai));

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

                        show_class();
                        socket.close();

                        EnableTxt(false);
                        btnThem.setEnabled(true);
                        btnXoa.setVisible(false);
                        btnLuu.setVisible(false);
                        btnHuy.setVisible(false);
                        txtMa.setText("");
                        txtTen.setText("");
                        r1.setSelected(false);
                        r2.setSelected(false);
                        r1.setEnabled(false);
                        r2.setEnabled(false);
                    } catch (IOException e) {
                        try {
                            if (socket != null) {
                                socket.close();
                            }
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
            java.util.logging.Logger.getLogger(frmClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTB;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JTable tb_Class;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}