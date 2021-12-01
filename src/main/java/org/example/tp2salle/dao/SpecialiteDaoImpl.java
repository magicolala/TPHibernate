package org.example.tp2salle.dao;

import org.example.tp2salle.entity.Specialite;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteDaoImpl implements SpecialiteDao {
    private DaoFactory daoFactory;

    public SpecialiteDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Specialite specialite) {
        EntityManager     entityManager = null;
        EntityTransaction transaction   = null;

        try {
            entityManager = daoFactory.getEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(specialite);
            transaction.commit();
        } catch (Exception exception) {
            assert transaction != null;
            transaction.rollback();
            exception.printStackTrace();
        }
    }

    @Override
    public List<?> lister() {
        List<?>       specialites = new ArrayList<>();
        EntityManager entityManager;

        try {
            entityManager = daoFactory.getEntityManager();

            Query query = entityManager.createQuery("SELECT spec from Specialite spec");
            specialites = query.getResultList();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return specialites;
    }
}
