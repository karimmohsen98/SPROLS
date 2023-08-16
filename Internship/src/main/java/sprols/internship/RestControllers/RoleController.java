package sprols.internship.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sprols.internship.Entities.Role;
import sprols.internship.Services.IRoleServiceIMP;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
public class RoleController {
    private final IRoleServiceIMP iRoleServiceIMP;

    @PostMapping("/ajouterrole")
    public ResponseEntity<Object> ajouterRole(@RequestBody Role role){
        return iRoleServiceIMP.ajoutRole(role);
    }
    @PatchMapping("/modifierrole")
    public ResponseEntity<Object> ModifierRole(@RequestBody Role role){
        return iRoleServiceIMP.modifierRole(role);
    }
    @DeleteMapping("/supprimerrole/{idRole}")
    public void removeAutomate(@PathVariable Integer idRole){
        iRoleServiceIMP.supprimerRole(idRole);
    }
}
