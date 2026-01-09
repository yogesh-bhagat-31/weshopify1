 package com.weshopifyapp.features.categories.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.categories.bean.CategoriesBean;
import com.weshopifyapp.features.categories.exceptions.CategoriesNotFoundException;
import com.weshopifyapp.features.categories.model.Categories;
import com.weshopifyapp.features.categories.repo.CategoriesRepo;

@Service
public class CategoryServiceImpl implements CategoriesService {
   
	private CategoriesRepo categoriesRepo;

	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoriesRepo categoriesRepo, ModelMapper modelMapper) {

		this.categoriesRepo = categoriesRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoriesBean createCategories(CategoriesBean categoriesBean) {

		try {

			Categories categoriesEntity = modelMapper.map(categoriesBean, Categories.class);
			categoriesEntity.setCreatedDate(new Date());
			categoriesEntity.setModifiedDate(new Date());
			if(categoriesEntity.getImage() != null) {
				categoriesRepo.save(categoriesEntity);
				categoriesBean = modelMapper.map(categoriesEntity, CategoriesBean.class);
				return categoriesBean;
			}else {
				throw new RuntimeException("Image is not available");
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public CategoriesBean updateCategories(CategoriesBean categoriesBean) { 
		Categories categoriesEntity = categoriesRepo.findById(categoriesBean.getId()).get();
		modelMapper.map(categoriesBean, categoriesEntity);
		categoriesEntity.setModifiedDate(new Date());
		categoriesRepo.save(categoriesEntity);
		categoriesBean = modelMapper.map(categoriesEntity, CategoriesBean.class);
		return categoriesBean;
	}

	@Override
	public CategoriesBean getCategoryById(int catId) {
		Categories categoriesEntity = categoriesRepo.findById(catId).get();
		CategoriesBean categoriesBean = modelMapper.map(categoriesEntity, CategoriesBean.class);
		return categoriesBean;
	}

	@Override
	public List<CategoriesBean> findAllCategories() {
		List<Categories> catList = categoriesRepo.findAll();
		List<CategoriesBean> catBeanList = new ArrayList<>();
		Optional.ofNullable(catList).ifPresentOrElse(catlist -> {
			catlist.stream().forEach(categoryEntity -> {
				CategoriesBean categoriesBean = modelMapper.map(categoryEntity, CategoriesBean.class);
				catBeanList.add(categoriesBean);
			});
		}, () -> {
			throw new RuntimeException("Categories List is Empty");
		});
		return catBeanList;
	}

	@Override
	public List<CategoriesBean> deleteCategory(int catId) {
		categoriesRepo.deleteById(catId);
		return findAllCategories();
	}

	@Override
	public List<CategoriesBean> searchAllCategories(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriesBean> findAllChildCategories(int parentCatId) {
		List<Categories> catList = categoriesRepo.findChildCategories(parentCatId);
		List<CategoriesBean> catBeanList = new ArrayList<>();
		Optional.ofNullable(catList).ifPresentOrElse(catlist -> {
			catlist.stream().forEach(categoryEntity -> {
				CategoriesBean categoriesBean = modelMapper.map(categoryEntity, CategoriesBean.class);
				catBeanList.add(categoriesBean);
			});
		}, () -> {
			throw new CategoriesNotFoundException("Categories List is Empty" +parentCatId);
		});
		return catBeanList;
	}

}
