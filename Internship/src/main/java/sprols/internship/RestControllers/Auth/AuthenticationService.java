package sprols.internship.RestControllers.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Token;
import sprols.internship.Entities.TokenType;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.TokenRepository;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Security.JwtService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = Utilisateur.builder()
                .numMatricule(registerRequest.getNumMatricule())
                .nomUtilisateur(registerRequest.getNomUtilisateur())
                .dateNaissance(registerRequest.getDateNaissance())
                .prenomUtilisateur(registerRequest.getPrenomUtilisateur())
                .batiment(registerRequest.getBatiment())
                .StatusCompte(TRUE)
                .role(registerRequest.getRole())
                .soldesConge(30.0)
                .poste(registerRequest.getPoste())
                .direction(registerRequest.getDirection())
                .service(registerRequest.getService())
                .bureau(registerRequest.getBureau())
                .password((bCryptPasswordEncoder.encode(registerRequest.getPassword())))
                .build();
        var saveduser = utilisateurRepository.save(user);
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(saveduser, jwt);
        revokeAllUserTokens(saveduser);
        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .build();

    }

    private void revokeAllUserTokens(Utilisateur user) {
        var tokens = tokenRepository.findAllValidTokensByUtilisateur(user.getIdUtilisateur());
        if (tokens.isEmpty()) return;
        tokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(tokens);
    }


    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getNumMatricule(),
                        authenticateRequest.getPassword()
                )
        );
        var user = utilisateurRepository.findByNumMatricule(authenticateRequest.getNumMatricule());
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwt);
        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .build();

    }

    private void saveUserToken(Utilisateur user, String jwt) {
        var token = Token.builder()
                .user(user)
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userNumMatricule;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userNumMatricule = jwtService.extractuserNumMatricule(refreshToken);
        if (userNumMatricule != null) {
            var userDetails = this.utilisateurRepository.findByNumMatricule(userNumMatricule);
            if (Boolean.TRUE.equals(jwtService.isTokenValid(refreshToken, userDetails))) {
                var accessToken = jwtService.generateToken(userDetails);
                revokeAllUserTokens(userDetails);
                saveUserToken(userDetails, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}
