package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateProductResponse;
import kodlama.io.ecommerce.common.utils.dtoConverter.DtoConverterService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final DtoConverterService dtoConverter;

    @Override
    public List<GetAllProductsResponse> getAll() {
        var products = repository.findAll();
        var response = dtoConverter.toListDto(products, GetAllProductsResponse.class);
        return response;
    }

    @Override
    public GetProductResponse getById(int id) {
        var product = repository.findById(id).orElseThrow();
        var response = dtoConverter.toDto(product, GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        var product = dtoConverter.toEntity(request, Product.class);
        product.setId(0);
        repository.save(product);
        var response = dtoConverter.toDto(product, CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        var product = dtoConverter.toEntity(request, Product.class);
        product.setId(id);
        repository.save(product);
        var response = dtoConverter.toDto(product, UpdateProductResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
