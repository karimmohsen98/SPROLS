package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur);
    ResponseEntity<Object> modifierUtilisateur(Integer id,Utilisateur utilisateur);
    ResponseEntity<Object> supprimerUtilisateur(Integer idUser);
    Optional<Utilisateur> rechercherUser(int id);
    List<Utilisateur> afficherToutUsers();
    void desactiverCompte(String numMat);
    void activeCompte(String numMat);



    }
