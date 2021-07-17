package com.firmadigital.tramitador.model.upload;

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
@Table(name = "documents")
public class Document extends ModelBase {

    @Column(name = "doc_type", nullable = false, length = 10)
    private String docType;

    @Column(name = "doc_name", nullable = false, length = 100)
    private String docName;
}
