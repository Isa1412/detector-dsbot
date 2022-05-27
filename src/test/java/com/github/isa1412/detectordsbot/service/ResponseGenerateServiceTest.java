package com.github.isa1412.detectordsbot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit-level testing for ResponseGenerateService")
class ResponseGenerateServiceTest {

    private ResponseGenerateService responseService;

    @BeforeEach
    public void init() {
        Resource testResource = new ClassPathResource("responses.txt");
        responseService = new ResponseGenerateServiceImpl(testResource);
    }

    @Test
    public void shouldProperlyGenerateNewMemberResponse() {
        //when
        String response = responseService.getNewMemberResponse();

        //then
        assertEquals("Welcome to the game test", response);
    }

    @Test
    public void shouldProperlyGenerateAlreadyInResponse() {
        //when
        String response = responseService.getAlreadyInResponse();

        //then
        assertEquals("You are already in the game test", response);
    }

    @Test
    public void shouldProperlyGenerateInGameResponse() {
        //when
        String response = responseService.getInGameResponse();

        //then
        assertEquals("You are back in the game test", response);
    }

    @Test
    public void shouldProperlyGenerateOutGameResponse() {
        //when
        String response = responseService.getOutGameResponse();

        //then
        assertEquals("You are not in the game now test", response);
    }

    @Test
    public void shouldProperlyGenerateAlreadyOutResponse() {
        //when
        String response = responseService.getAlreadyOutResponse();

        //then
        assertEquals("You are already out of the game test", response);
    }

    @Test
    public void shouldProperlyGenerateNotMemberResponse() {
        //when
        String response = responseService.getNotMemberResponse();

        //then
        assertEquals("You are not a member of the game test", response);
    }
}