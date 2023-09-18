package sprols.internship.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(nullable = true)
    private String avisDemandeurVerification;

    @Column(nullable = true)
    private String numMatriculeDemandeur;


    @Column(nullable = true)
    private LocalDate dateAvisDemandeur;

    @Column(nullable = true)
    private String avisChefInformatique;


    @Column(nullable = true)
    private LocalDate dateAvisChefInformatique;

    @Column(nullable = true)
    private String numMatriculeChefInformatique;

    @JsonProperty("realisationInterventionV")
    private void unpackNested(Integer realisationInterventionId) {
        this.realisationInterventionV = new RealisationIntervention();
        realisationInterventionV.setIdRealisationIntervnetion(realisationInterventionId);
    }

    @ManyToOne
    private RealisationIntervention realisationInterventionV;
}
