package com.avenga.a360.service;

import com.avenga.a360.dto.EmailConfDto;

public interface EmailConfigService {
    EmailConfDto findEmailConfig();
    boolean editEmailConfig(EmailConfDto emailConfDto);
}
