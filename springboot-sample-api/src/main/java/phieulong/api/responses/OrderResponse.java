package phieulong.api.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String userId;
    private String note;
}
