package ir.maktabsharif.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsPaper extends BaseModel{

    @Column(unique=true)
    private int serialNumber;

    @Embedded
    private Information information;


    //relation
    @ManyToOne
    private Publisher publisher;
    @ManyToOne
    private Author author;
}
