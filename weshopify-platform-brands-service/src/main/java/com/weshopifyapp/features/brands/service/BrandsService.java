package com.weshopifyapp.features.brands.service;

import java.util.List;

import com.weshopifyapp.features.brands.bean.BrandsBean;

public interface BrandsService {

	BrandsBean createBrand(BrandsBean brandsBean);

	BrandsBean updateBrand(BrandsBean brandsBean);

	List<BrandsBean> findAllBrands();

	BrandsBean findBrandById(String id);

	BrandsBean findBrandByName(String name);

	List<BrandsBean> deleteBrand(String id);

	List<BrandsBean> findBrandsOfCategory(int id);
	
	void cleanDb();

}
