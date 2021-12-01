package org.example.tp2salle.dao;

import org.example.tp2salle.entity.Etudiant;
import org.example.tp2salle.entity.Specialite;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoImpl implements org.example.tp2salle.dao.EtudiantDao {

    private org.example.tp2salle.dao.DaoFactory daoFactory;

    public EtudiantDaoImpl(org.example.tp2salle.dao.DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Etudiant etudiant) {
        EntityManager     em;
        EntityTransaction transaction = null;

        try {
            em = daoFactory.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(etudiant);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<Etudiant> lister() {
        List<Etudiant> etudiants     = new ArrayList<>();
        EntityManager  entityManager    ;

        try {
            entityManager = daoFactory.getEntityManager();

            //JPQL
//            Query query = entityManager.createQuery("SELECT etud from Etudiant etud");
//            etudiants = query.getResultList();

            //Criteria
            CriteriaBuilder         criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Etudiant> cr              = criteriaBuilder.createQuery(Etudiant.class);
            Root<Etudiant>          root            = cr.from(Etudiant.class);
            cr.select(root);
            TypedQuery<Etudiant> result = entityManager.createQuery(cr);
            etudiants = result.getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return etudiants;
    }

    @Override
    public void update(Etudiant nouvelEtudiant, Long id) {
        EntityManager     em;
        EntityTransaction transaction;

        em = daoFactory.getEntityManager();
        Etudiant etudiantAModifier = em.find(Etudiant.class, id);
        etudiantAModifier.setNom(nouvelEtudiant.getNom());
        etudiantAModifier.setPrenom(nouvelEtudiant.getPrenom());
        etudiantAModifier.setMatricule(nouvelEtudiant.getMatricule());

        transaction = em.getTransaction();
        transaction.begin();
        em.persist(etudiantAModifier);
        transaction.commit();

        System.out.println("---------- Update Ok ------------");
    }

    @Override
    public void delete(Long id) {
        EntityManager     entityManager;
        EntityTransaction transaction   = null;
        try {
            entityManager = daoFactory.getEntityManager();
            Etudiant etudiantASupprimer = entityManager.find(Etudiant.class, id);
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(etudiantASupprimer);
            transaction.commit();
            System.out.println("------------------------- Delete OK ----------------------");
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void affecterSpecialiteAEtudiant(Long idEtudiant, Long idSpecialite) {
        EntityManager entityManager;
        EntityTransaction transaction;

        try {
            entityManager = daoFactory.getEntityManager();
            Etudiant etudiant = entityManager.find(Etudiant.class, idEtudiant);
            Specialite specialite = entityManager.find(Specialite.class, idSpecialite);
            etudiant.setSpecialite(specialite);

            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(etudiant);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
