package com.livenow.application.domain.member.dto;

import com.livenow.core.domain.member.Member;
import com.livenow.core.domain.member.MemberRank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private Long id;
    private MemberRank memberRank;
    private String name;
    private LocalDateTime joinDate;

    @Builder
    public MemberDto(Long id, MemberRank memberRank, String name, LocalDateTime joinDate) {
        this.id = id;
        this.memberRank = memberRank;
        this.name = name;
        this.joinDate = joinDate;
    }

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .memberRank(member.getMemberRank())
                .joinDate(member.getJoinDate())
                .build();
    }
}
