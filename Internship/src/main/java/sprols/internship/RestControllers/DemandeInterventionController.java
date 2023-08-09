package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Repositories.DemandeInterventionRepository;
import sprols.internship.Services.IDemandeInterventionServiceIMP;
import sprols.internship.Utils.ModifierEtatGeneric;

@RestController
@RequestMapping("/api/demandeintervention")
@AllArgsConstructor
public class DemandeInterventionController {

    private final IDemandeInterventionServiceIMP iDemandeInterventionServiceIMP;
    private final ModifierEtatGeneric modifierEtatGeneric;
    private final DemandeInterventionRepository demandeInterventionRepository;

    @PostMapping("/ajouterdemande/{numMatricule}")
    public ResponseEntity<Object> ajoutDemandeInterv(@RequestBody DemandeIntervention demandeIntervention, @PathVariable String numMatricule) {
        return iDemandeInterventionServiceIMP.ajoutDemandeInterv(demandeIntervention, numMatricule);
    }

    @PutMapping("/modifieretatdemande/{id}")
    public ResponseEntity<Object> modifierEtatAppro(@PathVariable int id, @RequestParam Etat etat) {
        return iDemandeInterventionServiceIMP.modifierEtatDemandeInter(id, etat);
    }

    @PutMapping("/modifierdemande")
    public ResponseEntity<Object> modifierDemandeInterv(@RequestBody DemandeIntervention demandeIntervention) {
        return iDemandeInterventionServiceIMP.modifierDemandeInterv(demandeIntervention);

    }
}
