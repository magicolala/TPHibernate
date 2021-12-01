package org.example.tp2salle.dao;

import org.example.tp2salle.entity.Etudiant;
import org.example.tp2salle.entity.Specialite;

import java.util.List;

public interface EtudiantDao {
    void ajouter(Etudiant etudiant);

    List<Etudiant> lister();

    void update(Etudiant nouvelEtudiant, Long id);

    void delete(Long id);

    void affecterSpecialiteAEtudiant(Long idEtudiant, Long idSpecialite);
}
