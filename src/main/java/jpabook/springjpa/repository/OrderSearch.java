/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-28
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.repository;

import jpabook.springjpa.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 28
 * @version 1.0
*/

@Getter
@Setter
public class OrderSearch {

	private String memberName;
	private OrderStatus orderStatus;
}
