package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur,int idRole);
    ResponseEntity<Object> modifierUtilisateur(Utilisateur utilisateur);
    ResponseEntity<Object> supprimerUtilisateur(Integer idRole);

    ResponseEntity<Utilisateur> rechercherUser(String numMat);
    ResponseEntity<List<Utilisateur>> afficherToutUsers();
}
