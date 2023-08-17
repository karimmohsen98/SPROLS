package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.UtilisateurRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IUtilisateurServiceIMP implements UtilisateurService{

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur, int idRole) {

        Role role = roleRepository.findById(idRole).orElse(null);
        if (role!=null){
            utilisateur.setSoldesConge(30);
            utilisateur.setRole(role);
            utilisateur.setStatusCompte(true);
            utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
            utilisateurRepository.save(utilisateur);
        }
        return ResponseEntity.ok(utilisateur);
    }

    @Override
    public ResponseEntity<Object> modifierUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(utilisateur);

    }

    @Override
    public ResponseEntity<Object> supprimerUtilisateur(Integer idUser) {
        utilisateurRepository.deleteById(idUser);
        return ResponseEntity.ok("utilisateur supprimer");
    }

    @Override
    public ResponseEntity<Utilisateur> rechercherUser(String numMat) {
        return ResponseEntity.ok(utilisateurRepository.findByNumMatricule(numMat));
    }

    @Override
    public ResponseEntity<List<Utilisateur>> afficherToutUsers() {
        return ResponseEntity.ok(utilisateurRepository.findAll());
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

        }
        utilisateurRepository.save(utilisateur);

    }

    @Override
    public ResponseEntity<Object> getUserbyRole(int idRole) {
        Optional<Role> optionalRole = roleRepository.findById(idRole);
        if (optionalRole.isPresent()) {
            List<Utilisateur> result = utilisateurRepository.findAll()
                    .stream()
                    .filter(e -> e.getRole().getIdRole() == idRole)
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Liste des authorites est vide " + idRole);
            }
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(result);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Authorite avec id " + idRole);
    }
}
