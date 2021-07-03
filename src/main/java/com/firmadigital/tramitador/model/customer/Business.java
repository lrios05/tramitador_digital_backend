package com.firmadigital.tramitador.model.customer;

import com.firmadigital.tramitador.model.ModelBase;
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
@Table(name = "businesses",
        indexes = @Index(
                name = "idx_business_nit",
                columnList = "nit",
                unique = true
        )
)
public class Business extends ModelBase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @Column(name = "nit", nullable = false, length = 15, unique = true)
    private String nit;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "website", length = 50)
    private String website;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity", nullable = false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_business_type", nullable = false)
    private BusinessType businessType;
}
