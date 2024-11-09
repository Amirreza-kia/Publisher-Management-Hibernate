package ir.maktabsharif.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    public String firstname;

    @Column(name = "last_name")
    public String lastname;

    @Column(name = "national_code")
    private String nationalCode;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
