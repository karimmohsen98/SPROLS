package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sprols.internship.Entities.Approvisionnement;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.ApprovisionnementRepository;
import sprols.internship.Repositories.UtilisateurRepository;

@Service
@AllArgsConstructor
public class IApprovisionementServiceIMP implements ApprovisionnementService{
    UtilisateurRepository utilisateurRepository;
    ApprovisionnementRepository approvisionnementRepository;

    @Override
    public ResponseEntity<Object> ajoutAppro(Approvisionnement approvisionnement, String numMatricule) {

        Utilisateur User = utilisateurRepository.findByNumMatricule(numMatricule);
        //Assert.notNull(approvisionnement, "remplire approvionnement");
        approvisionnement.setNumMatriculeD(numMatricule);
        approvisionnement.setEtatApprovisionnement(Etat.ENATTENTE);
        approvisionnement.setUtilisateurAppro(User);
        approvisionnementRepository.save(approvisionnement);

        return ResponseEntity.ok(approvisionnement);

    }
}
