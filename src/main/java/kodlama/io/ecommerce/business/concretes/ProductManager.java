package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product add(Product product) {
        validateProduct(product);
        return repository.save(product);
    }
    @Override
    public void delete(int id) {
        checkIfProductExists(id);
        repository.deleteById(id);
    }

    @Override
    public Product update(int id, Product product) {
        validateProduct(product);
        checkIfProductExists(id);
        product.setId(id);
        return repository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(int id) {
        checkIfProductExists(id);
        return repository.findById(id).orElseThrow();
    }

    // iş kuralları - business rules
    private void validateProduct(Product product) {
        checkIfPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }

    private void checkIfPriceValid(Product product) {
        if (product.getPrice() <= 0)
            throw new IllegalArgumentException("Price cannot be less than or equal to zero.");
    }

    private void checkIfQuantityValid(Product product) {
        if (product.getQuantity() < 0)
            throw new IllegalArgumentException("Ürünlerin quantity(miktarı) 0 dan küçük olamaz");
    }

    private void checkIfDescriptionLengthValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            throw new IllegalArgumentException("Ürünlerin description(açıklama) alanı min 10 karakter max 50 karakter olmalıdır.");
        }
    }
    private void checkIfProductExists(int id){
        if (!repository.existsById(id)) throw new RuntimeException("ürün bulunamadı.");
    }
}
