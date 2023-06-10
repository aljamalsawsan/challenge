package com.challenge.numverifyapi.service;

import com.challenge.numverifyapi.model.NumVerifyResponse;
import org.springframework.http.ResponseEntity;

public interface NumVerifyService {
    ResponseEntity<NumVerifyResponse> isNumberValid(String paramString);
}
