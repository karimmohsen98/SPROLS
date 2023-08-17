package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur);
    ResponseEntity<Object> modifierUtilisateur(Utilisateur utilisateur);
    ResponseEntity<Object> supprimerUtilisateur(Integer idUser);
    ResponseEntity<Utilisateur> rechercherUser(String numMat);
    ResponseEntity<List<Utilisateur>> afficherToutUsers();
    void desactiverCompte(String numMat);
    void activeCompte(String numMat);



    }
