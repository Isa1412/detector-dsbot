package com.github.isa1412.detectordsbot.service;

/**
 * Service for generating responses to evens.
 */
public interface ResponseGenerateService {

    /**
     * Generate a response for the new member.
     *
     * @return {@link String} with response text.
     */
    String getNewMemberResponse();

    /**
     * Generate a response for member who already in the game.
     *
     * @return {@link String} with response text.
     */
    String getAlreadyInResponse();

    /**
     * Generate response for member returning to the game.
     *
     * @return {@link String} with response text.
     */
    String getInGameResponse();

    /**
     * Generate response for member leaving the game.
     *
     * @return {@link String} with response text.
     */
    String getOutGameResponse();

    /**
     * Generate a response for a member who has already left the game.
     *
     * @return {@link String} with response text.
     */
    String getAlreadyOutResponse();

    /**
     * Generate response for a non-member.
     *
     * @return {@link String} with response text.
     */
    String getNotMemberResponse();
}
