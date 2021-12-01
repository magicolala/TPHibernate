package org.example.tp2salle.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Specialite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long           id;
    private String         titre;
    private String         description;
    @OneToMany(mappedBy = "specialite")
    private List<Etudiant> etudiants;

    public Specialite() {
        this.etudiants = new ArrayList<>();
    }

    public Specialite(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.etudiants = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Specialite{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
