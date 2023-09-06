package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.DemandeInterventionRepository;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Utils.ModifierEtatGeneric;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IDemandeInterventionServiceIMP implements DemandeInterventionService {

    private final DemandeInterventionRepository demandeInterventionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ModifierEtatGeneric modifierEtatGeneric;


    @Override
    public ResponseEntity<Object> ajoutDemandeInterv(DemandeIntervention demandeIntervention, String numMatricule) {

        Utilisateur user = utilisateurRepository.findByNumMatricule(numMatricule);
        Assert.notNull(demandeIntervention, "remplire la demande");
        if (user != null) {
            demandeIntervention.setUtilisateurDemandeInterv(user);
            demandeIntervention.setEtatDemandeIntervention(Etat.ENATTENTE);
            demandeIntervention.setNumMatriculeDemandeur(numMatricule);
        }
        demandeInterventionRepository.save(demandeIntervention);
        return ResponseEntity.ok(demandeIntervention);
    }

    @Override
    public ResponseEntity<Object> modifierDemandeInterv(DemandeIntervention demandeIntervention) {
        demandeInterventionRepository.save(demandeIntervention);
        return ResponseEntity.ok(demandeIntervention);
    }

    @Override
    public ResponseEntity<Object> modifierEtatDemandeInter(int id,DemandeIntervention demandeIntervention) {
        DemandeIntervention demandeToUpdate = demandeInterventionRepository.findById(id).orElse(null);
        if (demandeToUpdate != null) {
            demandeToUpdate.setEtatDemandeIntervention(demandeIntervention.getEtatDemandeIntervention());
            demandeToUpdate.setDateDemandeIntervention(demandeIntervention.getDateDemandeIntervention());
            demandeToUpdate.setNumMatriculeDemandeur(demandeIntervention.getNumMatriculeDemandeur());
            demandeToUpdate.setCodeMateriel(demandeIntervention.getCodeMateriel());
            demandeToUpdate.setConstructeur(demandeIntervention.getConstructeur());
            demandeToUpdate.setDescriptionPanne(demandeIntervention.getDescriptionPanne());
            demandeToUpdate.setModele(demandeIntervention.getModele());
            demandeToUpdate.setTypeMat(demandeIntervention.getTypeMat());
            demandeToUpdate.setTypeModel(demandeIntervention.getTypeModel());
            demandeToUpdate.setVersion(demandeIntervention.getVersion());
            demandeInterventionRepository.save(demandeToUpdate);
        }
        return ResponseEntity.ok(demandeToUpdate);
    }
    @Override
    public ResponseEntity<Object> supprimerDemandeInter(int id){
        demandeInterventionRepository.deleteById(id);
        return ResponseEntity.ok("Demande supprimé");
    }

    @Override
    public List<DemandeIntervention> afficherDemandeInterv(){
        return demandeInterventionRepository.findAll();
    }
    @Override
    public List<DemandeIntervention> afficherDemandeIntervByNumMatricule(String numMatricule){
        return demandeInterventionRepository.findByNumMatriculeDemandeur(numMatricule);
    }
    public Optional<DemandeIntervention> findByIdDemandeIntervention(int idDemande){
        return demandeInterventionRepository.findById(idDemande);
    }



}

