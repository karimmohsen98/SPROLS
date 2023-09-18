package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.VerificationEfficacite;

import java.util.List;

public interface VerificationEfficaciteRepository extends JpaRepository<VerificationEfficacite,Integer> {

    List<VerificationEfficacite> findAllByNumMatriculeDemandeur(String numMatriculeDemandeur);
}
