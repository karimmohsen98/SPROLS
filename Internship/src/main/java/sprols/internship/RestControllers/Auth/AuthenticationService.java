package sprols.internship.RestControllers.Auth;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Utilisateur;
import sprols.internship.Repositories.UtilisateurRepository;
import sprols.internship.Security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = Utilisateur.builder()
                .numMatricule(registerRequest.getNumMatricule())
                .nomUtilisateur(registerRequest.getNomUtilisateur())
                .dateNaissance(registerRequest.getDateNaissance())
                .prenomUtilisateur(registerRequest.getPrenomUtilisateur())
                .batiment(registerRequest.getBatiment())
                .poste(registerRequest.getPoste())
                .direction(registerRequest.getDirection())
                .service(registerRequest.getService())
                .bureau(registerRequest.getBureau())
                .password((bCryptPasswordEncoder.encode(registerRequest.getPassword())))
                .build();
        utilisateurRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

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
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

    }
}
