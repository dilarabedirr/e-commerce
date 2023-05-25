package kodlama.io.ecommerce.business.dto.responses.get.product;

import kodlama.io.ecommerce.business.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse implements BaseDto {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
}
