package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.RealisationIntervention;

import java.util.List;

public interface RealisationInterventionRepository extends JpaRepository<RealisationIntervention,Integer> {

    List<RealisationIntervention> findAllByNumMatriculeIntervenant(String numMatricule);
}
