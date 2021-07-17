package com.firmadigital.tramitador.model.tracking;

import com.firmadigital.tramitador.model.ModelBase;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.user.User;
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
@Table(name = "notes")
public class Note extends ModelBase {

    @OneToOne(targetEntity = Contract.class, cascade = CascadeType.ALL)
    private Contract contract;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Temporal(TemporalType.DATE)
    @Column(name = "deadline", nullable = false, updatable = false)
    private Date deadline;

    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_note")
    private List<Detail> details = new ArrayList<>();
    */

}
