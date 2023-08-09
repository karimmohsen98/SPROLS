package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private DemandeIntervention demandeInterventionPlan;

    @OneToOne(mappedBy = "planificationInterventionR")
    private RealisationIntervention realisationIntervention;
}
