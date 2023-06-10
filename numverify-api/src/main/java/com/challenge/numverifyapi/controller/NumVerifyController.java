package com.challenge.numverifyapi.controller;

import com.challenge.numverifyapi.model.NumVerifyResponse;
import com.challenge.numverifyapi.service.NumVerifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping({"/api"})
public class NumVerifyController {

    @Autowired
    NumVerifyService numVerifyService;

    @GetMapping({"/verifyNumber/{number}"})
    public ResponseEntity<NumVerifyResponse> verifyNumber(@PathVariable("number") String number) {
        log.debug("START verifyNumber : {}", number);
        return this.numVerifyService.isNumberValid(number);
    }
}
