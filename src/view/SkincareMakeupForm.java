/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import config.Koneksi;
import model.MakeCare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SkincareMakeupForm extends javax.swing.JFrame {

    private int idTerpilih = 0;

    public SkincareMakeupForm() {
        initComponents(); // dari NetBeans (JANGAN DIUBAH)
        setLocationRelativeTo(null);
        tampilData();
    }

    // ================== TAMPIL DATA ==================
    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Skincare");
        model.addColumn("Brand");
        model.addColumn("Harga");
        model.addColumn("Kadaluarsa");

        try {
            String sql = "SELECT * FROM skincare";
            Connection conn = Koneksi.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("jenis_skincare"),
                    rs.getString("nama_brand"),
                    rs.getBigDecimal("harga_skincare"),
                    rs.getDate("tanggal_kadaluarsa")
                });
            }

            model.addRow(new Object[]{
                makecare.getId(),
                makecare.getJenisSkincare(),
                makecare.getNamaBrand(),
                makecare.getHarga(),
                makecare.getTanggalKadaluarsa()
            });
            
            tb1DataMS.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil: " + e.getMessage());
        }
    }

    // ================== CLEAR ==================
    private void bersihkanForm() {
        txtJenisSkincare.setText("");
        txtNamaBrand.setText("");
        txtHarga.setText("");
        txtTanggalKadaluarsa.setText("");
        idTerpilih = 0;
        
        txtJenisSkincare.requestFocus();
    }

    // ================== SIMPAN ==================
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtJenisSkincare.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Judul tidak boleh kosong");
        txtJenisSkincare.requestFocus();
        return;
    }

        try {
            String sql = "INSERT INTO skincare (nama_brand, jenis_skincare, harga_skincare, tanggal_kadaluarsa) VALUES (?, ?, ?, ?)";
            Connection conn = Koneksi.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNamaBrand.getText());
            pst.setString(2, txtJenisSkincare.getText());
            pst.setDouble(3, Double.parseDouble(txtHarga.getText()));
            pst.setString(4, txtTanggalKadaluarsa.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            tampilData();
            bersihkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ================== UBAH ==================
    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {
        if (idTerpilih == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }

        try {
            
            MakeCare makecare = new MakeCare(
            txtJenisSkincare.getText(),
            txtNamaBrand.getText(),
            txtHarga.getText(),
            txtTanggalKadaluarsa.getText());
            
            String sql = "UPDATE skincare SET nama_brand=?, jenis_skincare=?, harga_skincare=?, tanggal_kadaluarsa=? WHERE id=?";
            Connection conn = Koneksi.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, makecare.getJenisSkincare());
            pst.setString(2, makecare.getNamaBrand());
            pst.setDouble(3, Double.parseDouble(txtHarga.getText()));
            pst.setString(4, makecare.getTanggalKadaluarsa);
            pst.setInt(5, idTerpilih);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            tampilData();
            bersihkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ================== KLIK TABEL ==================
    private void tblDataMSMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tblDataMS.getSelectedRow();

        idTerpilih = Integer.parseInt(tblDataMS.getValueAt(baris, 0).toString());

        MakeCare makecare = new MakeCare (
        txtJenisSkincare.setText(tblDataMS.getValueAt(baris, 1).toString());
        txtnamaBrand.setText(tblDataMS.getValueAt(baris, 2).toString());
        txtHarga.setText(tblDataMS.getValueAt(baris, 3).toString());
        txtTanggalKadaluarsa.setText(tblDataMS.getValueAt(baris, 4).toString());
        )
        
    }

    //===============TOMBOL UBAH=================
        private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah terlebih dahulu");
            return;
        }

        if (txtJudul.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul tidak boleh kosong");
            txtJudul.requestFocus();
            return;
        }

        try {
            MakeCare makecare = new MakeCare(
                    Integer.parseInt(txtId.getText()),
                    txtJudul.getText(),
                    txtKeterangan.getText(),
                    cmbStatus.getSelectedItem().toString()
            );

            String sql = "UPDATE todos SET judul = ?, keterangan = ?, status = ? WHERE id = ?";
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, makecare.getJenisSkincare());
            ps.setString(2, makecare.getNamaBrand());
            ps.setString(3, makecare.getHarga());
            ps.setString(4, makecare.getTanggalKadaluarsa;
            ps.setInt(5, todo.getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diubah");

            tampilData();
            bersihkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + e.getMessage());
        }
    }
        
    // ================== TOMBOL HAPUS ==================
    
        
    // ================== TOMBOL BERSIHKAN ==================
        
    // ================== TOMBOL KELUAR ==================
        
    // ================== MAIN ==================
    

    // ================== VARIABLES (dari NetBeans) ==================
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JTable tblDataMS;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKadaluarsa;
    private javax.swing.JTextField txtSkincare;

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel1)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
