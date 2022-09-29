/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 29
 * @version 1.0
*/

@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/")
	public String home() {
		log.info("home controller");
		return "home";
	}
}
