/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-19
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.springjpa.domain.item.Item;
import jpabook.springjpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 19
 * @version 1.0
*/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itempRepository;

	@Transactional
	public void saveItem(Item item) {
		itempRepository.save(item);
	}

	public List<Item> findItems() {
		return itempRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itempRepository.findOne(itemId);
	}
}
