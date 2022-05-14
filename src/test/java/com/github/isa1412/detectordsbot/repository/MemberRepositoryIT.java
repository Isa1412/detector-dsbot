package com.github.isa1412.detectordsbot.repository;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.Set;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Integration-level testing for {@link MemberRepository}.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class MemberRepositoryIT {

    @Autowired
    private MemberRepository memberRepository;

    @Sql(scripts = {"/sql/clearDB.sql", "/sql/guild.sql", "/sql/member.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsersByGuildId() {
        //when
        Set<Member> members = memberRepository.findAllByGuildIdAndActiveTrue("90045684560");

        //then
        Assertions.assertEquals(3, members.size());
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlySaveMember() {
        //given
        MemberId id = new MemberId("123987546", "230470234");
        Member member = new Member(id);
        memberRepository.save(member);

        //when
        Optional<Member> saved = memberRepository.findById(id);

        //then
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(member, saved.get());
    }
}
