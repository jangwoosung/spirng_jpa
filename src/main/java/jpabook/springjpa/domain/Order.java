/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name="orders")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue
	@Column(name="order_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne
	@JoinColumn(name="delivery_id")
	private Delivery delivery;

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;
}
