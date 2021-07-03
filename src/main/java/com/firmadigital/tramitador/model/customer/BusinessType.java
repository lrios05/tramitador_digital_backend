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
@Table(name = "business_types")
public class BusinessType extends ModelBase {

    @Column(name = "type", nullable = false, length = 15)
    private String businessType;

    @Column(name = "description", length = 50)
    private String description;

}
