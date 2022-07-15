/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-07-15
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 7. 15
 * @version 1.0
*/


@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello");
		return "hello";
	}
}
