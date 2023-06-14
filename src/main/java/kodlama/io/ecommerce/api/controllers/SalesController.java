package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.get.sale.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.sale.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SalesController {
    private final SaleService service;

    @GetMapping
    public List<GetAllSalesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetSaleResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateSaleResponse add(@Valid @RequestBody CreateSaleRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateSaleResponse update(@PathVariable int id, @RequestBody UpdateSaleRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
