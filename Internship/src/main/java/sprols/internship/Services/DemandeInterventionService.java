package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;

import java.util.List;

public interface DemandeInterventionService {

    ResponseEntity<Object> ajoutDemandeInterv(DemandeIntervention demandeIntervention, String numMatricule);
    ResponseEntity<Object> modifierDemandeInterv(DemandeIntervention demandeIntervention);
    ResponseEntity<Object> modifierEtatDemandeInter(int id,DemandeIntervention demandeIntervention);
    ResponseEntity<Object> supprimerDemandeInter(int id);
    List<DemandeIntervention> afficherDemandeInterv();
    List<DemandeIntervention> afficherDemandeIntervByNumMatricule(String numMatricule);
}
