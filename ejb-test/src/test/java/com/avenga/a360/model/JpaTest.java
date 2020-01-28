package com.avenga.a360.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JpaTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    private static String persistenceUnitName = "a360test";

    @BeforeClass
    public static void init() {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }

    @Before
    public void initTransaction() {
        em.getTransaction().begin();
    }

    @After
    public void closeTransaction() {
        em.getTransaction().rollback();

    }

    @AfterClass
    public static void tearDown() {
        em.clear();
        em.close();
        emf.close();
    }
}