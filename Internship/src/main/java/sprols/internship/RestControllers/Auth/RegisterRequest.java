package sprols.internship.RestControllers.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String numMatricule;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private LocalDate dateNaissance;
    //private boolean StatusCompte;
    private String password;
    private String poste;
    //private double soldesConge;
    private String direction;
    private String service;
    private String batiment;
    private String bureau;
}
