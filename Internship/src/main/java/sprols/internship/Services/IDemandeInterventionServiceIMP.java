package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.DemandeInterventionRepository;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Utils.ModifierEtatGeneric;

import java.util.function.Consumer;

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
    public ResponseEntity<Object> modifierEtatDemandeInter(int id,Etat etat) {
        return modifierEtatGeneric.modifyEtat(
                id,
                demandeInterventionRepository,
                etat,
                demandeintervention->demandeintervention.setEtatDemandeIntervention(etat),
                "Demande n'existe pas."
        );
    }
    @Override
    public ResponseEntity<Object> supprimerDemandeInter(int id){
        demandeInterventionRepository.deleteById(id);
        return ResponseEntity.ok("Demande supprimé");
    }


}

