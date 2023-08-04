package sprols.internship.Entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class Materiel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateriel;

    @NotNull
    @Column(nullable = false)
    private String nomMateriel;

    @NotNull
    @Column(nullable = false)
    private int quantiteDemande;

    @Null
    private int quantiteApprovisionne;
}
