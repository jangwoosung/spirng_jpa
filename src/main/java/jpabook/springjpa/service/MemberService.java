/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-20
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.springjpa.domain.Member;
import jpabook.springjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 20
 * @version 1.0
*/

@Service
@Transactional(readOnly = true) //읽기 전용만 true, 기본값 false
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 회원 가입
	@Transactional // false로 먹힘
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	// 중복 회원 검증
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 회원 한건 조회
	public Member findOne(Long meberId) {
		return memberRepository.findOne(meberId);
	}
}
