package sprols.internship.Entities;

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
public class DemandeIntervention implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDemandeIntervention;

    @NotNull
    @Column(nullable = false)
    private int numMatriculeDemandeur;

    @NotNull
    @Column(nullable = false)
    private int codeMateriel;

    @Null
    private String constructeur;

    @Null
    private String modele;

    @Null
    private String version;

    @Null
    private String typeModel;

    @NotNull
    @Column(nullable = false)
    private String descriptionPanne;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDemandeIntervention;

    @NotNull
    @Column(nullable = false)
    private Etat etatDemandeIntervention;

    @Null
    private String typeMat;

    @ManyToOne
    private Utilisateur utilisateurDemandeInterv;

    @OneToOne(mappedBy = "demandeInterventionPlan")
    private PlanificationIntervention planificationIntervention;
}
