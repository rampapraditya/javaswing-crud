package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produk;
import repository.ProdukRepository;
import repository.ProdukRepositoryImpl;
import view.ViewProduk;

/**
 *
 * @author Rampa
 */
public class ProdukController {

    private final ViewProduk view;
    private final ProdukRepository repository;

    public ProdukController(ViewProduk view) {
        this.view = view;
        this.repository = new ProdukRepositoryImpl();
    }

    public void loadData() {
        try {
            List<Produk> list = repository.findAll();
            DefaultTableModel model = (DefaultTableModel) view.getTableProduk().getModel();
            model.setRowCount(0); // Mengosongkan baris tabel sebelum diisi baru
            for (Produk p : list) {
                model.addRow(new Object[]{p.getId(), p.getNamaProduk(), p.getHarga(), p.getStok()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mengambil input teks via Getter lalu menyimpannya ke MySQL
    public void saveProduk() {
        try {
            String nama = view.getTxtNama().getText().trim();
            
            // Validasi input kosong
            if (nama.isEmpty() || view.getTxtHarga().getText().isEmpty() || view.getTxtStok().getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double harga = Double.parseDouble(view.getTxtHarga().getText());
            int stok = Integer.parseInt(view.getTxtStok().getText());

            Produk p = new Produk(0, nama, harga, stok);
            
            // Mengecek nilai balik boolean dari repository
            boolean isSuccess = repository.insert(p);

            if (isSuccess) {
                JOptionPane.showMessageDialog(view, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData(); // Segarkan tampilan tabel
                clearForm(); // Kosongkan form input
            } else {
                JOptionPane.showMessageDialog(view, "Data gagal disimpan ke database!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga dan Stok harus berupa angka!", "Format Salah", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mengambil data form termasuk ID via Getter untuk memperbarui data
    public void updateProduk() {
        try {
            // Validasi jika ID belum dipilih atau kosong
            if (view.getTxtId().getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data yang akan diubah terlebih dahulu dari tabel!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(view.getTxtId().getText());
            String nama = view.getTxtNama().getText().trim();
            
            if (nama.isEmpty() || view.getTxtHarga().getText().isEmpty() || view.getTxtStok().getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double harga = Double.parseDouble(view.getTxtHarga().getText());
            int stok = Integer.parseInt(view.getTxtStok().getText());

            Produk p = new Produk(id, nama, harga, stok);
            
            // Mengecek nilai balik boolean dari repository
            boolean isSuccess = repository.update(p);

            if (isSuccess) {
                JOptionPane.showMessageDialog(view, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Data gagal diperbarui! ID tidak ditemukan atau data tidak berubah.", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Format angka pada ID/Harga/Stok salah!", "Format Salah", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mengambil ID via Getter untuk menghapus data di database
    public void deleteProduk() {
        try {
            // Validasi jika ID belum dipilih atau kosong
            if (view.getTxtId().getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu dari tabel!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Menambahkan konfirmasi sebelum menghapus data
            int konfirmasi = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            
            if (konfirmasi == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(view.getTxtId().getText());
                
                // Mengecek nilai balik boolean dari repository
                boolean isSuccess = repository.delete(id);

                if (isSuccess) {
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Data gagal dihapus! ID tidak ditemukan.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mengosongkan semua field input di form via Getter
    public void clearForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtHarga().setText("");
        view.getTxtStok().setText("");
    }
}
