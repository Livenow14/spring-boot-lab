package com.livenow.core.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MemberRank memberRank;

    private String name;
    private LocalDateTime joinDate;

    @Builder
    public Member(Long id, MemberRank memberRank, String name, LocalDateTime joinDate) {
        this.id = id;
        this.memberRank = memberRank;
        this.name = name;
        this.joinDate = joinDate;
    }

    public Member promote(MemberRank promotionRole) {
        if (this.memberRank.isCeo()) {
            throw new IllegalStateException("사장님은 진급할 수 없어요!");
        }
        if (this.memberRank.isHigherThan(promotionRole)) {
            throw new IllegalStateException("높은 직급으로만 진급할 수 있어요!");
        }
        this.memberRank = promotionRole;
        return this;
    }

}
