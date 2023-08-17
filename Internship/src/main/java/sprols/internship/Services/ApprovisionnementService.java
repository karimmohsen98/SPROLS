package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Etat;

public interface ApprovisionnementService {

    ResponseEntity<Object> ajoutAppro(Approvisionnement approvisionnement, String numMatricule,int idMat);
    ResponseEntity<Object> modifierAppro(Approvisionnement approvisionnement);
    ResponseEntity<Object> modifierQuantiteLivre(int idApprovisionnement, int quantiteLivre);
    ResponseEntity<Object> modifierEtatAppro(int idApprovisionnement,Etat etat);
    void supprimerAppro(Integer idAppro);
}
