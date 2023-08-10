package sprols.internship.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Conge;
import sprols.internship.Services.ICongeServiceIMP;

@RestController
@RequestMapping("/api/conge")
@AllArgsConstructor
public class CongeController {
    private final ICongeServiceIMP iCongeServiceIMP;


    @PostMapping("/ajouterconge/{numMatriculeD}/{numMatriculeR}")
    public ResponseEntity<Object> ajoutConge(@RequestBody Conge conge,
                                             @PathVariable String numMatriculeD,
                                             @PathVariable String numMatriculeR) {
        return iCongeServiceIMP.ajoutConge(conge, numMatriculeD, numMatriculeR);
    }
}
