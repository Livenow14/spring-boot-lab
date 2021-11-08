package com.livenow.application.domain.member.service;

import com.livenow.core.domain.member.Member;
import com.livenow.core.domain.member.MemberRank;

import java.time.LocalDateTime;

public class MemberFixture {

    public static Member createMember(String name, MemberRank memberRank) {
        LocalDateTime now = LocalDateTime.now();
        return Member.builder()
                .name(name)
                .memberRank(memberRank)
                .build();
    }
}
