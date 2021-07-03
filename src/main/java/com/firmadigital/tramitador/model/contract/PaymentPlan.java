package com.firmadigital.tramitador.model.contract;

import com.firmadigital.tramitador.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "payments_plan")
public class PaymentPlan extends ModelBase {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contract", referencedColumnName = "id")
    private Contract contract;
    /*
    @Id
    @Column(name = "id_contract")
    Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_contract")
    private Contract contract;
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paytype", nullable = false)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payfrequency", nullable = false)
    private PaymentFrequency paymentFrequency;

    @Column(name = "payments", nullable = false)
    private int payments;

    @Column(name = "amount", nullable = false, scale = 2)
    private double amount;

}
