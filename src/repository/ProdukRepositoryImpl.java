package repository;

import config.DatabaseConfig;
import model.Produk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rampa
 */
public class ProdukRepositoryImpl implements ProdukRepository {

    private final Connection connection;

    public ProdukRepositoryImpl() {
        this.connection = DatabaseConfig.getConnection();
    }

    @Override
    public boolean insert(Produk produk) {
        String sql = "INSERT INTO produk (nama_produk, harga, stok) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produk.getNamaProduk());
            ps.setDouble(2, produk.getHarga());
            ps.setInt(3, produk.getStok());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Mengembalikan true jika ada baris yang bertambah
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false; // Mengembalikan false jika terjadi error SQL
        }
    }

    @Override
    public boolean update(Produk produk) {
        String sql = "UPDATE produk SET nama_produk=?, harga=?, stok=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produk.getNamaProduk());
            ps.setDouble(2, produk.getHarga());
            ps.setInt(3, produk.getStok());
            ps.setInt(4, produk.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Mengembalikan true jika ada baris yang diperbarui
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;        // Mengembalikan false jika terjadi error SQL
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM produk WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Mengembalikan true jika ada baris yang dihapus
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Produk> findAll() {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produk p = new Produk(
                        rs.getInt("id"),
                        rs.getString("nama_produk"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

}
