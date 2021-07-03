package com.firmadigital.tramitador.model.contract;

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
@Table(name = "waste_types")
public class WasteType extends ModelBase {

    @Column(name = "waste", nullable = false, length = 15)
    private String waste;

    @Column(name = "description", length = 100)
    private String description;

}
