package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Role;

public interface ApprovisionnementService {

    ResponseEntity<Object> ajoutAppro(Approvisionnement approvisionnement, String numMatricule);
//    ResponseEntity<Object> modifierAppro(Approvisionnement approvisionnement, int numMatricule);
//    void supprimerAppro(Integer idRole);
}
