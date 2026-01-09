/**
 * 
 */
package com.weshopifyapp.features.brands.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.brands.bean.BrandsBean;
import com.weshopifyapp.features.brands.config.FeignClientConfig;
import com.weshopifyapp.features.brands.model.Brands;
import com.weshopifyapp.features.brands.repo.BrandsMongoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandsServiceImpl implements BrandsService {

	private BrandsMongoRepo brandsRepo;
	private ModelMapper modelMapper;
	private FeignClientConfig feignClientConfig;

	public BrandsServiceImpl(BrandsMongoRepo brandsRepo, ModelMapper modelMapper, FeignClientConfig feignClientConfig) {
		super();
		this.brandsRepo = brandsRepo;
		this.modelMapper = modelMapper;
		this.feignClientConfig = feignClientConfig;
	}

	@Override
	public BrandsBean createBrand(BrandsBean brandsBean) {
		
		Brands brands = modelMapper.map(brandsBean, Brands.class);
		log.info("Brands model created as:\t" + brands.toString());
		/**
		 * call to categories service get the original categories and put it in brands
		 * DB
		 */

		Optional.ofNullable(brands.getCategories()).ifPresentOrElse(catSet -> {

			Set<String> originalCatSet = new HashSet<>();

			catSet.stream().forEach(catId -> {
				log.info("Invoking the category service.....");

				ResponseEntity<String> resp = feignClientConfig.getCategoriesById(Integer.valueOf(catId));

				if (HttpStatus.OK.value() == resp.getStatusCode().value()) {
					String originalCatJson = resp.getBody();
					log.info("CategoryServiceResponse is :\t" + originalCatJson);
					originalCatSet.add(originalCatJson);
				}

				brands.setCategories(originalCatSet);

			});
 
		},

				() -> {

					throw new RuntimeException("categories are not present in category db");

				});

		brandsRepo.save(brands);
		modelMapper.map(brands, brandsBean);
		return brandsBean;
	}

	@Override
	public BrandsBean updateBrand(BrandsBean brandsBean) {
		log.info("Brands Bean updating is:\t" + brandsBean.toString());
		Brands brands = modelMapper.map(brandsBean, Brands.class);
		brandsRepo.save(brands);
		modelMapper.map(brands, brandsBean);
		return brandsBean;
	}

	@Override
	public List<BrandsBean> findAllBrands() {
		List<Brands> brandsList = brandsRepo.findAll();
		List<BrandsBean> brandsBeanList = new ArrayList<>();
		Optional.ofNullable(brandsList).get().stream()
				.forEach((brandModel) -> brandsBeanList.add(modelMapper.map(brandModel, BrandsBean.class)));
		return brandsBeanList;
	}

	@Override
	public BrandsBean findBrandById(String id) {
		Brands brand = brandsRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Brand is not available with the id:\t" + id));
		BrandsBean brandsBean = modelMapper.map(brand, BrandsBean.class);
		return brandsBean;
	}

	@Override
	public BrandsBean findBrandByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandsBean> deleteBrand(String id) {
		brandsRepo.deleteById(id);
		return findAllBrands();
	}

	@Override
	public List<BrandsBean> findBrandsOfCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanDb() {
		brandsRepo.deleteAll();
	}

}
