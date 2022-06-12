package com.github.isa1412.detectordsbot.repository;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * {@link Repository} for handling with {@link Member} entity.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, MemberId> {
    List<Member> findAllByGuildIdAndActiveTrue(String id);
    Optional<Member> findAllByIdAndActiveTrue(MemberId id);
}
