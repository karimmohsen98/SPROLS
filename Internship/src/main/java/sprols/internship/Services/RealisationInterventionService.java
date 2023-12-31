package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.RealisationIntervention;

public interface RealisationInterventionService {
    ResponseEntity<Object> ajoutRealiInterv(RealisationIntervention realisationIntervention, int idPlaniInterv,String matriculeIntervenant);
    ResponseEntity<Object> modifierRealiInterv(int idRealisation,RealisationIntervention realisationIntervention);
    ResponseEntity<Object> supprimerRealiInter(int id);
}
