package repository;

import java.util.List;
import model.Produk;

/**
 *
 * @author Rampa
 */
public interface ProdukRepository {

    void insert(Produk produk);

    void update(Produk produk);

    void delete(int id);

    List<Produk> findAll();
}
