package phieulong.api.requests;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateOrderRequest {

    @NotNull
    private String note;
}
