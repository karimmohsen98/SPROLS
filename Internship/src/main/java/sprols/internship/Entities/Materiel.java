package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Materiel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateriel;

    @NotNull
    @Column(nullable = false)
    private String nomMateriel;

    @ManyToMany(mappedBy = "materielList")
    List<Approvisionnement> approvisionnementList;
}
