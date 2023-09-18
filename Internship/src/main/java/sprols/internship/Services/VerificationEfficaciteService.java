package sprols.internship.Services;

import org.springframework.http.ResponseEntity;
import sprols.internship.Entities.DemandeIntervention;
import sprols.internship.Entities.Etat;
import sprols.internship.Entities.VerificationEfficacite;

public interface VerificationEfficaciteService {

    ResponseEntity<Object> ajoutVerifEffi(VerificationEfficacite verificationEfficacite, String numMatriculeD,int idRealisationIntervention);

}
