package com.lp.email.services;

import com.lp.email.models.dto.ResponseDto;

public interface ResponseProducerService {
    public void sendResponse(ResponseDto response);
}