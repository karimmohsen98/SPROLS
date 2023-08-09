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
public class DemandeIntervention implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDemandeIntervention;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeDemandeur;

    @NotNull
    @Column(nullable = false)
    private int codeMateriel;

    @Column(nullable = true)
    private String constructeur;

    @Column(nullable = true)
    private String modele;

    @Column(nullable = true)
    private String version;

    @Column(nullable = true)
    private String typeModel;

    @NotNull
    @Column(nullable = false)
    private String descriptionPanne;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDemandeIntervention;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Etat etatDemandeIntervention;

    @Column(nullable = true)
    private String typeMat;

    @JsonIgnore
    @ManyToOne
    private Utilisateur utilisateurDemandeInterv;

    @OneToOne(mappedBy = "demandeInterventionPlan")
    private PlanificationIntervention planificationIntervention;
}
