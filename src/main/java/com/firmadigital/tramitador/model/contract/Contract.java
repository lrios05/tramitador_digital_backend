package com.firmadigital.tramitador.model.contract;

import com.firmadigital.tramitador.model.ModelBase;
import com.firmadigital.tramitador.model.customer.Customer;
import com.firmadigital.tramitador.model.serviceoffer.ServiceOffer;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import com.firmadigital.tramitador.model.upload.Attache;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "contracts",
        indexes = @Index(
                name = "idx_contract_code",
                columnList = "contract_code",
                unique = true
        )
)
public class Contract extends ModelBase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @Column(name = "contract_code", nullable = false, length = 15)
    private String contractCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "init_date", nullable = false, updatable = false)
    private Date initDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false, updatable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = false)
    private ServiceOffer serviceOffer;

    @Column(name = "total_cost", nullable = false, scale = 2)
    private double totalCost;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gfrequency", nullable = false)
    private GatherFrequency gatherFrequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wtype", nullable = false)
    private WasteType wasteType;

    @Column(name = "volume", nullable = false, scale = 2)
    private double volume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit", nullable = false)
    private Unit unit;

    @Column(name = "days", nullable = false, length = 15)
    private String days;

    @Column(name = "status", length = 15)
    private String status;

}
