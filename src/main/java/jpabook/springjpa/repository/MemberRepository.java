/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-20
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.springjpa.domain.Member;
import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 20
 * @version 1.0
*/

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

//	@PersistenceContext
//	private EntityManager em;

	// persist를 호출하는 순간 영속성 컨텍스트에 올림
	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	// 전체조회는 JPQL(실제 테이블이 아닌 Member 객체에서 검색함)
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	// JPQL(실제 테이블이 아닌 Member 객체에서 검색함)
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
