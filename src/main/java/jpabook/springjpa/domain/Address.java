/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 15
 * @version 1.0
*/

@Embeddable
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
}
