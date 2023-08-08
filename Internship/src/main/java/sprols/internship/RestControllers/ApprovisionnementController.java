package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Role;
import sprols.internship.Services.ApprovisionnementService;
import sprols.internship.Services.IApprovisionementServiceIMP;

@RestController
@RequestMapping("/api/approvisionnement")
@AllArgsConstructor
public class ApprovisionnementController {
    private final IApprovisionementServiceIMP iApprovisionementServiceIMP;

    @PostMapping("/ajouterappro/{numMatricule}")
    public ResponseEntity<Object> ajouterApp(@RequestBody Approvisionnement approvisionnement, @PathVariable String numMatricule) {
        return iApprovisionementServiceIMP.ajoutAppro(approvisionnement, numMatricule);
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
}
