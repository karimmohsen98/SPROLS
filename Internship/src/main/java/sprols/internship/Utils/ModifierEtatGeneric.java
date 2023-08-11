package sprols.internship.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ModifierEtatGeneric {
    public <T> ResponseEntity<Object> modifyEtat(int entityId, JpaRepository<T, Integer> repository,
                                                 Enum<?> targetEnum,
                                                 Consumer<T> fieldModifier,
                                                 String errorMessage) {
        T entity = repository.findById(entityId).orElse(null);

        if (entity != null) {
            Enum<?>[] enumConstants = targetEnum.getDeclaringClass().getEnumConstants();
            boolean isValidEnumValue = false;
            for (Enum<?> enumValue : enumConstants) {
                if (enumValue.name().equalsIgnoreCase(targetEnum.name())) {
                    isValidEnumValue = true;
                    break;
                }
            }
            if (isValidEnumValue) {
                fieldModifier.accept(entity);
                repository.save(entity);
            } else {
                throw new IllegalArgumentException("Invalid enum value.");
            }
        } else {
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok(entity);
    }
}
