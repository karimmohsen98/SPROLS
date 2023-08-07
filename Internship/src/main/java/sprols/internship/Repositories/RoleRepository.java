package sprols.internship.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sprols.internship.Entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
