package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @NotNull
    private int quantiteApprovisionne;
}
