package kodlama.io.ecommerce.business.dto.responses.get.product;

import kodlama.io.ecommerce.common.dto.BaseDto;
import kodlama.io.ecommerce.business.dto.responses.CategoryResponse;
import kodlama.io.ecommerce.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private State state;
    private Set<CategoryResponse> categories;
}
