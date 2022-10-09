/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import Entity.Employee;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
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
public final class frmEmployee extends javax.swing.JFrame {

    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;

    private static int Sex = 0;
    private static int UserId = 0;
    private static int ROLE = 0;
    
    private int sort = 1;

    public static List<Employee> listEmployees = new ArrayList<>();

    private void EnableTxt(boolean b) {
        txtAddess.setEnabled(b);
        txtEmail.setEnabled(b);
        txtName.setEnabled(b);
        txtSDT.setEnabled(b);
        rNam.setEnabled(b);
        rNu.setEnabled(b);
        rKhac.setEnabled(b);
        if (!b) {
            lbTB.setText("Đã khoá chỉnh sửa");
            lbTB.setForeground(Color.RED);
        } else {
            lbTB.setText("Cho phép chỉnh sửa");
            lbTB.setForeground(Color.GREEN);
        }
    }

    public frmEmployee() {
        initComponents();

        EnableTxt(false);
        btnEdit.setEnabled(false);
        btnDelete.setVisible(false);
        btnSave.setVisible(false);
        btnHuy.setVisible(false);
        txtId.setEnabled(false);
        btnRemove.setVisible(false);
        txtTrangthai.setEnabled(false);

        tb_Employee.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Mã NV", "Họ tên", "Email", "Số điện thoại", "Giới tính", "Địa chỉ", "Trạng thái", "Quyền truy cập"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tb_Employee.getTableHeader().setFont(new Font("#9Slide03 SFU Futura_03", Font.PLAIN, 16));
        TableColumnModel column = tb_Employee.getColumnModel();
        column.getColumn(0).setPreferredWidth(10);
        column.getColumn(1).setPreferredWidth(50);
        column.getColumn(2).setPreferredWidth(150);
        column.getColumn(3).setPreferredWidth(40);
        column.getColumn(4).setPreferredWidth(30);

        show_employee();
    }

    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }

    public void show_employee() {

        // nối thông tin gửi đi thành một chuỗi
        String inputString = StringHandling.StringHandling.stringSoncatenation("getAllEmployee", "null");

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

            if (!ketquaString.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) tb_Employee.getModel();
                
                StringHandling.StringHandling.getList_Employee(listEmployees, ketquaString);
                
                switch (sort) {
                    case 0 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getEmployeeId().compareTo(b.getEmployeeId()));
                    }
                    case 1 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getRole() - b.getRole());
                    }
                    case 2 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getFullName().compareTo(b.getFullName()));
                    }
                    case 3 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getEmail().replaceAll("@gmail.com", "").compareTo(b.getEmail().replaceAll("@gmail.com", "")));
                    }
                    case 4 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getPhoneNumber().substring(a.getPhoneNumber().length() - 1).compareTo(b.getPhoneNumber().substring(a.getPhoneNumber().length() - 1)));
                    }
                    case 5 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getSex() - b.getSex());
                    }
                    case 6 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> a.getAddress().compareTo(b.getAddress()));
                    }
                    case 7 -> {
                        Collections.sort(listEmployees, (Employee a, Employee b) -> String.valueOf(a.isEnable()).compareTo(String.valueOf(b.isEnable())));
                    }
                }
                StringHandling.StringHandling.getDefaultTableModel_Employee(model, listEmployees);
                for (int i = 0; i < 8; i++) {
                    StringHandling.StringHandling.changeTable(tb_Employee, i, "");
                }
                
                lbCout.setText("Tổng số nhân viên: " + listEmployees.size());
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
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Employee = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtAddess = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rNam = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        rNu = new javax.swing.JRadioButton();
        rKhac = new javax.swing.JRadioButton();
        btnReload = new javax.swing.JButton();
        lbTB = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTrangthai = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        lbCout = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý nhân viên phòng công tác");

        btnEdit.setBackground(new java.awt.Color(255, 204, 102));
        btnEdit.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 204, 102));
        btnDelete.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnDelete.setText("Đình chỉ");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(255, 204, 102));
        btnSave.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jScrollPane1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        tb_Employee.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        tb_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_Employee.setAlignmentY(0.9F);
        tb_Employee.setRowHeight(30);
        tb_Employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_EmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Employee);

        jLabel1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã nhân viên (đã mã hoá)");

        txtId.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Họ tên");

        txtName.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        txtSDT.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });

        txtAddess.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Số điện thoại");

        jLabel5.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Địa chỉ");

        rNam.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        rNam.setForeground(new java.awt.Color(255, 255, 255));
        rNam.setText("Nam");
        rNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rNamActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Giới tính");

        rNu.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        rNu.setForeground(new java.awt.Color(255, 255, 255));
        rNu.setText("Nữ");
        rNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rNuActionPerformed(evt);
            }
        });

        rKhac.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        rKhac.setForeground(new java.awt.Color(255, 255, 255));
        rKhac.setText("Khác");
        rKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rKhacActionPerformed(evt);
            }
        });

        btnReload.setBackground(new java.awt.Color(255, 204, 102));
        btnReload.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnReload.setText("Tải lại");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        lbTB.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 2, 14)); // NOI18N
        lbTB.setForeground(new java.awt.Color(255, 0, 51));
        lbTB.setText("Đã khoá chỉnh sửa");

        jLabel7.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trạng thái");

        txtTrangthai.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        btnHuy.setBackground(new java.awt.Color(255, 204, 102));
        btnHuy.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("#9Slide03 SFU Futura_07", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("QUẢN LÝ NHÂN VIÊN PHÒNG CÔNG TÁC");

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
        lbCout.setText("Tổng số nhân viên");

        jLabel11.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tìm kiếm");

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton1.setText("Mã nhân viên");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sắp xếp theo");

        jButton2.setBackground(new java.awt.Color(255, 204, 255));
        jButton2.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton2.setText("Quyền truy cập");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton3.setText("Họ tên");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 204));
        jButton4.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton4.setText("Email");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 255, 204));
        jButton5.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton5.setText("Số điện thoại");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(204, 255, 255));
        jButton6.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton6.setText("Giới tính");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(153, 255, 204));
        jButton7.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton7.setText("Địa chỉ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(158, 230, 255));
        jButton8.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton8.setText("Trạng thái");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jDesktopPane1.setLayer(btnEdit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtId, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtSDT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtAddess, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(rNam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(rNu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(rKhac, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnReload, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbTB, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTrangthai, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnHuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnRemove, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbCout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT)
                            .addComponent(txtAddess)
                            .addComponent(txtName)
                            .addComponent(txtEmail)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(txtId)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTrangthai))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(rNam, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rNu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rKhac)))
                        .addGap(246, 246, 246))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(95, 95, 95)
                        .addComponent(lbTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCout, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHuy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReload))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32)
                                .addComponent(txtSearch)))))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnSave)
                    .addComponent(btnHuy)
                    .addComponent(btnRemove)
                    .addComponent(btnReload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rNam)
                    .addComponent(rNu)
                    .addComponent(rKhac))
                .addGap(11, 11, 11)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(5, 5, 5)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel10)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(lbTB)
                    .addComponent(lbCout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
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

    private void rNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNamActionPerformed
        // TODO add your handling code here:
        rNu.setSelected(false);
        rKhac.setSelected(false);
        if (rNam.isSelected()) {
            Sex = 1;
        } else
            Sex = 0;
    }//GEN-LAST:event_rNamActionPerformed

    private void rNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNuActionPerformed
        // TODO add your handling code here:
        rNam.setSelected(false);
        rKhac.setSelected(false);
        if (rNu.isSelected()) {
            Sex = 2;
        } else
            Sex = 0;
    }//GEN-LAST:event_rNuActionPerformed

    private void rKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rKhacActionPerformed
        // TODO add your handling code here:
        rNu.setSelected(false);
        rNam.setSelected(false);
        if (rNu.isSelected()) {
            Sex = 3;
        } else
            Sex = 0;
    }//GEN-LAST:event_rKhacActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        show_employee();
        txtSDT.setText("");
        txtAddess.setText("");
        txtEmail.setText("");
        txtId.setText("");
        txtName.setText("");
        rNam.setSelected(false);
        rNu.setSelected(false);
        rKhac.setSelected(false);
        txtTrangthai.setText("");
    }//GEN-LAST:event_btnReloadActionPerformed

    private void tb_EmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EmployeeMouseClicked
        // TODO add your handling code here:
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(true);
        btnRemove.setEnabled(false);
        btnRemove.setVisible(true);

        // lấy dòng dl hiện tại mình đang nhấn chuột
        int row = this.tb_Employee.getSelectedRow();
        //lấy EmployeeID vừa nhận và ssu đó đổi sang string
        String EmployeeID = (this.tb_Employee.getModel()).getValueAt(row, 0).toString();

        txtId.setText(tb_Employee.getValueAt(row, 0).toString());
        txtName.setText(tb_Employee.getValueAt(row, 1).toString());
        txtAddess.setText(tb_Employee.getValueAt(row, 5).toString());
        txtSDT.setText(tb_Employee.getValueAt(row, 3).toString());
        txtEmail.setText(tb_Employee.getValueAt(row, 2).toString());
        txtTrangthai.setText(tb_Employee.getValueAt(row, 6).toString());

        if (tb_Employee.getValueAt(row, 6).toString().equals("Đang làm")) {
            btnDelete.setText("Đình chỉ");
        } else {
            btnDelete.setText("Hoàn tác đình chỉ");
        }

        Employee employee = StringHandling.StringHandling.getEmployee(listEmployees, EmployeeID);

        UserId = employee.getUserId();
        Sex = employee.getSex();
        if ((tb_Employee.getModel()).getValueAt(row, 7).toString().equals("Phòng công tác")) {
            ROLE = 2;
        } else {
            ROLE = 1;
        }

        switch (tb_Employee.getValueAt(row, 4).toString()) {
            case "Nam" -> {
                rNam.setSelected(true);
                rNu.setSelected(false);
                rKhac.setSelected(false);
            }
            case "Nữ" -> {
                rNam.setSelected(false);
                rNu.setSelected(true);
                rKhac.setSelected(false);
            }
            default -> {
                rNam.setSelected(false);
                rNu.setSelected(false);
                rKhac.setSelected(true);
            }
        }
    }//GEN-LAST:event_tb_EmployeeMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        EnableTxt(true);
        btnDelete.setEnabled(true);
        btnRemove.setEnabled(true);
        btnSave.setVisible(true);
        btnHuy.setVisible(true);
        btnEdit.setEnabled(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String name = txtName.getText();
        String email = txtEmail.getText();
        String sdt = txtSDT.getText();
        String diachi = txtAddess.getText();
        String id = txtId.getText();
        String trangThai;
        if (txtTrangthai.getText().equals("Đang làm")) {
            trangThai = "true";
        } else {
            trangThai = "false";
        }

        if (!id.isEmpty()) {
            // nối thông tin gửi đi thành một chuỗi
            String inputString = StringHandling.StringHandling.stringSoncatenation("updateEmployee", id, String.valueOf(UserId), name, email, sdt, String.valueOf(Sex), diachi, trangThai, String.valueOf(ROLE));

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

                socket.close();
                show_employee();

                JOptionPane.showMessageDialog(null, ketquaString);

                EnableTxt(false);
                btnDelete.setVisible(false);
                btnRemove.setVisible(false);
                btnSave.setVisible(false);
                btnHuy.setVisible(false);
                txtSDT.setText("");
                txtAddess.setText("");
                txtEmail.setText("");
                txtId.setText("");
                txtName.setText("");
                rNam.setSelected(false);
                rNu.setSelected(false);
                rKhac.setSelected(false);

            } catch (IOException e) {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (!txtId.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);

            if (input == 0) {
                String name = txtName.getText();
                String email = txtEmail.getText();
                String sdt = txtSDT.getText();
                String diachi = txtAddess.getText();
                String id = txtId.getText();
                String trangThai;
                if (txtTrangthai.getText().equals("Nghỉ việc")) {
                    trangThai = "true";
                } else {
                    trangThai = "false";
                }

                if (!id.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("updateEmployee", id, String.valueOf(UserId), name, email, sdt, String.valueOf(Sex), diachi, trangThai, String.valueOf(ROLE));

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

                        show_employee();

                        JOptionPane.showMessageDialog(null, ketquaString);

                        EnableTxt(false);
                        btnDelete.setVisible(false);
                        btnSave.setVisible(false);
                        btnHuy.setVisible(false);
                        txtSDT.setText("");
                        txtAddess.setText("");
                        txtEmail.setText("");
                        txtId.setText("");
                        txtName.setText("");
                        rNam.setSelected(false);
                        rNu.setSelected(false);
                        rKhac.setSelected(false);

                    } catch (IOException ex) {
                        Logger.getLogger(frmEmployee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        EnableTxt(false);
        btnDelete.setVisible(false);
        btnRemove.setVisible(false);
        btnSave.setVisible(false);
        btnHuy.setVisible(false);
        txtSDT.setText("");
        txtAddess.setText("");
        txtEmail.setText("");
        txtId.setText("");
        txtName.setText("");
        rNam.setSelected(false);
        rNu.setSelected(false);
        rKhac.setSelected(false);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTKeyTyped

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if (!txtId.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);

            if (input == 0) {
                String EmployeeId = txtId.getText();

                if (!EmployeeId.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("deleteEmployee", EmployeeId);

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
                        
                        show_employee();

                        JOptionPane.showMessageDialog(null, ketquaString);

                        EnableTxt(false);
                        btnDelete.setVisible(false);
                        btnRemove.setVisible(false);
                        btnSave.setVisible(false);
                        btnHuy.setVisible(false);
                        txtSDT.setText("");
                        txtAddess.setText("");
                        txtEmail.setText("");
                        txtId.setText("");
                        txtName.setText("");
                        rNam.setSelected(false);
                        rNu.setSelected(false);
                        rKhac.setSelected(false);
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
        show_employee();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        sort = 1;
        show_employee();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        sort = 2;
        show_employee();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        sort = 3;
        show_employee();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        sort = 4;
        show_employee();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        sort = 5;
        show_employee();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        sort = 6;
        show_employee();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        sort = 7;
        show_employee();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        show_employee();
        for (int i = 0; i < 8; i++) {
            StringHandling.StringHandling.changeTable(tb_Employee, i, StringHandling.StringHandling.removeAccent(txtSearch.getText()));
        }
    }//GEN-LAST:event_txtSearchKeyReleased

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
            java.util.logging.Logger.getLogger(frmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmEmployee().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCout;
    private javax.swing.JLabel lbTB;
    private javax.swing.JRadioButton rKhac;
    private javax.swing.JRadioButton rNam;
    private javax.swing.JRadioButton rNu;
    private javax.swing.JTable tb_Employee;
    private javax.swing.JTextField txtAddess;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTrangthai;
    // End of variables declaration//GEN-END:variables
}
