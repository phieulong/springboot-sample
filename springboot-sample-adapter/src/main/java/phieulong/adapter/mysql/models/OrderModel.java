package phieulong.adapter.mysql.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="orders")
public class OrderModel extends BaseModel {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "note")
    private String note;
}
