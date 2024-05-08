package phieulong.api.requests;

import lombok.*;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateOrderRequest {

    @NotNull
    private String note;
}
