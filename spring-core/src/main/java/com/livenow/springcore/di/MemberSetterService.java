package com.livenow.springcore.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberSetterService {
    private MemberRepository memberRepository;

    public String sayGump() {
        return memberRepository.sayGump();
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
