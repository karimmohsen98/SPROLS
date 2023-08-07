package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.Materiel;

public interface MaterielRepository extends JpaRepository<Materiel,Integer> {
}
