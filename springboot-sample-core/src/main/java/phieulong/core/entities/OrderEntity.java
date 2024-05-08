package phieulong.core.entities;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEntity {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String userId;
    private String note;
}
