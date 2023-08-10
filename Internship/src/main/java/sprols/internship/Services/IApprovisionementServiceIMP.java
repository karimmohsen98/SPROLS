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
        Assert.notNull(approvisionnement, "remplire approvionnement");
        if (User!=null) {
            approvisionnement.setNumMatriculeD(numMatricule);
            approvisionnement.setEtatApprovisionnement(Etat.ENATTENTE);
            approvisionnement.setUtilisateurAppro(User);
        }
        approvisionnementRepository.save(approvisionnement);
        return ResponseEntity.ok(approvisionnement);

    }

    @Override
    public ResponseEntity<Object> modifierAppro(Approvisionnement approvisionnement) {
        approvisionnementRepository.save(approvisionnement);
        return ResponseEntity.ok(approvisionnement);
    }

    @Override
    public ResponseEntity<Object> modifierQuantiteLivre(int idApprovisionnement, int quantiteLivre) {
        Approvisionnement approvisionnement = approvisionnementRepository.findById(idApprovisionnement).orElse(null);
          if (approvisionnement!=null && approvisionnement.getEtatApprovisionnement().equals(Etat.ACCEPTER)){
                approvisionnement.setQuantiteLivre(quantiteLivre);
                approvisionnementRepository.save(approvisionnement);
            } else {
              return ResponseEntity.badRequest().body("approvisionnement n'existe pas ou n'est pas accepter");}
          return ResponseEntity.ok(approvisionnement);

    }

    @Override
    public ResponseEntity<Object> modifierEtatAppro(int idApprovisionnement,Etat etat) {
        Approvisionnement approvisionnement = approvisionnementRepository.findById(idApprovisionnement).orElse(null);
        if (approvisionnement != null) {
            if ("accepter".equalsIgnoreCase(etat.toString()) || "refuser".equalsIgnoreCase(etat.toString())) {
                approvisionnement.setEtatApprovisionnement(etat);
                approvisionnementRepository.save(approvisionnement);
            } else {
                throw new IllegalArgumentException("une erreur s'est produit");
            }
        }
        return ResponseEntity.ok(approvisionnement);

    }

    @Override
    public void supprimerAppro(Integer idAppro) {
        approvisionnementRepository.deleteById(idAppro);

    }
}
