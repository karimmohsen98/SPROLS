package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.Conge;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Integer> {
    List<Conge> findAllByNumMatriculeD(String numMatricule);
}
