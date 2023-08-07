package sprols.internship.Entities;

import java.io.Serializable;
import java.time.LocalDate;

public class DemandeIntervention implements Serializable {
    private int idDemandeIntervention;
    private int numMatriculeDemandeur;
    private int codeMateriel;
    private String constructeur;
    private String modele;
    private String version;
    private String typeModel;
    private String descriptionPanne;
    private LocalDate dateDemandeIntervention;
    private Etat etatDemandeIntervention;
    private String typeMat;
}
