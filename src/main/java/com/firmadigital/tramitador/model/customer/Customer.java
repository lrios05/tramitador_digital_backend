package com.firmadigital.tramitador.model.customer;

import com.firmadigital.tramitador.model.ModelBase;
import com.firmadigital.tramitador.model.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "customers",
        indexes = @Index(
                name = "idx_customer_code",
                columnList = "customer_code",
                unique = true
        )
)
public class Customer extends ModelBase {

    @Column(name = "customer_code", nullable = false, length = 15)
    private String customerCode;

    @Column(name = "dni", unique = true, nullable = false, length = 15)
    private String dni;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "paternal", nullable = false, length = 30)
    private String paternal;

    @Column(name = "maternal", length = 30)
    private String maternal;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
