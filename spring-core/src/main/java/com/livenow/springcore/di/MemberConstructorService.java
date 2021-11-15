package com.livenow.springcore.di;

import org.springframework.stereotype.Service;

@Service
public class MemberConstructorService {
    private final MemberRepository memberRepository;

    public MemberConstructorService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String sayGump() {
        return memberRepository.sayGump();
    }
}
