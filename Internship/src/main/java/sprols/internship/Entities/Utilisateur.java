package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements Serializable {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int idUtilisateur;

       @NotNull
       @Column(nullable = false,unique = true)
       private String numMatricule;

       @NotNull
       @Column(nullable = false)
       private String nomUtilisateur;

       @NotNull
       @Column(nullable = false)
       private String prenomUtilisateur;

       @NotNull
       @Column(nullable = false)
       private LocalDate dateNaissance;

       @Email
       @NotNull
       @Column(nullable = false)
       private String email;

       @NotNull
       @Column(nullable = false)
       private String password;

       @NotNull
       @Column(nullable = false)
       private String poste;

       @NotNull
       @Column(nullable = false)
       private String direction;

       @NotNull
       @Column(nullable = false)
       private String service;

       @NotNull
       @Column(nullable = false)
       private String batiment;

       @NotNull
       @Column(nullable = false)
       private String bureau;

       @OneToOne()
       private Role role;

       @OneToMany(mappedBy = "utilisateurConge")
       private List<Conge> congeList;

       @OneToMany(mappedBy = "utilisateurAppro")
       private List<Approvisionnement> approvisionnementList;

       @OneToMany(mappedBy = "utilisateurDemandeInterv")
       private List<DemandeIntervention> demandeInterventionList;

}
