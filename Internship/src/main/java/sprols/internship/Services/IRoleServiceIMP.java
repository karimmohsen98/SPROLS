package sprols.internship.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sprols.internship.Entities.Role;
import sprols.internship.Repositories.RoleRepository;

@Service
@AllArgsConstructor
public class IRoleServiceIMP implements RoleService{

    private final RoleRepository roleRepository;
    @Override
    public ResponseEntity<Object> ajoutRole(Role role) {
        Assert.notNull(role, "Role ne doit pas etre vide");
        roleRepository.save(role);
        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<Object> modifierRole(Role role) {
        Assert.notNull(role, "Role ne doit pas etre vide");
        roleRepository.save(role);
        return ResponseEntity.ok(role);
    }

    @Override
    public void supprimerRole(Integer idRole) {
        Assert.notNull(idRole, "RoleId ne doit pas etre vide");

    }
}
