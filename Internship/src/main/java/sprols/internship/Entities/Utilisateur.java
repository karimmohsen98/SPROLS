package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements  UserDetails {

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

       @NotNull
       @Column(name = "StatusCompte",nullable = false)
       private boolean StatusCompte;

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
       private double soldesConge;

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


       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
              return List.of(new SimpleGrantedAuthority("ROLE_"+role.getNomRole()));
       }

       @Override
       public String getUsername() {
              return numMatricule;
       }

       @Override
       public boolean isAccountNonExpired() {
              return true;
       }

       @Override
       public boolean isAccountNonLocked() {
              return true;
       }

       @Override
       public boolean isCredentialsNonExpired() {
              return true;
       }

       @Override
       public boolean isEnabled() {
              return true;
       }
}
