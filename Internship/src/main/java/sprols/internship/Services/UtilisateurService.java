package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Utilisateur;

public interface UtilisateurService {

    ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur,int idRole);
    ResponseEntity<Object> modifierUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(Integer idRole);
}
