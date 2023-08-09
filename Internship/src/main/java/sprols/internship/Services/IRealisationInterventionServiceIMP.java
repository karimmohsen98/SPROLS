package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.PlanificationIntervention;
import sprols.internship.Entities.RealisationIntervention;
import sprols.internship.Repositories.PlanificationInterventionRepository;
import sprols.internship.Repositories.RealisationInterventionRepository;

@Service
@AllArgsConstructor
public class IRealisationInterventionServiceIMP implements RealisationInterventionService{

    private final RealisationInterventionRepository realisationInterventionRepository;
    private final PlanificationInterventionRepository planificationInterventionRepository;
    @Override
    public ResponseEntity<Object> ajoutRealiInterv(RealisationIntervention realisationInterv, int idPlaniInterv) {
        PlanificationIntervention planificationIntervention = planificationInterventionRepository.findById(idPlaniInterv).orElse(null);

        if (planificationIntervention != null) {
            realisationInterv.setPlanificationInterventionR(planificationIntervention);
            realisationInterventionRepository.save(realisationInterv);
        }
        return ResponseEntity.ok(planificationIntervention);
    }

    @Override
    public ResponseEntity<Object> modifierRealiInterv(RealisationIntervention realisationIntervention) {
        realisationInterventionRepository.save(realisationIntervention);
        return ResponseEntity.ok(realisationIntervention);
    }

    @Override
    public ResponseEntity<Object> supprimerRealiInter(int id) {
        realisationInterventionRepository.deleteById(id);
        return ResponseEntity.ok("Realisation Intervention supprime");
    }
}
