package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Role;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.RoleRepository;
import sprols.internship.Repositories.UtilisateurRepository;

@Service
@AllArgsConstructor
public class IUtilisateurServiceIMP implements UtilisateurService{

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    @Override
    public ResponseEntity<Object> ajoutUtilisateur(Utilisateur utilisateur, int idRole) {

        Role role = roleRepository.findById(idRole).orElse(null);
        if (role!=null){
            utilisateur.setRole(role);
            utilisateurRepository.save(utilisateur);
        }
        return ResponseEntity.ok(utilisateur);
    }

    @Override
    public ResponseEntity<Object> modifierUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public void supprimerUtilisateur(Integer idRole) {

    }
}
