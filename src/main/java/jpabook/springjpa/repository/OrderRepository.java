/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-19
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.springjpa.domain.Order;
import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 19
 * @version 1.0
*/

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	//public List<Order> findAll(OrderSearch orderSearch) {}
}
