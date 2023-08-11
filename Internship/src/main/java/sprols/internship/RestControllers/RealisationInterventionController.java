package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.RealisationIntervention;
import sprols.internship.Services.IRealisationInterventionServiceIMP;

@RestController
@RequestMapping("/api/realisationintervention")
@AllArgsConstructor
public class RealisationInterventionController {

    private final IRealisationInterventionServiceIMP iRealisationInterventionServiceIMP;

    @PostMapping("/ajouterrealisationintervention/{idPlaniInterv}/{matriculeIntervenant}")
    public ResponseEntity<Object> ajoutRealiInterv(@RequestBody RealisationIntervention realisationInterv
            ,@PathVariable int idPlaniInterv
            ,@PathVariable String matriculeIntervenant) {
        return iRealisationInterventionServiceIMP.ajoutRealiInterv(realisationInterv, idPlaniInterv,matriculeIntervenant);

    }
    @PutMapping("/modifierrealisationintervention")
    public ResponseEntity<Object> modifierRealiInterv(@RequestBody RealisationIntervention realisationIntervention) {
        return iRealisationInterventionServiceIMP.modifierRealiInterv(realisationIntervention);
    }

    @DeleteMapping("/supprimerrealisationintervention/{id}")
    public ResponseEntity<Object> supprimerRealiInter(@PathVariable int id) {
        return iRealisationInterventionServiceIMP.supprimerRealiInter(id);
    }
}
