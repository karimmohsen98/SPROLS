package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.RealisationIntervention;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Entities.VerificationEfficacite;
import sprols.internship.Repositories.RealisationInterventionRepository;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Repositories.VerificationEfficaciteRepository;

@Service
@AllArgsConstructor
public class IVerificationEfficaciteServiceIMP implements VerificationEfficaciteService{

    private final UtilisateurRepository utilisateurRepository;
    private final VerificationEfficaciteRepository verificationEfficaciteRepository;
    private final RealisationInterventionRepository realisationInterventionRepository;
    @Override
    public ResponseEntity<Object> ajoutVerifEffi(VerificationEfficacite verificationEfficacite
            , String numMatriculeD
            , String numMatriculeChefInfo
            , int idRealisationIntervention) {
        RealisationIntervention realisationIntervention = realisationInterventionRepository.findById(idRealisationIntervention).orElse(null);
        Utilisateur userD = utilisateurRepository.findByNumMatricule(numMatriculeD);
        Utilisateur userChef = utilisateurRepository.findByNumMatricule(numMatriculeChefInfo);

        if(userD!=null && userChef!=null && realisationIntervention!=null){

            verificationEfficacite.setRealisationInterventionV(realisationIntervention);
            verificationEfficacite.setNumMatriculeDemandeur(numMatriculeD);
            verificationEfficacite.setNumMatriculeChefInformatique(numMatriculeChefInfo);
            verificationEfficaciteRepository.save(verificationEfficacite);
            return ResponseEntity.ok(verificationEfficacite);

        }
         return ResponseEntity.badRequest().body("Demandeur ou chef informatique n'existe pas.");
    }
}
