/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-19
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jpabook.springjpa.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 19
 * @version 1.0
*/

@Entity
@Getter
@Setter
public class Category {

	@Id @GeneratedValue
	@Column(name ="item_id")
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "category_item",  // 중간 테이블(n:m 푸는 테이블) -- 실전에서 거의 안쓰는걸 추천(중간테이블에 컬럼을 추가할수없다)
	                                    // * 해결법 중간 엔티티를 만들고 일대다, 다대일 매핑으로 풀어내서 사용
			joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블의 카테고리 아이디
				inverseJoinColumns = @JoinColumn(name = "item_id") // 중간 테이블의 아이템의 아이디
			)
	private List<Item> items = new ArrayList<>();

	// self로 양방향 엔티티 매핑 시작..
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();
	// self로 양방향 엔티티 매핑 종료..

	// 연관관계 메서드(양방향)
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}

}
