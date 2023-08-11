package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.PlanificationIntervention;

public interface PlanificationInterventionService {
    ResponseEntity<Object> ajoutPlaniInterv(PlanificationIntervention planificationIntervention,int idDemandeInterv,String matriculeIntervenant);
    ResponseEntity<Object> modifierPlaniInterv(PlanificationIntervention planificationIntervention);
    ResponseEntity<Object> supprimerPlaniInter(int id);
}
