package com.weshopifyapp.features.categories.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weshopifyapp.features.categories.model.Categories;

public interface CategoriesRepo extends JpaRepository<Categories, Integer> {
	
	@Query("from Categories c where c.parentCategory=:parentId")
	List<Categories> findChildCategories(int parentId);

}
