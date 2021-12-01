package org.example.tp2salle.dao;

import org.example.tp2salle.entity.Specialite;

import java.util.List;

public interface SpecialiteDao {
    void ajouter(Specialite specialite);

    List<?> lister();
}
