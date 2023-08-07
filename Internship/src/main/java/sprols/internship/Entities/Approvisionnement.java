package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Approvisionnement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idApprovisionnement;

    @NotNull
    @Column(nullable = false)
    private int numMatriculeD;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDemandeApprovisionnement;

    @NotNull
    @Column(nullable = false)
    private Etat etatApprovisionnement;

    @ManyToOne
    private Utilisateur utilisateurAppro;
}
