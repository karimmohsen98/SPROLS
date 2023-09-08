package sprols.internship.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RealisationIntervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRealisationIntervnetion;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateRealisationIntervention;


    @Column()
    private String typeInternvetion;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeIntervenant;


    @NotNull
    @Column(nullable = false)
    private LocalDate dateConstatDiagnostic;

    @NotNull
    @Column()
    private String typeDefaut;

    @NotNull
    @Column()
    private String descriptionConstatDiagnostic;

    @NotNull
    @Column()
    private String descriptionIntervention;

    @Enumerated(EnumType.STRING)
    @Column
    private Frequence frequence;


    @Column()
    @Enumerated(EnumType.STRING)
    private Origine origine;

    @JsonProperty("planificationInterventionR")
    private void unpackNested(Integer planificationInterventionId) {
        this.planificationInterventionR = new PlanificationIntervention();
        planificationInterventionR.setIdPlanification(planificationInterventionId);
    }

    @ManyToOne
    private PlanificationIntervention planificationInterventionR;

    @JsonIgnore
    @OneToMany(mappedBy = "realisationInterventionV")
    private List<VerificationEfficacite> verificationEfficacite;
}
