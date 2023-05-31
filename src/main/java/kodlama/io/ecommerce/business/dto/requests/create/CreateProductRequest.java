package kodlama.io.ecommerce.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import kodlama.io.ecommerce.business.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest implements BaseDto {
    @NotBlank
    private String name;
    @Min(1)
    private int quantity;
    @Min(1)
    private double price;
    @NotBlank
    private String description;
}
