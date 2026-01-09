package com.weshopifyapp.features.brands.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weshopifyapp.features.brands.model.Brands;

public interface BrandsMongoRepo extends MongoRepository<Brands, String> {

}
