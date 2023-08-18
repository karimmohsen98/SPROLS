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
public class VerificationEfficacite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVerification;

    @NotNull
    @Column(nullable = false)
    private String avisDemandeurVerification;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeDemandeur;


    @NotNull
    @Column(nullable = false)
    private LocalDate dateAvisDemandeur;

    @NotNull
    @Column(nullable = false)
    private String avisChefInformatique;


    @NotNull
    @Column(nullable = false)
    private LocalDate dateAvisChefInformatique;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeChefInformatique;

    @ManyToOne
    private RealisationIntervention realisationInterventionV;
}
