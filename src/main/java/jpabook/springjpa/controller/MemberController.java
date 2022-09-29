/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.controller;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jpabook.springjpa.domain.Address;
import jpabook.springjpa.domain.Member;
import jpabook.springjpa.service.MemberService;
import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 29
 * @version 1.0
*/

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}

	@PostMapping("/members/new")
	public String create(@Valid MemberForm form, BindingResult result) {

		if (result.hasErrors()) {
			return "members/createMemberForm";
		}

		Address address = new  Address(form.getCity(), form.getStreet(), form.getZipcode());

		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);

		memberService.join(member);

		return "redirect:/";
	}
}
