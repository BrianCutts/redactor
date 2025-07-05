package com.bcutts.redactor.controller;

import com.bcutts.redactor.model.Redacted;
import com.bcutts.redactor.service.RedactorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/redact")
public class RedactorController {

    private static final String REDACTOR_RESPONSE = "Redaction Service";

    private final RedactorService redactorService;

    public RedactorController(RedactorService redactorService) {
        this.redactorService = redactorService;
    }

    @GetMapping
    public @ResponseBody String redactorResponse() {
        return REDACTOR_RESPONSE;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Redacted receiveMessageToRedact(@RequestBody String stringToRedact) throws FileNotFoundException {
        return new Redacted(redactorService.redact(stringToRedact));
    }
}
