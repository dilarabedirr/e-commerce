package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.responses.get.invoice.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.invoice.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateInvoiceResponse;
import kodlama.io.ecommerce.business.rules.InvoiceBusinessRules;
import kodlama.io.ecommerce.common.utils.dtoConverter.DtoConverterService;
import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final DtoConverterService dtoConverterService;
    public final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        var invoices = repository.findAll();
        return dtoConverterService.toListDto(invoices, GetAllInvoicesResponse.class);
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);
        var invoice = repository.findById(id).orElseThrow();
        return dtoConverterService.toDto(invoice, GetInvoiceResponse.class);
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        var invoice = dtoConverterService.toEntity(request, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        return dtoConverterService.toDto(invoice, CreateInvoiceResponse.class);
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        var invoice = dtoConverterService.toEntity(request, Invoice.class);
        invoice.setId(id);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        return dtoConverterService.toDto(invoice, UpdateInvoiceResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Invoice invoice) {
        return invoice.getPrice() * invoice.getQuantity();
    }
}
