package com.livenow.springcore.di;

public class MemberFieldService {
    private MemberRepository memberRepository;

    public String sayGump() {
        return memberRepository.sayGump();
    }
}
