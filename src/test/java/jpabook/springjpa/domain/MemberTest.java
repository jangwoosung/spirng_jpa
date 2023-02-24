/*******************************************************************************
 * created by        : jws
 * creation date     : 2023-02-24
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import org.junit.jupiter.api.Test;

/**
 * Class description
 *
 * @author jws
 * @since 2023. 2. 24
 * @version 1.0
*/
@SpringBootTest
@Transactional
class MemberTest {

	@Autowired
	EntityManager em;

	@Test
	public void testEntitiy() {
		Team teamA = new Team("testA");
		Team teamB = new Team("testB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);

		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);

		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		// 초기화
		em.flush(); //영속성에있는걸 Db로 넣음
		em.clear(); //영속성을 초기화

		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

		for(Member member : members) {
			System.out.println("member : "  +member);
			System.out.println("member.team : " + member.getTeam());
		}

	}
}
