package com.github.isa1412.detectordsbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResponseGenerateServiceImpl implements ResponseGenerateService {

    private final Set<String> responses;

    public ResponseGenerateServiceImpl(@Value("classpath:responses.txt") Resource resource) {
        responses = loadResponses(resource);
    }

    private Set<String> loadResponses(Resource resource) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            return new BufferedReader(inputStreamReader)
                    .lines()
                    .filter(line -> line.contains("["))
                    .map(String::trim)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error reading %s: %s", resource.getFilename(), e.getMessage()));
        }
    }

    private String getRandomResponse(String pattern) {
        List<String> selectedResponses = responses.stream()
                .filter(response -> response.contains(pattern))
                .map(response -> response.replace(pattern, ""))
                .collect(Collectors.toList());
        return selectedResponses.get(new Random().nextInt(selectedResponses.size()));
    }

    public String getNewMemberResponse() {
        return getRandomResponse("[NM]");
    }

    public String getAlreadyInResponse() {
        return getRandomResponse("[AI]");
    }

    public String getInGameResponse() {
        return getRandomResponse("[IN]");
    }
}
