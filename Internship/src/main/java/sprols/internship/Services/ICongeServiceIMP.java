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
import sprols.internship.Utils.ModifierEtatGeneric;

import java.time.DayOfWeek;

@Service
@AllArgsConstructor
public class ICongeServiceIMP implements CongeService {

    private final CongeRepository congeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ModifierEtatGeneric modifierEtatGeneric;


    @Override
    public ResponseEntity<Object> ajoutConge(Conge conge, String numMatriculeD, String numMatriculeR) {
        Utilisateur userD = utilisateurRepository.findByNumMatricule(numMatriculeD);
        Utilisateur userR = utilisateurRepository.findByNumMatricule(numMatriculeR);

        Assert.notNull(conge, "remplire conge");
        if (conge.getDateFin().getDayOfWeek() == DayOfWeek.FRIDAY) {
            conge.setNombreJours(conge.getNombreJours() + 2);
        }
        if (userD != null && userR != null) {
            conge.setUtilisateurConge(userD);
            conge.setEtatConge(Etat.ENATTENTE);
            conge.setTraite(false);
            conge.setNumMatriculeD(userD.getNumMatricule());
            conge.setNumMatriculeR(userR.getNumMatricule());
            congeRepository.save(conge);
        }
        return ResponseEntity.ok(conge);
    }

    @Override
    public ResponseEntity<Object> modifierConge(Conge conge) {
        congeRepository.save(conge);
        return ResponseEntity.ok(conge);

    }

    @Override
    public ResponseEntity<Object> modifierEtatConge(int idConge, Etat etat) {
        Conge congeFind = congeRepository.findById(idConge).orElse(null);
        modifierEtatGeneric.modifyEtat(
                idConge,
                congeRepository,
                etat,
                conge -> conge.setEtatConge(etat),
                "conge n'existe pas."
        );
        assert congeFind != null;
        if (congeFind.getEtatConge() == Etat.ACCEPTER && Boolean.TRUE.equals(!congeFind.getTraite())) {
            Utilisateur user = utilisateurRepository.findByNumMatricule(congeFind.getNumMatriculeD());
            int nbrJours = congeFind.getNombreJours();

            if (nbrJours <= user.getSoldesConge()) {
                int soldeConge = user.getSoldesConge() - nbrJours;
                user.setSoldesConge(soldeConge);
                utilisateurRepository.save(user);

                congeFind.setTraite(true);
                congeRepository.save(congeFind);
            } else {
                return ResponseEntity.ok("Solde insuffisant");

            }
        }
        return ResponseEntity.ok().body("Mis a jour");

    }


    @Override
    public void supprimerCOnge(Integer idConge) {
        congeRepository.deleteById(idConge);

    }

    @Override
    public ResponseEntity<Object> afficherlisteconge(String numMatD) {
        return ResponseEntity.ok(congeRepository.findAllByNumMatriculeD(numMatD));

    }

}
