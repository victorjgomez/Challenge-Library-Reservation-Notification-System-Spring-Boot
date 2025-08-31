package com.library.library.member;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member){
        return memberRepository.save(member);
    }

    public Optional<Member> getMember(Long id){
        return memberRepository.findById(id);
    }
}
