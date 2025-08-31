package com.library.library.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberResources {


    @PostMapping("/members")
    public Member createMember(Member member){
        throw new UnsupportedOperationException();
    }
}
