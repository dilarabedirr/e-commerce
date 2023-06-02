package kodlama.io.ecommerce.business.dto.responses.update;

import kodlama.io.ecommerce.business.dto.BaseDto;
import kodlama.io.ecommerce.business.dto.responses.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductResponse implements BaseDto {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private Set<CategoryResponse> categories;
}
