package com.example.application.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.application.factory.service.AbstractServiceFactory;
import com.example.application.repository.DescRepository;
import com.example.application.repository.ProductRepository;
import com.example.application.repository.ReviewRepository;
import com.example.application.service.IFactoryService;
import com.example.application.service.IProductService;
import com.example.application.service.impl.DescImpl;
import com.example.application.service.impl.ReviewImpl;
import com.example.application.utility.Keyword;

@Component
public class ProductServiceFactory implements AbstractServiceFactory {

	@Autowired
	DescRepository descRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	IProductService productService;

	@Override
	public IFactoryService getProduct(String modelType) {

		IFactoryService factoryService = null;

		if (Keyword.REVIEW.equalsIgnoreCase(modelType)) {
			factoryService = new ReviewImpl(reviewRepository);
		} else if (Keyword.DESCRIPTION.equalsIgnoreCase(modelType)) {
			factoryService = new DescImpl(descRepository);
		}
		return factoryService;
	}

	@Override
	public IFactoryService getRate(String modelType) {

		IFactoryService factoryService = null;

		if (Keyword.REVIEW.equalsIgnoreCase(modelType)) {
			factoryService = new ReviewImpl(reviewRepository,productService);
		} else if (Keyword.DESCRIPTION.equalsIgnoreCase(modelType)) {
			factoryService = new DescImpl(descRepository,productService);
		}
		return factoryService;
	}

}