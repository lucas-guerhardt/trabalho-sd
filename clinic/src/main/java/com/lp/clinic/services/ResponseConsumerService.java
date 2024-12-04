package com.lp.clinic.services;

import com.lp.clinic.models.dto.EmailService.ResponseDto;

public interface ResponseConsumerService {
    public void consumeResponse(ResponseDto response);
}