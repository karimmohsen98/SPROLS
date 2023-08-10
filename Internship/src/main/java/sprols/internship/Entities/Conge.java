package sprols.internship.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConge;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDebut;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateFin;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Etat etatConge;

    @NotNull
    @Column(nullable = false)
    private int nombreJours;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeD;

    @NotNull
    @Column(nullable = false)
    private String numMatriculeR;

    @NotNull
    @Column(nullable = false)
    private String addressDurantConge;

    @NotNull
    @Column(nullable = false)
    private Boolean traite;

    @ManyToOne
    @JsonIgnore
    private Utilisateur utilisateurConge;

}
