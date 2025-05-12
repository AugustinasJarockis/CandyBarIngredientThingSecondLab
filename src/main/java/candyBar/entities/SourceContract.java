package candyBar.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SOURCE_CONTRACT")
@Getter
@Setter
public class SourceContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 75)
    @Column(name = "COUNTRY_OF_ORIGIN")
    private String countryOfOrigin;

    @Size(max = 20)
    @Column(name = "PRICE")
    private String price;

    @ManyToOne
    private Ingredient ingredient;

    @Version
    @Column(name="OPT_LOCK_VERSION")
    private Integer version;
}
