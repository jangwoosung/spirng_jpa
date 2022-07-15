/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain.item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속관계 전략
@DiscriminatorColumn(name="dtype") // 하나의 테이블에서 컬럼으로 값구분시 사용
@Getter
@Setter
public abstract class Item {

	@Id
	@GeneratedValue
	@Column(name="item_id")
	private Long id;

	private String name;
	private int price;
	private int stockQuantity;
}
