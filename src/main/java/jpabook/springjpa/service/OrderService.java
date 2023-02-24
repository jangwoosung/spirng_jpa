/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-19
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.springjpa.domain.Delivery;
import jpabook.springjpa.domain.OldMember;
import jpabook.springjpa.domain.Order;
import jpabook.springjpa.domain.OrderItem;
import jpabook.springjpa.domain.item.Item;
import jpabook.springjpa.repository.ItemRepository;
import jpabook.springjpa.repository.MemberRepository;
import jpabook.springjpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 19
 * @version 1.0
*/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	/**
	 * @param memberId
	 * @param itemId
	 * @param count
	 * @return Long
	 * 주문
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {

		// 엔티티 조회
		OldMember member = memberRepository.findOne(memberId);
		Item item =  itemRepository.findOne(itemId);

		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		//주문 상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// @NoArgsConstructor(access = AccessLevel.PROTECTED)
		// 위 어노테이션을 선언하면 아래 처럼 직접 생성해서 사용못하고, 사용자가 만든 생성 메서드를 이용 해야함
		//OrderItem orderItem1 = new OrderItem();

		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);

		//주문 저장
		// CASCADE 속성 때문에 아래 한줄로 끝남
		// CASCADE를 쓰는 범위는 order만 orderItems, delivery를 참조하는 이런경우만 사용
		orderRepository.save(order);

		return order.getId();
	}

	/**
	 * @param orderId void
	 * 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {

		// 주문 엔티티 조회
		Order order =  orderRepository.findOne(orderId);
		//주문 취소
		// JPA는 Entitiy 에 값만 변경해도 더티체크로 인해 업데이트 쿼리가 날라감
		// Mybatis같은 라이브러리를 사용하면 직접 update sql을 날려줘야함
		order.cancel();
	}

	/**
	 * @param orderSerach
	 * @return List<Order>
	 * 검색
	 */
//	public List<Order> findOrders(OrderSearch orderSerach) {
//		return orderRepository.findOne(orderSerach);
//	}
}
