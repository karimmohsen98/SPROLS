package sprols.internship.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    private String numMatriculeD;

    private String typeApprovisionnement;

    @NotNull
    @Column(nullable = false)
    private String nomMateriel;

    @NotNull
    @Column(nullable = false)
    private int quantiteDemande;

    @NotNull
    @Column(nullable = false)
    private int quantiteLivre;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDemandeApprovisionnement;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Etat etatApprovisionnement;

    @ManyToOne
    @JsonIgnore
    private Utilisateur utilisateurAppro;
}
