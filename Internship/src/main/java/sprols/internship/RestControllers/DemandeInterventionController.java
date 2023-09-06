package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Repositories.DemandeInterventionRepository;
import sprols.internship.Services.IDemandeInterventionServiceIMP;
import sprols.internship.Utils.ModifierEtatGeneric;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandeintervention")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class DemandeInterventionController {

    private final IDemandeInterventionServiceIMP iDemandeInterventionServiceIMP;
    private final ModifierEtatGeneric modifierEtatGeneric;
    private final DemandeInterventionRepository demandeInterventionRepository;

    @PostMapping("/ajouterdemande/{numMatricule}")
    public ResponseEntity<Object> ajoutDemandeInterv(@RequestBody DemandeIntervention demandeIntervention, @PathVariable String numMatricule) {
        return iDemandeInterventionServiceIMP.ajoutDemandeInterv(demandeIntervention, numMatricule);
    }

    @PatchMapping("/modifieretatdemande/{id}")
    public ResponseEntity<Object> modifierEtatAppro(@PathVariable int id, @RequestBody DemandeIntervention demandeIntervention) {
        return iDemandeInterventionServiceIMP.modifierEtatDemandeInter(id, demandeIntervention);
    }

    @PutMapping("/modifierdemande")
    public ResponseEntity<Object> modifierDemandeInterv(@RequestBody DemandeIntervention demandeIntervention) {
        return iDemandeInterventionServiceIMP.modifierDemandeInterv(demandeIntervention);

    }

    @GetMapping("/affichertoutdemandeintervention")
    public List<DemandeIntervention> afficherDemandeInterv() {
        return iDemandeInterventionServiceIMP.afficherDemandeInterv();
    }

    @GetMapping("/afficherDemandeIntervByNumMatricule/{numMatricule}")
    public List<DemandeIntervention> afficherDemandeIntervByNumMatricule(@PathVariable String numMatricule) {
        return iDemandeInterventionServiceIMP.afficherDemandeIntervByNumMatricule(numMatricule);
    }

    @GetMapping("/findByIdDemandeIntervention/{idDemande}")
    public Optional<DemandeIntervention> findByIdDemandeIntervention(@PathVariable int idDemande){
        return iDemandeInterventionServiceIMP.findByIdDemandeIntervention(idDemande);
    }
}
