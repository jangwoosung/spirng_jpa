/*******************************************************************************
 * created by        : jws
 * creation date     : 2023-02-24
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa;

import static jpabook.springjpa.domain.QMember.member;
import static jpabook.springjpa.domain.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jpabook.springjpa.domain.Member;
import jpabook.springjpa.domain.QMember;
import jpabook.springjpa.domain.Team;;

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

		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
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
		QMember m = new QMember("m"); // 같은 테이이블을 조인할때 별칭으로 구분 짓기위해서 사용함(별칭)

		Member member = queryFactory.select(m).from(m).where(m.username.eq("member1")).fetchOne();

		assertThat(member.getUsername()).isEqualTo("member1");
	}

	@Test
	@DisplayName("검색조건 쿼리1")
	void search() {
		QMember m = new QMember("m"); // 같은 테이이블을 조인할때 별칭으로 구분 짓기위해서 사용함(별칭)

		Member member = queryFactory.select(m)
				.from(m)
				.where(m.username.eq("member1")
				       .and(m.age.eq(10))
						)
				.fetchOne();

		assertThat(member.getUsername()).isEqualTo("member1");
	}

	@Test
	@DisplayName("검색조건 쿼리2")
	void search2() {
		QMember m = new QMember("m"); // 같은 테이이블을 조인할때 별칭으로 구분 짓기위해서 사용함(별칭)

		Member member = queryFactory.select(m).from(m).where(
		                                                     m.username.eq("member1"),
		                                                     (m.age.eq(10))
		                                                     )
				.fetchOne();

		assertThat(member.getUsername()).isEqualTo("member1");
	}

	/**
	 *  예) 회원과 팀을 조읺아면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
	 *  JPQL : select m, t from Member m left join m.team t on t.name = 'teamA'
	 */
	@Test
	public void join_on_filtering() {
		List<Tuple> result = queryFactory
				.select(member, team)
				.from(member)
				.leftJoin(member.team, team)
//				.on(team.name.eq("teamA"))
				.where(team.name.eq("teamA"))
				.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

	/**
	 *  연관관계 없는 엔티티 외부 조인
	 *  회원의 이름이 팀 이름과 같음
	 */
	@Test
	public void join_on_no_relation() {
		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));
		em.persist(new Member("teamC"));

		List<Tuple> result = queryFactory
				.select(member, team)
				.from(member)
				.leftJoin(team).on(member.username.eq(team.name))
				.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

}
