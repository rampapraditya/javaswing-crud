package repository;

import java.util.List;
import model.Produk;

/**
 *
 * @author Rampa
 */
public interface ProdukRepository {

    boolean insert(Produk produk);
    boolean update(Produk produk);
    boolean delete(int id);

    List<Produk> findAll();
}
