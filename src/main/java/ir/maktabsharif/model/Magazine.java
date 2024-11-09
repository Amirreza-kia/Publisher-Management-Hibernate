package ir.maktabsharif.model;


import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Magazine extends BaseModel{

    private String Circulation;

    @Embedded
    private Information information;


    //relation
    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;


}
