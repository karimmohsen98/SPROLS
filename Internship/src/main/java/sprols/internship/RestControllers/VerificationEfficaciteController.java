package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.VerificationEfficacite;
import sprols.internship.Services.IVerificationEfficaciteServiceIMP;

import java.util.List;

@RestController
@RequestMapping("/api/verificationefficacite")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class VerificationEfficaciteController {

    private final IVerificationEfficaciteServiceIMP verificationEfficaciteServiceIMP;

    @PostMapping("/ajoutverifeffi/{idRealisationIntervention}/{numMatriculeD}")
    public ResponseEntity<Object> ajoutVerifEffi(@RequestBody VerificationEfficacite verificationEfficacite
            , @PathVariable String numMatriculeD
            , @PathVariable int idRealisationIntervention) {

        return verificationEfficaciteServiceIMP.ajoutVerifEffi(verificationEfficacite, numMatriculeD, idRealisationIntervention);
    }

    @PutMapping("/modifierverifeffi/{idVerif}")
    public ResponseEntity<Object> modifierverification(@PathVariable int idVerif,@RequestBody VerificationEfficacite verificationEfficacite) {
        return verificationEfficaciteServiceIMP.modifierverification(idVerif, verificationEfficacite);
    }

        @GetMapping("/affichertoutverifeffi")
    public List<VerificationEfficacite> getAllVerifEfficacite() {
        return verificationEfficaciteServiceIMP.getAllVerifEfficacite();
    }

    @GetMapping("/afficherVerifDemandeur/{numMatriculeDemandeur}")
    public List<VerificationEfficacite> findRealisationParId(@PathVariable String numMatriculeDemandeur) {
        return verificationEfficaciteServiceIMP.findRealisationParId(numMatriculeDemandeur);
    }

    @GetMapping("/afficherVerif/{idVerif}")
    public VerificationEfficacite findVerificationParId(@PathVariable int idVerif) {
        return verificationEfficaciteServiceIMP.findVerificationParId(idVerif);
    }
}
