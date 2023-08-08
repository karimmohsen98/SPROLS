package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Role;
import sprols.internship.Services.ApprovisionnementService;
import sprols.internship.Services.IApprovisionementServiceIMP;

@RestController
@RequestMapping("/api/approvisionnement")
@AllArgsConstructor
public class ApprovisionnementController {
    private final IApprovisionementServiceIMP iApprovisionementServiceIMP;

    @PostMapping("/ajouterappro/{numMatricule}")
    public ResponseEntity<Object> ajouterApp(@RequestBody Approvisionnement approvisionnement,@PathVariable String numMatricule){
        return iApprovisionementServiceIMP.ajoutAppro(approvisionnement, numMatricule);
    }

    @PutMapping("/modifierappro/{idApprovisionnement}")
    public ResponseEntity<Object> modifierQuantiteLivre(@PathVariable int idApprovisionnement, int quantiteLivre){
        return iApprovisionementServiceIMP.modifierQuantiteLivre(idApprovisionnement, quantiteLivre);
    }
}
