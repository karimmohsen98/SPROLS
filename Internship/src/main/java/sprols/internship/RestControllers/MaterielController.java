package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Materiel;
import sprols.internship.Services.IMaterielServiceIMP;
import sprols.internship.Services.MaterielService;

import java.util.List;

@RestController
@RequestMapping("/api/materiel")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ADMIN')")
public class MaterielController {

    private final IMaterielServiceIMP imaterielService;


    @PostMapping("/ajoutmat")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> ajoutMat(@RequestBody Materiel materiel){
        return imaterielService.ajoutMat(materiel);
    }
    @PostMapping("/modifiermat/{id}")
    ResponseEntity<Object> modifierMat(@PathVariable Integer id,@RequestBody Materiel materiel){
        return new ResponseEntity<>(imaterielService.modifierMat(id,materiel),HttpStatus.OK);
    }
    @DeleteMapping("/supprimermat/{idMat}")
    void supprimerMat(@PathVariable Integer idMat){
        imaterielService.supprimerMat(idMat);
    }

    @GetMapping("/affichertoutmat")
    public List<Materiel> afficherToutMat(){
        return imaterielService.afficherToutMat();
    }

}
