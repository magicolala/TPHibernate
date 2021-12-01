package org.example.tp2salle;

import com.github.javafaker.Faker;
import org.example.tp2salle.dao.DaoFactory;
import org.example.tp2salle.dao.EtudiantDao;
import org.example.tp2salle.dao.SpecialiteDao;
import org.example.tp2salle.entity.Etudiant;
import org.example.tp2salle.entity.Specialite;

import java.util.List;

public class Main {

    static EtudiantDao   etudiantDao;
    static SpecialiteDao specialiteDao;

    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactory();
        etudiantDao = daoFactory.getEtudiantDao();
        specialiteDao = daoFactory.getSpecialiteDao();

        ajouter();
        lister();
        update();
        lister();
        delete();
        lister();

        Specialite sp1 = new Specialite("Specialite AB", "description de AB");
        Specialite sp2 = new Specialite("Specialite CD", "desciption de CD");
        specialiteDao.ajouter(sp1);
        specialiteDao.ajouter(sp2);

        etudiantDao.affecterSpecialiteAEtudiant(1L, 10L);
        lister();
    }

    private static void ajouter() {
        Faker faker = new Faker();

        String   firstName = faker.name().firstName(); // Emory
        String   lastName  = faker.name().lastName();
        Etudiant etudiant  = new Etudiant(lastName, firstName, "1ERHFE");
        etudiantDao.ajouter(etudiant);
    }

    private static void lister() {
        List<Etudiant> etudiants = etudiantDao.lister();
        etudiants.forEach(System.out::println);
    }

    private static void update() {
        Etudiant etudiant = new Etudiant("Oloa", "Cédric", "12IHJ");
        etudiantDao.update(etudiant, 2L);
    }

    private static void delete() {
        etudiantDao.delete(3L);
    }
}
