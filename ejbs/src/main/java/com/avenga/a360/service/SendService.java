package com.avenga.a360.service;

import com.avenga.a360.domain.model.Email;

public interface SendService {


    boolean sendEmail(Email email);
}
