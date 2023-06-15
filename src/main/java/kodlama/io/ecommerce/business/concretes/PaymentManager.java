package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.PosService;
import kodlama.io.ecommerce.business.dto.requests.create.CreatePaymentRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.responses.get.payment.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.payment.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdatePaymentResponse;
import kodlama.io.ecommerce.business.rules.PaymentBusinessRules;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.common.utils.dtoConverter.DtoConverterService;
import kodlama.io.ecommerce.entities.Payment;
import kodlama.io.ecommerce.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final DtoConverterService dtoConverterService;
    private final PaymentBusinessRules rules;
    private final PosService posService;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        var payments = repository.findAll();
        return dtoConverterService.toListDto(payments, GetAllPaymentsResponse.class);
    }

    @Override
    public GetPaymentResponse getById(int id) {
        rules.checkIfPaymentExists(id);
        var payment = repository.findById(id).orElseThrow();
        return dtoConverterService.toDto(payment, GetPaymentResponse.class);
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        var payment = dtoConverterService.toEntity(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        return dtoConverterService.toDto(payment, CreatePaymentResponse.class);
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        var payment = dtoConverterService.toEntity(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        return dtoConverterService.toDto(payment, UpdatePaymentResponse.class);
    }

    @Override
    public void processSalePayment(CreateSalePaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        var payment = repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceIsEnough(payment.getBalance(), request.getPrice());
        posService.pay();
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }

    @Override
    public void delete(int id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }
}
