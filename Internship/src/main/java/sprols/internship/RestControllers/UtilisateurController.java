package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Services.IUtilisateurServiceIMP;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
@AllArgsConstructor
public class UtilisateurController {
    private final IUtilisateurServiceIMP iUtilisateurServiceIMP;

    @PostMapping("/ajouteraffecterutilisateur/{idRole}")
    public ResponseEntity<Object> ajouterAffecterUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable int idRole) {
        return iUtilisateurServiceIMP.ajoutUtilisateur(utilisateur, idRole);
    }

    @PutMapping("/modifierutilisateur")
    public ResponseEntity<Object> modifierUtilisateur(@RequestBody Utilisateur utilisateur){
        return iUtilisateurServiceIMP.modifierUtilisateur(utilisateur);
    }

    @DeleteMapping("/supprimerutilisateur/{idUser}")
    public ResponseEntity<Object> supprimerUtilisateur(Integer idUser){
        return iUtilisateurServiceIMP.supprimerUtilisateur(idUser);
    }
    @GetMapping("/rechercheruser/{numMat}")
    public ResponseEntity<Utilisateur> rechercherUser(@PathVariable String numMat){
        return iUtilisateurServiceIMP.rechercherUser(numMat);
    }
    @GetMapping("/affichertoutusers")
    public ResponseEntity<List<Utilisateur>> afficherToutUsers(){
        return iUtilisateurServiceIMP.afficherToutUsers();
    }
}
