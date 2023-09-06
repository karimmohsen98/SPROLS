package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.PlanificationIntervention;
import sprols.internship.Services.IPlanificationInterventionServiceIMP;

import java.util.List;

@RestController
@RequestMapping("/api/planificationintervention")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class PlanificationInterventionController {

    private final IPlanificationInterventionServiceIMP iPlanificationInterventionServiceIMP;

    @PostMapping("/ajouterplanificationintervention/{idDemandeInterv}/{matriculeIntervenant}")
    public ResponseEntity<Object> ajoutPlaniInterv(@RequestBody PlanificationIntervention planificationIntervention
            , @PathVariable int idDemandeInterv
            , @PathVariable String matriculeIntervenant) {
        return iPlanificationInterventionServiceIMP.ajoutPlaniInterv(planificationIntervention, idDemandeInterv,matriculeIntervenant);
    }

    @PutMapping("/modifierplanificationintervention")
    public ResponseEntity<Object> modifierPlaniInterv(@RequestBody PlanificationIntervention planificationIntervention) {
        return iPlanificationInterventionServiceIMP.modifierPlaniInterv(planificationIntervention);

    }
    @DeleteMapping("/supprimerplanificationintervention/{id}")
    public ResponseEntity<Object> supprimerPlaniInter(@PathVariable int id) {
        return iPlanificationInterventionServiceIMP.supprimerPlaniInter(id);
    }
    @GetMapping("/affichertoutplanification")
    public List<PlanificationIntervention> displayAllPlanifications(){
        return iPlanificationInterventionServiceIMP.displayAllPlanifications();
    }
    @GetMapping("/afficherplanificationinterventionliste/{numMat}")
    public List<PlanificationIntervention> displayAllByMatricule(@PathVariable String numMat){
        return iPlanificationInterventionServiceIMP.findBymatricule(numMat);
    }
}
