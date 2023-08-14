package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Materiel;
import sprols.internship.Repositories.MaterielRepository;

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
    public ResponseEntity<Object> modifierMat(Materiel materiel) {
        materielRepository.save(materiel);
        return ResponseEntity.ok(materiel);
    }

    @Override
    public void supprimerMat(Integer idMateriel) {
        materielRepository.deleteById(idMateriel);
    }
}
