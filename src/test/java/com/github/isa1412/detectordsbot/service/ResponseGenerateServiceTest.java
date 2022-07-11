package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for ResponseGenerateService")
class ResponseGenerateServiceTest {

    private final ResponseGenerateService responseService = new ResponseGenerateServiceImpl(new ClassPathResource("responses.txt"));

    @Test
    public void shouldProperlyGenerateNewMemberResponse() {
        //when
        String response = responseService.getNewMemberResponse();

        //then
        assertEquals("Welcome to the game", response);
    }

    @Test
    public void shouldProperlyGenerateAlreadyInResponse() {
        //when
        String response = responseService.getAlreadyInResponse();

        //then
        assertEquals("You are already in the game", response);
    }

    @Test
    public void shouldProperlyGenerateInGameResponse() {
        //when
        String response = responseService.getInGameResponse();

        //then
        assertEquals("You are back in the game", response);
    }

    @Test
    public void shouldProperlyGenerateOutGameResponse() {
        //when
        String response = responseService.getOutGameResponse();

        //then
        assertEquals("You are not in the game now", response);
    }

    @Test
    public void shouldProperlyGenerateAlreadyOutResponse() {
        //when
        String response = responseService.getAlreadyOutResponse();

        //then
        assertEquals("You are already out of the game", response);
    }

    @Test
    public void shouldProperlyGenerateNotMemberResponse() {
        //when
        String response = responseService.getNotMemberResponse();

        //then
        assertEquals("You are not a member of the game", response);
    }

    @Test
    public void shouldProperlyGenerateRollResponse() {
        //given
        String id = "123";

        //when
        List<String> responses = responseService.getRollResponse(id);

        //then
        assertFalse(responses.isEmpty());
        assertEquals(responses.size(), 1);
        assertEquals("Oh <@123>, you are the winner today!!!", responses.get(0));
    }

    @Test
    public void shouldProperlyGenerateCDResponse() {
        //given
        long timestamp = 123L;

        //when
        String response = responseService.getCDResponse(timestamp);

        //then
        assertEquals("Cooldown, you can roll <t:123:R>", response);
    }

    @Test
    public void shouldProperlyGenerateTopResponse() {
        //given
        String userId = "123123123";
        String guildId = "678969697";
        MemberId id = new MemberId(userId, guildId);
        Member member = new Member(id);
        List<Member> members = List.of(member);

        //when
        String response = responseService.getTopResponse(members);

        //then
        assertEquals("Top 10 winners:\n<@123123123> - 0", response);
    }

    @Test
    public void shouldProperlyGenerateWinsResponse() {
        //given
        String userId = "123123123";
        int winsCount = 21;

        //when
        String response = responseService.getWinsResponse(userId, winsCount);

        //then
        assertEquals("<@123123123> you have 21 wins", response);
    }

    @Test
    public void shouldProperlyGenerateDescriptions() {
        //when
        Map<String, String> expected = new HashedMap<>();
        expected.put("start", "start/unpause the game");
        expected.put("stop", "stop/pause the game");
        expected.put("roll", "determine the winner of the day");
        expected.put("top", "show top 10 winners");
        expected.put("wins", "show your wins");
        Map<String, String> descriptions = responseService.getDescriptions();
        MapDifference<String, String> difference = Maps.difference(expected, descriptions);
        Map<String, ValueDifference<String>> entriesDiffering = difference.entriesDiffering();

        //then
        assertNotNull(descriptions);
        assertTrue(difference.areEqual());
        assertEquals(0, entriesDiffering.size());
    }
}