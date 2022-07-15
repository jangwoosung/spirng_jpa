/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa;

import static org.junit.jupiter.api.Assertions.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import jpabook.springjpa.domain.Member;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 15
 * @version 1.0
*/
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false) // test에서는 자동으로 @Transactional 롤백이됨 그걸방지하는 어노테이션
	@DisplayName(value = "")
	void testMember() {

		//given
		Member member = new Member();
//		member.setUsername("memberA");

		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);

		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member);

		//1차영속성때문에 같음..
		System.out.println("findMember == meber:" + (findMember == member));
	}

}
