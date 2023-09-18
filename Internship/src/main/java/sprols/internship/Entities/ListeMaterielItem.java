package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ListeMaterielItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idListeMaterielItem;
    private int quantiteDemande;
    private int quantiteApprovisionne;

    @ManyToOne
    private ListeDesMateriel listeMats;

    @ManyToOne
    private Materiel listeDesMateriel;
}
