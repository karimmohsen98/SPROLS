package sprols.internship.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Conge;
import sprols.internship.Entities.Etat;
import sprols.internship.Services.ICongeServiceIMP;

import java.util.List;

@RestController
@RequestMapping("/api/conge")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class CongeController {
    private final ICongeServiceIMP iCongeServiceIMP;

    @PostMapping("/ajouterconge/{numMatriculeD}/{numMatriculeR}")
    public ResponseEntity<Object> ajoutConge(@RequestBody Conge conge,
                                             @PathVariable String numMatriculeD,
                                             @PathVariable String numMatriculeR) {
        return iCongeServiceIMP.ajoutConge(conge, numMatriculeD, numMatriculeR);
    }
    @PatchMapping("/modifieretatconge/{idConge}")
    public ResponseEntity<Object> modifierEtatConge(@PathVariable int idConge,@RequestBody Conge conge){
        return iCongeServiceIMP.modifierEtatConge(idConge, conge);
    }

    @PutMapping("/modifierconge")
    public ResponseEntity<Object> modifierConge(@RequestBody Conge conge) {
        return iCongeServiceIMP.modifierConge(conge);
    }

    @DeleteMapping("/supprimerconge/{idConge}")
    public void supprimerCOnge(@PathVariable Integer idConge) {
        iCongeServiceIMP.supprimerCOnge(idConge);
    }

    @GetMapping("/afficherlistecongeuser/{numMatD}")
    public List<Conge> afficherlisteconge(@PathVariable String numMatD){
        return iCongeServiceIMP.afficherlisteconge(numMatD);
    }

    @GetMapping("/afficherlisteconge")
    public List<Conge> afficherlisteconge(){
        return iCongeServiceIMP.afficherCongeToututilisateur();
    }

    @GetMapping("/affichercongeparid/{idConge}")
    public Conge afficherCongeParId(@PathVariable int idConge){
        return iCongeServiceIMP.afficherCongeParId(idConge);
    } }
