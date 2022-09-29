/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.controller;

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
public class BookForm {

	private Long id;

	private String name;
	private int price;
	private int stockQuantity;

	private String author;
	private String isbn;


}
