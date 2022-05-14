package com.github.isa1412.detectordsbot.service;

/**
 * Service for generating responses to evens.
 */
public interface ResponseGenerateService {

    /**
     * Generate response for new member.
     *
     * @return {@link String} with response text.
     */
    String getNewMemberResponse();

    /**
     * Generate response for member who already in the game.
     *
     * @return {@link String} with response text.
     */
    String getAlreadyInResponse();

    /**
     * Generate response for member who back in the game.
     *
     * @return {@link String} with response text.
     */
    String getInGameResponse();
}
