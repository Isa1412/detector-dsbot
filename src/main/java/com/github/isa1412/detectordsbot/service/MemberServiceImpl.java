package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.repository.MemberRepository;
import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(MemberId id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findActiveById(MemberId id) {
        return memberRepository.findAllByIdAndActiveTrue(id);
    }

    @Override
    public List<Member> findActiveByGuildId(String guildId) {
        return memberRepository.findAllByGuildIdAndActiveTrue(guildId);
    }
}
