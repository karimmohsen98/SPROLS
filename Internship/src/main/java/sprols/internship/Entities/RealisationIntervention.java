package sprols.internship.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(nullable = false)
    private LocalDate dateIntervention;


    @Column
    private Frequence frequence;


    @Column()
    private Origine origine;

    @JsonIgnore
    @OneToOne
    private PlanificationIntervention planificationInterventionR;

    @JsonIgnore
    @OneToOne(mappedBy = "realisationInterventionV")
    private VerificationEfficacite verificationEfficacite;
}
