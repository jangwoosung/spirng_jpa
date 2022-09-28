/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class description
 * 값 타입 객체는 Setter는 절대로 제공하면 안됨, 생성자에서 모든값을 초기호하게 설계
 * 기본 생성자 접근제어자는 public 또는 protected로 설정해야함
 * @author jws
 * @since 2022. 7. 15
 * @version 1.0
*/

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	private String city;
	private String street;
	private String zipcode;
}
