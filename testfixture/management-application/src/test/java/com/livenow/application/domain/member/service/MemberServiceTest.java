package com.livenow.application.domain.member.service;

import com.livenow.application.config.ApplicationConfig;
import com.livenow.application.domain.member.dto.MemberDto;
import com.livenow.application.domain.member.repository.MemberRepository;
import com.livenow.core.domain.member.Member;
import com.livenow.core.domain.member.MemberFixture;
import com.livenow.core.domain.member.MemberRank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@SpringBootTest(classes = ApplicationConfig.class)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @DisplayName("저장한 사원중 직급이 일치하면 검색된다.")
    @Test
    void findAllByMemberRank1() {
        //given
        Member expect = MemberFixture.createMember("검프", MemberRank.CEO);
        //when
        memberRepository.save(expect);
        List<MemberDto> foundMembers = memberService.findAllByMemberRank(MemberRank.CEO);
        //then
        assertThat(foundMembers).hasSize(1);
    }

    @DisplayName("저장한 사원중 직급이 일치하지 않으면 검색되지 않는다.")
    @Test
    void findAllByMemberRank2() {
        //given
        Member expect = MemberFixture.createMember("검프", MemberRank.CEO);
        //when
        memberRepository.save(expect);
        List<MemberDto> foundMembers = memberService.findAllByMemberRank(MemberRank.MANAGER);
        //then
        assertThat(foundMembers).isEmpty();
    }
}