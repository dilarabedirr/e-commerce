package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.common.dto.CreateProductSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateProductResponse;
import kodlama.io.ecommerce.entities.enums.State;

import java.util.List;

public interface ProductService {
    List<GetAllProductsResponse> getAll(boolean includePassive);
    GetProductResponse getById(int id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update(int id, UpdateProductRequest request);
    void changeProductState(int productId, State state);
    void delete(int id);

    void processProductSale(CreateProductSaleRequest request);
}
