package sprols.internship.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;
import sprols.internship.Entities.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class Authentification extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        Utilisateur appUser = new Utilisateur();
        try {
            appUser = new ObjectMapper()
                    .readValue(request.getInputStream(), Utilisateur.class);
        } catch (IOException e) {
            log.info("Erreur d'authentification",e);
        }
        Assert.notNull(appUser.getUsername(),"Utilisateur vide");
        return this.getAuthenticationManager()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(appUser
                                .getUsername()
                                ,appUser
                                .getPassword()
                        )
                );
    }
}
