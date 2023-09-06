package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Services.IUtilisateurServiceIMP;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateur")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
    private final IUtilisateurServiceIMP iUtilisateurServiceIMP;

    @PostMapping("/ajouteraffecterutilisateur")
    public ResponseEntity<Object> ajouterAffecterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return iUtilisateurServiceIMP.ajoutUtilisateur(utilisateur);
    }

    @PatchMapping("/modifierutilisateur/{id}")
    public ResponseEntity<Object> modifierUtilisateur(@PathVariable int id,@RequestBody Utilisateur utilisateur) {
        return iUtilisateurServiceIMP.modifierUtilisateur(id,utilisateur);
    }

    @DeleteMapping("/supprimerutilisateur/{idUser}")
    public ResponseEntity<Object> supprimerUtilisateur(Integer idUser) {
        return iUtilisateurServiceIMP.supprimerUtilisateur(idUser);
    }

    @GetMapping("/rechercheruser/{id}")
    public Optional<Utilisateur> rechercherUser(@PathVariable int id) {
        return iUtilisateurServiceIMP.rechercherUser(id);
    }

    @GetMapping("/affichertoutusers")
    public List<Utilisateur> afficherToutUsers() {
        return iUtilisateurServiceIMP.afficherToutUsers();
    }

    @PatchMapping("/desactivercompte/{numMat}")
    void desactiverCompte(@PathVariable String numMat){
        iUtilisateurServiceIMP.desactiverCompte(numMat);

    }
    @PutMapping("/activercompte/{numMat}")
    void activeCompte(String numMat){
        iUtilisateurServiceIMP.activeCompte(numMat);

    }
    @PostMapping("/activerdesactivercompte/{numMat}")
    public void enabledisableCompte(@PathVariable String numMat,Utilisateur user){
        iUtilisateurServiceIMP.enabledisableCompte(numMat,user);
    }

    @GetMapping("/findusersoldeconge/{numMat}")
    public double findUserSoldeConge(@PathVariable String numMat){
        return iUtilisateurServiceIMP.findUserSoldeConge(numMat);
    }


}

