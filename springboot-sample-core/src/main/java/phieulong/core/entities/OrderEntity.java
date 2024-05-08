package phieulong.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    private Long id;
    private Instant created_at;
    private Instant updated_at;
    private Long user_id;
    private String note;
}
