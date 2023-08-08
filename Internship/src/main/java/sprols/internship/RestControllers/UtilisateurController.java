package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Services.IUtilisateurServiceIMP;

@RestController
@RequestMapping("/api/utilisateur")
@AllArgsConstructor
public class UtilisateurController {
    private final IUtilisateurServiceIMP iUtilisateurServiceIMP;

    @PostMapping("/ajouteraffecterutilisateur/{idRole}")
    public ResponseEntity<Object> ajouterAffecterUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable int idRole) {
        return iUtilisateurServiceIMP.ajoutUtilisateur(utilisateur, idRole);
    }
}
