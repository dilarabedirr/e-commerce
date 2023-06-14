package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.get.sale.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.sale.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;

import java.util.List;

public interface SaleService {
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(int id);
    CreateSaleResponse add(CreateSaleRequest request);
    UpdateSaleResponse update(int id,UpdateSaleRequest request);
    void delete(int id);
}
