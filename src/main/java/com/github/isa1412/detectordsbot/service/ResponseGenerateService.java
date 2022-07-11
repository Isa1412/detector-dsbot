package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.command.RollCommand;
import com.github.isa1412.detectordsbot.repository.entity.Member;

import java.util.List;
import java.util.Map;

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
     * Generate a response for a member who already in the game.
     *
     * @return {@link String} with response text.
     */
    String getAlreadyInResponse();

    /**
     * Generate a response for a member returning to the game.
     *
     * @return {@link String} with response text.
     */
    String getInGameResponse();

    /**
     * Generate a response for a member leaving the game.
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
     * Generate a response for a non-member.
     *
     * @return {@link String} with response text.
     */
    String getNotMemberResponse();

    /**
     * Generate a response for a {@link RollCommand}.
     *
     * @param memberId provided member ID.
     * @return 1-4 {@link String} with response text.
     */
    List<String> getRollResponse(String memberId);

    /**
     * Generate a response to recharge.
     *
     * @param timestamp provided timestamp.
     * @return {@link String} with response text.
     */
    String getCDResponse(long timestamp);

    /**
     * Generate a response with top 10 winners.
     *
     * @param members provided top 10 members.
     * @return {@link String} with response text.
     */
    String getTopResponse(List<Member> members);

    /**
     * Generate a response with member wins.
     *
     * @param memberId provided member ID.
     * @param count provided wins count.
     * @return {@link String} with response text.
     */
    String getWinsResponse(String memberId, int count);

    /**
     * Generate command descriptions.
     *
     * @return map with command name(key) and description text(value).
     */
    Map<String, String> getDescriptions();
}
