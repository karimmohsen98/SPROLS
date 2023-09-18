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

import java.util.List;

@Service
@AllArgsConstructor
public class IVerificationEfficaciteServiceIMP implements VerificationEfficaciteService{

    private final UtilisateurRepository utilisateurRepository;
    private final VerificationEfficaciteRepository verificationEfficaciteRepository;
    private final RealisationInterventionRepository realisationInterventionRepository;
    @Override
    public ResponseEntity<Object> ajoutVerifEffi(VerificationEfficacite verificationEfficacite
            , String numMatriculeD
            , int idRealisationIntervention) {
        RealisationIntervention realisationIntervention = realisationInterventionRepository.findById(idRealisationIntervention).orElse(null);
        Utilisateur userD = utilisateurRepository.findByNumMatricule(numMatriculeD);

        if(userD!=null && realisationIntervention!=null){

            verificationEfficacite.setRealisationInterventionV(realisationIntervention);
            verificationEfficacite.setNumMatriculeDemandeur(numMatriculeD);
            verificationEfficaciteRepository.save(verificationEfficacite);
            return ResponseEntity.ok(verificationEfficacite);

        }
         return ResponseEntity.badRequest().body("Demandeur ou chef informatique n'existe pas.");
    }

    public ResponseEntity<Object> modifierverification(int idVerif,VerificationEfficacite verificationEfficacite){
        VerificationEfficacite existingVerif = verificationEfficaciteRepository.findById(idVerif).orElse(null);
        if (existingVerif!=null){
            existingVerif.setAvisDemandeurVerification(verificationEfficacite.getAvisDemandeurVerification());
            existingVerif.setAvisChefInformatique(verificationEfficacite.getAvisChefInformatique());
            existingVerif.setNumMatriculeChefInformatique(verificationEfficacite.getNumMatriculeChefInformatique());
            existingVerif.setDateAvisChefInformatique(verificationEfficacite.getDateAvisChefInformatique());
            existingVerif.setDateAvisDemandeur(verificationEfficacite.getDateAvisDemandeur());
            existingVerif.setNumMatriculeDemandeur(verificationEfficacite.getNumMatriculeDemandeur());

            verificationEfficaciteRepository.save(existingVerif);
        }
        return ResponseEntity.ok(existingVerif);

    }

    public List<VerificationEfficacite> getAllVerifEfficacite(){
        return verificationEfficaciteRepository.findAll();
    }

    public List<VerificationEfficacite> findRealisationParId(String numMatriculeDemandeur){
        return verificationEfficaciteRepository.findAllByNumMatriculeDemandeur(numMatriculeDemandeur);
    }
    public VerificationEfficacite findVerificationParId(int idVerif){
        return verificationEfficaciteRepository.findById(idVerif).orElse(null);
    }}
