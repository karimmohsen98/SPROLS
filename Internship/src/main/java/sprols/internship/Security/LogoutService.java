package sprols.internship.Security;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import sprols.internship.Repositories.TokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");
        final String JWT;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            return;
        }
        JWT = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(JWT).orElse(null);
        if (storedToken!=null){
            storedToken.setRevoked(true);
            storedToken.setExpired(true);
            tokenRepository.save(storedToken);
        }
    }
}
