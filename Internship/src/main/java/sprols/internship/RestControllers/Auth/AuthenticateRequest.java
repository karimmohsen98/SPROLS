package sprols.internship.RestControllers.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {

    private String numMatricule;
    private String password;
    private boolean StatusCompte;

}
