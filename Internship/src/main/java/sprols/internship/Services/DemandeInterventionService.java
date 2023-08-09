package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.DemandeIntervention;

public interface DemandeInterventionService {

    ResponseEntity<Object> ajoutDemandeInterv(DemandeIntervention demandeIntervention, String numMatricule);
    ResponseEntity<Object> modifierDemandeInterv(DemandeIntervention demandeIntervention);
}
