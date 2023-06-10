package com.challenge.numverifyapi.service;

import com.challenge.numverifyapi.model.NumVerifyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumVerifyServiceImpl implements NumVerifyService {
    private final String verifyNumApiKey;

    private final String apiUrl;

    public NumVerifyServiceImpl(@Value("${numVerify.apiKey}") String verifyNumApiKey,
                                @Value("${numVerify.apiUrl}") String apiUrl) {
        this.verifyNumApiKey = verifyNumApiKey;
        this.apiUrl = apiUrl;
    }

    public ResponseEntity<NumVerifyResponse> isNumberValid(String number) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = apiUrl + "?access_key=" + this.verifyNumApiKey + "&number=" + number + "&format=1";
        NumVerifyResponse response = restTemplate.getForObject(requestUrl, NumVerifyResponse.class);
        HttpStatus httpStatus = response.isValid() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, httpStatus);
    }
}