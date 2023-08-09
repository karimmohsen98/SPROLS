package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;

public interface DemandeInterventionService {

    ResponseEntity<Object> ajoutDemandeInterv(DemandeIntervention demandeIntervention, String numMatricule);
    ResponseEntity<Object> modifierDemandeInterv(DemandeIntervention demandeIntervention);
    ResponseEntity<Object> modifierEtatDemandeInter(int id, Etat etat);
    ResponseEntity<Object> supprimerDemandeInter(int id);
}
