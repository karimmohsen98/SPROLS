package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.ListeMaterielItem;


public interface ListeMaterielItemRepository extends JpaRepository<ListeMaterielItem, Integer> {
}
