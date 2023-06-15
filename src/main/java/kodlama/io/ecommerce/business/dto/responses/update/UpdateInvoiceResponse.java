package kodlama.io.ecommerce.business.dto.responses.update;

import kodlama.io.ecommerce.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceResponse implements BaseDto {
    private int id;
    private String cardHolder;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
}
