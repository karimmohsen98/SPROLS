package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Role;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.UtilisateurRepository;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IUtilisateurServiceIMP implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur) {


        utilisateur.setSoldesConge(30);
        utilisateur.setRole(Role.USER);
        utilisateur.setStatusCompte(true);
        utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
        utilisateurRepository.save(utilisateur);

        return ResponseEntity.ok(utilisateur);
    }

    @Override
    public ResponseEntity<Object> modifierUtilisateur(Integer id, Utilisateur utilisateur) {
        Utilisateur utilisateurToUpdate = utilisateurRepository.findById(id).orElse(null);
        if (utilisateurToUpdate != null) {
            utilisateurToUpdate.setNomUtilisateur(utilisateur.getNomUtilisateur());
            utilisateurToUpdate.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
            utilisateurToUpdate.setRole(utilisateur.getRole());
            utilisateurToUpdate.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
            utilisateurToUpdate.setService(utilisateur.getService());
            utilisateurToUpdate.setBatiment(utilisateur.getBatiment());
            utilisateurToUpdate.setBureau(utilisateur.getBureau());
            utilisateurToUpdate.setDirection(utilisateur.getDirection());
            utilisateurToUpdate.setPoste(utilisateur.getPoste());
            utilisateurRepository.save(utilisateurToUpdate);

        }
        return ResponseEntity.ok(utilisateur);

    }

    @Override
    public ResponseEntity<Object> supprimerUtilisateur(Integer idUser) {
        utilisateurRepository.deleteById(idUser);
        return ResponseEntity.ok("utilisateur supprimer");
    }

    @Override
    public Optional<Utilisateur> rechercherUser(int id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public List<Utilisateur> afficherToutUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void desactiverCompte(String numMat) {
        Utilisateur utilisateur = utilisateurRepository.findByNumMatricule(numMat);
        if (utilisateur != null && utilisateur.isStatusCompte()) {
            utilisateur.setStatusCompte(false);

        }
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void activeCompte(String numMat) {
        Utilisateur utilisateur = utilisateurRepository.findByNumMatricule(numMat);
        if (utilisateur != null && !utilisateur.isStatusCompte()) {
            utilisateur.setStatusCompte(true);
            utilisateurRepository.save(utilisateur);
        }

    }

    public void enabledisableCompte(String numMat, Utilisateur user) {
        Utilisateur utilisateur = utilisateurRepository.findByNumMatricule(numMat);
        if (utilisateur != null && !utilisateur.isStatusCompte()) {
            utilisateur.setStatusCompte(true);
        } else {
            utilisateur.setStatusCompte(false);
        }
        utilisateurRepository.save(utilisateur);
    }

    public double findUserSoldeConge(String numMat){
        return utilisateurRepository.findUserLeaveBalanceFromNumMatricule(numMat);
    }

}


