package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sprols.internship.Entities.Conge;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.CongeRepository;
import sprols.internship.Repositories.UtilisateurRepository;

@Service
@AllArgsConstructor
public class ICongeServiceIMP implements CongeService{

    private final CongeRepository congeRepository;
    private final UtilisateurRepository utilisateurRepository;;

    @Override
    public ResponseEntity<Object> ajoutConge(Conge conge, String numMatriculeD, String numMatriculeR) {
        Utilisateur UserD = utilisateurRepository.findByNumMatricule(numMatriculeD);
        Utilisateur UserR = utilisateurRepository.findByNumMatricule(numMatriculeR);

        Assert.notNull(conge, "remplire conge");
        if (UserD!=null && UserR!=null){
            conge.setUtilisateurConge(UserD);
            conge.setEtatConge(Etat.ENATTENTE);
            conge.setNumMatriculeD(UserD.getNumMatricule());
            conge.setNumMatriculeR(UserR.getNumMatricule());
            congeRepository.save(conge);
        }
        return ResponseEntity.ok(conge);
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
