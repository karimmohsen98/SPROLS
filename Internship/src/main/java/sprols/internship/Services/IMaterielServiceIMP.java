package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Materiel;
import sprols.internship.Repositories.MaterielRepository;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class IMaterielServiceIMP implements MaterielService{
    private final MaterielRepository materielRepository;
    @Override
    public ResponseEntity<Object> ajoutMat(Materiel materiel) {
         materielRepository.save(materiel);
            return ResponseEntity.ok(materiel);
    }

    @Override
    public ResponseEntity<Object> modifierMat(Integer id,Materiel materiel) {
        Materiel materielToUpdate  = materielRepository.findById(id).orElse(null);
        if (materielToUpdate != null) {
            materielToUpdate.setNomMateriel(materiel.getNomMateriel());
            }
        materielRepository.save(materielToUpdate);
        return ResponseEntity.ok(materiel);
    }

    @Override
    public void supprimerMat(Integer idMateriel) {
        materielRepository.deleteById(idMateriel);
    }

    public List<Materiel> afficherToutMat(){
        return materielRepository.findAll();    }
}
