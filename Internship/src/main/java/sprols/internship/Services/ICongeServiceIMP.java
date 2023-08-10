package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Conge;
import sprols.internship.Entities.Etat;

@Service
@AllArgsConstructor
public class ICongeServiceIMP implements CongeService{
    
    @Override
    public ResponseEntity<Object> ajoutConge(Conge conge, String numMatricule) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifierConge(Conge conge) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifierConge(int idConge) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifierEtatConge(int idConge, Etat etat) {
        return null;
    }

    @Override
    public void supprimerCOnge(Integer idConge) {

    }
}
