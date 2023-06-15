package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.common.dto.CreateProductSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.get.sale.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.sale.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import kodlama.io.ecommerce.business.rules.SaleBusinessRules;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.common.utils.dtoConverter.DtoConverterService;
import kodlama.io.ecommerce.entities.Sale;
import kodlama.io.ecommerce.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final DtoConverterService dtoConverterService;
    private final ProductService productService;
    private final SaleBusinessRules rules;
    private final PaymentService paymentService;


    @Override
    public List<GetAllSalesResponse> getAll() {
        var sales = repository.findAll();
        var response = dtoConverterService.toListDto(sales, GetAllSalesResponse.class);
        return response;
    }

    @Override
    public GetSaleResponse getById(int id) {
        rules.checkIfSaleExists(id);
        var sale = repository.findById(id).orElseThrow();
        return dtoConverterService.toDto(sale, GetSaleResponse.class);
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        var sale = dtoConverterService.toEntity(request, Sale.class);
        sale.setId(0);
        sale.setDateTime(LocalDateTime.now());
        sale.setTotalPrice(getTotalPrice(sale));
        repository.save(sale);

        processSalePayment(request, sale);
        processProductSale(sale);

        return dtoConverterService.toDto(sale, CreateSaleResponse.class);
    }

    @Override
    public UpdateSaleResponse update(int id, UpdateSaleRequest request) {
        rules.checkIfSaleExists(id);
        var sale = dtoConverterService.toEntity(request, Sale.class);
        sale.setId(id);
        sale.setTotalPrice(getTotalPrice(sale));
        repository.save(sale);

        return dtoConverterService.toDto(sale, UpdateSaleResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfSaleExists(id);
        repository.deleteById(id);
    }

    public double getTotalPrice(Sale sale) {
        return sale.getPrice() * sale.getQuantity();
    }

    private void processProductSale(Sale sale) {
        CreateProductSaleRequest productSaleRequest = new CreateProductSaleRequest();

        productSaleRequest.setProductId(sale.getProduct().getId());
        productSaleRequest.setQuantity(sale.getQuantity());

        productService.processProductSale(productSaleRequest);
    }

    private void processSalePayment(CreateSaleRequest request, Sale sale) {
        CreateSalePaymentRequest salePaymentRequest = new CreateSalePaymentRequest();
        salePaymentRequest.setCardNumber(request.getPaymentRequest().getCardNumber());
        salePaymentRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        salePaymentRequest.setCardExpirationYear(request.getPaymentRequest().getCardExpirationYear());
        salePaymentRequest.setCardExpirationMonth(request.getPaymentRequest().getCardExpirationMonth());
        salePaymentRequest.setCardCvv(request.getPaymentRequest().getCardCvv());
        salePaymentRequest.setPrice(sale.getTotalPrice());
        paymentService.processSalePayment(salePaymentRequest);
    }
}
