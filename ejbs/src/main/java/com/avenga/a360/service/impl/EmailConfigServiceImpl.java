package com.avenga.a360.service.impl;

import com.avenga.a360.dao.EmailConfDao;
import com.avenga.a360.dao.UserDao;
import com.avenga.a360.dto.EmailConfDto;
import com.avenga.a360.model.EmailConf;
import com.avenga.a360.model.Session;
import com.avenga.a360.service.EmailConfigService;

import javax.inject.Inject;

public class EmailConfigServiceImpl implements EmailConfigService {

    @Inject
    EmailConfDao emailConfDao;


    @Override
    public EmailConfDto findEmailConfig() {
        return emailConfigToDto(emailConfDao.findEmailConfiguration());
    }

    @Override
    public boolean editEmailConfig(EmailConfDto emailConfDto) {
        EmailConf emailConf = emailConfDao.findEmailConfiguration();
        if (emailConf != null) {
            emailConf.setAuth(emailConfDto.isAuth());
            emailConf.setDebug(emailConfDto.isDebug());
            emailConf.setFrom(emailConfDto.getFrom());
            emailConf.setPort(emailConfDto.getPort());
            emailConf.setHost(emailConfDto.getHost());
            emailConf.setUsername(emailConfDto.getUsername());
            emailConf.setPassword(emailConfDto.getPassword());
            emailConfDao.updateEmailConf(emailConf);
            return true;
        }
        return false;
    }

    private EmailConfDto emailConfigToDto(EmailConf emailConf) {
        EmailConfDto emailConfDto = new EmailConfDto();
        emailConfDto.setHost(emailConf.getHost());
        emailConfDto.setPort(emailConf.getPort());
        emailConfDto.setFrom(emailConf.getFrom());
        emailConfDto.setAuth(emailConf.isAuth());
        emailConfDto.setDebug(emailConf.isDebug());
        emailConfDto.setUsername(emailConf.getUsername());
        emailConfDto.setPassword(emailConf.getPassword());
        return emailConfDto;
    }
}
