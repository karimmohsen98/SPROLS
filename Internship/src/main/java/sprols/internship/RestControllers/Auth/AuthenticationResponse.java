package sprols.internship.RestControllers.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sprols.internship.Entities.Utilisateur;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    private Utilisateur utilisateur;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
