package com.josue.kodeur.xtremanalyse.security.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GhostKodeur
 * **/

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_matricule", nullable = false, length = 50, unique = true)
    private String userMatricule;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 200)
    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_date_display")
    private Date lastLoginDateDisplay;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "is_active", length = 1, nullable = false)
    private Boolean isActive;

    @Transient
    private String token;

    @Transient
    private boolean isTokenValid=false;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Role> roles=new ArrayList<>();

}