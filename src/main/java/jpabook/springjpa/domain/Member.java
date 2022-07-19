/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Member {

	@Id
	@GeneratedValue
	@Column(name="member_id")
	private Long id;

	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member") // order 테이블에있는 member 필드에의해 매핑된걸 나타냄
	private List<Order> orders = new ArrayList<>();

}
