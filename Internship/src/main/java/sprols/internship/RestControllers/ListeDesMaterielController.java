package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sprols.internship.Entities.ListeDesMateriel;
import sprols.internship.Entities.Materiel;
import sprols.internship.Repositories.ListeDesMaterielRepository;
import sprols.internship.Repositories.MaterielRepository;
import sprols.internship.Services.ListeDesMaterielService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/listedesmateriel")
public class ListeDesMaterielController {

    private ListeDesMaterielService listeDesMaterielService;
    private ListeDesMaterielRepository listeDesMaterielRepository;
    private MaterielRepository materialRepository;

    @PostMapping("/addMaterialToListeDesMateriel")
    public ResponseEntity<String> addMaterielToListeDesMateriel(
            @RequestParam List<Integer> materialId,
            @RequestParam int boughtQuantity) {
//        ListeDesMateriel shoppingList = listeDesMaterielRepository.findById(shoppingListId)
//                .orElseThrow(null);

        // Retrieve the material by ID
        List<Materiel> material = materialRepository.findAllById(materialId);


        listeDesMaterielService.addMaterialToListeDesMateriel(material, boughtQuantity);
        return ResponseEntity.ok("Material added to shopping list successfully.");
    }
}
