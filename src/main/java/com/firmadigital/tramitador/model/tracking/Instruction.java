package com.firmadigital.tramitador.model.tracking;

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
@Table(name = "instructions")
public class Instruction extends ModelBase {

    @Column(name = "instruction", nullable = false, length = 100)
    private String instruction;

}
