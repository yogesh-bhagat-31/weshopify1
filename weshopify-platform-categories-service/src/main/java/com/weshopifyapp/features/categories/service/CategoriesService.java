package com.weshopifyapp.features.categories.service;

import java.util.List;

import com.weshopifyapp.features.categories.bean.CategoriesBean;

public interface CategoriesService {

	CategoriesBean createCategories(CategoriesBean categoriesBean);
	CategoriesBean updateCategories(CategoriesBean categoriesBean);
	CategoriesBean getCategoryById(int catId);
	List<CategoriesBean> findAllCategories();
	List<CategoriesBean> deleteCategory(int catId);
	
	List<CategoriesBean> searchAllCategories(String name);
	
	List<CategoriesBean> findAllChildCategories(int parentCatId);
	

}
 