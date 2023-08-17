package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.Role;
import sprols.internship.Entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

     Utilisateur findByNumMatricule(String numMatricule);
     List<Utilisateur> findByRole(Role role);
}
