package ir.maktabsharif.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information  {
    private String title;
    private String author;
    private String publisher;
    private int numberOfPages;

}
