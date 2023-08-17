package sprols.internship.Entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(Permission.ADMIN_READ, Permission.ADMIN_WRITE, Permission.ADMIN_UPDATE, Permission.ADMIN_DELETE)),
    USER(Set.of(Permission.USER_READ, Permission.USER_WRITE, Permission.USER_UPDATE, Permission.USER_DELETE));

    @Getter
    private final Set<Permission> permissions;
}
