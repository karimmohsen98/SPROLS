package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.Role;

import java.util.List;

public interface RoleService {

    ResponseEntity<Object> ajoutRole(Role role);
    ResponseEntity<Object> modifierRole(Role role);
    void supprimerRole(Integer idRole);
}
