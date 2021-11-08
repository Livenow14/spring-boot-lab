package com.livenow.application.domain.member.repository;

import com.livenow.core.domain.member.Member;
import com.livenow.core.domain.member.MemberRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByMemberRank(MemberRank memberRank);
}
