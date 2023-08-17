package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Materiel;
import sprols.internship.Services.IMaterielServiceIMP;
import sprols.internship.Services.MaterielService;

@RestController
@RequestMapping("/api/materiel")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class MaterielController {

    private final IMaterielServiceIMP imaterielService;

    @PostMapping("/ajoutmat")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> ajoutMat(@RequestBody Materiel materiel){
        return imaterielService.ajoutMat(materiel);
    }
    @PutMapping("/modifiermat")
    ResponseEntity<Object> modifierMat(@RequestBody Materiel materiel){
        return imaterielService.modifierMat(materiel);
    }
    @DeleteMapping("/supprimermat/{idMat}")
    void supprimerMat(@PathVariable Integer idMat){
        imaterielService.supprimerMat(idMat);
    }
}
