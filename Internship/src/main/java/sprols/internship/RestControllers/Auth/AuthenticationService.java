package sprols.internship.RestControllers.Auth;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Token;
import sprols.internship.Entities.TokenType;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.TokenRepository;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Security.JwtService;

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
        saveUserToken(saveduser, jwt);
        revokeAllUserTokens(saveduser);
        return AuthenticationResponse.builder()
                .token(jwt)
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
        revokeAllUserTokens(user);
        saveUserToken(user, jwt);
        return AuthenticationResponse.builder()
                .token(jwt)
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
}
