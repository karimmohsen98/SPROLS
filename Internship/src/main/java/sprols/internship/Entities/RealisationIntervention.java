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
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RealisationIntervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRealisationIntervnetion;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(nullable = false)
    private LocalDate dateRealisationIntervention;

    @Null
    @Column()
    private String typeInternvetion;

    @NotNull
    @Column(nullable = false)
    private int numMatriculeIntervenant;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(nullable = false)
    private LocalDate dateConstatDiagnostic;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(nullable = false)
    private LocalDate dateIntervention;

    @Null
    @Column
    private Frequence frequence;

    @Null
    @Column()
    private Origine origine;

    @OneToOne
    private PlanificationIntervention planificationInterventionR;

    @OneToOne(mappedBy = "realisationInterventionV")
    private VerificationEfficacite verificationEfficacite;
}
