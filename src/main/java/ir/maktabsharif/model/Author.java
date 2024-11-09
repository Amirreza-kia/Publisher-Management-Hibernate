package ir.maktabsharif.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Author extends User{


    //relation
    @OneToMany(mappedBy = "author")
    private List<NewsPaper> newsPapers = new ArrayList<>();
    @OneToMany(mappedBy = "author")
    private List<Magazine> magazines = new ArrayList<>();
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();
    @ManyToMany
    private List<Publisher> publishers = new ArrayList<>();

}
