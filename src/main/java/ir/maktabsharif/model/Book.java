package ir.maktabsharif.model;


import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends BaseModel{

    private String coverMaterial;
    private String ISBN;

    @Embedded
    private Information information;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    private Author author;

}
