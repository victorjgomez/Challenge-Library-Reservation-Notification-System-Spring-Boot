package com.library.library.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberResources {

    private final MemberService memberService;

    public MemberResources(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }
}
