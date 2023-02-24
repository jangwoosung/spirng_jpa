/*******************************************************************************
 * created by        : jws
 * creation date     : 2023-02-24
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jpabook.springjpa.domain.Member;
import jpabook.springjpa.domain.QMember;
import jpabook.springjpa.domain.Team;

/**
 * Class description
 *
 * @author jws
 * @since 2023. 2. 24
 * @version 1.0
*/
@SpringBootTest
@Transactional
class QuerydslBasicTest {

	@Autowired
	EntityManager em;

	JPAQueryFactory queryFactory;

	@BeforeEach
	public void before() {

		queryFactory = new JPAQueryFactory(em);

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
	}

	@Test
	void startJPQL() {
		//member1을 찾아라.
		String qlString = "select m from Member m where m.username = :username";

		Member member = em.createQuery(qlString, Member.class)
		.setParameter("username", "member1").getSingleResult();

		assertThat(member.getUsername()).isEqualTo("member1");
	}


	@Test
	void startQueryDsl_vsersion1() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QMember m = new QMember("m");

		Member member = queryFactory.select(m).from(m).where(m.username.eq("member1")).fetchOne();

		assertThat(member.getUsername()).isEqualTo("member1");
	}


	@Test
	@DisplayName("동시성 문제가 없고, JPAQueryFactory 부분을 중복 선언 제거")
	void startQueryDsl_vsersion2() {
		QMember m = new QMember("m");

		Member member = queryFactory.select(m).from(m).where(m.username.eq("member1")).fetchOne();

		assertThat(member.getUsername()).isEqualTo("member1");
	}

}
