package ir.maktabsharif.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher extends User{




    @OneToMany(mappedBy = "publisher")
    private List<NewsPaper> newsPapers = new ArrayList<>();
    @OneToMany(mappedBy = "publisher")
    private List<Magazine> magazines = new ArrayList<>();
    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
    @ManyToMany(mappedBy = "publishers")
    private List<Author> authors = new ArrayList<>();

}
