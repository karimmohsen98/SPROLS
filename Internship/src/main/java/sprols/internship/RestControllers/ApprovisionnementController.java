package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Materiel;
import sprols.internship.Services.IApprovisionementServiceIMP;

import java.util.List;

@RestController
@RequestMapping("/api/approvisionnement")
@AllArgsConstructor
public class ApprovisionnementController {
    private final IApprovisionementServiceIMP iApprovisionementServiceIMP;

    @PostMapping("/ajouterappro/{numMatricule}/{idMat}")
    public ResponseEntity<Object> ajouterApp(@RequestBody Approvisionnement approvisionnement
            , @PathVariable String numMatricule
            , @PathVariable List<Materiel> idMat) {
        return iApprovisionementServiceIMP.ajoutAppro(approvisionnement, numMatricule, idMat);
    }

    @PutMapping("/modifierappro/{idApprovisionnement}")
    public ResponseEntity<Object> modifierQuantiteLivre(@PathVariable int idApprovisionnement, int quantiteLivre) {
        return iApprovisionementServiceIMP.modifierQuantiteLivre(idApprovisionnement, quantiteLivre);
    }

    @PatchMapping("/modifieretatappro/{idApp}")
    public ResponseEntity<Object> modifierAppStatus(@PathVariable int idApp, @RequestParam Etat etat) {

        try {
            ResponseEntity<Object> modifieretat = iApprovisionementServiceIMP.modifierEtatAppro(idApp, etat);
            if (modifieretat != null) {
                return ResponseEntity.ok(modifieretat);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/modifierappro")
    ResponseEntity<Object> modifierAppro(Approvisionnement approvisionnement){
        return iApprovisionementServiceIMP.modifierAppro(approvisionnement);
    }

    @DeleteMapping("/supprimerappro/{idAppro}")
    public void supprimerAppro(@PathVariable Integer idAppro) {
        iApprovisionementServiceIMP.supprimerAppro(idAppro);
    }
}
