package com.livenow.springcore.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberFieldService {

    @Autowired
    private MemberRepository memberRepository;

    public String sayGump() {
        return memberRepository.sayGump();
    }
}
