package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Produk;
import repository.ProdukRepository;
import repository.ProdukRepositoryImpl;
import view.Main;

/**
 *
 * @author Rampa
 */
public class ProdukController {

    private final Main view;
    private final ProdukRepository repository;

    public ProdukController(Main view) {
        this.view = view;
        this.repository = new ProdukRepositoryImpl();
    }

    public void loadData() {
        List<Produk> list = repository.findAll();
        DefaultTableModel model = (DefaultTableModel) view.getTableProduk().getModel();
        model.setRowCount(0); // Mengosongkan baris tabel sebelum diisi baru
        for (Produk p : list) {
            model.addRow(new Object[]{p.getId(), p.getNamaProduk(), p.getHarga(), p.getStok()});
        }
    }

    // Mengambil input teks via Getter lalu menyimpannya ke MySQL
    public void saveProduk() {
        String nama = view.getTxtNama().getText();
        double harga = Double.parseDouble(view.getTxtHarga().getText());
        int stok = Integer.parseInt(view.getTxtStok().getText());

        Produk p = new Produk(0, nama, harga, stok);
        repository.insert(p);
        loadData(); // Segarkan tampilan tabel
        clearForm(); // Kosongkan form input
    }

    // Mengambil data form termasuk ID via Getter untuk memperbarui data
    public void updateProduk() {
        int id = Integer.parseInt(view.getTxtId().getText());
        String nama = view.getTxtNama().getText();
        double harga = Double.parseDouble(view.getTxtHarga().getText());
        int stok = Integer.parseInt(view.getTxtStok().getText());

        Produk p = new Produk(id, nama, harga, stok);
        repository.update(p);
        loadData();
        clearForm();
    }

    // Mengambil ID via Getter untuk menghapus data di database
    public void deleteProduk() {
        int id = Integer.parseInt(view.getTxtId().getText());
        repository.delete(id);
        loadData();
        clearForm();
    }

    // Mengosongkan semua field input di form via Getter
    public void clearForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtHarga().setText("");
        view.getTxtStok().setText("");
    }
}
