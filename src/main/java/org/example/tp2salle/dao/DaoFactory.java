package org.example.tp2salle.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {

    private EntityManagerFactory entityManagerFactory;

    public DaoFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("tp2-pu");
    }

    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public EtudiantDao getEtudiantDao() {
        return new EtudiantDaoImpl(this);
    }

    public SpecialiteDao getSpecialiteDao() {
        return new SpecialiteDaoImpl(this);
    }
}
