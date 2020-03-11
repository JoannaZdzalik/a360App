package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.UserDao;
import com.avenga.a360.model.EmailConf;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public boolean createUser(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try {
            user = em.createNamedQuery("findUserByLogin", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean findLoginInDB(String login){
        User user = null;
        try {
            user = em.createNamedQuery("findUserByLogin", User.class).setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> findAllUsers() {
        return em.createNamedQuery("findAllUsers", User.class)
                .getResultList();
    }

    @Override
    public boolean updateUser(User user) {
        try {
            em.merge(user);
        } catch (Exception e) {
           return false;
        }
        return true;
    }

}
