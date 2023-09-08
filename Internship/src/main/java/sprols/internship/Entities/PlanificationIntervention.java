package sprols.internship.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PlanificationIntervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlanification;

    @Column(nullable = true)
    private String phases;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeIntervenant;

    @NotNull
    private LocalDate dateIntervention;

    @OneToOne
    @JoinColumn(unique = true)
    private DemandeIntervention demandeInterventionPlan;

    @JsonProperty("demandeInterventionPlan")
    private void unpackNested(Integer demandeInterventionPlanId) {
        this.demandeInterventionPlan = new DemandeIntervention();
        demandeInterventionPlan.setIdDemandeIntervention(demandeInterventionPlanId);
    }

    @OneToMany(mappedBy = "planificationInterventionR")
    @JsonIgnore
    private List<RealisationIntervention> realisationIntervention;
}
