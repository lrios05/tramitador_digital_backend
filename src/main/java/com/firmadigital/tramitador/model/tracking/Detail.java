package com.firmadigital.tramitador.model.tracking;

import com.firmadigital.tramitador.model.ModelBase;
import com.firmadigital.tramitador.model.customer.Customer;
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
@Table(name = "details")
public class Detail extends ModelBase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_note", nullable = false)
    private Note note;

    @Column(name = "comment", length = 100)
    private String comment;

    @Column(name = "priority", nullable = false, length = 10)
    private String priority;

    @Temporal(TemporalType.DATE)
    @Column(name = "deadline", nullable = false, updatable = false)
    private Date deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_user", nullable = false)
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_user", nullable = false)
    private User toUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "send_date", nullable = false, updatable = false)
    private Date sendDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "received_date", updatable = true)
    private Date receiveDate;

    @Column(name = "received_status", nullable = false, length = 10)
    private String receivedStatus;

    @Column(name = "send_status", nullable = false, length = 10)
    private String sendStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instruction", nullable = false)
    private Instruction instruction;
}
