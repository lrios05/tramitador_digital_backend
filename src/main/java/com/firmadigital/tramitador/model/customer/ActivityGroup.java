package com.firmadigital.tramitador.model.customer;

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
@Table(name = "activity_groups")
public class ActivityGroup extends ModelBase {

    @Column(name = "activity_group", nullable = false, length = 50)
    private String activityGroup;
}
