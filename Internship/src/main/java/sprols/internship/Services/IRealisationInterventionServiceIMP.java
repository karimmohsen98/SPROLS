package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.PlanificationIntervention;
import sprols.internship.Entities.RealisationIntervention;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.PlanificationInterventionRepository;
import sprols.internship.Repositories.RealisationInterventionRepository;
import sprols.internship.Repositories.UtilisateurRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IRealisationInterventionServiceIMP implements RealisationInterventionService{

    private final RealisationInterventionRepository realisationInterventionRepository;
    private final PlanificationInterventionRepository planificationInterventionRepository;
    private final UtilisateurRepository utilisateurRepository;
    @Override
    public ResponseEntity<Object> ajoutRealiInterv(RealisationIntervention realisationInterv, int idPlaniInterv,String matriculeIntervenant){
        PlanificationIntervention planificationIntervention = planificationInterventionRepository.findById(idPlaniInterv).orElse(null);
        Utilisateur user = utilisateurRepository.findByNumMatricule(matriculeIntervenant);

        if (planificationIntervention != null && user != null) {
            realisationInterv.setNumMatriculeIntervenant(matriculeIntervenant);
            realisationInterv.setPlanificationInterventionR(planificationIntervention);
            realisationInterventionRepository.save(realisationInterv);
        }
        return ResponseEntity.ok(planificationIntervention);
    }

    @Override
    public ResponseEntity<Object> modifierRealiInterv(int idRealisation,RealisationIntervention realisationIntervention) {
       RealisationIntervention  existingRealisation = realisationInterventionRepository.findById(idRealisation).orElse(null);
       if (existingRealisation!=null){
           existingRealisation.setNumMatriculeIntervenant(realisationIntervention.getNumMatriculeIntervenant());
           existingRealisation.setPlanificationInterventionR(realisationIntervention.getPlanificationInterventionR());
           existingRealisation.setDateRealisationIntervention(realisationIntervention.getDateRealisationIntervention());
           existingRealisation.setFrequence(realisationIntervention.getFrequence());
           existingRealisation.setOrigine(realisationIntervention.getOrigine());
           existingRealisation.setDescriptionIntervention(realisationIntervention.getDescriptionIntervention());
           existingRealisation.setDescriptionConstatDiagnostic(realisationIntervention.getDescriptionConstatDiagnostic());
           existingRealisation.setTypeDefaut(realisationIntervention.getTypeDefaut());
           existingRealisation.setTypeInternvetion(realisationIntervention.getTypeInternvetion());
           existingRealisation.setDateConstatDiagnostic(realisationIntervention.getDateConstatDiagnostic());

           realisationInterventionRepository.save(existingRealisation);
       }
        return ResponseEntity.ok(existingRealisation);
    }

    @Override
    public ResponseEntity<Object> supprimerRealiInter(int id) {
        realisationInterventionRepository.deleteById(id);
        return ResponseEntity.ok("Realisation Intervention supprime");
    }
    public List<RealisationIntervention> getAllRealisationIntervention(){
        return realisationInterventionRepository.findAll();
    }
    public List<RealisationIntervention> getAllRealisationInterventionByMatricule(String matricule){
        return realisationInterventionRepository.findAllByNumMatriculeIntervenant(matricule);
    }

    public RealisationIntervention getRealisationInterventionById(int id){
        return realisationInterventionRepository.findById(id).orElse(null);
    }
}
