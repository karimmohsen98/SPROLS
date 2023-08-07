package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
}
