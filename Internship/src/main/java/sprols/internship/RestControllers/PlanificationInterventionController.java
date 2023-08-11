package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.PlanificationIntervention;
import sprols.internship.Services.IPlanificationInterventionServiceIMP;

@RestController
@RequestMapping("/api/planificationintervention")
@AllArgsConstructor
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
}
