package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.repository.entity.Guild;
import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link Member} entity.
 */
public interface MemberService {

    /**
     * Save provided {@link Member} entity.
     *
     * @param  member provided {@link Member}.
     */
    void save(Member member);

    /**
     * Find {@link Member} by id.
     *
     * @param id provided {@link MemberId} ID.
     * @return {@link Member} with provided ID or null otherwise.
     */
    Optional<Member> findById(MemberId id);

    /**
     * Find active {@link Member} by id.
     *
     * @param id provided {@link MemberId} ID.
     * @return {@link Member} with provided ID or null otherwise.
     */
    Optional<Member> findActiveById(MemberId id);

    /**
     * Find all active {@link Member}s by {@link Guild} id.
     *
     * @param guildId provided {@link Guild} ID.
     * @return the collection of {@link Member} objects with provided guild ID.
     */
    List<Member> findActiveByGuildId(String guildId);
}
