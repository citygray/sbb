package com.mysite.sbb.category;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategorySevice {
	
	private final CategoryRepository categoryRepository;

	public void create(String name) {
		
		Category category = new Category();
		category.setName(name);
		this.categoryRepository.save(category);
		
	}

	public List<Category> getList() {
		return this.categoryRepository.findAll();
	}

}
