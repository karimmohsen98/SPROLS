package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.DemandeIntervention;

import java.util.List;

public interface DemandeInterventionRepository extends JpaRepository<DemandeIntervention, Integer> {
    List<DemandeIntervention> findByNumMatriculeDemandeur(String numMatricule);
}
