package com.livenow.core.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Member 도메인 테스트")
class MemberTest {

    @Test
    void promoteTest() {
        //given
        Member expect = MemberFixture.createMember("검프", MemberRank.CTO);
        MemberRank targetRank = MemberRank.CEO;
        //when
        final Member actual = expect.promote(targetRank);
        //then
        assertThat(actual.getMemberRank()).isEqualTo(targetRank);
    }

    @Test
    void promoteTest2() {
        //given
        Member expect = MemberFixture.createMember("검프", MemberRank.CTO);
        MemberRank targetRank = MemberRank.SENIOR;
        //when
        //then
        assertThatThrownBy(() -> expect.promote(targetRank))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void promoteTest3() {
        //given
        Member expect = MemberFixture.createMember("검프", MemberRank.CEO);
        MemberRank targetRank = MemberRank.CEO;
        //when
        //then
        assertThatThrownBy(() -> expect.promote(targetRank))
                .isInstanceOf(IllegalStateException.class);
    }
}