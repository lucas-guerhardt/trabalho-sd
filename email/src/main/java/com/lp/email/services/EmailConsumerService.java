package com.lp.email.services;

import com.lp.email.models.dto.EmailDto;

public interface EmailConsumerService {
    public void receiveEmail(EmailDto message);
}
