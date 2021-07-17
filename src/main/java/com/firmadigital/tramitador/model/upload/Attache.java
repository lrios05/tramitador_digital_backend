package com.firmadigital.tramitador.model.upload;

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
@Table(name = "attaches")
public class Attache extends ModelBase {

    @OneToOne(targetEntity = Contract.class, cascade = CascadeType.ALL)
    private Contract contract;

    @Column(name = "origin_url", nullable = false, length = 100)
    private String originUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_attache")
    private List<Document> documents = new ArrayList<>();
}
