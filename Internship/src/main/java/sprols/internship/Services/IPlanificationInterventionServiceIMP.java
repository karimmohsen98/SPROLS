package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.PlanificationIntervention;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.DemandeInterventionRepository;
import sprols.internship.Repositories.PlanificationInterventionRepository;
import sprols.internship.Repositories.UtilisateurRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IPlanificationInterventionServiceIMP implements PlanificationInterventionService{

    private final DemandeInterventionRepository demandeInterventionRepository;
    private final PlanificationInterventionRepository planificationInterventionRepository;
    private UtilisateurRepository utilisateurRepository;
    @Override
    public ResponseEntity<Object> ajoutPlaniInterv(PlanificationIntervention planificationIntervention
            ,int idDemandeInterv
            ,String matriculeIntervenant) {
        Utilisateur user = utilisateurRepository.findByNumMatricule(matriculeIntervenant);
        DemandeIntervention demandeIntervention = demandeInterventionRepository.findById(idDemandeInterv).orElse(null);

        if (user != null && demandeIntervention != null && (demandeIntervention.getEtatDemandeIntervention().equals(Etat.ACCEPTER))) {
            PlanificationIntervention existingPlanification = planificationInterventionRepository.findByDemandeInterventionPlan(demandeIntervention);
            if (existingPlanification == null) {
                planificationIntervention.setNumMatriculeIntervenant(matriculeIntervenant);
                planificationIntervention.setDemandeInterventionPlan(demandeIntervention);
                planificationInterventionRepository.save(planificationIntervention);
                return ResponseEntity.ok(planificationIntervention);
            } else {
                return ResponseEntity.badRequest().body("Demande Intervention n'existe pas ou n'est pas accepter");
            }
        }
        return ResponseEntity.badRequest().body("Intervenant n'existe pas");
    }
    @Override
    public ResponseEntity<Object> modifierPlaniInterv(int idPlani,PlanificationIntervention planificationIntervention) {

        PlanificationIntervention existingPlanification = planificationInterventionRepository.findById(idPlani).orElse(null);
        if (existingPlanification !=null) {
            existingPlanification.setNumMatriculeIntervenant(planificationIntervention.getNumMatriculeIntervenant());
            existingPlanification.setPhases(planificationIntervention.getPhases());
            existingPlanification.setDateIntervention(planificationIntervention.getDateIntervention());
            existingPlanification.setDemandeInterventionPlan(planificationIntervention.getDemandeInterventionPlan());

            planificationInterventionRepository.save(existingPlanification);


        }
        return ResponseEntity.ok(planificationIntervention);
    }
    @Override
    public ResponseEntity<Object> supprimerPlaniInter(int id) {
        planificationInterventionRepository.deleteById(id);
        return ResponseEntity.ok("Planification Intervention supprime");
    }

    public List<PlanificationIntervention> displayAllPlanifications(){
        return planificationInterventionRepository.findAll();
    }

    public List<PlanificationIntervention> findBymatricule(String numMat){
        return planificationInterventionRepository.findAllByNumMatriculeIntervenant(numMat);
    }
    public PlanificationIntervention findByIdPlanification(int idPlanification){
        return planificationInterventionRepository.findByIdPlanification(idPlanification);
    }
}
