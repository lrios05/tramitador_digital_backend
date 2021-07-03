package com.firmadigital.tramitador.model.serviceoffer;

import com.firmadigital.tramitador.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "units")
public class Unit extends ModelBase {

    @Column(name = "unit", nullable = false, length = 5)
    private String unit;

    @Column(name = "description", length = 30)
    private String description;
}
