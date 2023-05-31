package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfProductExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Product.NotExists);
        }
    }

    public void checkIfProductExistsByName(String name){
        if (!repository.existsByNameIgnoreCase(name)){
        }
    }
}
