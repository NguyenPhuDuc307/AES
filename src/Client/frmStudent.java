/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import Entity.Student;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenphuduc
 */
public final class frmStudent extends javax.swing.JFrame {

    /**
     * Creates new form frmClass
     */
    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;

    private int SEX;
    private boolean ENABLE;
    private int action = 0;
    private int sort = 0;

    public frmStudent() {
        initComponents();

        txtId.setEnabled(false);

        tb_Student.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Mã sinh viên", "Mã lớp học", "Họ tên", "Email", "Số điện thoại", "Giới tính", "Địa chỉ", "Trạng thái"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tb_Student.getTableHeader().setFont(new Font("#9Slide03 SFU Futura_03", Font.PLAIN, 16));

        EnableTxt(false);
        btnSua.setEnabled(false);
        btnXoa.setVisible(false);
        btnDelete.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);

        rNam.setEnabled(false);
        rNu.setEnabled(false);
        rKhac.setEnabled(false);

        show_class();
    }

    public static List<Student> listStudents = new ArrayList<>();

    private void EnableTxt(boolean b) {
        txtId.setEnabled(b);
        txtName.setEnabled(b);
        txtAddess.setEnabled(b);
        txtEmail.setEnabled(b);
        txtSDT.setEnabled(b);
        txtTrangthai.setEnabled(b);
        cbClass.setEnabled(b);

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
        String inputString = StringHandling.StringHandling.stringSoncatenation("getAllStudents", "null");

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

            String arrString[] = ketquaString.split("\n\n");

            String Students = String.valueOf(arrString[0]);
            String Classes = String.valueOf(arrString[1]);

            String classString[] = Classes.split("\n");

            cbClass.removeAllItems();

            for (String classString1 : classString) {
                cbClass.addItem(classString1);
            }

            if (!Students.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) tb_Student.getModel();

                StringHandling.StringHandling.getList_Student(listStudents, Students);

                switch (sort) {
                    case 0 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getStudentId().compareTo(b.getStudentId()));
                    }
                    case 1 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getClassId().compareTo(b.getClassId()));
                    }
                    case 2 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getFullName().compareTo(b.getFullName()));
                    }
                    case 3 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getEmail().replaceAll("@gmail.com", "").compareTo(b.getEmail().replaceAll("@gmail.com", "")));
                    }
                    case 4 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getPhoneNumber().substring(a.getPhoneNumber().length() - 1).compareTo(b.getPhoneNumber().substring(a.getPhoneNumber().length() - 1)));
                    }
                    case 5 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getSex() - b.getSex());
                    }
                    case 6 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> a.getAddress().compareTo(b.getAddress()));
                    }
                    case 7 -> {
                        Collections.sort(listStudents, (Student a, Student b) -> String.valueOf(a.isEnable()).compareTo(String.valueOf(b.isEnable())));
                    }
                }

                StringHandling.StringHandling.getDefaultTableModel_Student(model, listStudents);

                for (int i = 0; i < 8; i++) {
                    StringHandling.StringHandling.changeTable(tb_Student, i, "");
                }

                lbCout.setText("Tổng số sinh viên: " + listStudents.size());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Student = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rNam = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        rNu = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        rKhac = new javax.swing.JRadioButton();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAddess = new javax.swing.JTextField();
        txtTrangthai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbTB = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbClass = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lbCout = new javax.swing.JLabel();
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
        btnLoad = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý sinh viên");

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

        tb_Student.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        tb_Student.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_Student.setRowHeight(30);
        tb_Student.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_StudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Student);

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

        jLabel1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sinh viên");

        jLabel6.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Giới tính");

        txtId.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        rNu.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        rNu.setForeground(new java.awt.Color(255, 255, 255));
        rNu.setText("Nữ");
        rNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rNuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Họ tên");

        rKhac.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        rKhac.setForeground(new java.awt.Color(255, 255, 255));
        rKhac.setText("Khác");
        rKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rKhacActionPerformed(evt);
            }
        });

        txtName.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        txtSDT.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trạng thái");

        txtAddess.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        txtTrangthai.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");

        lbTB.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 2, 14)); // NOI18N
        lbTB.setForeground(new java.awt.Color(255, 0, 51));
        lbTB.setText("Đã khoá chỉnh sửa");

        jLabel8.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mã lớp học");

        cbClass.setEditable(true);
        cbClass.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("#9Slide03 SFU Futura_07", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 204, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("QUẢN LÝ SINH VIÊN");

        btnDelete.setBackground(new java.awt.Color(255, 204, 102));
        btnDelete.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lbCout.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 16)); // NOI18N
        lbCout.setForeground(new java.awt.Color(255, 255, 255));
        lbCout.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCout.setText("Tổng số sinh viên");

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jButton1.setText("Mã sinh viên");
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
        jButton2.setText("Mã lớp học");
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

        btnLoad.setBackground(new java.awt.Color(255, 204, 102));
        btnLoad.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        btnLoad.setText("Tải lại");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("#9Slide03 SFU Futura_03", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tìm kiếm");

        jDesktopPane1.setLayer(btnThem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXoa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnLuu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnHuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(rNam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtId, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(rNu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(rKhac, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtSDT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtAddess, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTrangthai, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbTB, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(cbClass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbCout, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
        jDesktopPane1.setLayer(btnLoad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(8, 8, 8)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT)
                            .addComponent(txtAddess)
                            .addComponent(txtName)
                            .addComponent(txtEmail)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(rNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rNu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rKhac)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                .addComponent(lbTB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbCout))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTrangthai))
                            .addComponent(txtSearch)))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoad)))
                .addContainerGap())
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(btnDelete)
                    .addComponent(btnLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCout)
                    .addComponent(lbTB)
                    .addComponent(jButton1)
                    .addComponent(jLabel10)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
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

    private void tb_StudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_StudentMouseClicked
        // TODO add your handling code here:
        btnSua.setEnabled(true);
        btnXoa.setEnabled(false);
        btnXoa.setVisible(true);
        txtTrangthai.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(true);

        // lấy dòng dl hiện tại mình đang nhấn chuột
        int row = this.tb_Student.getSelectedRow();

        //lấy StudentId vừa nhận và ssu đó đổi sang string
        txtId.setText(tb_Student.getValueAt(row, 0).toString());
        cbClass.setSelectedItem(tb_Student.getValueAt(row, 1).toString());
        txtName.setText(tb_Student.getValueAt(row, 2).toString());
        txtAddess.setText(tb_Student.getValueAt(row, 6).toString());
        txtSDT.setText(tb_Student.getValueAt(row, 4).toString());
        txtEmail.setText(tb_Student.getValueAt(row, 3).toString());
        txtTrangthai.setText(tb_Student.getValueAt(row, 7).toString());

        if (tb_Student.getValueAt(row, 7).toString().equals("Đang học")) {
            btnXoa.setText("Ngưng");
            ENABLE = true;
        } else {
            btnXoa.setText("Tiếp tục");
            ENABLE = false;
        }

        switch (tb_Student.getValueAt(row, 5).toString()) {
            case "Nam" -> {
                rNam.setSelected(true);
                rNu.setSelected(false);
                rKhac.setSelected(false);
                SEX = 1;
            }
            case "Nữ" -> {
                rNam.setSelected(false);
                rNu.setSelected(true);
                rKhac.setSelected(false);
                SEX = 2;
            }
            default -> {
                rNam.setSelected(false);
                rNu.setSelected(false);
                rKhac.setSelected(true);
                SEX = 0;
            }
        }
    }//GEN-LAST:event_tb_StudentMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        EnableTxt(true);
        btnLuu.setVisible(true);
        btnHuy.setVisible(true);
        rNam.setSelected(false);
        rNu.setSelected(false);
        rKhac.setSelected(false);
        rNam.setEnabled(true);
        rNu.setEnabled(true);
        rKhac.setEnabled(true);
        txtTrangthai.setEnabled(false);

        btnSua.setEnabled(false);

        txtId.setText("");
        cbClass.setSelectedItem("Chọn lớp học");
        txtAddess.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtTrangthai.setText("");
        txtName.setText("");

        action = 1;

        SEX = 0;
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        EnableTxt(false);
        btnThem.setEnabled(true);
        btnXoa.setVisible(false);
        btnDelete.setVisible(false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);
        btnSua.setEnabled(false);
        txtId.setText("");
        cbClass.setSelectedItem("");
        txtAddess.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtTrangthai.setText("");
        txtName.setText("");
        rNam.setSelected(false);
        rNu.setSelected(false);
        rKhac.setSelected(false);

        rNam.setEnabled(false);
        rNu.setEnabled(false);
        rKhac.setEnabled(false);

    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        EnableTxt(true);
        txtTrangthai.setEnabled(false);

        btnLuu.setVisible(true);
        btnThem.setEnabled(false);
        btnHuy.setVisible(true);
        btnDelete.setEnabled(true);
        rNam.setEnabled(true);
        rNu.setEnabled(true);
        rKhac.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(true);

        txtId.setEnabled(false);

        action = 2;
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        String StudentId = txtId.getText();
        String ClassId = cbClass.getSelectedItem().toString();
        String Fullname = txtName.getText();
        String Email = txtEmail.getText();
        String Phonenum = txtSDT.getText();
        int Sex = SEX;
        String Address = txtAddess.getText();
        boolean Enable = ENABLE;

        if (!StudentId.isEmpty()) {

            // nối thông tin gửi đi thành một chuỗi
            String inputString = "";
            if (action == 1) {
                inputString = StringHandling.StringHandling.stringSoncatenation("addStudent", StudentId, ClassId, Fullname, Email, Phonenum, String.valueOf(Sex), Address);
            }
            if (action == 2) {
                inputString = StringHandling.StringHandling.stringSoncatenation("updateStudent", StudentId, ClassId, Fullname, Email, Phonenum, String.valueOf(Sex), Address, String.valueOf(Enable));
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

                if (ketquaString.contains("thành công")) {
                    EnableTxt(false);
                    btnThem.setEnabled(true);
                    btnXoa.setVisible(false);
                    btnDelete.setVisible(false);
                    btnLuu.setVisible(false);
                    btnHuy.setVisible(false);
                    btnSua.setEnabled(false);
                    txtId.setText("");
                    cbClass.setSelectedItem("");
                    txtAddess.setText("");
                    txtEmail.setText("");
                    txtSDT.setText("");
                    txtTrangthai.setText("");
                    txtName.setText("");
                    rNam.setSelected(false);
                    rNu.setSelected(false);
                    rKhac.setSelected(false);

                    rNam.setEnabled(false);
                    rNu.setEnabled(false);
                    rKhac.setEnabled(false);
                }

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
        if (!txtId.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn?", "Thông báo",
                    JOptionPane.OK_CANCEL_OPTION);

            if (input == 0) {
                String StudentId = txtId.getText();
                String ClassId = cbClass.getSelectedItem().toString();
                String Fullname = txtName.getText();
                String Email = txtEmail.getText();
                String Phonenum = txtSDT.getText();
                int Sex = SEX;
                String Address = txtAddess.getText();
                boolean Enable = ENABLE;

                if (Enable == true) {
                    Enable = false;
                } else if (Enable == false) {
                    Enable = true;
                }

                if (!ClassId.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("updateStudent", StudentId, ClassId, Fullname, Email, Phonenum, String.valueOf(Sex), Address, String.valueOf(Enable));

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
                        btnSua.setEnabled(false);
                        txtId.setText("");
                        cbClass.setSelectedItem("");
                        txtAddess.setText("");
                        txtEmail.setText("");
                        txtSDT.setText("");
                        txtTrangthai.setText("");
                        txtName.setText("");
                        rNam.setSelected(false);
                        rNu.setSelected(false);
                        rKhac.setSelected(false);

                        rNam.setEnabled(false);
                        rNu.setEnabled(false);
                        rKhac.setEnabled(false);
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

    private void rNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNamActionPerformed
        // TODO add your handling code here:
        rNu.setSelected(false);
        rKhac.setSelected(false);
        if (rNam.isSelected()) {
            SEX = 1;
        } else
            SEX = 0;
    }//GEN-LAST:event_rNamActionPerformed

    private void rNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNuActionPerformed
        // TODO add your handling code here:
        rNam.setSelected(false);
        rKhac.setSelected(false);
        if (rNu.isSelected()) {
            SEX = 2;
        } else
            SEX = 0;
    }//GEN-LAST:event_rNuActionPerformed

    private void rKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rKhacActionPerformed
        // TODO add your handling code here:
        rNu.setSelected(false);
        rNam.setSelected(false);
        if (rNu.isSelected()) {
            SEX = 3;
        } else
            SEX = 0;
    }//GEN-LAST:event_rKhacActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTKeyTyped

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (!txtId.getText().isEmpty()) {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);

            if (input == 0) {
                String StudentId = txtId.getText();

                if (!StudentId.isEmpty()) {
                    // nối thông tin gửi đi thành một chuỗi
                    String inputString = StringHandling.StringHandling.stringSoncatenation("deleteStudent", StudentId);

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

                        show_class();
                        socket.close();

                        if (ketquaString.contains("thành công")) {
                            EnableTxt(false);
                            btnThem.setEnabled(true);
                            btnXoa.setVisible(false);
                            btnDelete.setVisible(false);
                            btnLuu.setVisible(false);
                            btnHuy.setVisible(false);
                            btnSua.setEnabled(false);
                            txtId.setText("");
                            cbClass.setSelectedItem("");
                            txtAddess.setText("");
                            txtEmail.setText("");
                            txtSDT.setText("");
                            txtTrangthai.setText("");
                            txtName.setText("");
                            rNam.setSelected(false);
                            rNu.setSelected(false);
                            rKhac.setSelected(false);

                            rNam.setEnabled(false);
                            rNu.setEnabled(false);
                            rKhac.setEnabled(false);
                        }
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
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        show_class();
        for (int i = 0; i < 8; i++) {
            StringHandling.StringHandling.changeTable(tb_Student, i, StringHandling.StringHandling.removeAccent(txtSearch.getText()));
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        txtId.setText("");
        cbClass.setSelectedItem("");
        txtAddess.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtTrangthai.setText("");
        txtName.setText("");
        txtSearch.setText("");
        show_class();
        for (int i = 0; i < 8; i++) {
            StringHandling.StringHandling.changeTable(tb_Student, i, "");
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sort = 0;
        show_class();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        sort = 1;
        show_class();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        sort = 2;
        show_class();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        sort = 3;
        show_class();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        sort = 4;
        show_class();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        sort = 5;
        show_class();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        sort = 6;
        show_class();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        sort = 7;
        show_class();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(frmStudent.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmStudent.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmStudent.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmStudent.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbClass;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCout;
    private javax.swing.JLabel lbTB;
    private javax.swing.JRadioButton rKhac;
    private javax.swing.JRadioButton rNam;
    private javax.swing.JRadioButton rNu;
    private javax.swing.JTable tb_Student;
    private javax.swing.JTextField txtAddess;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTrangthai;
    // End of variables declaration//GEN-END:variables
}
