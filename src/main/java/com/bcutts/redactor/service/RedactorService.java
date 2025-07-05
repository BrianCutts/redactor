package com.bcutts.redactor.service;

import com.bcutts.redactor.config.ConfigProperties;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class RedactorService {

    private final ArrayList<String> blackList;

    public RedactorService(ConfigProperties configProperties) {
        this.blackList = configProperties.getBlackList();
    }

    public String redact(String stringToRedact) throws FileNotFoundException {
        printToLogFile(stringToRedact);
        String result = stringToRedact;
        for (String word : blackList) {
            //Use regex to match on only whole word matches in the input string.
            result = result.replaceAll("(?i)\\b" + Pattern.quote(word) + "\\b", "REDACTED");
        }
        return result;
    }

    private void printToLogFile(String stringToRedact) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("src/main/java/com/bcutts/redactor/logs/inputText.txt")) {
            writer.println(stringToRedact);
            writer.println(LocalDateTime.now());
        }
    }


}
