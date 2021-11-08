package com.livenow.application.domain.member.service;

import com.livenow.application.domain.member.dto.MemberDto;
import com.livenow.application.domain.member.repository.MemberRepository;
import com.livenow.core.domain.member.Member;
import com.livenow.core.domain.member.MemberRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberDto> findAllByMemberRank(MemberRank memberRank) {
        List<Member> members = memberRepository.findAllByMemberRank(memberRank);
        return members.stream()
                .map(MemberDto::from)
                .collect(Collectors.toList());
    }
}
