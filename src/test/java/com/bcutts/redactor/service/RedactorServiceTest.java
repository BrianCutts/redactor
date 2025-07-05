package com.bcutts.redactor.service;

import com.bcutts.redactor.config.ConfigProperties;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class RedactorServiceTest {

    @Test
    public void testRedactsBlacklistedWords() throws FileNotFoundException {
        ArrayList<String> testList = new ArrayList<>();
        testList.add("quick");
        testList.add("lazy");
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setBlackList(testList);
        RedactorService redactorService = new RedactorService(configProperties);
        String input = "The quick red fox jumps over the lazy dog";
        String expected = "The REDACTED red fox jumps over the REDACTED dog";

        String result = redactorService.redact(input);

        assertThat(result)
                .as("The words 'quick' and 'lazy' should be REDACTED")
                .isEqualTo(expected);
    }

    @Test
    public void testShouldRedactRegardlessOfCase() throws FileNotFoundException {
        //Given
        ArrayList<String> testList = new ArrayList<>();
        testList.add("quick");
        testList.add("lazy");
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setBlackList(testList);
        RedactorService redactorService = new RedactorService(configProperties);
        String input = "The qUIcK brown FOX jumps OveR the lazY dog";
        String expected = "The REDACTED brown FOX jumps OveR the REDACTED dog";

        //When
        String result = redactorService.redact(input);

        //Then
        assertThat(result)
                .as("Words should be redacted regardless of case")
                .isEqualTo(expected);
    }

    @Test
    public void testShouldSaveStringToFile() throws IOException {
        //Given
        ArrayList<String> testList = new ArrayList<>();
        testList.add("quick");
        testList.add("lazy");
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setBlackList(testList);
        RedactorService redactorService = new RedactorService(configProperties);
        String input = "The quick brown fox jumps over the lazy dog";
        //when
        String result = redactorService.redact(input);
        Path path = Paths.get("src/main/java/com/bcutts/redactor/logs/inputText.txt");

        String updatedFileText = Files.readAllLines(path).getFirst();

        //Then
        assertThat(updatedFileText)
                .as("File should contain input text")
                .isEqualTo(input);
    }

    @Test
    public void testShouldPopulateARedactedObject() throws FileNotFoundException {
        //Given
        ArrayList<String> testList = new ArrayList<>();
        testList.add("quick");
        testList.add("lazy");
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setBlackList(testList);
        RedactorService redactorService = new RedactorService(configProperties);
        String input = "The quick brown fox jumps over the lazy dog";
        //when
        String result = redactorService.redact(input);
    }

}
