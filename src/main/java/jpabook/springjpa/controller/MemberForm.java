/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 29
 * @version 1.0
*/
@Getter
@Setter
public class MemberForm {

	@NotEmpty(message="회원 이름은 필수 입니다.")
	private String name;

	private String city;
	private String street;
	private String zipcode;
}
