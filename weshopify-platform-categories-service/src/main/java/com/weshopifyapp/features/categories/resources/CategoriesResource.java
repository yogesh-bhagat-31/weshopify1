package com.weshopifyapp.features.categories.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyapp.features.categories.bean.CategoriesBean;
import com.weshopifyapp.features.categories.service.CategoriesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CategoriesResource {

	@Autowired
	private CategoriesService categoriesService;

	@PostMapping(value = "/categories")
	@Operation(summary ="saveCategory", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CategoriesBean> saveCategory(@RequestBody @Valid CategoriesBean categoriesBean) {
		log.info("categories data is:\t" + categoriesBean.toString());
		categoriesBean = categoriesService.createCategories(categoriesBean);
		return ResponseEntity.ok(categoriesBean);
	}

	@PutMapping(value = "/categories")
	@Operation(summary ="updateCategory", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CategoriesBean> updateCategory(@RequestBody CategoriesBean categoriesBean) {
		log.info("categories data is:\t" + categoriesBean.toString());
		categoriesBean = categoriesService.updateCategories(categoriesBean);
		return ResponseEntity.ok(categoriesBean);
	}

	@GetMapping(value = "/categories")
	@Operation(summary ="findAllCategory", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<CategoriesBean>> findAllCategory() {
		List<CategoriesBean> catList = categoriesService.findAllCategories();
		return ResponseEntity.ok(catList);
	}

	@GetMapping(value = "/categories/{id}")
	@Operation(summary ="findCategoryById", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CategoriesBean> findCategoryById(@PathVariable("id") int id) {
		CategoriesBean categoriesBean = categoriesService.getCategoryById(id);
		return ResponseEntity.ok(categoriesBean);
	}

	@GetMapping(value = "/categories/childs/{parentId}")
	@Operation(summary ="findChildCategoryByParentId", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<CategoriesBean>> findChildCategoryByParentId(@PathVariable("parentId") int id) {
		List<CategoriesBean> catList = categoriesService.findAllChildCategories(id);
		return ResponseEntity.ok(catList);
	}

	@DeleteMapping(value = "/categories/{id}")
	@Operation(summary ="deleteCategoryById", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<CategoriesBean>> deleteCategoryById(@PathVariable("id") int id) {
		List<CategoriesBean> categoriesList = categoriesService.deleteCategory(id);
		return ResponseEntity.ok(categoriesList);
	}

}
