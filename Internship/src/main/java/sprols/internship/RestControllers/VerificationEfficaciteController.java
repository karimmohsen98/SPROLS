package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.VerificationEfficacite;
import sprols.internship.Services.IVerificationEfficaciteServiceIMP;

@RestController
@RequestMapping("/api/verificationefficacite")
@AllArgsConstructor
public class VerificationEfficaciteController {

    private final IVerificationEfficaciteServiceIMP verificationEfficaciteServiceIMP;

    @PostMapping("/ajoutverifeffi/{numMatriculeD}/{numMatriculeChefInfo}/{idRealisationIntervention}")
    public ResponseEntity<Object> ajoutVerifEffi(@RequestBody VerificationEfficacite verificationEfficacite
            ,@PathVariable String numMatriculeD
            ,@PathVariable String numMatriculeChefInfo
            ,@PathVariable int idRealisationIntervention) {

        return verificationEfficaciteServiceIMP.ajoutVerifEffi(verificationEfficacite, numMatriculeD, numMatriculeChefInfo, idRealisationIntervention);

    }

}
