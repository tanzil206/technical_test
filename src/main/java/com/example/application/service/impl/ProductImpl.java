package com.example.application.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.model.Product;
import com.example.application.repository.ProductRepository;
import com.example.application.service.IProductService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public long getAllProductNumber() {
		long totalProduct = productRepository.findAllByNum();
		return totalProduct;
	}

	/**
	 * get product by jan.
	 *
	 * 
	 */
	@Override
	public Product getProductByJan(String jan) throws NullPointerException {

		Product product = productRepository.findByJan(jan);

		return product;
	}

}
