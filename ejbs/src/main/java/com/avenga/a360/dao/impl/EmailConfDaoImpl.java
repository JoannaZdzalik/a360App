package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.EmailConfDao;
import com.avenga.a360.model.EmailConf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class EmailConfDaoImpl implements EmailConfDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public EmailConf findEmailConfiguration() {
        EmailConf emailConf = null;
        try {
            emailConf = em.createNamedQuery("findEmailConfiguration", EmailConf.class)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return emailConf;
    }

    @Override
    @Transactional
    public boolean updateEmailConf(EmailConf emailConf) {
        try {
            em.merge(emailConf);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
