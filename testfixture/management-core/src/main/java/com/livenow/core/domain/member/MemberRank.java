package com.livenow.core.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRank {
    CEO(1),
    CTO(2),
    MANAGER(3),
    SENIOR(4),
    ;

    private final Integer grade;

    public boolean isCeo() {
        return this == CEO;
    }

    public boolean isHigherThan(MemberRank other) {
        return this.grade.compareTo(other.grade) < 0;
    }
}
