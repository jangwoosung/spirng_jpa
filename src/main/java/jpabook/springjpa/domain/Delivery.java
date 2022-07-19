/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name="delivery_id")
	private Long id;

	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING) //기본값 ORDINAL, String을 사용해야 중간에 값이들어와도 문제가없다.
	private DeliveryStatus status;
}
