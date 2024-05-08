package phieulong.api.responses;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String userId;
    private String note;
}
