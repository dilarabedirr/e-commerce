package kodlama.io.ecommerce.business.dto.requests.update;

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
public class UpdateSaleRequest implements BaseDto {
    private int productId;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;
}
