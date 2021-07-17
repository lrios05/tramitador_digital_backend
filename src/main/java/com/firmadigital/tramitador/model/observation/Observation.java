package com.firmadigital.tramitador.model.observation;

import com.firmadigital.tramitador.model.ModelBase;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "observations")
public class Observation extends ModelBase {

    @OneToOne(targetEntity = Contract.class, cascade = CascadeType.ALL)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_obs")
    private List<Comment> comments = new ArrayList<>();
}
