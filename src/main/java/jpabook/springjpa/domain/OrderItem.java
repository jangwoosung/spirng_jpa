/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jpabook.springjpa.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 15
 * @version 1.0
*/

@Entity
@Getter
@Setter
public class OrderItem {

	@Id @GeneratedValue
	@Column(name ="order_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;

	private int orderPrice; // 주문시점 가격
	private int count; // 주문 수량

}
