package view;
import config.Koneksi;
import model.MakeCare;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SkincareMakeupForm extends javax.swing.JFrame{

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
                    rs.getDouble("harga_skincare"),
                    rs.getString("tanggal_kadaluarsa")
                });
            }

            tblDataMS.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil: " + e.getMessage());
        }
    }

    // ================== BERSIHKAN ==================
    private void bersihkanForm() {
        txtSkincare.setText("");
        txtBrand.setText("");
        txtHarga.setText("");
        txtKadaluarsa.setText("");
        idTerpilih = 0;
    }

    // ================== SIMPAN ==================
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String sql = "INSERT INTO skincare (nama_brand, jenis_skincare, harga_skincare, tanggal_kadaluarsa) VALUES (?, ?, ?, ?)";

            Connection conn = Koneksi.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtBrand.getText());
            pst.setString(2, txtSkincare.getText());
            pst.setDouble(3, Double.parseDouble(txtHarga.getText()));
            pst.setString(4, txtKadaluarsa.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            tampilData();
            bersihkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // ================== KLIK TABEL ================== (by Ayu)
    private void tblDataMSMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tblDataMS.getSelectedRow();

        idTerpilih = Integer.parseInt(tblDataMS.getValueAt(baris, 0).toString());

        txtSkincare.setText(tblDataMS.getValueAt(baris, 1).toString());
        txtBrand.setText(tblDataMS.getValueAt(baris, 2).toString());
        txtHarga.setText(tblDataMS.getValueAt(baris, 3).toString());
        txtKadaluarsa.setText(tblDataMS.getValueAt(baris, 4).toString());
    }

    // ================== UBAH ================== (by Ayu)
    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {

        if (idTerpilih == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }

        try {
            String sql = "UPDATE skincare SET nama_brand=?, jenis_skincare=?, harga_skincare=?, tanggal_kadaluarsa=? WHERE id=?";

            Connection conn = Koneksi.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtBrand.getText());
            pst.setString(2, txtSkincare.getText());
            pst.setDouble(3, Double.parseDouble(txtHarga.getText()));
            pst.setString(4, txtKadaluarsa.getText());
            pst.setInt(5, idTerpilih);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diubah");
            tampilData();
            bersihkanForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ubah: " + e.getMessage());
        }
    }

    // ================== HAPUS ================== (by Syifa)
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (idTerpilih == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
            this,
            "Yakin hapus?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM skincare WHERE id=?";

                Connection conn = Koneksi.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setInt(1, idTerpilih);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");

                tampilData();
                bersihkanForm();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    // ================== BERSIHKAN ==================
    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {
        bersihkanForm();
    }



    // ================== MAIN ==================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SkincareMakeupForm().setVisible(true);
            }
        });
    }

    // ================== VARIABLE (HARUS SAMA DENGAN DESIGN) NO UBAH GES
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnBatal;
    private javax.swing.JTable tblDataMS;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKadaluarsa;
    private javax.swing.JTextField txtSkincare;

    // ================== INIT (JANGAN DIUBAH MANUAL) NO UBAH GES
    @SuppressWarnings("unchecked")
    private void initComponents() {

        txtSkincare = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtKadaluarsa = new javax.swing.JTextField();

        btnSimpan = new javax.swing.JButton("SIMPAN");
        btnUbah = new javax.swing.JButton("UBAH");
        btnHapus = new javax.swing.JButton("HAPUS");
        btnBatal = new javax.swing.JButton("BATAL");

        tblDataMS = new javax.swing.JTable();
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tblDataMS);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // EVENT
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);
        btnUbah.addActionListener(this::btnUbahActionPerformed);

        tblDataMS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMSMouseClicked(evt);
            }
        });

        // LAYOUT SEDERHANA
        setLayout(new java.awt.GridLayout(6, 2));

        add(new javax.swing.JLabel("Skincare"));
        add(txtSkincare);

        add(new javax.swing.JLabel("Brand"));
        add(txtBrand);

        add(new javax.swing.JLabel("Harga"));
        add(txtHarga);

        add(new javax.swing.JLabel("Kadaluarsa"));
        add(txtKadaluarsa);

        add(btnSimpan);
        add(btnUbah);
        add(btnHapus);
        add(btnBatal);

        add(scroll);

        pack();
    }
}
