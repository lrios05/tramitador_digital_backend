package com.firmadigital.tramitador.model.serviceoffer;

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
@Table(name = "services")
public class ServiceOffer extends ModelBase {

    @Column(name = "service", nullable = false, length = 50)
    private String service;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serv_type")
    private ServiceType serviceType;

}
