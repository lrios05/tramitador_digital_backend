package com.firmadigital.tramitador.model.user;

import com.firmadigital.tramitador.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "roles")
public class Role extends ModelBase {

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Column(name = "status", nullable = false, length = 1)
    private Character status;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
