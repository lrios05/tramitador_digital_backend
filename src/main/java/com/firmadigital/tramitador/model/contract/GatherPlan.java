package com.firmadigital.tramitador.model.contract;

import com.firmadigital.tramitador.model.ModelBase;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
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
@Table(name = "gathers_plan")
public class GatherPlan extends ModelBase {
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
}
