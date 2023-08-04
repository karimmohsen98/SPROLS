package sprols.internship.Entities;

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
    private TypeConge typeConge;

    @NotNull
    @Column(nullable = false)
    private Etat etatConge;

    @NotNull
    @Column(nullable = false)
    private int soldesConge;

    @NotNull
    @Column(nullable = false)
    private int nombreJours;

    @NotNull
    @Column(nullable = false)
    private int numMatriculeD;

    @NotNull
    @Column(nullable = false)
    private int numMatriculeR;

    @NotNull
    @Column(nullable = false)
    private String addressDurantConge;

}
