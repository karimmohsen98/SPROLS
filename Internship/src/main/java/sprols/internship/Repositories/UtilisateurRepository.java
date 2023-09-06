package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sprols.internship.Entities.Role;
import sprols.internship.Entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

     Utilisateur findByNumMatricule(String numMatricule);
     List<Utilisateur> findByRole(Role role);

     @Query("SELECT (l.soldesConge) FROM Utilisateur l WHERE l.numMatricule = :numMatricule")
     Double findUserLeaveBalanceFromNumMatricule(@Param("numMatricule") String numMatricule);
}
