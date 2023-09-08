package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.RealisationIntervention;
import sprols.internship.Services.IRealisationInterventionServiceIMP;

import java.util.List;

@RestController
@RequestMapping("/api/realisationintervention")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class RealisationInterventionController {

    private final IRealisationInterventionServiceIMP iRealisationInterventionServiceIMP;

    @PostMapping("/ajouterrealisationintervention/{idPlaniInterv}/{matriculeIntervenant}")
    public ResponseEntity<Object> ajoutRealiInterv(@RequestBody RealisationIntervention realisationInterv
            ,@PathVariable int idPlaniInterv
            ,@PathVariable String matriculeIntervenant) {
        return iRealisationInterventionServiceIMP.ajoutRealiInterv(realisationInterv, idPlaniInterv,matriculeIntervenant);

    }
    @PutMapping("/modifierrealisationintervention/{idRealisation}")
    public ResponseEntity<Object> modifierRealiInterv(@PathVariable int idRealisation,@RequestBody RealisationIntervention realisationIntervention) {
        return iRealisationInterventionServiceIMP.modifierRealiInterv(idRealisation,realisationIntervention);
    }

    @DeleteMapping("/supprimerrealisationintervention/{id}")
    public ResponseEntity<Object> supprimerRealiInter(@PathVariable int id) {
        return iRealisationInterventionServiceIMP.supprimerRealiInter(id);
    }

    @GetMapping("/affichertoutrealisation")
    public List<RealisationIntervention> getAllRealisationIntervention(){
        return iRealisationInterventionServiceIMP.getAllRealisationIntervention();
    }
    @GetMapping("/affichertoutrealisationparmatricule/{matricule}")
    public List<RealisationIntervention> getAllRealisationInterventionByMatricule(@PathVariable String matricule){
        return iRealisationInterventionServiceIMP.getAllRealisationInterventionByMatricule(matricule);
    }

    @GetMapping("/afficherrealisationintervention/{id}")
    public RealisationIntervention getRealisationInterventionById(@PathVariable int id){
        return iRealisationInterventionServiceIMP.getRealisationInterventionById(id);
    }
}
