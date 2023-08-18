package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sprols.internship.Entities.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    @Query("select t from Token t inner join Utilisateur u on t.user.idUtilisateur = u.idUtilisateur " +
            "where u.idUtilisateur = :id and(t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokensByUtilisateur(Integer id);

    Optional<Token> findByToken(String Token);
}
