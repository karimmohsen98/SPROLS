package sprols.internship.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sprols.internship.Entities.Conge;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.CongeRepository;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Utils.ModifierEtatGeneric;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ICongeServiceIMP implements CongeService {

    private final CongeRepository congeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ModifierEtatGeneric modifierEtatGeneric;

   // private List<Utilisateur> users = new ArrayList<>(); // Simulated list of users


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
                double soldeConge = user.getSoldesConge() - nbrJours;
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

    @Transactional
    @Scheduled(cron = "0 0 0 L * ?")
    public void modifieCongeSolde() {
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        for (Utilisateur utilisateur : utilisateurList) {
            double nSolde = utilisateur.getSoldesConge() + 2.5;
            utilisateur.setSoldesConge(nSolde);
            utilisateurRepository.save(utilisateur);
        }

    }


    // Simulate loading users (you can load users from a database)
//    public void LeaveBalanceService() {
//        users = utilisateurRepository.findAll();
//    }
//
//    @Scheduled(cron = "0 0 1 1 *") // Schedule to run at 1:00 AM on the 1st day of every year
//    public void updateLeaveBalanceAutomatically() {
//        LocalDate currentDate = LocalDate.now();
//
//        for (Utilisateur user : users) {
//            double currentLeaveBalance = user.getSoldesConge();
//            int updatedLeaveBalance = updateLeaveBalance(currentLeaveBalance, currentDate);
//            user.setLeaveBalance(updatedLeaveBalance);
//        }
//    }
//
//    private int updateLeaveBalance(double currentLeaveBalance, LocalDate currentDate) {
//        LocalDate startOfYear = currentDate.withDayOfYear(1);
//        LocalDate twoYearsAgo = startOfYear.minusYears(2);
//
//        double sumOfLeaveBalance = currentLeaveBalance;
//        for (Utilisateur user : users) {
//            int leaveBalanceFromPastTwoYears = user.getLeaveBalanceFromPastTwoYears(twoYearsAgo, currentDate);
//            sumOfLeaveBalance += leaveBalanceFromPastTwoYears;
//        }
//
//        if (sumOfLeaveBalance > 60) {
//            int excess = sumOfLeaveBalance - 60;
//            sumOfLeaveBalance -= excess;
//        }
//
//        return sumOfLeaveBalance;
//
//    }
}
