package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import org.apache.logging.log4j.util.Strings;
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
import java.util.stream.IntStream;

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
                    .filter(l -> l.contains("["))
                    .map(String::trim)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error reading %s: %s", resource.getFilename(), e.getMessage()));
        }
    }

    private String getRandomResponse(String pattern) {
        List<String> selectedResponses = responses.stream()
                .filter(r -> r.contains(pattern))
                .map(r -> r.replace(pattern, "").trim())
                .collect(Collectors.toList());
        return selectedResponses.get(new Random().nextInt(selectedResponses.size()));
    }

    @Override
    public String getNewMemberResponse() {
        return getRandomResponse("[NM]");
    }

    @Override
    public String getAlreadyInResponse() {
        return getRandomResponse("[AI]");
    }

    @Override
    public String getInGameResponse() {
        return getRandomResponse("[IN]");
    }

    @Override
    public String getOutGameResponse() {
        return getRandomResponse("[OUT]");
    }

    @Override
    public String getAlreadyOutResponse() {
        return getRandomResponse("[AO]");
    }

    @Override
    public String getNotMemberResponse() {
        return getRandomResponse("[NTM]");
    }

    @Override
    public List<String> getRollResponse(String memberId) {
        return IntStream
                .range(1, 5)
                .mapToObj(i -> getRandomResponse("[R" + i + "]"))
                .filter(Strings::isNotBlank)
                .map(r -> r.replace("[W]", "<@" + memberId + ">"))
                .collect(Collectors.toList());
    }

    @Override
    public String getCDResponse(long timestamp) {
        return getRandomResponse("[CD]").replace("[T]", "<t:" + timestamp + ":R>");
    }

    @Override
    public String getTopResponse(List<Member> members) {
        return getRandomResponse("[TOP]") + "\n" +
                members.stream()
                        .map(m -> "<@" + m.getId().getUserId() + "> - " + m.getCount())
                        .collect(Collectors.joining("\n"));
    }
}
