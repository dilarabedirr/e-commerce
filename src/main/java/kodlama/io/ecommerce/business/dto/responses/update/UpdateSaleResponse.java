package kodlama.io.ecommerce.business.dto.responses.update;

import kodlama.io.ecommerce.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSaleResponse implements BaseDto {
    private int id;
    private int productId;
    private int quantity;
    private double price;
    private double totalPrice;
    private LocalDateTime dateTime;
}
