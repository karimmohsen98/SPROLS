package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Materiel;

public interface MaterielService {

    ResponseEntity<Object> ajoutMat(Materiel materiel);
    ResponseEntity<Object> modifierMat(Materiel materiel);
    void supprimerMat(Integer idMateriel);
}
