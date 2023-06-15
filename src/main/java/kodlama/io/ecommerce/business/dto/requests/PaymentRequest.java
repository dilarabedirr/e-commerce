package kodlama.io.ecommerce.business.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kodlama.io.ecommerce.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest implements BaseDto {
    @NotBlank
    @Length(min = 16, max = 16)
    private String cardNumber;
    @NotBlank
    @Length(min = 5)
    private String cardHolder;
    @Min(value = 2023)
    private int cardExpirationYear;
    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;
    @NotNull
    @NotBlank
    @Length(min = 3, max = 3)
    private String cardCvv;
}



