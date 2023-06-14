package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.CreateProductSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateProductResponse;
import kodlama.io.ecommerce.business.rules.ProductBusinessRules;
import kodlama.io.ecommerce.common.utils.dtoConverter.DtoConverterService;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.State;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final DtoConverterService dtoConverter;
    private final ProductBusinessRules rules;
    private final CategoryService categoryService;

    @Override
    public List<GetAllProductsResponse> getAll(boolean includePassive) {
        var products = filterProductsByPassiveState(includePassive);
        var response = dtoConverter.toListDto(products, GetAllProductsResponse.class);
        return response;
    }

    @Override
    public GetProductResponse getById(int id) {
        rules.checkIfProductExists(id);
        var product = repository.findById(id).orElseThrow();
        var response = dtoConverter.toDto(product, GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        rules.checkIfProductExistsByName(request.getName());
        var product = dtoConverter.toEntity(request, Product.class);
        product.setId(0);
        setCategoryToProduct(request.getCategoryIds(), product);
        repository.save(product);
        var response = dtoConverter.toDto(product, CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        rules.checkIfProductExists(id);
        var product = dtoConverter.toEntity(request, Product.class);
        product.setId(id);
        setCategoryToProduct(request.getCategoryIds(), product);
        repository.save(product);
        var response = dtoConverter.toDto(product, UpdateProductResponse.class);
        return response;
    }

    @Override
    public void changeProductState(int productId, State state){
        var product =repository.findById(productId).orElseThrow();
        product.setState(state);
        repository.save(product);
    }

    @Override
    public void delete(int id) {
        rules.checkIfProductExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processProductSale(CreateProductSaleRequest request) {
        var product = repository.findById(request.getProductId()).orElseThrow();
        product.setQuantity(product.getQuantity()- request.getQuantity());
        repository.save(product);
    }

    private void setCategoryToProduct(Set<Integer> categoryIds, Product product) {
        categoryIds.stream().forEach(categoryId -> {
            product.getCategories().add(dtoConverter.toEntity(categoryService.getById(categoryId), Category.class));
        });
    }

    private List<Product> filterProductsByPassiveState(boolean includePassive){
        if (includePassive){
            return repository.findAll();
        }
        return repository.findAllByStateIsNot(State.PASSIVE);
    }
}
