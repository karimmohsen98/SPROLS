package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Materiel;

import java.util.List;

public interface MaterielService {

    ResponseEntity<Object> ajoutMat(Materiel materiel);
    ResponseEntity<Object> modifierMat(Integer id,Materiel materiel);
    void supprimerMat(Integer idMateriel);
    List<Materiel> afficherToutMat();
}
