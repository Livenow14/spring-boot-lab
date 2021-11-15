package com.livenow.springcore.di;

public class MemberSetterService {
    private MemberRepository memberRepository;

    public String sayGump() {
        return memberRepository.sayGump();
    }
}
