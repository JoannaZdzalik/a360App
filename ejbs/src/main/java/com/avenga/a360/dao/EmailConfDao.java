package com.avenga.a360.dao;

import com.avenga.a360.model.EmailConf;

public interface EmailConfDao {
    EmailConf findEmailConfiguration();
    boolean updateEmailConf(EmailConf emailConf);
}
