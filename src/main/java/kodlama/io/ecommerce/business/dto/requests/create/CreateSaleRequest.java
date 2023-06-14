package kodlama.io.ecommerce.business.dto.requests.create;

import kodlama.io.ecommerce.business.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleRequest implements BaseDto {
    private int productId;
    private int quantity;
    private double price;
}
