package com.livenow.springcore.di;

public class MemberConstructorService {
    private MemberRepository memberRepository;

    public String sayGump() {
        return memberRepository.sayGump();
    }
}
