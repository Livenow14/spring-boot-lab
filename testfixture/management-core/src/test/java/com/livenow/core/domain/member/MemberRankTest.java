package com.livenow.core.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("")
class MemberRankTest {

    @ParameterizedTest
    @CsvSource(value = {
            "CEO,MANAGER,true",
            "CEO,CEO,false",
            "MANAGER,CEO,false"
    })
    void isHigherThanTest(MemberRank expect, MemberRank target, boolean isHigherThan) {
        //given
        //when
        //then
        assertThat(expect.isHigherThan(target)).isEqualTo(isHigherThan);
    }
}