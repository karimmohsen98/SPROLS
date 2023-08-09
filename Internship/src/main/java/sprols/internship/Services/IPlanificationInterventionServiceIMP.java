package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.PlanificationIntervention;
import sprols.internship.Repositories.DemandeInterventionRepository;
import sprols.internship.Repositories.PlanificationInterventionRepository;

@Service
@AllArgsConstructor
public class IPlanificationInterventionServiceIMP implements PlanificationInterventionService{

    private final DemandeInterventionRepository demandeInterventionRepository;
    private final PlanificationInterventionRepository planificationInterventionRepository;


    @Override
    public ResponseEntity<Object> ajoutPlaniInterv(PlanificationIntervention planificationIntervention, int idDemandeInterv) {
        DemandeIntervention demandeIntervention = demandeInterventionRepository.findById(idDemandeInterv).orElse(null);

        if (demandeIntervention == null) {
            return ResponseEntity.badRequest().body("Demande Intervention pas trouve");
        } else {
            planificationIntervention.setDemandeInterventionPlan(demandeIntervention);
            planificationInterventionRepository.save(planificationIntervention);
        }
        return ResponseEntity.ok(planificationIntervention);

    }

    @Override
    public ResponseEntity<Object> modifierPlaniInterv(PlanificationIntervention planificationIntervention) {
        planificationInterventionRepository.save(planificationIntervention);
        return ResponseEntity.ok(planificationIntervention);

    }

    @Override
    public ResponseEntity<Object> supprimerPlaniInter(int id) {
        planificationInterventionRepository.deleteById(id);
        return ResponseEntity.ok("Planification Intervention supprime");
    }
}
