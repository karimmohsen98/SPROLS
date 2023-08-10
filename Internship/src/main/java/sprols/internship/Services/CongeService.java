package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Conge;
import sprols.internship.Entities.Etat;

public interface CongeService {
    ResponseEntity<Object> ajoutConge(Conge conge, String numMatriculeD, String numMatriculeR);
    ResponseEntity<Object> modifierConge(Conge conge);
    ResponseEntity<Object> modifierConge(int idConge);
    ResponseEntity<Object> modifierEtatConge(int idConge, Etat etat);
    void supprimerCOnge(Integer idConge);
}
