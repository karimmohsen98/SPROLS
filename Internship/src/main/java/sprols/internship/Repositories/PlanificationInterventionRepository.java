package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.PlanificationIntervention;

import java.util.List;

public interface PlanificationInterventionRepository extends JpaRepository<PlanificationIntervention,Integer> {
    List<PlanificationIntervention> findAllByNumMatriculeIntervenant(String numMatricule);
    PlanificationIntervention findByDemandeInterventionPlan(DemandeIntervention idDemandeIntervention);
    PlanificationIntervention findByIdPlanification(int idPlanification);
}
