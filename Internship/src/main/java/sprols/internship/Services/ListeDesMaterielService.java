package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.ListeDesMateriel;
import sprols.internship.Entities.ListeMaterielItem;
import sprols.internship.Entities.Materiel;
import sprols.internship.Repositories.ListeDesMaterielRepository;
import sprols.internship.Repositories.ListeMaterielItemRepository;
import sprols.internship.Repositories.MaterielRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ListeDesMaterielService {

    private ListeDesMaterielRepository listeDesMaterielRepository;
    private ListeMaterielItemRepository listeMaterielItemRepository;
    private MaterielRepository materielRepository;

    public void addMaterialToListeDesMateriel(List<Materiel> idMat, int boughtQuantity) {
        //Materiel material = materielRepository.findById(idMat).orElse(null);
        ListeDesMateriel LMD = new ListeDesMateriel();
        listeDesMaterielRepository.save(LMD);
        List<ListeMaterielItem> listeMats = new ArrayList<>();
        for (Materiel idMateriel : idMat) {
            ListeMaterielItem listeDesMaterieux = new ListeMaterielItem();

            listeDesMaterieux.setListeDesMateriel(idMateriel);
            listeDesMaterieux.setQuantiteDemande(boughtQuantity);

            listeDesMaterieux.setListeMats(LMD);
            listeMats.add(listeDesMaterieux);
        }
        listeMaterielItemRepository.saveAll(listeMats);
        //listeDesMaterielRepository.save(LMD);

    }
}

