/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-20
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import jpabook.springjpa.domain.OldMember;
import jpabook.springjpa.repository.MemberRepository;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 20
 * @version 1.0
*/
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional // 테스트 중에는 기본 롤백
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception {

		// given
		OldMember member = new OldMember();
		member.setName("Kim");

		// when
		Long saveId = memberService.join(member);

		// then
		assertEquals(member, memberRepository.findOne(saveId));
	}

	@Test
	public void 중복_회원_에러() throws Exception {

		// given
		OldMember member1 = new OldMember();
		member1.setName("Kim");
		OldMember member2 = new OldMember();
		member2.setName("Kim");

		// when
		memberService.join(member1);

		Throwable exception  = assertThrows(IllegalStateException.class, () -> {
			memberService.join(member2);
		});

		// then
		assertEquals(exception.getMessage(), "이미 존재하는 회원입니다.");
	}
}
