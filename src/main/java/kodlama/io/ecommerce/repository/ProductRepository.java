package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameIgnoreCase(String name);
    List<Product> findAllByStateIsNot(State state);
}
