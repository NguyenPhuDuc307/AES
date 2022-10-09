/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import static Client.frmStudent.listStudents;
import Entity.Student;
import static StringHandling.StringHandling.removeAccent;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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

    private boolean ENABLE;
    private int action = 0;
    private int sort = 0;

    public frmClass() {
        initComponents();

        txtMa.setEnabled(false);

        tb_Class.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Mã lớp học", "Tên lớp học", "Trạng thái"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tb_Class.getTableHeader().setFont(new Font("#9Slide03 SFU Futura_03", Font.PLAIN, 16));
        TableColumnModel column = tb_Class.getColumnModel();
        column.getColumn(0).setPreferredWidth(10);
        column.getColumn(1).setPreferredWidth(250);

        EnableTxt(false);
        btnSua.setEnabled(false);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);
        btnRemove.setVisible(false);

        r1.setEnabled(false);
        r2.setEnabled(false);

        show_class();
    }

    public static List<Entity.Class> listClass = new ArrayList<>();

    private void EnableTxt(boolean b) {
        txtTen.setEnabled(b);
        txtMa.setEnabled(b);

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

            if (!ketqua.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) tb_Class.getModel();

                StringHandling.StringHandling.getList_Class(listClass, ketquaString);

                switch (sort) {
                    case 0 -> {
                        Collections.sort(listClass, (var a, var b) -> a.getClassIdString().compareTo(b.getClassIdString()));
                    }
                    case 1 -> {
                        Collections.sort(listClass, (var a, var b) -> a.getClassName().compareTo(b.getClassName()));
                    }
                    case 2 -> {
                        Collections.sort(listClass, (var a, var b) -> String.valueOf(a.isEnable()).compareTo(String.valueOf(b.isEnable())));
                    }
                }

                StringHandling.StringHandling.getDefaultTableModel_Class(model, listClass);

                for (int i = 0; i < 3; i++) {
                    StringHandling.StringHandling.changeTable(tb_Class, i, "");
                }

                lbCout.setText("Tổng số lớp học: " + listClass.size());
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
        jLabel8 = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        lbCout = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnLoad = new javax.swing.JButton();

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

        jLabel8.setFont(new java.awt.Font("#9Slide03 SFU Futura_07", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("QUẢN LÝ LỚP HỌC");

        btnRemove.setBackground(new java.awt.Color(255, 204, 102));
        btnRemove.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnRemove.setText("Xoá");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lbCout.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 16)); // NOI18N
        lbCout.setForeground(new java.awt.Color(255, 255, 255));
        lbCout.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCout.setText("Tổng số lớp học");

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton1.setText("Mã lớp học");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sắp xếp theo");

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton3.setText("Tên lớp học");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 204));
        jButton4.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton4.setText("Trạng thái");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tìm kiếm");

        txtSearch.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnLoad.setBackground(new java.awt.Color(255, 204, 102));
        btnLoad.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnLoad.setText("Tải lại");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

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
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnRemove, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbCout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnLoad, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLoad)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(r2))
                            .addComponent(lbTB)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTen)
                                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbCout, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
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
                    .addComponent(btnHuy)
                    .addComponent(btnRemove)
                    .addComponent(btnLoad))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel10)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(lbCout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r1)
                            .addComponent(r2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTB)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
        btnRemove.setEnabled(false);
        btnRemove.setVisible(true);

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
        btnRemove.setVisible(false);
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
        btnRemove.setEnabled(true);
        r1.setEnabled(true);
        r2.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(true);

        txtMa.setEnabled(false);

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
                btnRemove.setVisible(false);
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

                if (ENABLE == true) {
                    ENABLE = false;
                } else if (ENABLE == false) {
                    ENABLE = true;
                }

                if (!ClassId.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("updateClass", ClassId, ClassName, String.valueOf(ENABLE));

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

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if (!txtMa.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);

            if (input == 0) {
                String ClassId = txtMa.getText();

                if (!ClassId.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("deleteClass", ClassId);

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
                        btnRemove.setVisible(false);
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
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sort = 0;
        show_class();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        sort = 1;
        show_class();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        sort = 2;
        show_class();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        show_class();
        for (int i = 0; i < 3; i++) {
            StringHandling.StringHandling.changeTable(tb_Class, i, removeAccent(txtSearch.getText()));
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        txtSearch.setText("");
        show_class();
        for (int i = 0; i < 3; i++) {
            StringHandling.StringHandling.changeTable(tb_Class, i, "");
        }
    }//GEN-LAST:event_btnLoadActionPerformed

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
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCout;
    private javax.swing.JLabel lbTB;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JTable tb_Class;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
