package com.mysite.sbb.category;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequestMapping("/category")
@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategorySevice categorySevice;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Category> categoryList = this.categorySevice.getList();
		model.addAttribute("categoryList",categoryList);
		return "category_list";
	}
	
	@GetMapping("/create")
	public String categoryCreate(CategoryForm categoryForm) {
		return "category_form";
	}
	
	@PostMapping("/create")
	public String categoryCreate(@Valid CategoryForm categoryForm,BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return "category_form";
		}
		
		this.categorySevice.create(categoryForm.getName());
		return "redirect:/category/list";
	}
}
