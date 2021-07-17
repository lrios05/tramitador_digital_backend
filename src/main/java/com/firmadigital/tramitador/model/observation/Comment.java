package com.firmadigital.tramitador.model.observation;

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
@Table(name = "comments")
public class Comment extends ModelBase {

    @Column(name = "comment", nullable = false, length = 100)
    private String comment;

    @Column(name = "status", nullable = false, length = 10)
    private String status;
}
