/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-19
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

import jpabook.springjpa.domain.Address;
import jpabook.springjpa.domain.Member;
import jpabook.springjpa.domain.Order;
import jpabook.springjpa.domain.OrderStatus;
import jpabook.springjpa.domain.item.Book;
import jpabook.springjpa.domain.item.Item;
import jpabook.springjpa.exception.NotEnoughStockException;
import jpabook.springjpa.repository.OrderRepository;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 19
 * @version 1.0
*/

class OrderServiceTest {

	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception {

		//given
		Member member = createMember();
		Book book = createBook("시골 JPA", 10000, 10);

		int orderCount = 2;

		// when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// then
		Order getOrder = orderRepository.findOne(orderId);

		assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
		assertThat(1).isEqualTo(getOrder.getOrderItems().size());
		assertThat(10000 * orderCount).isEqualTo(getOrder.getTotalPrice());

	}

	@Test
	public void 주문취소() throws Exception {

		//given
		Member member = createMember();
		Item item = createBook("시골 JPA", 10000, 10);

		int orderCount = 11;

		//when
		//then
		assertThrows(NotEnoughStockException.class, () -> {
			orderService.order(member.getId(), item.getId(), orderCount);
		});
	}

	@Test
	public void 상품주문_재고수량초과() throws Exception {

	}


	/**
	 * @param stockQuantity
	 * @param price
	 * @param name
	 * @return Book
	 */
	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		em.persist(book);
		return book;
	}

	/**
	 * @return Member
	 */
	private Member createMember() {
		Member member = new Member();
		member.setName("");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}

}


