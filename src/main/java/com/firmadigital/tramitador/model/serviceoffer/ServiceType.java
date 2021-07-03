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
@Table(name = "service_types")
public class ServiceType extends ModelBase {

    @Column(name = "service_type", nullable = false, length = 15)
    private String serviceType;

    @Column(name = "description", length = 100)
    private String description;

}
